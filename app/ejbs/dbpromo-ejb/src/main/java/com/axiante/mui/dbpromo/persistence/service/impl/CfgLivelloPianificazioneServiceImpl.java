package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgLivelloPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgLivelloPianificazioneService;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Default
@Transactional
public class CfgLivelloPianificazioneServiceImpl extends AbstractDbPromoService<CfgLivelloPianificazioneEntity>
		implements CfgLivelloPianificazioneService {
	private static final long serialVersionUID = 517209098241194496L;

	@Inject
	@Getter
	CfgLivelloPianificazioneDAO dao;

	@Override
	public CfgLivelloPianificazioneEntity findByDescription(@NonNull final String description) {
		return dao.findByDescription(description);
	}
}
