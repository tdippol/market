package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.enumeration.DBPromoUserFilterEnum;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.Connection;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Slf4j
@DbPromoJpaDao
public class JpaPromozioneTestataDaoImpl extends JpaDbPromoDAO<PromozioneTestataEntity>
        implements PromozioneTestataDAO {
    private static final long serialVersionUID = -8666965094064313429L;

    @Override
    public List<PromozioneTestataEntity> findAllSecured(List<CanalePromozioneEntity> canali) {
        if ((canali != null) && canali.isEmpty()) {
            return Collections.emptyList();
        }

        final StringBuilder sql = new StringBuilder("SELECT p FROM PromozioneTestataEntity p ");
        if (canali != null) {
            sql.append("INNER JOIN FETCH p.muiCanalePromozione c ")
                    .append(
                            String.format("WHERE c IS NOT NULL AND c.id IN (%s)", channelsIdsAsString(canali)));
        }

        return getEm().createQuery(sql.toString(), PromozioneTestataEntity.class).getResultList();
    }

    @Override
    public List<PromozioneTestataEntity> findAllNotCancelled(List<CanalePromozioneEntity> canali) {
        return findAllNotCancelled(null, canali);
    }

    @Override
    public List<PromozioneTestataEntity> findAllNotCancelled(Map<String, String> userFilters,
                                                             List<CanalePromozioneEntity> canali) {
        return findAllNotCancelledWithChannelsAndPlannedMecc(userFilters, canali, null);
        }

    @Override
    public List<PromozioneTestataEntity> findAllNotCancelledWithPlannedMecc(List<CanalePromozioneEntity> canali,
                                                                            List<Long> idMeccaniche) {
        return findAllNotCancelledWithChannelsAndPlannedMecc(null, canali, idMeccaniche);
        }

    @Override
    public List<PromozioneTestataEntity> findAllNotCancelledWithPlannedMecc(Map<String, String> userFilters,
                                                                            List<CanalePromozioneEntity> canali,
                                                                            List<Long> idMeccaniche) {
        return findAllNotCancelledWithChannelsAndPlannedMecc(userFilters, canali, idMeccaniche);
    }

    @Override
    public List<PromozioneTestataEntity> findOverlappingPromo(PromozioneTestataEntity promozioneTestataEntity,
                                                              List<CanalePromozioneEntity> canali) {
        if ((canali != null) && canali.isEmpty()) {
            return Collections.emptyList();
        }
        String sql = "SELECT p " + "FROM PromozioneTestataEntity p " + "INNER JOIN FETCH p.promozioneStatoEntities st "
                + "INNER JOIN FETCH st.statoPromozioneEntity tt ";
        // promo non cancellate
        final StringBuilder whereClause = new StringBuilder(
                "WHERE tt.codiceStato <> :code AND st.dataFineStato IS NULL ");
        // escludo promo di riferimento
        whereClause.append("AND p.id <> :idTestata ").append("AND (")
                // inizia durante la promo di riferimento
                .append("(p.dataInizio BETWEEN :dataInizio AND :dataFine) OR ")
                // termina durane la promo di riferimento
                .append("(p.dataFine BETWEEN :dataInizio AND :dataFine) OR ")
                // inizia prima e termina dopo la promo di riferimento
                .append("(p.dataInizio <= :dataInizio and p.dataFine >= :dataFine) OR ")
                // inizia dopo e termina prima della promo di riferimento
                .append("(p.dataInizio >= :dataInizio and p.dataFine <= :dataFine) ").append(")");

        if (canali != null) {
            sql = sql + "INNER JOIN FETCH p.muiCanalePromozione c ";
            whereClause.append(String.format(" AND c IS NOT NULL AND c.id IN (%s) ", channelsIdsAsString(canali)));
        }

        final String queryString = sql + whereClause.toString();
        return getEm().createQuery(queryString, PromozioneTestataEntity.class)
                .setParameter("code", PromoStatusEnum.CANCELLATA_00.getKey())
                .setParameter("idTestata", promozioneTestataEntity.getId())
                .setParameter("dataInizio", promozioneTestataEntity.getDataInizio())
                .setParameter("dataFine", promozioneTestataEntity.getDataFine()).getResultList();
    }

    @Override
    public List<PromozioneTestataEntity> findAllByAnnoAndCanale(String anno, CanalePromozioneEntity canale) {
        return getEm().createNamedQuery("PromozioneTestataEntity.findAllByAnnoAndCanale", PromozioneTestataEntity.class)
                .setParameter("anno", anno)
                .setParameter("canale", canale)
                .getResultList();
    }

    @Override
    public List<PromozioneTestataEntity> findOverlappingByCodiciMeccanica(@NonNull PromozioneTestataEntity promozione,
                                                                          List<String> codiciMeccanica) {
        if (codiciMeccanica == null || codiciMeccanica.isEmpty()) {
            throw new IllegalArgumentException("'codiciMeccanica' cannot be null or empty");
        }
        return getEm().createNamedQuery("PromozioneTestataEntity.findOverlappingByCodiciMeccanica", PromozioneTestataEntity.class)
                .setParameter("code", PromoStatusEnum.CANCELLATA_00.getKey())
                .setParameter("codiciMeccanica", codiciMeccanica)
                .setParameter("dataInizio", promozione.getDataInizio())
                .setParameter("dataFine", promozione.getDataFine())
                .getResultList();
    }

    @Override
    public List<PromozioneTestataEntity> findAllByAnnoAndMeccanica(PromozioneTestataEntity promozione,
                                                                   List<String> codiciMeccanica) {
        if (codiciMeccanica == null || codiciMeccanica.isEmpty()) {
            throw new IllegalArgumentException("'codiciMeccanica' cannot be null or empty");
        }

        return getEm().createNamedQuery("PromozioneTestataEntity.findByAnnoAndCodiciMeccanica", PromozioneTestataEntity.class)
                .setParameter("code", PromoStatusEnum.CANCELLATA_00.getKey())
                .setParameter("anno", promozione.getAnno())
                .setParameter("codiciMeccanica", codiciMeccanica)
                .getResultList();
    }

    @Override
    public List<PromozioneTestataEntity> findOverlappingByAnnoAndCodiceMeccanicaWithOffset(@NonNull PromozioneTestataEntity promozione,
                                                                                           List<String> codiciMeccanica,
                                                                                           Integer overlapOffsetStart,
                                                                                           Integer overlapOffsetEnd) {
        if (codiciMeccanica == null || codiciMeccanica.isEmpty()) {
            throw new IllegalArgumentException("'codiciMeccanica' cannot be null or empty");
        }
        Date dataInizio = promozione.getDataInizio();
        Date dataFine = promozione.getDataFine();
        if (overlapOffsetStart != null && overlapOffsetStart != 1) {
            dataInizio = addDays(dataInizio, -overlapOffsetStart);
        }
        if (overlapOffsetEnd != null && overlapOffsetEnd != 1) {
            dataFine = addDays(dataFine, overlapOffsetEnd);
        }
        return getEm().createNamedQuery("PromozioneTestataEntity.findOverlappingByAnnoAndCodiciMeccanica", PromozioneTestataEntity.class)
                .setParameter("code", PromoStatusEnum.CANCELLATA_00.getKey())
                .setParameter("anno", promozione.getAnno())
                .setParameter("codiciMeccanica", codiciMeccanica)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();
    }

    @Override
    public PromozioneTestataEntity findByPromoCode(final String codicePromozione) {
        try {
            return getEm().createNamedQuery("PromozioneTestataEntity.findByPromoCode", PromozioneTestataEntity.class)
                    .setParameter("codicePromozione", codicePromozione).getSingleResult();
        } catch (final NoResultException ex) {
            log.info("Promozione code [" + codicePromozione + "] not found", ex);
            return null;
        }
    }

    @Override
    public boolean runFunctionAccodamentoPubblicazioni(Long idPromozione, String username, Long idNuovoStatoPromozione)
            throws Exception {
        final StoredProcedureQuery query = getEm().createStoredProcedureQuery(Constants.SP_PUBBLICA_TRANSIZIONE_STATO)
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .setParameter(1, idPromozione)
                .setParameter(2, idNuovoStatoPromozione)
                .setParameter(3, username);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean runFunctionCheckStatusTransiction(Long idPromozione, String username, Long idNuovoStatoPromozione)
            throws Exception {
        final StoredProcedureQuery query = getEm().createStoredProcedureQuery(Constants.SP_CONTROLLO_TRANSIZIONE_STATO)
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .setParameter(1, idPromozione)
                .setParameter(2, idNuovoStatoPromozione)
                .setParameter(3, username);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean runFunctionExportPianificazioni(Long idPromozione, String idCompratori, String username, Integer timeout)
            throws Exception {

        getEm().createStoredProcedureQuery(Constants.SP_EXPORT_PIANIFICAZIONE)
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .setParameter(1, idPromozione)
                .setParameter(2, idCompratori)
                .setParameter(3, username)
                .setHint("javax.persistence.query.timeout", timeout)
                .setHint("eclipselink.jdbc.timeout.unit", java.util.concurrent.TimeUnit.SECONDS)
                .executeUpdate();
        return true;
    }

    @Override
    public boolean runFunctionCalcolaSovrapposizioni(Long idPromozione, String username, Integer timeout) throws Exception {
        getEm().createStoredProcedureQuery(Constants.SP_CALCOLA_SOVRAPPOSIZIONI)
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, idPromozione)
                .setParameter(2, username)
                .setHint("javax.persistence.query.timeout", timeout)
                .setHint("eclipselink.jdbc.timeout.unit", java.util.concurrent.TimeUnit.SECONDS)
                .executeUpdate();
        return true;
    }

    private List<PromozioneTestataEntity> findAllNotCancelledWithChannelsAndPlannedMecc(Map<String, String> userFilters,
                                                                                        List<CanalePromozioneEntity> canali,
                                                                                        List<Long> idMeccaniche) {
        if (canali != null && canali.isEmpty()) {
            return Collections.emptyList();
        }
        String sql = String.format("SELECT %s p FROM PromozioneTestataEntity p INNER JOIN FETCH p.promozioneStatoEntities st INNER JOIN FETCH st.statoPromozioneEntity tt ",
                idMeccaniche != null && !idMeccaniche.isEmpty() ? "DISTINCT" : "");
        final StringBuilder whereClause = new StringBuilder("WHERE tt.codiceStato <> :code AND st.dataFineStato IS NULL ");
        final AtomicBoolean dataInizio = new AtomicBoolean(Boolean.FALSE);
        final AtomicBoolean dataFine = new AtomicBoolean(Boolean.FALSE);
        // trucco sporchissimo
        final List<Date> dataInizioValue = new ArrayList<>();
        final List<Date> dataFineValue = new ArrayList<>();
        DateTimeUtils dtUtils = new DateTimeUtils();
        if (userFilters != null) {
            userFilters.forEach((filterKey, filterValue) -> {
                if (filterValue.contains(",")) {
                    final StringJoiner joiner = new StringJoiner(",");
                    Arrays.stream(filterValue.split(","))
                            .forEach(f -> joiner.add(new StringBuilder().append("'").append(f).append("'")));
                    filterValue = joiner.toString();
                } else {
                    filterValue = "'" + filterValue + "'";
                }
                try {
                    switch (DBPromoUserFilterEnum.valueOf(filterKey.toUpperCase())) {
                        case ANNO:
                            whereClause.append(String.format("AND p.anno IN (%s) ", filterValue));
                            break;
                        case CANALE:
                            whereClause
                                    .append(String.format("AND p.muiCanalePromozione.descrizione IN (%s) ", filterValue));
                            break;
                        case GRUPPO:
                            whereClause.append(String.format(
                                    "AND p.muiCanalePromozione.gruppoPromozioneEntity.descrizione IN (%s) ", filterValue));
                            break;
                        case PROMOZIONE:
                            whereClause.append(String.format("AND p.descrizioneEstesa IN (%s) ", filterValue));
                            break;
                        case SEMESTRE:
                            whereClause.append(String.format("AND p.semestre IN (%s) ", filterValue));
                            break;
                        case UTENTE:
                            whereClause.append(String.format("AND p.codiceUtenteInserimento IN (%s) ", filterValue));
                            break;
                        case STATO:
                            final StringJoiner joiner = new StringJoiner(",");
                            Arrays.stream(filterValue.split(","))
                                    .forEach(f -> joiner.add(new StringBuilder().append(f.replaceAll("[^0-9]", ""))));
                            filterValue = joiner.toString();
                            whereClause.append(String.format("AND tt.codiceStato IN (%s) ", filterValue));
                            break;
                        case DATA_INIZIO:
                            whereClause.append("AND p.dataInizio >= :dataInizio ");
                            dataInizio.set(Boolean.TRUE);
                            dataInizioValue.add(dtUtils.excelToDate(filterValue.replaceAll("\'", "")));
                            break;
                        case DATA_FINE:
                            whereClause.append("AND p.dataFine <= :dataFine ");
                            dataFineValue.add(dtUtils.excelToDate(filterValue.replaceAll("\'", "")));
                            dataFine.set(Boolean.TRUE);
                            break;
                    }
                } catch (final IllegalArgumentException ex) {
                    log.warn(String.format("Il filtro %s non è previsto", filterKey), ex);
                }
            });
        }
        if (canali != null) {
            sql = sql + "INNER JOIN FETCH p.muiCanalePromozione c ";
            whereClause.append(String.format(" AND c IS NOT NULL AND c.id IN (%s) ", channelsIdsAsString(canali)));
        }
        if (idMeccaniche != null && !idMeccaniche.isEmpty()) {
            sql = sql + "INNER JOIN FETCH p.promozionePianificazioneEntities pp INNER JOIN FETCH pp.meccanicaEntity m ";
            whereClause.append(String.format(" AND m.id IN (%s) ", idMeccaniche.stream().map(Object::toString)
                    .collect(Collectors.joining(","))));
        }
        final String queryString = sql + whereClause.toString();
        if (dataInizio.get() && dataFine.get()) {
            return getEm().createQuery(queryString, PromozioneTestataEntity.class)
                    .setParameter("dataInizio", dataInizioValue.get(0))
                    .setParameter("dataFine", dataFineValue.get(0))
                    .setParameter("code", PromoStatusEnum.CANCELLATA_00.getKey()).getResultList();
        } else {
            if (dataInizio.get()) {
                return getEm().createQuery(queryString, PromozioneTestataEntity.class)
                        .setParameter("dataInizio", dataInizioValue.get(0))
                        .setParameter("code", PromoStatusEnum.CANCELLATA_00.getKey()).getResultList();
            }
            if (dataFine.get()) {
                return getEm().createQuery(queryString, PromozioneTestataEntity.class)
                        .setParameter("dataFine", dataFineValue.get(0))
                        .setParameter("code", PromoStatusEnum.CANCELLATA_00.getKey()).getResultList();
            }
        }
        return getEm().createQuery(queryString, PromozioneTestataEntity.class)
                .setParameter("code", PromoStatusEnum.CANCELLATA_00.getKey()).getResultList();
    }

    private String channelsIdsAsString(List<CanalePromozioneEntity> canali) {
        return canali.stream().map(CanalePromozioneEntity::getId).map(Object::toString)
                .collect(Collectors.joining(","));
    }

    private Date addDays(Date date, Integer days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    @Override
    public Long countByLogoFilenameAndIdTestata(String logoFilename, Long idTestata) {
        return getEm()
                .createQuery(
                        "SELECT COUNT(c) FROM CastellettoMessaggiEntity c "
                                + "WHERE LOWER(c.logo) = LOWER(:logoFilename) "
                                + "AND c.pianificazione.promozioneTestataEntity.id = :idTestata",
                        Long.class)
                .setParameter("logoFilename", logoFilename)
                .setParameter("idTestata", idTestata)
                .getSingleResult();
    }

    @Override
    public List<PromozioneTestataEntity> findByCanaleMeccanicheDate(@NonNull Long idCanale,
                                                                    @NonNull List<String> codiciMeccaniche,
                                                                    @NonNull Date dataInizio,
                                                                    @NonNull Date dataFine) {
        if (codiciMeccaniche.isEmpty()) {
            throw new IllegalArgumentException("codiciMeccaniche cannot be null or empty");
        }
        return getEm().createNamedQuery("PromozioneTestataEntity.findByCanaleMeccanicheDate", PromozioneTestataEntity.class)
                .setParameter("codiceStato", PromoStatusEnum.CANCELLATA_00.getKey())
                .setParameter("idCanale", idCanale)
                .setParameter("codiciMeccaniche", codiciMeccaniche)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();
    }

    @Override
    public PromozioneTestataEntity findByIdFullEagerFetch(Long id) {
        try {
            return getEm()
                    .createNamedQuery(
                            "PromozioneTestataEntity.findByIdFullEagerFetch", PromozioneTestataEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            log.error("Error fetching PromozioneTestataEntity by id: " + id, e);
            return null;
        }
    }

    @Override
    public Long copiaPromozione(String source, String codiceUtente, Long idPromozione, Date dataInizio, Date dataFine) {
        Long ret = -1L;
        try {
            Connection conn = getEm().unwrap(Connection.class);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try (java.sql.CallableStatement cs = conn.prepareCall(String.format("{ CALL DBPROMO.%s(?, ?, ?, ?, ?, ?) }",
                    Constants.P_MUI_COPY_PROMO_MAIN))) {
                cs.registerOutParameter(6, Types.BIGINT);
                cs.setString(1, source);
                cs.setString(2, codiceUtente);
                cs.setLong(3, idPromozione);
                cs.setString(4, sdf.format(dataInizio));
                cs.setString(5, sdf.format(dataFine));
                cs.setNull(6, Types.BIGINT); // reset OUT parameter
                cs.execute();
                ret = cs.getLong(6);
            }
        } catch (Exception ex) {
            log.error(String.format("Error copying promozione with id %d", idPromozione), ex);
        }
        return ret;
    }

    @Override
    public List<PromozioneTestataEntity> findOverlappingPromoWithAttributo(@NonNull PromozioneTestataEntity promozione,
                                                                           @NonNull Long idAttributo,
                                                                           @NonNull String valoreAttributo) {
        return getEm().createNamedQuery("PromozioneTestataEntity.findOverlappingPromoWithAttributo",
                        PromozioneTestataEntity.class)
                .setParameter("code", PromoStatusEnum.CANCELLATA_00.getKey())
                .setParameter("idTestata", promozione.getId())
                .setParameter("dataInizio", promozione.getDataInizio())
                .setParameter("dataFine", promozione.getDataFine())
                .setParameter("idAttributo", idAttributo)
                .setParameter("valoreAttributo", valoreAttributo)
                .getResultList();
    }
}
