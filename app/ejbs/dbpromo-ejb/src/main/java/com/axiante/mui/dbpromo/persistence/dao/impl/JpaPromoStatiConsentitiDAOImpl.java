package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromoStatiConsentitiDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiConsentitiEntity;

@DbPromoJpaDao
public class JpaPromoStatiConsentitiDAOImpl extends JpaDbPromoDAO<PromoStatiConsentitiEntity>
        implements PromoStatiConsentitiDAO {

    private static final long serialVersionUID = -9144329136535532352L;
}
