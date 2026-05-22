package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import java.util.List;

public interface RepartoDAO extends DbPromoDAO<RepartoEntity> {

    RepartoEntity findByCode(String codiceReparto);

    List<RepartoEntity> findAllOrderedBy();

    List<RepartoEntity> findAllByCodiciCompratore(List<String> codiciCompratore);
}
