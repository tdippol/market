package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.ObiettivoEntity;

import java.util.List;

public interface ObiettivoDAO extends DbPromoDAO<ObiettivoEntity> {
    List<ObiettivoEntity> findAllByPromozione(Long idPromozione);
}
