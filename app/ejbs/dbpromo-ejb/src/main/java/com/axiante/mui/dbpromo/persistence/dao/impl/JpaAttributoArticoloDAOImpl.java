package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.AttributoArticoloDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.AttributoArticoloEntity;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DbPromoJpaDao
public class JpaAttributoArticoloDAOImpl extends JpaDbPromoDAO<AttributoArticoloEntity> implements AttributoArticoloDAO {
    private static final long serialVersionUID = 1955368938091275731L;

    @Override
    public List<AttributoArticoloEntity> findAllActive() {
        return getEm().createNamedQuery("AttributoArticoloEntity.findAllActive", AttributoArticoloEntity.class)
                .getResultList();
    }
}
