package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneCostiContattoEntity;

public interface PromozioneCostiContattoDAO extends DbPromoDAO<PromozioneCostiContattoEntity> {
    PromozioneCostiContattoEntity findByIdPromozione(Long idPromozione);
}
