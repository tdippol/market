package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpPianificazioneEntity;

import java.util.List;

public interface MuiSpPianificazioneService extends DbPromoService<MuiSpPianificazioneEntity> {

    List<MuiSpPianificazioneEntity> findByPromozioneAndTipo(Long idPromozione, String tipo);

    void saveOrUpdate(MuiSpPianificazioneEntity entity, String username);

    void refreshPremiFinaliParametro(Long idPromozione,
                                     Long idCompratore,
                                     Long idFornitore,
                                     String username);

}