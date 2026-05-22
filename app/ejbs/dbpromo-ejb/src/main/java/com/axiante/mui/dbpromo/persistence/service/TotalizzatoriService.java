package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.TotalizzatoriEntity;
import java.util.List;

public interface TotalizzatoriService extends DbPromoService<TotalizzatoriEntity> {
    List<TotalizzatoriEntity> findAllWithParentByIdIniziativa(Long idIniziativa);
}
