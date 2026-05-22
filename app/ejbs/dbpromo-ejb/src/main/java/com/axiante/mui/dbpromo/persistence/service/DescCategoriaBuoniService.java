package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.DescCategoriaBuoniEntity;

public interface DescCategoriaBuoniService extends DbPromoService<DescCategoriaBuoniEntity> {
    DescCategoriaBuoniEntity findByIdPromozione(Long idPromozione);
}
