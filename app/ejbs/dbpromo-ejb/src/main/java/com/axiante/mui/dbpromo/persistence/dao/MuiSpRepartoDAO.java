package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpRepartoEntity;

import java.util.List;

public interface MuiSpRepartoDAO extends DbPromoDAO<MuiSpRepartoEntity> {

    List<MuiSpRepartoEntity> findByPromozioneId(Long idPromozione);

    MuiSpRepartoEntity findByPromozioneIdAndRepartoId(Long idPromozione, Long idReparto);

    MuiSpRepartoEntity update(MuiSpRepartoEntity entity);
}