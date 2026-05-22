package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaStatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;

@DbPromoJpaDao
public class JpaPianoMediaStatoDAOImpl extends JpaDbPromoDAO<PianoMediaStatoEntity>
        implements PianoMediaStatoDAO {

    private static final long serialVersionUID = -317622932559622176L;
}
