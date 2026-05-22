package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;
import java.util.Map;

public interface CfgPianificazioneDAO extends DbPromoDAO<CfgPianificazioneEntity> {

	List<CfgPianificazioneEntity> findAllByMuiCfgSetPianificazione(PromozioneTestataEntity muiCfgSetPianificazione);

	List<String> findListaByPromoIdAndMeccanicaIdAndCampoTipoElemento(Long promoId, Long meccanicaId);

	List<CfgPianificazioneEntity> findAllByPromoAndMeccanicaId(PromozioneTestataEntity promo, Long meccanica);

	List<CfgPianificazioneEntity> findAllDistinctByCanaleAndMeccanica(CanalePromozioneEntity canale, MeccanicheEntity meccanica);

	List<CfgPianificazioneEntity> findAllByCanaleAndMeccanicaAndField(CanalePromozioneEntity canale, MeccanicheEntity meccanica, String field);

	CfgPianificazioneEntity findByCanaleAndMeccanicaAndTipoRigaAndFieldAndPromo(CanalePromozioneEntity canale,
			MeccanicheEntity meccanica, CfgPianificazTipoRigaEntity tipoRiga, String field, PromozioneTestataEntity testata);

	List<CfgPianificazioneEntity> findBySetAndMeccanicaAndCampo(CfgSetPianificazioneEntity setPianificazioneEntity,
			MeccanicheEntity meccanicaEntity, String campo);

	List<CfgPianificazioneEntity> findBySetAndMeccanicaAndCampoAndTipoRiga(
			CfgSetPianificazioneEntity setPianificazione, MeccanicheEntity meccanica,
			String field, PianificazioneRowTypeEnum rowType);

	Map<String, String> getDefaultValues(Long testataId, Long meccanicaId, String codeTipoRiga);

	List<String> findTipiRigaByHeaderAndCampo(Long headerId, String field);
}
