package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.TipoPromoRifatturazioneEntity;

import java.util.List;

public interface TipoPromoRifatturazioneDAO {
    List<TipoPromoRifatturazioneEntity> findAll();
}
