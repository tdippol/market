package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiPromoArticoliDbPromoEntity;
import java.util.List;

public interface MuiPromoArticoliDbPromoDAO {
    List<MuiPromoArticoliDbPromoEntity> findAll();
    List<MuiPromoArticoliDbPromoEntity> findAllByCodicePromozione(String codicePromozione);
}
