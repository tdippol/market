package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiSottoclasseEntity;

import java.util.List;

public interface MuiSottoclasseDAO  {
    MuiSottoclasseEntity findById(String id);

    MuiSottoclasseEntity findByCode(String codice);

    MuiSottoclasseEntity findActiveByCode(String codice);

    List<MuiSottoclasseEntity> findAll() ;

    List<MuiSottoclasseEntity> findAllAttive();
}
