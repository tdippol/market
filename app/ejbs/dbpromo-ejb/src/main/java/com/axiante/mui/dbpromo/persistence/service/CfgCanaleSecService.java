package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleSecEntity;

public interface CfgCanaleSecService extends DbPromoService<CfgCanaleSecEntity>{
	CfgCanaleSecEntity findByCanale(CanalePromozioneEntity canale);
}
