package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgStatiCanaleConsentDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiCanaleConsentEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgStatiCanaleConsentitiService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class CfgStatiCanaleConsentitiServiceImpl extends AbstractDbPromoService<CfgStatiCanaleConsentEntity>
		implements CfgStatiCanaleConsentitiService {
	private static final long serialVersionUID = 6643732035301214790L;

	@Inject
	@Getter
	private CfgStatiCanaleConsentDAO dao;

	@Override
	public CfgStatiCanaleConsentEntity findByCanaleAndStato(CanalePromozioneEntity canale, StatoPromozioneEntity stato) {
		return dao.findByCanaleAndStato(canale, stato);
	}

	@Override
	public List<CfgAzioniEntity> findAzioniConsentByCanale(CanalePromozioneEntity canale) {
		return getDao().findAzioniConsentByCanale(canale);
	}

	@Override
	public List<CfgAzioniEntity> findAzioniConsentByCanaleAndStato(CanalePromozioneEntity canale, StatoPromozioneEntity stato){
		return getDao().findAzioniConsentByCanaleAndStato(canale, stato);
	}

	@Override
	public List<String> findCodiciAzioniConsentiteByCanaleAndStato(CanalePromozioneEntity canale, StatoPromozioneEntity stato) {
		return getDao().findCodiciAzioniConsentiteByCanaleAndStato(canale, stato);
	}
}
