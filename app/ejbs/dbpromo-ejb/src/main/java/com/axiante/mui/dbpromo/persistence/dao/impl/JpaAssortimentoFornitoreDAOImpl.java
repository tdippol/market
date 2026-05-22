package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.AssortimentoFornitoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.AssortimentoFornitoreEntity;

@DbPromoJpaDao
public class JpaAssortimentoFornitoreDAOImpl extends JpaDbPromoDAO<AssortimentoFornitoreEntity>
        implements AssortimentoFornitoreDAO {

    private static final long serialVersionUID = -8568383996182022448L;
}
