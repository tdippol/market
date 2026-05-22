package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneStatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoStatoService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class PromoStatoServiceImpl extends AbstractDbPromoService<PromozioneStatoEntity> implements PromoStatoService {
	@Inject
	@Getter
	private PromozioneStatoDAO dao;

	@Override
	public List<PromozioneStatoEntity> findByPromozioneID(Long promoId) throws Exception {
		return dao.findByPromozioneID(promoId);
	}
}
