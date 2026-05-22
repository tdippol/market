package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;

import java.util.List;

public interface MuiBottoneDAO extends DbPromoDAO<MuiBottoneEntity> {
    List<MuiBottoneEntity> findAll();

    MuiBottoneEntity findById(Long id);
}
