package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.SottoscrizioneEntity;

import java.util.List;

public interface SottoscrizioneService extends DbPromoService<SottoscrizioneEntity> {
    List<SottoscrizioneEntity> findAll();
}
