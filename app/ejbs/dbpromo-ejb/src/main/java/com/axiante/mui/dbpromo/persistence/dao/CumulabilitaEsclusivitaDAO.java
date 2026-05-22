package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import com.axiante.mui.dbpromo.business.enumeration.OperazioneCumulabilita;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;

import java.util.List;

public interface CumulabilitaEsclusivitaDAO extends DbPromoDAO<CumulabilitaEsclusivitaEntity> {
    List<CumulabilitaEsclusivitaEntity> findByType(CumulabilitaType type);

    List<CumulabilitaEsclusivitaEntity> findOpenByType(CumulabilitaType type);

    List<CumulabilitaEsclusivitaEntity> findOverlapping(CumulabilitaEsclusivitaEntity entity);

    boolean exportCumulabilita(Long idCumulabilita, String codiceUtente);

    boolean updateCumulabilita(Long idCumulabilita, OperazioneCumulabilita operazione, String codiceUtente);
}
