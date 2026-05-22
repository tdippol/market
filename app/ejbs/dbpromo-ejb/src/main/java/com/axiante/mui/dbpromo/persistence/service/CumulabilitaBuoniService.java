package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaBuoniEntity;

public interface CumulabilitaBuoniService {
    CumulabilitaBuoniEntity findByCodicePromozioneAndCodiceCanaleAndPrefissoBs(String codicePromozione, String codiceCanale, String prefissoBS);
}
