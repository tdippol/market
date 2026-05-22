package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiCanaleConsentEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;

public interface CfgStatiCanaleConsentitiService extends DbPromoService<CfgStatiCanaleConsentEntity> {
	CfgStatiCanaleConsentEntity findByCanaleAndStato(CanalePromozioneEntity canale, StatoPromozioneEntity stato);
	List<CfgAzioniEntity> findAzioniConsentByCanale(CanalePromozioneEntity canale);
	List<CfgAzioniEntity> findAzioniConsentByCanaleAndStato(CanalePromozioneEntity canale, StatoPromozioneEntity stato);
	List<String> findCodiciAzioniConsentiteByCanaleAndStato(CanalePromozioneEntity canale, StatoPromozioneEntity stato);
}
