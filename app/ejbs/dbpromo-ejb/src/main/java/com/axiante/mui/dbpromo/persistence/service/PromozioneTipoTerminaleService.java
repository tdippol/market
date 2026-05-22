package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTipoTerminaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoTerminaleCassaEntity;

public interface PromozioneTipoTerminaleService extends DbPromoService<PromozioneTipoTerminaleEntity> {

	PromozioneTipoTerminaleEntity findByIdTestataIdTerminale(PromozioneTestataEntity promozioneTestataEntity,
			TipoTerminaleCassaEntity tipoTerminaleCassaEntity);
}
