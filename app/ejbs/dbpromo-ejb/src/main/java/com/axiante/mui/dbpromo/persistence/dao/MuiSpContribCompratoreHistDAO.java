package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreHistEntity;

import java.util.List;

public interface MuiSpContribCompratoreHistDAO extends DbPromoDAO<MuiSpContribCompratoreHistEntity> {

    List<MuiSpContribCompratoreHistEntity> findByPromozioneId(Long idPromozione);
}