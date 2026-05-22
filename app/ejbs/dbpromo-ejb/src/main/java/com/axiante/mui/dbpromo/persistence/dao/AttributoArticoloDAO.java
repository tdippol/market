package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.AttributoArticoloEntity;
import java.util.List;

public interface AttributoArticoloDAO extends DbPromoDAO<AttributoArticoloEntity> {
    List<AttributoArticoloEntity> findAllActive();
}
