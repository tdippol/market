package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CreaPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaCreaPianoMediaDAOImpl extends JpaDbPromoDAO<CreaPianoMediaEntity> implements CreaPianoMediaDAO {
    private static final long serialVersionUID = 108756207409864937L;

    @Override
    public List<CreaPianoMediaEntity> findByUserId(@NonNull String userId) {
        return getEm().createNamedQuery("CreaPianoMediaEntity.findByUserId", CreaPianoMediaEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public CreaPianoMediaEntity findByUserIdAndSlotId(@NonNull String userId, @NonNull String slotId) {
        final Long count = getEm().createNamedQuery("CreaPianoMediaEntity.countByUserIdAndSlotId", Long.class)
                .setParameter("userId", userId)
                .setParameter("slotId", slotId)
                .getSingleResult();
        if (count == 0) {
            log.warn(String.format("Cannot find entity with userId '%s' and slotId '%s'", userId, slotId));
            return null;
        }
        if (count > 1) {
            log.error(String.format("Found '%d' entities with userId '%s' and slotId '%s'", count, userId, slotId));
            return null;
        }
        return getEm().createNamedQuery("CreaPianoMediaEntity.findByUserIdAndSlotId", CreaPianoMediaEntity.class)
                .setParameter("userId", userId)
                .setParameter("slotId", slotId)
                .getSingleResult();
    }
}
