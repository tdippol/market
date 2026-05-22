package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import java.util.List;

public interface CategoriaDAO extends DbPromoDAO<CategoriaEntity> {
    List<CategoriaEntity> findAllByCodiciCompratore(List<String> codiciCompratore);
}
