package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.AssortimentoFornitoreDAO;
import com.axiante.mui.dbpromo.persistence.entities.AssortimentoFornitoreEntity;
import com.axiante.mui.dbpromo.persistence.service.AssortimentoFornitoreService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional

public class AssortimentoFornitoreServiceImpl extends AbstractDbPromoService<AssortimentoFornitoreEntity>
		implements AssortimentoFornitoreService {
	@Inject
	@Getter
	AssortimentoFornitoreDAO dao;
}
