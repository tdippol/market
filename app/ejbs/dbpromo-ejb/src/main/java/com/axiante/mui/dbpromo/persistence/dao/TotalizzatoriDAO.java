package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.TotalizzatoriEntity;
import java.util.List;

public interface TotalizzatoriDAO extends DbPromoDAO<TotalizzatoriEntity> {
    List<TotalizzatoriEntity> findAllWithParentByIdIniziativa(Long idIniziativa);
}
