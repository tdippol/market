package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreHistEntity;

import java.util.List;

public interface MuiSpContribCompratoreHistService extends DbPromoService<MuiSpContribCompratoreHistEntity> {

    List<MuiSpContribCompratoreHistEntity> findByPromozioneId(Long idPromozione);
}