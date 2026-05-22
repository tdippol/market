package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.TipoInclusioneEsclusione;
import com.axiante.mui.dbpromo.persistence.entities.MuiInclusioneEsclusioneEntity;

import java.util.List;

public interface MuiInclusioneEsclusioneDAO extends DbPromoDAO<MuiInclusioneEsclusioneEntity> {
    List<MuiInclusioneEsclusioneEntity> findByPromozione(Long idPromozione);

    List<MuiInclusioneEsclusioneEntity> findByPromozioneAndTipo(Long idPromozione, TipoInclusioneEsclusione tipo);

    Long countByPromozioneAndMeccanicaAndTipo(Long idPromozione, Long idMeccanica, TipoInclusioneEsclusione tipo);

    Long countByPromozioneAndMeccanicaAndTipoAndTipoElemento(Long idPromozione, Long idMeccanica, TipoInclusioneEsclusione tipo, ElementType tipoElemento);

    boolean runFunctionExportInclusioniEsclusioni(Long idPromozione, String username) throws Exception;
}
