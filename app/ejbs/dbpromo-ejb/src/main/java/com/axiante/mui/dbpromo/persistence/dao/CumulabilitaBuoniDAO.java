package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaBuoniEntity;

public interface CumulabilitaBuoniDAO {
    CumulabilitaBuoniEntity findByCodicePromozioneAndCodiceCanaleAndPrefissoBs(String codicePromozione, String codiceCanale, String prefissoBS);
}
