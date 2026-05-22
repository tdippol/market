package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.RepartoDAO;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.RepartoService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class RepartoServiceImpl extends AbstractDbPromoService<RepartoEntity> implements RepartoService {

	@Inject
	@Getter
	private RepartoDAO dao;

	@Override
	public RepartoEntity findByCode(String code) {
		return getDao().findByCode(code);
	}
}
