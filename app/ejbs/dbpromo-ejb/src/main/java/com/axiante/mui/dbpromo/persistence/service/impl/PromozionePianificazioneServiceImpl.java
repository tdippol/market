package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozionePianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozionePianificazioneService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class PromozionePianificazioneServiceImpl extends AbstractDbPromoService<PromozionePianificazioneEntity>
		implements PromozionePianificazioneService {
	private static final long serialVersionUID = 7253535390125531122L;

	@Inject
	@Getter
	PromozionePianificazioneDAO dao;
}
