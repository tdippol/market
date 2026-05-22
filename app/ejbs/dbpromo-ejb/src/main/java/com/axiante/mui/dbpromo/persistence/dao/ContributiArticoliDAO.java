package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoArticoloEntity;
import java.util.List;

public interface ContributiArticoliDAO extends DbPromoDAO<ContributiPromoArticoloEntity> {
    List<ContributiPromoArticoloEntity> findAllByIds(List<Long> ids);
    List<ContributiPromoArticoloEntity> findAllByIdRata(Long idRata);
}
