package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatiTransizioneEntity;

import java.util.List;

public interface MuiSpStatiTransizioneService extends DbPromoService<MuiSpStatiTransizioneEntity> {


    List<MuiSpStatiTransizioneEntity> findByPromozioneAndFromStato(Long idPromozione, Long idStato);

}