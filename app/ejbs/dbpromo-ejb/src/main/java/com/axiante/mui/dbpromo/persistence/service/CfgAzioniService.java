package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;

public interface CfgAzioniService extends DbPromoService<CfgAzioniEntity>{

	CfgAzioniEntity findByCode(String code);
}
