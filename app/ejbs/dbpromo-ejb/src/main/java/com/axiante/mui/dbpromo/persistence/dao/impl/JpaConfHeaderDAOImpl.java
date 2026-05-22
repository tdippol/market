package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgConfHeaderDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;

@DbPromoJpaDao
public class JpaConfHeaderDAOImpl extends JpaDbPromoDAO<CfgConfHeaderEntity> implements CfgConfHeaderDAO {

    private static final long serialVersionUID = -1539400636284682525L;

    @Override
    public CfgConfHeaderEntity findByMeccanicaIdAndSetPianificazioneId(Long meccanicaId, Long setPianificazioneId) {
        return getEm().createNamedQuery("CfgConfHeaderEntity.findByMeccanicaIdAndSetPianificazioneId", CfgConfHeaderEntity.class)
                .setParameter("meccanicaId", meccanicaId)
                .setParameter("setPianificazioneId", setPianificazioneId)
                .getSingleResult();
    }

    @Override
    public String findLivelloByIdHeader(Long headerId) {
        return getEm().createNamedQuery("CfgConfHeaderEntity.findLivelloById", String.class)
                .setParameter("idHeader", headerId)
                .getSingleResult();
    }
}
