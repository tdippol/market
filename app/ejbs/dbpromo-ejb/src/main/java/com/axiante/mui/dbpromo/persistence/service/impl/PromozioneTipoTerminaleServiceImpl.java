package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneTipoTerminaleDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTipoTerminaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoTerminaleCassaEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTipoTerminaleService;
import javax.inject.Inject;
import lombok.Getter;

public class PromozioneTipoTerminaleServiceImpl extends AbstractDbPromoService<PromozioneTipoTerminaleEntity>
		implements PromozioneTipoTerminaleService {

	private static final long serialVersionUID = -7573387486459456258L;

	@Inject
	@Getter
	PromozioneTipoTerminaleDAO dao;

	@Override
	public PromozioneTipoTerminaleEntity findByIdTestataIdTerminale(PromozioneTestataEntity promozioneTestataEntity,
			TipoTerminaleCassaEntity tipoTerminaleCassaEntity) {
		return dao.findByTestataAndCassa(promozioneTestataEntity, tipoTerminaleCassaEntity);
	}

}
