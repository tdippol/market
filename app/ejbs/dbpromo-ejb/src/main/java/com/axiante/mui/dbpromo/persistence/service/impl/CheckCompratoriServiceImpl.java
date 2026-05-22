package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CheckCompratoriDAO;
import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CheckCompratoriService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Transactional
@Slf4j
public class CheckCompratoriServiceImpl extends AbstractDbPromoService<CheckCompratoriEntity>
		implements CheckCompratoriService {
	private static final long serialVersionUID = 2890429005826310706L;

	@Inject
	@Getter
	private CheckCompratoriDAO dao;

	@Override
	public List<CheckCompratoriEntity> findByPromozione(PromozioneTestataEntity promozione){
		return getDao().findByPromozione(promozione);
	}

	@Override
	public List<CheckCompratoriEntity> findByCompratore(CompratoreEntity compratore){
		return getDao().findByCompratore(compratore);
	}

	@Override
	public CheckCompratoriEntity findByPromozioneAndCompratore(PromozioneTestataEntity promozione, CompratoreEntity compratore) {
		try {
			return getDao().findByPromozioneAndCompratore(promozione, compratore);
		} catch ( Exception e) {
			log.error("error retrieving entity", e);
		}
		return null;
	}
}
