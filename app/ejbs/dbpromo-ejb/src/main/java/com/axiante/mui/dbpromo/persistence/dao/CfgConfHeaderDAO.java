package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;

public interface CfgConfHeaderDAO extends DbPromoDAO<CfgConfHeaderEntity> {
    CfgConfHeaderEntity findByMeccanicaIdAndSetPianificazioneId(Long meccanicaId, Long setPianificazioneId);
    String findLivelloByIdHeader(Long headerId);
}
