package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleSecEntity;

public interface CfgCanaleSecDAO extends DbPromoDAO<CfgCanaleSecEntity> {
	CfgCanaleSecEntity findByCanale(CanalePromozioneEntity canale);
	Long countByCanale(CanalePromozioneEntity canale);
}
