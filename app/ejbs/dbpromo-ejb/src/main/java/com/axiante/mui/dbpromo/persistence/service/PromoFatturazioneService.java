package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneEntity;

import java.io.Serializable;
import java.util.List;

public interface PromoFatturazioneService extends Serializable, DbPromoService<PromoFatturazioneEntity> {
    List<PromoFatturazioneEntity> findAllByIdsCompratori(List<Long> idsCompratori);

    List<PromoFatturazioneEntity> findAllByCodiciCompratori(List<String> idsCompratori);

    List<PromoFatturazioneEntity> findAllByIdCompratoreAndIdPromozione(Long idCompratore, Long idPromozione);
}
