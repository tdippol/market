package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.StringUtils;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.AuditLogInterface;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public abstract class JpaDbPromoDAO<DbPromoEntity> implements DbPromoDAO<DbPromoEntity> {
	private static final long serialVersionUID = 4235650364060291112L;

	@SuppressWarnings("rawtypes")
	protected Class entityClass;

	private StringUtils stringUtils = new StringUtils();

	@Inject
	@DbPromo
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Getter(value = AccessLevel.PROTECTED)
	private AuditLogFiller filler = new AuditLogFiller();

	@SuppressWarnings("rawtypes")
	public JpaDbPromoDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
	}

	public EntityTransaction getTransaction() {
		return getEm().getTransaction();
	}

	@Override
	public DbPromoEntity persist(@NonNull DbPromoEntity entity) {
		entity = em.merge(entity);
		em.persist(entity);
		em.flush();
		return entity;
	}

	@Override
	public void persist(@NonNull final List<DbPromoEntity> entities, final int batchSize) {
		int pendingFlush = 0;
		DbPromoEntity e = null;
		for (int i = 0; i < entities.size(); ++i) {
			if ((batchSize > 0) && ((i % batchSize) == 0)) {
				getEm().flush();
				getEm().clear();
				pendingFlush = 0;
			}
			if (entities.get(i) != null) {
				e = entities.get(i);
				e = getEm().merge(e);
				getEm().persist(e);
//				entities.set(i, getEm().merge(entities.get(i)));
//				getEm().persist(entities.get(i));
				entities.set(i,e);
				++pendingFlush;
			}
		}
		if (pendingFlush > 0) {
			getEm().flush();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public DbPromoEntity findById(@NonNull Serializable id) {
		return (DbPromoEntity) em.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DbPromoEntity> findAll() {
		return em.createQuery("SELECT m FROM " + entityClass.getSimpleName() + " m").getResultList();
	}

	@Override
	public void persistWithAuditLog(List<AuditLogInterface> entities, int batchSize, String user) {
		int pendingFlush = 0;
		for (int i = 0; i < entities.size(); ++i) {
			if ((batchSize > 0) && ((i % batchSize) == 0)) {
				getEm().flush();
				getEm().clear();
				pendingFlush = 0;
			}
			if (entities.get(i) != null) {
				getFiller();
				entities.set(i, getEm().merge(AuditLogFiller.fillAuditLogFields(entities.get(i), user)));
				getEm().persist(entities.get(i));
				++pendingFlush;
			}
		}
		if (pendingFlush > 0) {
			getEm().flush();
		}
	}

	@Override
	public AuditLogInterface persistWithAuditLog(AuditLogInterface entity, String user) {
		getFiller();
		entity = em.merge(AuditLogFiller.fillAuditLogFields(entity, user));
		em.persist(entity);
		em.flush();
		return entity;
	}

	@Override
	public List<DbPromoEntity> persistInTransaction(@NonNull final List<DbPromoEntity> entities) {
		persist(entities, entities.size());
		return entities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DbPromoEntity> findEmptyUuid() {
		if (UUIDEnabledEntity.class.isAssignableFrom(entityClass)) {
			return getEm().createQuery("SELECT m FROM " + entityClass.getSimpleName() + " m where m.uuid is null")
					.getResultList();
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Object findValueByAttribute(@NonNull String entityName,
			@NonNull String entityAttribute,
			@NonNull String lookupAttribute,
			@NonNull Object lookupValue) {
		try {
			final String sql = "SELECT e." + entityAttribute + " FROM " + entityName + " e "
					+ "WHERE e." + lookupAttribute + " = :value";
			return em.createQuery(sql).setParameter("value", lookupValue).getSingleResult();
		} catch (Exception ex) {
			log.error(String.format("Error retrieving value for attribute '%s' from entity '%s'",
					entityAttribute, entityName), ex);
			return null;
		}
	}

	/**
	 * risolve il nome della variabile a partire dal nome della colonna
	 *
	 * @param columnName : nome della colonna
	 * @return nome della variabile all'interno dell'entity, null se non presente
	 */
	String resolveMember(@NonNull final String columnName) {
		return Arrays.stream(entityClass.getFields()).filter(field -> getColName(field).equals(columnName))
				.map(Field::getName).findFirst().orElse(null);
	}

	/**
	 * Ritorna il nome di della colonna database con cui il campo e' annotato
	 *
	 * <pre>
	 *  &#64;Column(name=<a name>)
	 * </pre>
	 *
	 * . Se l'attributo non e' annotato con
	 *
	 * <pre>
	 *  &#64;Column(name=<a name>)
	 * </pre>
	 *
	 * o se manca il parametro
	 *
	 * <pre>
	 * name
	 *
	 * <pre>
	 * ritorna il nome dell'attributo snake case maiuscolo.
	 *
	 * @param field il membro della classe da ispezionare
	 * @return String, nome della colonna database corrispondente all'entity
	 *         attribute
	 */
	private String getColName(@NonNull final Field field) {
		String colName = null;
		if (field.getAnnotation(Column.class) != null) {
			// annotato con @Column
			colName = field.getAnnotation(Column.class).name();
		}
		if (colName == null) {
			colName = stringUtils.camelToSnake(field.getName());
		}
		return colName;
	}
	@Override
	public void remove(@NonNull DbPromoEntity entity) {
		entity = em.merge(entity);
		em.remove(entity);
		em.flush();
	}
	public void flush() {
		getEm().flush();
	}

}
