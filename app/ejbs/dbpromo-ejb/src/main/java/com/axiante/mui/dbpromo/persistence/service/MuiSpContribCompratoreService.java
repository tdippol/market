package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreEntity;

import java.util.List;
import java.util.Map;

public interface MuiSpContribCompratoreService extends DbPromoService<MuiSpContribCompratoreEntity> {

    List<MuiSpContribCompratoreEntity> findByPromozioneId(Long idPromozione);

    MuiSpContribCompratoreEntity findByPromozioneIdAndCompratoreId(Long idPromozione, Long idCompratore);

    Integer toggleContribuzione(Long idPromozione, Long idCompratore, String username);

    Map<Long, Integer> findContribuzioneByCompratore(Long idPromozione);
}