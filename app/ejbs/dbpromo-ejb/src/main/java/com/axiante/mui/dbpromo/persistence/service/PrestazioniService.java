package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PrestazioniEntity;
import java.util.List;

public interface PrestazioniService extends DbPromoService<PrestazioniEntity> {
    String findDescrizioneByCodice(String codice);

    List<PrestazioniEntity> findByCodiceGruppo(String codiceGruppo);
}
