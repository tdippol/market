package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MarchioPrivatoEntity;
import java.util.List;

public interface MarchioPrivatoDAO extends DbPromoDAO<MarchioPrivatoEntity> {
    List<MarchioPrivatoEntity> findAll();

    MarchioPrivatoEntity findByCodice(String codice);

    List<MarchioPrivatoEntity> findByCodici(List<String> codici);
}
