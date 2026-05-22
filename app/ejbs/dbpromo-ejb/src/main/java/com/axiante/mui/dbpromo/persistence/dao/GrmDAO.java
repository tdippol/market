package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import java.util.List;

public interface GrmDAO extends DbPromoDAO<GrmEntity> {

    GrmEntity findById(Long id);

    GrmEntity findByCode(String code);

    List<GrmEntity> findAllOrderedBy();

    List<GrmEntity> findAllByCodiciCompratore(List<String> codiciCompratore);
}
