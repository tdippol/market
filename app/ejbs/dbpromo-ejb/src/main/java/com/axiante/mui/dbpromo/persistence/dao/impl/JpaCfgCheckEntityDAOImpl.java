package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCheckDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgCheckEntity;

@DbPromoJpaDao
public class JpaCfgCheckEntityDAOImpl extends JpaDbPromoDAO<CfgCheckEntity> implements CfgCheckDAO {
    private static final long serialVersionUID = 8939171525781835395L;
}
