package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;

public interface CfgLivelloPianificazioneService extends DbPromoService<CfgLivelloPianificazioneEntity> {
	CfgLivelloPianificazioneEntity findByDescription(String description);
}
