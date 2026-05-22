package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiPromoArticoliDbPromoEntity;
import java.util.List;

public interface MuiPromoArticoliDbPromoService {
    List<MuiPromoArticoliDbPromoEntity> findAll();
    List<MuiPromoArticoliDbPromoEntity> findAllByCodicePromozione(String codicePromozione);
}
