package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneDefaultEntity;

import java.io.Serializable;
import java.util.List;

public interface PromoFatturazioneDefaultService extends Serializable, DbPromoService<PromoFatturazioneDefaultEntity> {
    List<PromoFatturazioneDefaultEntity> findAllByIdsCompratori(List<Long> idsCompratori);
}
