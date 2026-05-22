package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneCostiContattoEntity;

public interface PromozioneCostiContattoService extends DbPromoService<PromozioneCostiContattoEntity> {
    PromozioneCostiContattoEntity findByIdPromozione(Long idPromozione);
}
