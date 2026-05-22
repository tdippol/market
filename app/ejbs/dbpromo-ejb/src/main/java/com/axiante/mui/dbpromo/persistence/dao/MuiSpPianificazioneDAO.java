package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpPianificazioneEntity;

import java.math.BigDecimal;
import java.util.List;

public interface MuiSpPianificazioneDAO extends DbPromoDAO<MuiSpPianificazioneEntity> {

    List<MuiSpPianificazioneEntity> findByPromozioneAndTipo(Long idPromozione, String tipo);

    void updatePremioFinale(Long id, BigDecimal premioFinale);


    MuiSpPianificazioneEntity findByUniqueKey(Long idPromozione,
                                              Long idItem,
                                              Long idFornitore,
                                              String tipo);
}