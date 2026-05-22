package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;

public interface CfgCanaleDispositivoService extends DbPromoService<CfgCanaleDispositivoEntity> {
    CfgCanaleDispositivoEntity findByCode(String code);
}
