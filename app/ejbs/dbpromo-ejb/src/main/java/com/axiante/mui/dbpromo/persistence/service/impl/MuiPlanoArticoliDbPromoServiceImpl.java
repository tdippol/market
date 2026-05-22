package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiPlanoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiPlanoArticoliDbPromoService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class MuiPlanoArticoliDbPromoServiceImpl implements MuiPlanoArticoliDbPromoService, Serializable {
    private static final long serialVersionUID = 1400965815803781379L;

    @Inject
    @Getter
    private MuiPlanoArticoliDbPromoDAO dao;

    @Override
    public List<MuiPlanoArticoliDbPromoEntity> findAll() {
        return dao.findAll();
    }

    @Override
    public List<MuiPlanoArticoliDbPromoEntity> findAllByIdPlano(@NonNull String idPlano) {
        return dao.findAllByIdPlano(idPlano);
    }
    
    @Override
    public List<MuiPlanoArticoliDbPromoEntity> findAllByIdPlano(List<String> planos) {
        return dao.findAllByIdPlano(planos);
    }
}
