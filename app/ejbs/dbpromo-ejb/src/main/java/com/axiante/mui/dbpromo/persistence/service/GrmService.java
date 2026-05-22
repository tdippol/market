package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;

public interface GrmService extends DbPromoService<GrmEntity> {

	GrmEntity findByCode(String code);
}
