package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PrestazioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.PrestazioniEntity;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaPrestazioniDAOImpl extends JpaDbPromoDAO<PrestazioniEntity> implements PrestazioniDAO {
    private static final long serialVersionUID = -7158009656630311801L;

    @Override
    public String findDescrizioneByCodice(@NonNull String codice) {
        final Long count = getEm().createNamedQuery("PrestazioniEntity.countDescrizioneByCodice", Long.class)
                .setParameter("codice", codice)
                .getSingleResult();
        if (count != null && count == 1) {
            return getEm().createNamedQuery("PrestazioniEntity.findDescrizioneByCodice", String.class)
                    .setParameter("codice", codice)
                    .getSingleResult();
        }
        if (count == null || count == 0) {
            log.error(String.format("No results found for codice '%s'", codice));
        } else if (count > 1) {
            log.error(String.format("Found %d results for codice '%s'", count, codice));
        }
        return null;
    }

    @Override
    public List<PrestazioniEntity> findByCodiceGruppo(String codiceGruppo){
        return getEm().createNamedQuery("PrestazioniEntity.findByCodiceGruppo", PrestazioniEntity.class)
                .setParameter("codiceGruppo", codiceGruppo)
                .getResultList();
    }

}
