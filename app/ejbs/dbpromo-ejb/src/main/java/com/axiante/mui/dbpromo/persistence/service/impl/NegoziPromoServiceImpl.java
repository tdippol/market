package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneNegozioDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.service.NegoziPromoService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Slf4j
@Dependent
@Transactional
public class NegoziPromoServiceImpl extends AbstractDbPromoService<PromozioneNegozioEntity> implements NegoziPromoService {
	private static final long serialVersionUID = -589816852389034266L;

	@Inject
	@Getter
	PromozioneNegozioDAO dao;

	@Override
	public PromozioneNegozioEntity savePromozioneNegozioEntity(PromozioneNegozioEntity promozioneNegozioEntity) {
		return dao.persist(promozioneNegozioEntity);
	}

	@Override
	public List<PromozioneNegozioEntity> findById(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return dao.findById(ids);
	}
}
