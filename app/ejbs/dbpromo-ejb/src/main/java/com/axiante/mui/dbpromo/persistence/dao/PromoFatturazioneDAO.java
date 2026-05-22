package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneEntity;

import java.util.List;

public interface PromoFatturazioneDAO extends DbPromoDAO<PromoFatturazioneEntity> {
    List<PromoFatturazioneEntity> findAllByIdsCompratori(List<Long> idsCompratori);

    List<PromoFatturazioneEntity> findAllByCodiciCompratori(List<String> codiciCompratori);

    List<PromoFatturazioneEntity> findAllByIdCompratoreAndIdPromozione(Long idCompratore, Long idPromozione);
}
