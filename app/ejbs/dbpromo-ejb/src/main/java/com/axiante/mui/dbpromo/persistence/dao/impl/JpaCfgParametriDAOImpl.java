package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgParametriDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgParametriEntity;

@DbPromoJpaDao
public class JpaCfgParametriDAOImpl extends JpaDbPromoDAO<CfgParametriEntity> implements CfgParametriDAO {

    private static final long serialVersionUID = -3221777614169237381L;
}
