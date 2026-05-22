package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianoMediaEntity;
import java.util.List;

@DbPromoJpaDao
public class JpaCfgPianoMediaDAOImpl extends JpaDbPromoDAO<CfgPianoMediaEntity> implements CfgPianoMediaDAO {
    private static final long serialVersionUID = -2405656375328375579L;

    @Override
    public List<CfgPianoMediaEntity> findAllAttivi() {
        return getEm().createNamedQuery("CfgPianoMediaEntity.findAllAttivi", CfgPianoMediaEntity.class).getResultList();
    }
}
