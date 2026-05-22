package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.GrmDAO;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.service.GrmService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class GrmServiceImpl extends AbstractDbPromoService<GrmEntity> implements GrmService {

	@Inject
	@Getter
	private GrmDAO dao;

	@Override
	public GrmEntity findByCode(String code) {
		return getDao().findByCode(code);
	}
}
