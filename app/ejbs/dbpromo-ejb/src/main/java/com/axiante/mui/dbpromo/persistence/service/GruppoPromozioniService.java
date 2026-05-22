package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import java.util.List;

public interface GruppoPromozioniService extends DbPromoService<GruppoPromozioneEntity> {
    List<GruppoPromozioneEntity> findAllByCodiciCanale(List<Long> codiciCanale);
}
