package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.ValorePuntoEntity;
import java.util.Date;
import java.util.List;

public interface ValorePuntoDAO extends DbPromoDAO<ValorePuntoEntity> {
    /**
     * Ritorno lista di entity le cui date di inizio e fine si incrociano con la data impostata
     *
     * @param date data impostata
     * @return lista di entity
     */
    List<ValorePuntoEntity> findWhereDate(Date date);
}
