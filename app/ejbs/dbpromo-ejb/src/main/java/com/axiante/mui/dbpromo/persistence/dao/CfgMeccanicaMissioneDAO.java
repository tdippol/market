package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgMeccanicaMissioneEntity;

import java.util.List;

public interface CfgMeccanicaMissioneDAO extends DbPromoDAO<CfgMeccanicaMissioneEntity> {
    List<CfgMeccanicaMissioneEntity> findAll();
}
