package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.ContributiStatoAnagraficaEntity;

public interface StatoContributiDAO extends DbPromoDAO<ContributiStatoAnagraficaEntity> {
    String findDescrizioneByCodice(String codice);
}
