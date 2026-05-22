package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreEntity;

import java.util.List;

public interface MuiSpContribCompratoreDAO extends DbPromoDAO<MuiSpContribCompratoreEntity> {

    List<MuiSpContribCompratoreEntity> findByPromozioneId(Long idPromozione);

    MuiSpContribCompratoreEntity findByPromozioneIdAndCompratoreId(Long idPromozione, Long idCompratore);
}