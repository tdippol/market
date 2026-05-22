package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;

public interface MeccanicaService extends DbPromoService<MeccanicheEntity> {
    MeccanicheEntity findByCodice(String codice);
}
