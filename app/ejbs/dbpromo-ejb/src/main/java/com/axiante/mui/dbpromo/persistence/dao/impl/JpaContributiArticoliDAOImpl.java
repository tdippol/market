package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.ContributiArticoliDAO;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoArticoloEntity;
import java.util.List;

public class JpaContributiArticoliDAOImpl extends JpaDbPromoDAO<ContributiPromoArticoloEntity>
        implements ContributiArticoliDAO {
    private static final long serialVersionUID = 8390855614458247452L;

    @Override
    public List<ContributiPromoArticoloEntity> findAllByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("ids cannot be null or empty");
        }
        return getEm().createNamedQuery("ContributiPromoArticoloEntity.findAllByIds", ContributiPromoArticoloEntity.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public List<ContributiPromoArticoloEntity> findAllByIdRata(Long idRata) {
        return getEm().createNamedQuery("ContributiPromoArticoloEntity.findAllByIdRata", ContributiPromoArticoloEntity.class)
                .setParameter("idRata", idRata)
                .getResultList();
    }
}
