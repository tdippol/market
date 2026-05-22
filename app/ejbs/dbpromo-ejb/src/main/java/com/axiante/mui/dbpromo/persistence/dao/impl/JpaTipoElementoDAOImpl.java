package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.TipoElementoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;

@DbPromoJpaDao
public class JpaTipoElementoDAOImpl extends JpaDbPromoDAO<CfgTipoElementoEntity> implements TipoElementoDAO {

    private static final long serialVersionUID = -1222162221586042417L;

    @Override
    public CfgTipoElementoEntity findByMeccanicaIdAndSetPianificazioneId(Long meccanicaId, Long setPianificazioneId) {
        return getEm().createNamedQuery("CfgTipoElementoEntity.findByMeccanicaIdAndSetPianificazioneId", CfgTipoElementoEntity.class)
                .setParameter("meccanicaId", meccanicaId)
                .setParameter("setPianificazioneId", setPianificazioneId)
                .getSingleResult();
    }
}
