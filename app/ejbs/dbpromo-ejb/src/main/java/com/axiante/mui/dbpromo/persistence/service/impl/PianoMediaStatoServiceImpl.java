package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaStatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaStatoService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class PianoMediaStatoServiceImpl extends AbstractDbPromoService<PianoMediaStatoEntity>
		implements PianoMediaStatoService {
	private static final long serialVersionUID = 7692254476399645741L;

	@Inject
	@Getter
	PianoMediaStatoDAO dao;
}
