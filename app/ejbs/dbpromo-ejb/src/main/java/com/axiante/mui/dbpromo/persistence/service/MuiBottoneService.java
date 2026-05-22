package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;

import java.util.List;

public interface MuiBottoneService {
    List<MuiBottoneEntity> findAll();

    MuiBottoneEntity findById(Long id);
}
