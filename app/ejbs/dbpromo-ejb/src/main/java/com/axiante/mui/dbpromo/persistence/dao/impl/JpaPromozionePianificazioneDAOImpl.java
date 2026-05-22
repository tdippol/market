package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromozionePianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dto.SottoclasseCountDto;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.metamodel.Attribute;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DbPromoJpaDao
@Slf4j
public class JpaPromozionePianificazioneDAOImpl extends JpaDbPromoDAO<PromozionePianificazioneEntity>
        implements PromozionePianificazioneDAO {

    private static final long serialVersionUID = -9125223153336702748L;

    @Override
    @Deprecated
    public List<PromozionePianificazioneEntity> findAllMasterRowsByPromozioneTestata(
            PromozioneTestataEntity promozioneTestataEntity) {
        return getEm()
                .createNamedQuery("PromozionePianificazioneEntity.findAllByPromozioneTestataEntity",
                        PromozionePianificazioneEntity.class)
                .setParameter("promozioneTestataEntity", promozioneTestataEntity).getResultList();
    }

    @Override
    public List<PromozionePianificazioneEntity> findAllDetailsByIdPromozione(Long idPromozione) {
        return getEm().createNamedQuery("PromozionePianificazioneEntity.findAllDetailsByIdPromozione", PromozionePianificazioneEntity.class)
                .setParameter("idPromozione", idPromozione)
                .getResultList();
    }

    @Override
    public List<PromozionePianificazioneEntity> findAllParentRowsByPromozioneTestata(
            PromozioneTestataEntity promozioneTestataEntity) {
        return getEm().createNamedQuery("PromozionePianificazioneEntity.findAllParentRowsByPromozioneTestata", PromozionePianificazioneEntity.class)
                .setParameter("idPromozione", promozioneTestataEntity.getId())
                .getResultList();
    }

    @Override
    public Map<String, Field> getMappedFields() {
        final Map<String, Field> map = new HashMap<>();
        // Filter all fields on entity, excluding complex type (e.g. relations) and id
        getEm().getMetamodel().entity(PromozionePianificazioneEntity.class).getAttributes().stream()
                .filter(a -> Attribute.PersistentAttributeType.BASIC.equals(a.getPersistentAttributeType()))
                .filter(a -> !"ID".equalsIgnoreCase(a.getName()))
                .forEach(a -> {
                    try {
                        final Field field = PromozionePianificazioneEntity.class.getDeclaredField(a.getName());
                        map.put(getDeclaringColumn(field), field);
                    } catch (Exception ex) {
                        log.warn("Error getting field with name " + a.getName(), ex);
                    }
                });
        return map;
    }

    private String getDeclaringColumn(@NonNull final Field field) {
        final Column col = field.getAnnotation(Column.class);
        return col != null && !col.name().isEmpty()
                ? col.name()
                : camelToSnake(field.getName());
    }

    private String camelToSnake(@NonNull final String s) {
        return s.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toUpperCase();
    }

    /**
     * Ritorna tutte le righe di pianificazione di tipo SET per la testata T
     */
    @Override
    public List<PromozionePianificazioneEntity> findAllSetByPromozione(PromozioneTestataEntity testata) {
        return getEm().createNamedQuery("PromozionePianificazioneEntity.findAllSetByPromozione", PromozionePianificazioneEntity.class)
                .setParameter("testata", testata)
                .getResultList();
    }

    @Override
    public PromozionePianificazioneEntity findFirstChildByParent(PromozionePianificazioneEntity pianificazione) {
        return getEm().createNamedQuery("PromozionePianificazioneEntity.findChildByParent", PromozionePianificazioneEntity.class)
                .setParameter("pianificazione", pianificazione).getResultList()
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<PromozionePianificazioneEntity> findAllByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("ids cannot be null or empty");
        }
        return getEm().createNamedQuery("PromozionePianificazioneEntity.findAllByIds", PromozionePianificazioneEntity.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public List<PromozionePianificazioneEntity> findAllBuoni() {
        return getEm().createNamedQuery("PromozionePianificazioneEntity.findAllBuoni", PromozionePianificazioneEntity.class)
                .setParameter("dataFine", new Date())
                .getResultList();
    }

    @Override
    public List<PromozionePianificazioneEntity> findAllBuoniNotUsed(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("ids cannot be null or empty");
        }
        return getEm().createNamedQuery("PromozionePianificazioneEntity.findAllBuoniNotUsed", PromozionePianificazioneEntity.class)
                .setParameter("ids", ids)
                .setParameter("dataFine", new Date())
                .getResultList();
    }

    @Override
    public List<PromozionePianificazioneEntity> findOverlappingByAnnoCanaleMeccanica(@NonNull PromozionePianificazioneEntity pianificazione,
                                                                                     @NonNull String anno,
                                                                                     @NonNull CanalePromozioneEntity canale,
                                                                                     @NonNull MeccanicheEntity meccanica) {
        String sql = "SELECT p FROM PromozionePianificazioneEntity p "
                + "INNER JOIN FETCH p.promozioneTestataEntity t "
                + "INNER JOIN FETCH t.promozioneStatoEntities st "
                + "INNER JOIN FETCH st.statoPromozioneEntity tt ";
        // promo non cancellate
        final StringBuilder whereClause = new StringBuilder(
                "WHERE tt.codiceStato <> :code AND st.dataFineStato IS NULL ");
        // escludo pianificazione di riferimento
        whereClause.append("AND p.id <> :idPianificazione ")
                // stesso anno
                .append("AND t.anno = :anno ")
                // stesso canale
                .append("AND t.muiCanalePromozione = :canale ")
                // stessa meccanica
                .append("AND p.meccanicaEntity = :meccanica ").append("AND (")
                // inizia durante la pianificazione di riferimento
                .append("(p.dataInizio BETWEEN :dataInizio AND :dataFine) OR ")
                // termina durante la pianificazione di riferimento
                .append("(p.dataFine BETWEEN :dataInizio AND :dataFine) OR ")
                // inizia prima e termina dopo la pianificazione di riferimento
                .append("(p.dataInizio <= :dataInizio and p.dataFine >= :dataFine) OR ")
                // inizia dopo e termina prima della pianificazione di riferimento
                .append("(p.dataInizio >= :dataInizio and p.dataFine <= :dataFine) ").append(")");
        final String queryString = sql + whereClause.toString();
        Date dataInizio = pianificazione.getDataInizio() == null
                ? pianificazione.getPromozioneTestataEntity().getDataInizio()
                : pianificazione.getDataInizio();
        Date dataFine = pianificazione.getDataFine() == null
                ? pianificazione.getPromozioneTestataEntity().getDataFine()
                : pianificazione.getDataFine();
        return getEm().createQuery(queryString, PromozionePianificazioneEntity.class)
                .setParameter("code", PromoStatusEnum.CANCELLATA_00.getKey())
                .setParameter("idPianificazione", pianificazione.getId() != null ? pianificazione.getId() : -1)
                .setParameter("anno", anno)
                .setParameter("canale", canale)
                .setParameter("meccanica", meccanica)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();
    }

    @Override
    public Long countArticoli(PromozioneTestataEntity testata) {
        return
                getEm().createNamedQuery("PromozionePianificazioneEntity.countArticoliByPromozione", Long.class)
						.setParameter("idPromozione", testata.getId())
						.getSingleResult();
    }

    @Override
    public List<Integer> getUsedProgressiveDiscountCodesBuoniPotenziamento(Integer buonoScontoRadice, Date dataInizio, Date dataFine) {
        return getEm().createNamedQuery("PromozionePianificazioneEntity.getUsedBuonoScontoProgressivo", Integer.class)
                .setParameter("buonoScontoRadice", buonoScontoRadice)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();
    }

    @Override
    public List<SottoclasseCountDto> countSottoclassiUsedInPromoInProgress() {
        return getEm().createNamedQuery("PromozionePianificazioneEntity.countSottoclassiUsedInPromoByStato", SottoclasseCountDto.class)
                .setParameter("codiceStato", PromoStatusEnum.PROMOZIONE_IN_ESECUZIONE.getKey())
                .getResultList();
    }
}
