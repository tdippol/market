package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaStatiConsentitiDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatiConsentitiEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaStatiConsentitiService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class PianoMediaStatiConsentitiServiceImpl extends AbstractDbPromoService<PianoMediaStatiConsentitiEntity>
		implements PianoMediaStatiConsentitiService {
	private static final long serialVersionUID = 1585762185013452853L;

	@Inject
	@Getter
	PianoMediaStatiConsentitiDAO dao;
}
