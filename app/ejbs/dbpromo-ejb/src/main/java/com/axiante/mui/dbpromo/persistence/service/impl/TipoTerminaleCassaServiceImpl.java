package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.TipoTerminaleCassaDAO;
import com.axiante.mui.dbpromo.persistence.entities.TipoTerminaleCassaEntity;
import com.axiante.mui.dbpromo.persistence.service.TipoTerminaleCassaService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class TipoTerminaleCassaServiceImpl extends AbstractDbPromoService<TipoTerminaleCassaEntity>
		implements TipoTerminaleCassaService {
	private static final long serialVersionUID = 8785547561246258108L;

	@Inject
	@Getter
	TipoTerminaleCassaDAO dao;
}
