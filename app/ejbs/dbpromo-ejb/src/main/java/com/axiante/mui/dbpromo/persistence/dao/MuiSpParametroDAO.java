package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpParametroEntity;

import java.util.List;

public interface MuiSpParametroDAO extends DbPromoDAO<MuiSpParametroEntity> {

    List<MuiSpParametroEntity> findByPromozioneId(Long idPromozione);

    MuiSpParametroEntity findByPromozioneIdAndCompratoreAndFornitore(Long idPromozione,
                                                                     Long idCompratore,
                                                                     Long idFornitore);
}