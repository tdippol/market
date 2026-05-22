package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.TipoNegozioDAO;
import com.axiante.mui.dbpromo.persistence.entities.TipoNegozioEntity;

@DbPromoJpaDao
public class JpaTipoNegozioDAOImpl extends JpaDbPromoDAO<TipoNegozioEntity> implements TipoNegozioDAO {
    private static final long serialVersionUID = -1619539288728603496L;
}
