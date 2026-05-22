package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.TipoInclusioneEsclusione;
import com.axiante.mui.dbpromo.persistence.entities.MuiInclusioneEsclusioneEntity;

import java.util.List;

public interface MuiInclusioneEsclusioneService extends DbPromoService<MuiInclusioneEsclusioneEntity> {
    List<MuiInclusioneEsclusioneEntity> findByPromozione(Long idPromozione);

    List<MuiInclusioneEsclusioneEntity> findByPromozioneAndTipo(Long idPromozione, TipoInclusioneEsclusione tipo);

    Long countByPromozioneAndMeccanicaAndTipo(Long idPromozione, Long idMeccanica, TipoInclusioneEsclusione tipo);

    Long countByPromozioneAndMeccanicaAndTipoAndTipoElemento(Long idPromozione, Long idMeccanica, TipoInclusioneEsclusione tipo, ElementType tipoElemento);

    boolean runFunctionExportInclusioniEsclusioni(Long idPromozione, String username) throws Exception;
}
