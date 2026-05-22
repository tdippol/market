package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import java.util.List;

public interface GruppoPromozioneDAO extends DbPromoDAO<GruppoPromozioneEntity> {
    List<GruppoPromozioneEntity> findAllByCodiciCanale(List<Long> codiciCanale);
}
