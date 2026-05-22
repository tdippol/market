package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceContext;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Dependent
@SqlResultSetMapping(
        name = "LookupValueMapping",
        classes = @ConstructorResult(
                targetClass = LookupUtils.LookupValue.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "value", type = Object.class)
                }
        )
)
public class LookupUtils {
  /**
   * Checks if there are lookups configured for the promotion.
   *
   * @param configurations
   */
  @PersistenceContext(unitName = "dbPromoPU")
  private EntityManager em;

  private Map<Key, Map<Long, LookupValue>> values = new HashMap<>();
  private Map<CfgPianificazioneEntity, Key> entityToKey;

  public void initializeLookups(
      PromozioneTestataEntity testata, List<CfgPianificazioneEntity> configurations) {
    entityToKey =
        configurations.stream()
            .filter(cfg -> cfg.getLookupAttribute() != null && !cfg.getLookupAttribute().isEmpty())
            .filter(cfg -> cfg.getMuiCfgPianificazioneCampi() != null)
            .collect(
                Collectors.toMap(
                    cfg -> cfg,
                    cfg ->
                        new Key(
                            cfg.getMuiCfgPianificazioneCampi().getEntityLookup(),
                            cfg.getLookupAttribute(),
                            cfg.getMuiCfgPianificazioneCampi().getEntityAttribute(),
                            cfg.getValue(),
                            cfg.getValueType())));
    // adesso devo generare le query in join per ogni lookup
    // il valore della where viene SEMPRE da PromozionePianificazioneEntity
    // mi devo anche segnare da qualche parte la lista degli attributi per poter prendere il valore
    // in base al nome

  }

  public Object lookupValue(
      CfgPianificazioneEntity cfg, PromozionePianificazioneEntity pianificazione) {
    Key key = entityToKey.get(cfg);
    if (key == null) {
      log.error(
          String.format(
                  "The lookup key associated to the configuration with id %d is null", cfg.getId()));
      return null;
    }
    Map<Long, LookupValue> map = values.get(key);
    if (map == null) {
      List<LookupValue> list = fetchData(key, pianificazione.getPromozioneTestataEntity().getId());
      map = list.stream().collect(Collectors.toMap(v -> v.id, v -> v));
      values.put(key, map);
    }
    LookupValue value =  map.get(pianificazione.getId());
    if ( value != null ) return value.getValue();
    return null;
  }

    @SuppressWarnings("unchecked")
    private List<LookupValue> fetchData(Key key, Long idPromozioneTestata) {
        if (em != null) {
            List<?> raw = em.createNativeQuery(buildNativeQuery(key, idPromozioneTestata), "LookupValueMapping").getResultList();
            // If mapping fails, fallback to manual mapping
            if (!raw.isEmpty() && !(raw.get(0) instanceof LookupValue)) {
                return ((List<Object[]>) raw).stream()
                        .map(arr -> new LookupValue(((Number) arr[0]).longValue(), arr[1]))
                        .collect(Collectors.toList());
            }
            return (List<LookupValue>) raw;
        }
        return Collections.emptyList();
    }


  private Class<?> getEntity(String entityName) {
    for (EntityType<?> entityType : em.getMetamodel().getEntities()) {
      Class<?> clazz = entityType.getJavaType();
      // Match by entity name or class simple name
      if (entityType.getName().equals(entityName) || clazz.getSimpleName().equals(entityName)) {
        return clazz;
      }
    }
    throw new IllegalArgumentException("Entity not found: " + entityName);
  }

  private Table getTable(Class<?> clazz) {
    // Match by entity name or class simple name
    return clazz.getAnnotation(Table.class);
  }

  private String getTableName(@NonNull Table table) {
    if (!table.schema().isEmpty()) {
      return table.schema() + "." + table.name();
    }
    return table.name();
  }

  public String getColumnName(Class<?> entityClass, String attributeName) {
    try {
      Field field = entityClass.getDeclaredField(attributeName);
      Column column = field.getAnnotation(Column.class);
      if (column != null && !column.name().isEmpty()) {
        return column.name();
      }
      return attributeName; // fallback to field name if @Column is missing
    } catch (NoSuchFieldException e) {
      throw new IllegalArgumentException("Field not found: " + attributeName, e);
    }
  }

  public String buildNativeQuery(Key key, Long idPromozioneTestata) {
    // Start from MUI_PROMOZIONE_PIANIFICAZIONE p
    String mainTable = "MUI_PROMOZIONE_PIANIFICAZIONE";
    String mainAlias = "p";
    StringBuilder fromClause = new StringBuilder(mainTable + " " + mainAlias);

    // Join the lookup entity table as 'a'
    Class<?> entityClass = getEntity(key.getEntity());
    Table entityTable = getTable(entityClass);
    String entityTableName = getTableName(entityTable);
    String entityAlias = "a";

    // Join condition: a.<whereField> = CAST(p.<joinField> as <cast>)
    String joinCondition =
        String.format(
            "%s.%s = CAST(%s.%s as %s)",
            entityAlias,
            getColumnName(entityClass, key.getWhereField()),
            mainAlias,
            key.getJoinField(),
            key.getCast());
    StringBuilder joinClause = new StringBuilder();
    joinClause
        .append(" JOIN ")
        .append(entityTableName)
        .append(" ")
        .append(entityAlias)
        .append(" ON ")
        .append(joinCondition);

    // Handle dotted returnField (e.g., "foo.bar.baz")
    String[] parts = key.getReturnField().split("\\.");
    Class<?> currentClass = entityClass;
    String currentAlias = entityAlias;
    int aliasIdx = 1;

    // For each part except the last, join to the next entity
    for (int i = 0; i < parts.length - 1; i++) {
      String part = parts[i];
      Field field;
      try {
        field = currentClass.getDeclaredField(part);
      } catch (NoSuchFieldException e) {
        throw new IllegalArgumentException(
            "Field not found: " + part + " in " + currentClass.getName(), e);
      }
      Class<?> joinClass = field.getType();
      Table joinTable = getTable(joinClass);
      String joinTableName = getTableName(joinTable);
      String joinAlias = "t" + aliasIdx++;
      JoinColumn joinColumn = field.getAnnotation(javax.persistence.JoinColumn.class);
      if (joinColumn == null) {
        throw new IllegalArgumentException("No @JoinColumn on field: " + part);
      }
      String joinColumnName = joinColumn.name();
      String pkName = getIdColumnName(joinClass);
      joinClause
          .append(" JOIN ")
          .append(joinTableName)
          .append(" ")
          .append(joinAlias)
          .append(" ON ")
          .append(currentAlias)
          .append(".")
          .append(joinColumnName)
          .append(" = ")
          .append(joinAlias)
          .append(".")
          .append(pkName);
      currentClass = joinClass;
      currentAlias = joinAlias;
    }

    // Final column
    String lastField = parts[parts.length - 1];
    String columnName = getColumnName(currentClass, lastField);

    // SELECT and WHERE
    String select = String.format("SELECT %s.id, %s.%s", mainAlias, currentAlias, columnName);
    StringBuilder where = new StringBuilder();
    where.append(String.format(" WHERE %s.ID_PROMOZIONE = %d ", mainAlias, idPromozioneTestata));
    where.append(String.format(" AND %s.TIPO_ELEMENTO = 'ARTICOLO' ", mainAlias));

    // Build final SQL
    return select + " FROM " + fromClause + joinClause + where;
  }

  // Helper to get PK column name
  private String getIdColumnName(Class<?> clazz) {
    for (Field f : clazz.getDeclaredFields()) {
      if (f.isAnnotationPresent(Id.class)) {
        Column col = f.getAnnotation(Column.class);
        return (col != null && !col.name().isEmpty()) ? col.name() : f.getName();
      }
    }
    throw new IllegalArgumentException("No @Id field in " + clazz.getName());
  }

  public void releaseResources(){
      if ( values != null ) values.clear();
      if ( entityToKey != null ) entityToKey.clear();

  }
  @AllArgsConstructor
  @EqualsAndHashCode
  class Key {
    @Getter String entity; // ENTITY_LOOKUP
    @Getter String whereField; // LOOKUP_ATTRIBUTE
    @Getter String returnField; // ENTITY_ATTRIBUTE
    @Getter String joinField; // VALUE
    @Getter String cast; // VALUE_TYPE
  }

  @Data
  public static class LookupValue {
    Long id;
    Object value;

    public LookupValue(Long id, Object value) {
        this.id = id;
        this.value = value;
    }
  }


  /*


  |          ||

  select MuiPromozionePianificazione.id, MuiCfgPianificazioneCampi.entityAttribute
  from MuiCfgPianificazioneCampi.entityLookup A join MuiPromozionePianificazione P ON A.$(MuiCfgPianificazioneCampi.lookUpAttribute) = CAST(P.$(MuiCfgPianificazione.value) as $(MuiCfgPianificazione.valueType))
  where MuiPromozionePianificazione.MuiPromozioneTestata.id = idTEstata
     */
}
