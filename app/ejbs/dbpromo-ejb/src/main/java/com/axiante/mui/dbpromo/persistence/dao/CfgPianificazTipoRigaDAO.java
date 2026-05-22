package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;

public interface CfgPianificazTipoRigaDAO extends DbPromoDAO<CfgPianificazTipoRigaEntity> {
	CfgPianificazTipoRigaEntity findByDescription(String description);

	CfgPianificazTipoRigaEntity findByCodiceTipo(String codiceTipo);
}
