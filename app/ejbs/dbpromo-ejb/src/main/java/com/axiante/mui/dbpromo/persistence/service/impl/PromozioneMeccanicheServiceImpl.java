package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneMeccanicheDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozioneMeccanicheService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class PromozioneMeccanicheServiceImpl extends AbstractDbPromoService<PromozioneMeccanicheEntity>
		implements PromozioneMeccanicheService {
	private static final long serialVersionUID = 2913109273626511723L;

	@Inject
	@Getter
	PromozioneMeccanicheDAO dao;
}
