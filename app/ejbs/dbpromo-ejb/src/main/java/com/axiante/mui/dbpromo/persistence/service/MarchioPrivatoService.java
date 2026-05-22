package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MarchioPrivatoEntity;
import java.util.List;

public interface MarchioPrivatoService extends DbPromoService<MarchioPrivatoEntity>{
    List<MarchioPrivatoEntity> findAll();

    MarchioPrivatoEntity findByCodice(String codice);

    List<MarchioPrivatoEntity> findByCodici(List<String> codici);
}
