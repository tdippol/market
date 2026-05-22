package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneNegozioDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DbPromoJpaDao
public class JpaPromozioneNegozioDAOImpl extends JpaDbPromoDAO<PromozioneNegozioEntity>
        implements PromozioneNegozioDAO {
    private static final long serialVersionUID = -592404298937157825L;
    final static int maxChunk = 999;

    @Override
    public List<PromozioneNegozioEntity> findById(final List<Long> ids) {
        if ((ids == null) || ids.isEmpty()) {
            throw new IllegalArgumentException("ids cannot be null or empty");
        }
        final List<Long> uniqueIds = ids.stream().distinct().collect(Collectors.toList());
        final TypedQuery<PromozioneNegozioEntity> q = getEm()
                .createNamedQuery("PromozioneNegozioEntity.findByIds", PromozioneNegozioEntity.class);
        if (uniqueIds.size() > maxChunk) {
            // devo lavorare a chunk
            int upperBound = maxChunk;
            int lowerBound = 0;
            int round = 1;
            final List<PromozioneNegozioEntity> result = new ArrayList<>();
            while ((upperBound != lowerBound) && (upperBound <= uniqueIds.size())) {
                q.setParameter("ids", uniqueIds.subList(lowerBound, upperBound));
                result.addAll(q.getResultList());
                ++round;
                lowerBound = upperBound;
                upperBound = Math.min(uniqueIds.size(), round * maxChunk);
            }
            return result;
        } else {
            q.setParameter("ids", ids);
            return q.getResultList();
        }
    }

    @Override
    public boolean impostaNegozi(Long idPromozioneCorrente, String codicePromozioneSorgente,
                                 String codiceCategoriaPlano,
                                 String tipologiaPlano,
                                 String dimensionePlano,
                                 String username) throws Exception {
        /**
         IN ID_PROMOZIONE bigint,
         IN CODICE_PROMOZIONE VARCHAR(255),
         IN p_CodCategoriaEsp_Plano VARCHAR(255),
         IN p_Tipologia_Plano VARCHAR(255),
         IN p_Dimensione_Plano VARCHAR(255),
         IN CODICE_UTENTE VARCHAR(255)
         */
        final StoredProcedureQuery query = getEm().createStoredProcedureQuery(Constants.SP_IMPOSTA_NEGOZI)
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                .setParameter(1, idPromozioneCorrente)
                .setParameter(2, codicePromozioneSorgente)
                .setParameter(3, codiceCategoriaPlano)
                .setParameter(4, tipologiaPlano)
                .setParameter(5, dimensionePlano)
                .setParameter(6, username);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean eliminaNegozi(Long idPromozione, String codiceCategoriaPlano, String tipologiaPlano, String dimensionePlano)
            throws Exception {
        final StoredProcedureQuery query = getEm().createStoredProcedureQuery(Constants.SP_ELIMINA_NEGOZI)
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .setParameter(1, idPromozione)
                .setParameter(2, codiceCategoriaPlano)
                .setParameter(3, tipologiaPlano)
                .setParameter(4, dimensionePlano);
        query.executeUpdate();
        return true;
    }
}
