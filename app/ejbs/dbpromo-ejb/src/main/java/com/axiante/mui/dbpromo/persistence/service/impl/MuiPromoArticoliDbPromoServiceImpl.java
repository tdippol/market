package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiPromoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiPromoArticoliDbPromoService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class MuiPromoArticoliDbPromoServiceImpl implements MuiPromoArticoliDbPromoService, Serializable {
    private static final long serialVersionUID = -7623900245257900504L;

    @Inject
    @Getter
    private MuiPromoArticoliDbPromoDAO dao;

    @Override
    public List<MuiPromoArticoliDbPromoEntity> findAll() {
        return dao.findAll();
    }

    @Override
    public List<MuiPromoArticoliDbPromoEntity> findAllByCodicePromozione(@NonNull String codicePromozione) {
        return dao.findAllByCodicePromozione(codicePromozione);
    }
}
