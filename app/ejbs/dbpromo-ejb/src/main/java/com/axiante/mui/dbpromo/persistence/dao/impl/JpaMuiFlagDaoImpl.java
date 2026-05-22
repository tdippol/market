package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiFalgDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFlagEntity;

@DbPromoJpaDao
public class JpaMuiFlagDaoImpl extends JpaDbPromoDAO<MuiFlagEntity> implements MuiFalgDAO {
    private static final long serialVersionUID = 3818727668751374954L;
}
