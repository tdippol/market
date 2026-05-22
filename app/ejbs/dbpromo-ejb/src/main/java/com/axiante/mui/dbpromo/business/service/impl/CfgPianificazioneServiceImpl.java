package com.axiante.mui.dbpromo.business.service.impl;

import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.AbstractDbPromoService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class CfgPianificazioneServiceImpl extends AbstractDbPromoService<CfgPianificazioneEntity>
		implements CfgPianificazioneService {

	@Inject
	@Getter
	private CfgPianificazioneDAO dao;

	@Override
	public boolean isLockable(CfgSetPianificazioneEntity cfgPianificatione) {
		return cfgPianificatione.getPromozioneTestataEntities().stream().filter((promo) -> isActive(promo))
				.collect(Collectors.toSet()).isEmpty();
	}

	@Override
	public List<String> findTipoElement(Long promoID, Long meccanicaID) {
		return getDao().findListaByPromoIdAndMeccanicaIdAndCampoTipoElemento(promoID, meccanicaID);
	}

	@Override
	public List<CfgPianificazioneEntity> findPianificazioneByCanaleMeccanica(
			CanalePromozioneEntity canalePromozioneEntity, MeccanicheEntity meccanicheEntity) {
		return getDao().findAllDistinctByCanaleAndMeccanica(canalePromozioneEntity, meccanicheEntity);
	}

	private boolean isActive(PromozioneTestataEntity promo) {
		if (promo.getDataInizio() == null || promo.getDataFine() == null) {
			return false;
		}

		Date now = new Date();
		return promo.getDataInizio().before(now) && promo.getDataFine().after(now);
	}

	@Override
	public List<CfgPianificazioneEntity> findPianificazioneByCanaleMeccanicaField(
			CanalePromozioneEntity canalePromozioneEntity, MeccanicheEntity meccanicheEntity, String field) {
		return getDao().findAllByCanaleAndMeccanicaAndField(canalePromozioneEntity, meccanicheEntity, field);

	}

	@Override
	public CfgPianificazioneEntity findPianificazioneByCanaleMeccanicaTipoRigaField(
			CanalePromozioneEntity canalePromozioneEntity, MeccanicheEntity meccanicheEntity,
			CfgPianificazTipoRigaEntity tipoRiga, String field, PromozioneTestataEntity testata) {
		return getDao().findByCanaleAndMeccanicaAndTipoRigaAndFieldAndPromo(canalePromozioneEntity, meccanicheEntity,
				tipoRiga, field, testata);

	}
}
