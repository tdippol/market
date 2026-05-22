package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.ContributiStatoAnagraficaEntity;

public interface StatoContributiService extends DbPromoService<ContributiStatoAnagraficaEntity> {
    String findDescrizioneByCodice(String codice);
}
