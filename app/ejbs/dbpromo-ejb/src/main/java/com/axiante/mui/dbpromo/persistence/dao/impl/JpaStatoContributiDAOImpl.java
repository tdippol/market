package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.StatoContributiDAO;
import com.axiante.mui.dbpromo.persistence.entities.ContributiStatoAnagraficaEntity;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaStatoContributiDAOImpl extends JpaDbPromoDAO<ContributiStatoAnagraficaEntity> implements StatoContributiDAO {
    private static final long serialVersionUID = 3321826478524247006L;

    @Override
    public String findDescrizioneByCodice(String codice) {
        final Long count = getEm().createNamedQuery("ContributiStatoAnagraficaEntity.countDescrizioneByCodice", Long.class)
                .setParameter("codice", codice)
                .getSingleResult();
        if (count != null && count == 1) {
            return getEm().createNamedQuery("ContributiStatoAnagraficaEntity.findDescrizioneByCodice", String.class)
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
}
