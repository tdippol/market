package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.persistence.dao.CfgInclusioniEsclusioniDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgInclusioniEsclusioniEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@DbPromoJpaDao
@Slf4j
public class JpaCfgInclusioniEsclusioniDAOImpl extends JpaDbPromoDAO<CfgInclusioniEsclusioniEntity>
        implements CfgInclusioniEsclusioniDAO {
    private static final long serialVersionUID = -5014666250261253496L;

    @Override
    public List<Long> findAllIdMeccanica() {
        return getEm().createNamedQuery("CfgInclusioniEsclusioniEntity.findAllIdMeccanica", Long.class)
                .getResultList();
    }

    @Override
    public CfgInclusioniEsclusioniEntity findByMeccanicaAndTipoElemento(Long idMeccanica, ElementType tipoElemento) {
        return getEm().createNamedQuery("CfgInclusioniEsclusioniEntity.findByMeccanicaAndTipoElemento", CfgInclusioniEsclusioniEntity.class)
                .setParameter("idMeccanica", idMeccanica)
                .setParameter("tipoElemento", tipoElemento)
                .getSingleResult();
    }

    @Override
    public List<CfgInclusioniEsclusioniEntity> findByMeccanica(Long idMeccanica) {
        return getEm().createNamedQuery("CfgInclusioniEsclusioniEntity.findByMeccanica", CfgInclusioniEsclusioniEntity.class)
                .setParameter("idMeccanica", idMeccanica)
                .getResultList();
    }
}
