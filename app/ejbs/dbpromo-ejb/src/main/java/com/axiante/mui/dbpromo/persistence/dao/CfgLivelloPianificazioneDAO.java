package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;

public interface CfgLivelloPianificazioneDAO extends DbPromoDAO<CfgLivelloPianificazioneEntity> {
	CfgLivelloPianificazioneEntity findByDescription(String description);
}
