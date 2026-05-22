package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import java.util.Date;
import java.util.List;

public interface CfgSetPianificazioneService extends DbPromoService<CfgSetPianificazioneEntity> {
	List<CfgSetPianificazioneEntity> getAllEnabled();

	CfgSetPianificazioneEntity findOpenSet() throws RuntimeException;

	CfgSetPianificazioneEntity duplicateSet(CfgSetPianificazioneEntity openSet,
			CfgSetPianificazioneEntity pianificazione, String description, Date startDate, String user);

	CfgSetPianificazioneEntity createSet(CfgSetPianificazioneEntity openSet, String description, Date startDate,
			String user);

	CfgSetPianificazioneEntity persist(CfgSetPianificazioneEntity cfgSetPianificazione, String name);
}
