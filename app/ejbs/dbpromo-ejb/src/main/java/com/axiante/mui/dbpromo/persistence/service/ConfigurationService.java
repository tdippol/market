package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import java.util.List;

public interface ConfigurationService extends DbPromoService<CfgPianificazioneEntity> {
	String generateCompletePromoPlanningGrid(CfgPianificazioneEntity cfgPianificazioneEntity);

	List<CfgPianificazioneEntity> findBySetAndMeccanicaAndCampo(CfgSetPianificazioneEntity setPianificazioneEntity,
																MeccanicheEntity meccanicaEntity,
																String field);

	List<CfgPianificazioneEntity> findBySetAndMeccanicaAndCampoAndTipoRiga(
			CfgSetPianificazioneEntity setPianificazione, MeccanicheEntity meccanica,
			String field, PianificazioneRowTypeEnum rowType);

	CfgPianificazioneEntity findByCfgPianificazioneId(Long id);

	String asJsonObject(CfgPianificazioneEntity cfgPianificazioneEntity);
}
