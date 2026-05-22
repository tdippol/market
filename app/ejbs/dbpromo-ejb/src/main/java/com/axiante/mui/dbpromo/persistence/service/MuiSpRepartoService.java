package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpRepartoEntity;

import java.util.List;

public interface MuiSpRepartoService extends DbPromoService<MuiSpRepartoEntity> {

    List<MuiSpRepartoEntity> findByPromozioneId(Long idPromozione);

    MuiSpRepartoEntity findByPromozioneIdAndRepartoId(Long idPromozione, Long idReparto);

    MuiSpRepartoEntity update(MuiSpRepartoEntity entity);
}