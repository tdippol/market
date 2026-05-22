package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgParametriDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgParametriEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgParametriService;
import lombok.Getter;

import javax.inject.Inject;

public class CfgParametriServiceImpl extends AbstractDbPromoService<CfgParametriEntity> implements CfgParametriService {
	private static final long serialVersionUID = -6055077244112743528L;

	@Inject
	@Getter
	CfgParametriDAO dao;
}
