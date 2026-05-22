package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.StatoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
@Transactional
public class StatoPromoServiceImpl extends AbstractDbPromoService<StatoPromozioneEntity> implements StatoPromoService {
	private static final long serialVersionUID = -6399383042741805115L;

	@Inject
	@Getter
	private StatoPromozioneDAO dao;

	@Override
	public List<StatoPromozioneEntity> findAllSorted() throws Exception {
		return dao.findAllSorted();
	}

	@Override
	public StatoPromozioneEntity findByCode(String code) throws Exception {
		return dao.findByCode(code);
	}
}
