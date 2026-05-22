package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.VMuiSpRepartoCompratoreDAO;
import com.axiante.mui.dbpromo.persistence.entities.VMuiSpRepartoCompratoreEntity;

import java.util.List;

@DbPromoJpaDao
public class JpaVMuiSpRepartoCompratoreDAOImpl extends JpaDbPromoDAO<VMuiSpRepartoCompratoreEntity> implements VMuiSpRepartoCompratoreDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public List<VMuiSpRepartoCompratoreEntity> findAllOrdered() {
        return getEm()
                .createNamedQuery("VMuiSpRepartoCompratoreEntity.findAllOrdered", VMuiSpRepartoCompratoreEntity.class)
                .getResultList();
    }
}