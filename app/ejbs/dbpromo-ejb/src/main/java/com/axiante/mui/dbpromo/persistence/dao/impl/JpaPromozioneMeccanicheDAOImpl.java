package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneMeccanicheDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;

@DbPromoJpaDao
public class JpaPromozioneMeccanicheDAOImpl extends JpaDbPromoDAO<PromozioneMeccanicheEntity>
        implements PromozioneMeccanicheDAO {

    private static final long serialVersionUID = -5465403046723300467L;
}
