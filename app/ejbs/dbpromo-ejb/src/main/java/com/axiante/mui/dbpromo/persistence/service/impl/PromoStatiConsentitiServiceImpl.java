package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromoStatiConsentitiDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiConsentitiEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoStatiConsentitiService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class PromoStatiConsentitiServiceImpl extends AbstractDbPromoService<PromoStatiConsentitiEntity>
		implements PromoStatiConsentitiService {
	private static final long serialVersionUID = -4119860461376634103L;

	@Inject
	@Getter
	PromoStatiConsentitiDAO dao;
}
