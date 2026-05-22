package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.SubGrmDAO;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;

@DbPromoJpaDao
public class JpaSubGrmDAOImpl extends JpaDbPromoDAO<SubGrmEntity> implements SubGrmDAO {

    private static final long serialVersionUID = 5647283442217214119L;
}
