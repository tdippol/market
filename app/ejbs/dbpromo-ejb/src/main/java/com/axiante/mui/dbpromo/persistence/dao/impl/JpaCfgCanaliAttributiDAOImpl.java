package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaliAttributiDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaliAttributiEntity;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@DbPromoJpaDao
@Slf4j
public class JpaCfgCanaliAttributiDAOImpl extends JpaDbPromoDAO<CfgCanaliAttributiEntity> implements CfgCanaliAttributiDAO {
    private static final long serialVersionUID = 1623397023927662183L;

    @Override
    public String getListaByCanaleAndAttributo(@NonNull Long idCanale, @NonNull Long idAttributo) {
        return getEm().createNamedQuery("CfgCanaliAttributiEntity.getListaByCanaleAndAttributo", String.class)
                .setParameter("idCanale", idCanale).setParameter("idAttributo", idAttributo)
                .getSingleResult();
    }

    @Override
    public List<CfgCanaliAttributiEntity> getAllByCanale(@NonNull Long idCanale) {
        return getEm().createNamedQuery("CfgCanaliAttributiEntity.getAllByCanale", CfgCanaliAttributiEntity.class)
                .setParameter("idCanale", idCanale)
                .getResultList();
    }
}
