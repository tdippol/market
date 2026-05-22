package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;

public interface CfgCanaleDispositivoDAO extends DbPromoDAO<CfgCanaleDispositivoEntity> {
    CfgCanaleDispositivoEntity findByCodice(String codice);
}
