package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PrestazioniEntity;
import java.util.List;

public interface PrestazioniDAO extends DbPromoDAO<PrestazioniEntity> {
    String findDescrizioneByCodice(String codice);
    List<PrestazioniEntity> findByCodiceGruppo(String codiceGruppo);

}
