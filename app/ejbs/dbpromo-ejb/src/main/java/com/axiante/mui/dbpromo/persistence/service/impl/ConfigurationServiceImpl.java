package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.service.ConfigurationService;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Transactional
@Slf4j
public class ConfigurationServiceImpl extends AbstractDbPromoService<CfgPianificazioneEntity>
		implements ConfigurationService {
	private static final long serialVersionUID = -8624049082686156798L;

	@Inject
	@Getter
	private CfgPianificazioneDAO dao;

	@Override
	public String generateCompletePromoPlanningGrid(CfgPianificazioneEntity cfgPianificazioneEntity) {
		CfgPianificazioneEntity configuration = null;
		if (cfgPianificazioneEntity != null) {
			configuration = getDao().findById(cfgPianificazioneEntity.getId());
		}
		return configuration != null ? asJsonObject(cfgPianificazioneEntity) : null;
	}

	@Override
	public List<CfgPianificazioneEntity> findBySetAndMeccanicaAndCampo(CfgSetPianificazioneEntity setPianificazioneEntity,
																	   MeccanicheEntity meccanicaEntity,
																	   String field) {
		return dao.findBySetAndMeccanicaAndCampo(setPianificazioneEntity, meccanicaEntity, field);
	}

	@Override
	public List<CfgPianificazioneEntity> findBySetAndMeccanicaAndCampoAndTipoRiga(
			CfgSetPianificazioneEntity setPianificazione, MeccanicheEntity meccanica,
			String field, PianificazioneRowTypeEnum rowType) {
		return dao.findBySetAndMeccanicaAndCampoAndTipoRiga(setPianificazione, meccanica, field, rowType);
	}

	@Override
	public CfgPianificazioneEntity findByCfgPianificazioneId(Long id) {
		return getDao().findById(id);
	}

	@Override
	public String asJsonObject(CfgPianificazioneEntity cfgPianificazioneEntity) {
		try {
			return JsonUtils.getMapper().writeValueAsString(cfgPianificazioneEntity);
		} catch (IOException e) {
			log.error("error serializing CfgPianificazioneEntity", e);
			return null;
		}
	}

}
