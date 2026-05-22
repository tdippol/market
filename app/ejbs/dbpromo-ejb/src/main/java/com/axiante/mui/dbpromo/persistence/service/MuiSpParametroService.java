package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpParametroEntity;

import java.util.List;

public interface MuiSpParametroService extends DbPromoService<MuiSpParametroEntity> {

    List<MuiSpParametroEntity> findByPromozioneId(Long idPromozione);

    MuiSpParametroEntity findByPromozioneIdAndCompratoreAndFornitore(Long idPromozione,
                                                                     Long idCompratore,
                                                                     Long idFornitore);

    void saveOrUpdateParametro(MuiSpParametroEntity parametro, String username);
}