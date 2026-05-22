package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneFlagDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromozioneFlagEntity;

@DbPromoJpaDao
public class JpaPromozioneFlagDAOImpl extends JpaDbPromoDAO<MuiPromozioneFlagEntity> implements PromozioneFlagDAO {
    private static final long serialVersionUID = 6480267961451372340L;
}
