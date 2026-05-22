package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.SottoscrizioneEntity;

import java.util.List;

public interface SottoscrizioneDAO extends DbPromoDAO<SottoscrizioneEntity> {
    List<SottoscrizioneEntity> findAll();
}
