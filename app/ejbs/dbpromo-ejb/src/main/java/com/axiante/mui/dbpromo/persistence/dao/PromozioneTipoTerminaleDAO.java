package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTipoTerminaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoTerminaleCassaEntity;

public interface PromozioneTipoTerminaleDAO extends DbPromoDAO<PromozioneTipoTerminaleEntity>{

	PromozioneTipoTerminaleEntity findByTestataAndCassa(PromozioneTestataEntity testata,
														TipoTerminaleCassaEntity cassa);
}