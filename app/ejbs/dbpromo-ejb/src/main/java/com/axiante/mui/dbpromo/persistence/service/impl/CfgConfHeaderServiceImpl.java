package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.persistence.dao.CfgConfHeaderDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Transactional
@Slf4j
public class CfgConfHeaderServiceImpl extends AbstractDbPromoService<CfgConfHeaderEntity>
implements CfgConfHeaderService {
	private static final long serialVersionUID = -1723620027243573464L;

	@Inject
	@Getter
	CfgConfHeaderDAO dao;

	@Inject
	CfgPianificazioneDAO cfgPianificazioneDAO;

	@Override
	public CfgConfHeaderEntity findByMeccanicaIdAndSetPianificazioneId(Long meccanicaId, Long setPianificazioneId) {
		return dao.findByMeccanicaIdAndSetPianificazioneId(meccanicaId, setPianificazioneId);
	}

	@Override
	public Boolean isFieldMeccanicaFullyConfigured(@NonNull Long id) {
		final String livello = dao.findLivelloByIdHeader(id);
		final PlanningLevelEnum levelEnum = PlanningLevelEnum.fromDescription(livello);
		if (levelEnum == null) {
			log.error(String.format("Livello pianificazione %s non gestito", livello));
			return null;
		}
		final List<String> tipiRiga = cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(id,
				PianificazioneConstants.CAMPO_PIANIFICAZIONE_MECCANICA);
		switch (levelEnum) {
		case SET:
			return tipiRiga.contains("SET") && tipiRiga.contains("RAGGRUPPAMENTO") && tipiRiga.contains("ELEMENTO");
		case RAGGRUPPAMENTO:
			return tipiRiga.contains("RAGGRUPPAMENTO") && tipiRiga.contains("ELEMENTO");
		case ELEMENTO:
			return tipiRiga.contains("ELEMENTO");
		default:
			log.error(String.format("Livello pianificazione %s non gestito", livello));
		}
		return null;
	}
}
