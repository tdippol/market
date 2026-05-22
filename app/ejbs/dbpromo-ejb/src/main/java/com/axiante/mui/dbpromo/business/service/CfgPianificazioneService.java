package com.axiante.mui.dbpromo.business.service;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.DbPromoService;
import java.util.List;
import lombok.NonNull;

public interface CfgPianificazioneService extends DbPromoService<CfgPianificazioneEntity> {

	boolean isLockable(CfgSetPianificazioneEntity cfgPianificatione);

	List<String> findTipoElement(Long promoID, Long meccanicaID);

	List<CfgPianificazioneEntity> findPianificazioneByCanaleMeccanica(CanalePromozioneEntity canalePromozioneEntity,
			MeccanicheEntity meccanicheEntity);

	List<CfgPianificazioneEntity> findPianificazioneByCanaleMeccanicaField(
			CanalePromozioneEntity canalePromozioneEntity, MeccanicheEntity meccanicheEntity, String field);

	CfgPianificazioneEntity findPianificazioneByCanaleMeccanicaTipoRigaField(
			CanalePromozioneEntity canalePromozioneEntity, MeccanicheEntity meccanicheEntity,
			CfgPianificazTipoRigaEntity tipoRiga, String field, PromozioneTestataEntity testata);

	default CfgPianificazioneEntity saveCfgPianificazioneEntity(@NonNull CfgPianificazioneEntity cfgPianificazione,
			@NonNull String username) {
		return (CfgPianificazioneEntity) getDao().persistWithAuditLog(cfgPianificazione, username);
	}

	default void saveCfgPianificazioneEntities(@NonNull List<CfgPianificazioneEntity> entities) {
		getDao().persist(entities);
	}

	default void removeCfgPianificazioneEntity(CfgPianificazioneEntity cfgPianificazione) {
		getDao().remove(cfgPianificazione);
	}
}
