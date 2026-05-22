package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatiTransizioneEntity;

import java.util.List;

public interface MuiSpStatiTransizioneDAO extends DbPromoDAO<MuiSpStatiTransizioneEntity> {

    List<MuiSpStatiTransizioneEntity> findByPromozioneAndFromStato(Long idPromozione, Long idStato);
}