package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCheckDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgCheckEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCheckService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class CfgCheckServiceImpl extends AbstractDbPromoService<CfgCheckEntity> implements CfgCheckService{
	private static final long serialVersionUID = -4164860554006479353L;

	@Inject
	@Getter
	private CfgCheckDAO dao;
}
