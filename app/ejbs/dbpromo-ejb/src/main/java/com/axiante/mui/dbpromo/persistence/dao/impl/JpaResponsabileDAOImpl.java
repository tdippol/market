package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.ResponsabileDAO;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;

@DbPromoJpaDao
public class JpaResponsabileDAOImpl extends JpaDbPromoDAO<ResponsabileEntity> implements ResponsabileDAO {
    private static final long serialVersionUID = -1355496900731423816L;
}
