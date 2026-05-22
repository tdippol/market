package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoArticoliDbPromoEntity;
import java.util.List;

public interface MuiPlanoArticoliDbPromoService {
    List<MuiPlanoArticoliDbPromoEntity> findAll();
    List<MuiPlanoArticoliDbPromoEntity> findAllByIdPlano(String idPlano);
    List<MuiPlanoArticoliDbPromoEntity> findAllByIdPlano(List<String> planos);
    
    
}
