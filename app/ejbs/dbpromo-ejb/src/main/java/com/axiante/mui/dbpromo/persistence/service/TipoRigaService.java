package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;

public interface TipoRigaService extends DbPromoService<CfgPianificazTipoRigaEntity> {

	CfgPianificazTipoRigaEntity findByDescription(String string);

	CfgPianificazTipoRigaEntity findByCodiceTipo(String string);

}
