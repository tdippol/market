package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneDefaultEntity;

import java.util.List;

public interface PromoFatturazioneDefaultDAO extends DbPromoDAO<PromoFatturazioneDefaultEntity> {
    List<PromoFatturazioneDefaultEntity> findAllByIdsCompratori(List<Long> idsCompratori);
}
