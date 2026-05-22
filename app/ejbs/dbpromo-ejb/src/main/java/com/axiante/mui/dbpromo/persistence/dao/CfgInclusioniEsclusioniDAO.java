package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.persistence.entities.CfgInclusioniEsclusioniEntity;

import java.util.List;

public interface CfgInclusioniEsclusioniDAO extends DbPromoDAO<CfgInclusioniEsclusioniEntity> {
    List<Long> findAllIdMeccanica();

    CfgInclusioniEsclusioniEntity findByMeccanicaAndTipoElemento(Long idMeccanica, ElementType tipoElemento);

    List<CfgInclusioniEsclusioniEntity> findByMeccanica(Long idMeccanica);
}
