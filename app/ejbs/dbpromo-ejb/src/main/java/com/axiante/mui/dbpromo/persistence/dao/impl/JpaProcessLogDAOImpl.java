package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.ProcessLogDAO;
import com.axiante.mui.dbpromo.persistence.entities.ProcessLogEntity;

@DbPromoJpaDao
public class JpaProcessLogDAOImpl extends JpaDbPromoDAO<ProcessLogEntity> implements ProcessLogDAO {

    private static final long serialVersionUID = 3531155133382026254L;
}
