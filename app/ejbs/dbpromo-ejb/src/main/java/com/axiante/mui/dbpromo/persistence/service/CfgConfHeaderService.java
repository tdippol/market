package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;

public interface CfgConfHeaderService extends DbPromoService<CfgConfHeaderEntity> {

    CfgConfHeaderEntity findByMeccanicaIdAndSetPianificazioneId(Long meccanicaId, Long setPianificazioneId);

    Boolean isFieldMeccanicaFullyConfigured(Long id);
}
