package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleFlagDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleFlagEntity;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaCfgCanaleFlagDAOImpl extends JpaDbPromoDAO<CfgCanaleFlagEntity> implements CfgCanaleFlagDAO {
    private static final long serialVersionUID = 1761012683536948159L;

    @Override
    public List<CfgCanaleFlagEntity> findActiveByChannel(@NonNull Long canale) {
        return getEm().createNamedQuery("CfgCanaleFlagEntity.findActiveByChannel", CfgCanaleFlagEntity.class)
                .setParameter("idCanale", canale)
                .getResultList();
    }
}
