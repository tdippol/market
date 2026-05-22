package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgMeccanicaMissioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgMeccanicaMissioneEntity;

import java.util.List;

@DbPromoJpaDao
public class JpaCfgMeccanicaMissioneDAOImpl extends JpaDbPromoDAO<CfgMeccanicaMissioneEntity>
        implements CfgMeccanicaMissioneDAO {
    private static final long serialVersionUID = 350993020039143964L;

    public List<CfgMeccanicaMissioneEntity> findAll() {
        return getEm()
                .createNamedQuery("CfgMeccanicaMissioneEntity.findAll", CfgMeccanicaMissioneEntity.class)
                .getResultList();
    }
}
