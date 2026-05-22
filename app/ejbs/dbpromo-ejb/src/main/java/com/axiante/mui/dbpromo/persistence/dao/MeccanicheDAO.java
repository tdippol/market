package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;

public interface MeccanicheDAO extends DbPromoDAO<MeccanicheEntity> {
    MeccanicheEntity findByCodice(String codice);
}
