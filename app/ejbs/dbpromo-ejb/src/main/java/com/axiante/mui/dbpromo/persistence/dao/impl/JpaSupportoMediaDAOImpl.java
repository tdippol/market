package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.SupportoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaSupportoMediaDAOImpl extends JpaDbPromoDAO<SupportoMediaEntity> implements SupportoMediaDAO {
    private static final long serialVersionUID = -323587527371665632L;

    @Override
    public SupportoMediaEntity findByCode(@NonNull String codice) {
        final List<SupportoMediaEntity> entities = getEm().createNamedQuery("SupportoMediaEntity.findByCode", SupportoMediaEntity.class)
                .setParameter("codice", codice)
                .getResultList();
        if (entities.size() > 1) {
            final String msg = String.format("Found %d results for 'SupportoMedia' with codice '%s'", entities.size(), codice);
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        if (entities.size() == 1) {
            return entities.get(0);
        }
        return null;
    }

    @Override
    public List<SupportoMediaEntity> findAllAttivi() {
        return getEm().createNamedQuery("SupportoMediaEntity.findAllAttivi", SupportoMediaEntity.class)
                .getResultList();
    }
}
