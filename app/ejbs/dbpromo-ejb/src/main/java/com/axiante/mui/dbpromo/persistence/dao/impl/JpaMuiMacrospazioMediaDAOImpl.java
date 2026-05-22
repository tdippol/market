package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiMacrospazioMediaDAO;
import com.axiante.mui.dbpromo.persistence.dto.MacrospazioWithEventsDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@DbPromoJpaDao
@Slf4j
public class JpaMuiMacrospazioMediaDAOImpl extends JpaDbPromoDAO<MuiMacrospazioMediaEntity> implements MuiMacrospazioMediaDAO {
    private static final long serialVersionUID = 5746494167817271106L;

    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public Long countByCodiceMacrospazio(String codiceMacrospazio) {
        return getEm().createNamedQuery("MuiMacrospazioMediaEntity.countByCodiceMacrospazio", Long.class)
                .setParameter("codiceMacrospazio", codiceMacrospazio)
                .getSingleResult();
    }

    @Override
    public Long countByCodiceMacrospazio(String codiceMacrospazio, List<Long> excludedIds) {
        return getEm().createNamedQuery("MuiMacrospazioMediaEntity.countByCodiceMacrospazioExcludedIds", Long.class)
                .setParameter("codiceMacrospazio", codiceMacrospazio)
                .setParameter("excludedIds", excludedIds)
                .getSingleResult();
    }

    @Override
    public Long countByDescrizioneMacrospazio(String descrizioneMacrospazio) {
        return getEm().createNamedQuery("MuiMacrospazioMediaEntity.countByDescrizioneMacrospazio", Long.class)
                .setParameter("descrizioneMacrospazio", descrizioneMacrospazio)
                .getSingleResult();
    }

    @Override
    public Long countByDescrizioneMacrospazio(String descrizioneMacrospazio, List<Long> excludedIds) {
        return getEm().createNamedQuery("MuiMacrospazioMediaEntity.countByDescrizioneMacrospazioExcludedIds", Long.class)
                .setParameter("descrizioneMacrospazio", descrizioneMacrospazio)
                .setParameter("excludedIds", excludedIds)
                .getSingleResult();
    }

    public List<MacrospazioWithEventsDTO> findAllWithHasEvents() {
        String q = "SELECT new com.axiante.mui.dbpromo.persistence.dto.MacrospazioWithEventsDTO(" +
                " m.id, m.codice, m.descrizione, m.dataInizio, m.dataFine, " +
                " m.dataInserimento, m.codiceUtenteInserimento, m.dataAggiornamento, m.codiceUtenteAggiornamento, " +
                " (SELECT COUNT(e) FROM MuiEventoRetailEntity e WHERE e.macrospazio = m), " +
                " m.listino) " +
                "FROM MuiMacrospazioMediaEntity m " +
                "ORDER BY m.codice";
        return em.createQuery(q, MacrospazioWithEventsDTO.class).getResultList();
    }

    @Override
    public void confermaMacrospazi(String codiceUtente) {
        if (codiceUtente == null) {
            throw new IllegalArgumentException("codiceUtente can not be null");
        }
        final StoredProcedureQuery query = getEm().createStoredProcedureQuery(Constants.P_MUI_EXPORT_RETAIL_MEDIA_MACROSPAZI_EVENTS)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .setParameter(1, codiceUtente);
        try {
            query.executeUpdate();
        } catch (Exception e) {
            log.error("error executing stored procedure ", e);
            throw new RuntimeException("error executing stored procedure " + Constants.P_MUI_EXPORT_RETAIL_MEDIA_MACROSPAZI_EVENTS);
        }
    }
}
