package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;

public interface TipoElementoDAO extends DbPromoDAO<CfgTipoElementoEntity> {
    CfgTipoElementoEntity findByMeccanicaIdAndSetPianificazioneId(Long meccanicaId, Long setPianificazioneId);
}
