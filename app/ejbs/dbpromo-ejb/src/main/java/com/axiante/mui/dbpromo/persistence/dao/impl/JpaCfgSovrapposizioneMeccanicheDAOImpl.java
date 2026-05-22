package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgSovrapposizioneMeccanicheDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSovrapposizioneMeccanicheEntity;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaCfgSovrapposizioneMeccanicheDAOImpl extends JpaDbPromoDAO<CfgSovrapposizioneMeccanicheEntity>
        implements CfgSovrapposizioneMeccanicheDAO {

	private static final long serialVersionUID = 8438544469377034399L;

    @Override
    public List<CfgSovrapposizioneMeccanicheEntity> findByCanaleMeccanica(@NonNull CfgAbilitaMeccCanaleEntity meccanicaCanale) {
        return getEm().createNamedQuery("CfgSovrapposizioneMeccanicheEntity.findByCanaleMeccanica", CfgSovrapposizioneMeccanicheEntity.class)
                .setParameter("meccanicaCanaleAbilitata", meccanicaCanale)
                .getResultList();
    }
}
