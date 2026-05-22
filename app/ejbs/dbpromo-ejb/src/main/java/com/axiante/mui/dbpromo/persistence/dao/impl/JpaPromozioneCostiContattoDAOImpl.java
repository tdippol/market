package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneCostiContattoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneCostiContattoEntity;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaPromozioneCostiContattoDAOImpl extends JpaDbPromoDAO<PromozioneCostiContattoEntity>
        implements PromozioneCostiContattoDAO {
    private static final long serialVersionUID = 174958119796897001L;

    @Override
    public PromozioneCostiContattoEntity findByIdPromozione(Long idPromozione) {
        final Long count = getEm().createNamedQuery("PromozioneCostiContattoEntity.countByIdPromozione", Long.class)
                .setParameter("idPromozione", idPromozione)
                .getSingleResult();
        if (count != null && count == 1) {
            return getEm().createNamedQuery("PromozioneCostiContattoEntity.findByIdPromozione", PromozioneCostiContattoEntity.class)
                    .setParameter("idPromozione", idPromozione)
                    .getSingleResult();
        }
        if (count == null || count == 0) {
            log.warn(String.format("No result found for 'PromozioneCostiContatto' with idPromozione '%d'", idPromozione));
        } else if (count > 1) {
            log.warn(String.format("Found %d results for 'PromozioneCostiContatto' with idPromozione '%d'",
                    count, idPromozione));
        }
        return null;
    }
}
