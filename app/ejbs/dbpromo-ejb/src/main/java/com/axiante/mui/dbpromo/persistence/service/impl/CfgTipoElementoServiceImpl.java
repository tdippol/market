package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgTipoElementoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgTipoElementoService;
import lombok.Getter;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Default
@Transactional

public class CfgTipoElementoServiceImpl extends AbstractDbPromoService<CfgTipoElementoEntity>
		implements CfgTipoElementoService {
	private static final long serialVersionUID = -1241646022430730044L;

	@Inject
	@Getter
	CfgTipoElementoDAO dao;
}
