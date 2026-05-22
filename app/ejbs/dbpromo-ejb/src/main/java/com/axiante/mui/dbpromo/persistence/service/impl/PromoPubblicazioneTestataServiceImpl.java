package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromoPubblicazioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoPubblicazioneTestataService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class PromoPubblicazioneTestataServiceImpl extends AbstractDbPromoService<PromoPubblicazioneTestataEntity>
		implements PromoPubblicazioneTestataService {

	private static final long serialVersionUID = 6090005661743928249L;
	@Inject
	@Getter
	private PromoPubblicazioneTestataDAO dao;

	@Override
	public List<PromoPubblicazioneTestataEntity> findByPromoID(Long promoID) throws Exception {
		return dao.findByPromozioneID(promoID);
	}

}
