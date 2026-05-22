package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.AttributoArticoloEntity;
import java.util.List;

public interface AttributoArticoloService extends DbPromoService<AttributoArticoloEntity> {
    List<AttributoArticoloEntity> findAllActive();
}
