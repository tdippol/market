package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.DescCategoriaBuoniEntity;

public interface DescCategoriaBuoniDAO extends DbPromoDAO<DescCategoriaBuoniEntity> {
    DescCategoriaBuoniEntity findByIdPromozione(Long idPromozione);
}
