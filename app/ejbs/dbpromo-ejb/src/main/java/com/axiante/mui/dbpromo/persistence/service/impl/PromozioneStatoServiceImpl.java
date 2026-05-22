package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneStatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozioneStatoService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class PromozioneStatoServiceImpl extends AbstractDbPromoService<PromozioneStatoEntity>
		implements PromozioneStatoService {
	private static final long serialVersionUID = 9203263377779269743L;

	@Inject
	@Getter
	PromozioneStatoDAO dao;
}
