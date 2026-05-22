package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;

public interface RepartoService extends DbPromoService<RepartoEntity> {

	RepartoEntity findByCode(String code);
}
