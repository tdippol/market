package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoArticoliDbPromoEntity;
import java.util.List;

public interface MuiPlanoArticoliDbPromoDAO {
    List<MuiPlanoArticoliDbPromoEntity> findAll();
    List<MuiPlanoArticoliDbPromoEntity> findAllByIdPlano(String idPlano);
    List<MuiPlanoArticoliDbPromoEntity> findAllByIdPlano(List<String> idPlano);
}
