package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.CanaleLastProgService;
import com.axiante.mui.dbpromo.persistence.service.CfgParametriService;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import com.axiante.mui.dbpromo.persistence.service.PromoPubblicazioneTestataService;
import com.axiante.mui.dbpromo.persistence.service.PromoStatiConsentitiService;
import com.axiante.mui.dbpromo.persistence.service.PromoStatiTransizioneService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneMeccanicheService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneNegozioService;
import com.axiante.mui.dbpromo.persistence.service.PromozionePianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneStatoService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTestataService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTipoTerminaleService;
import com.axiante.mui.dbpromo.persistence.service.UuiUtilityService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class UuiUtilityServiceImpl implements UuiUtilityService {

	@Inject
	CanaleLastProgService canaleLastProgService;
	@Inject
	CfgParametriService cfgParametriService;
	@Inject
	CfgPianificazioneService cfgPianificazioneService;
	@Inject
	CreatePromotionService createPromotionService;
	@Inject
	PromoPubblicazioneTestataService promoPubblicazioneTestataService;
	@Inject
	PromoStatiConsentitiService promoStatiConsentitiService;
	@Inject
	PromoStatiTransizioneService promoStatiTransizioneService;
	@Inject
	PromozioneMeccanicheService promozioneMeccanicheService;
	@Inject
	PromozioneNegozioService promozioneNegozioService;
	@Inject
	PromozionePianificazioneService promozionePianificazioneService;
	@Inject
	PromozioneStatoService promozioneStatoService;
	@Inject
	PromozioneTestataService promozioneTestataService;
	@Inject
	PromozioneTipoTerminaleService proTerminaleService;

	@Override
	public void ensureAllNonEmptyUuid() {
		canaleLastProgService.ensureAllUuidAreFilled();
		cfgParametriService.ensureAllUuidAreFilled();
		cfgPianificazioneService.ensureAllUuidAreFilled();
		createPromotionService.ensureAllUuidAreFilled();
		promoPubblicazioneTestataService.ensureAllUuidAreFilled();
		promoStatiConsentitiService.ensureAllUuidAreFilled();
		promoStatiTransizioneService.ensureAllUuidAreFilled();
		promozioneMeccanicheService.ensureAllUuidAreFilled();
		promozioneNegozioService.ensureAllUuidAreFilled();
		promozionePianificazioneService.ensureAllUuidAreFilled();
		promozioneStatoService.ensureAllUuidAreFilled();
		promozioneTestataService.ensureAllUuidAreFilled();
		proTerminaleService.ensureAllUuidAreFilled();
	}
}
