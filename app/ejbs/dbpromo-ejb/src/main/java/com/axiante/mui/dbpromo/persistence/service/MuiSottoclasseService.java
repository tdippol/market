package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiSottoclasseEntity;

import java.util.List;

public interface MuiSottoclasseService {
    MuiSottoclasseEntity findByCode(String code);

    MuiSottoclasseEntity findActiveByCode(String code);

    MuiSottoclasseEntity findById(String id);

    List<MuiSottoclasseEntity> findAll();

    List<MuiSottoclasseEntity> findAllAttive();
}
