package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiSpStatiConsentitiDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatiConsentitiEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSpStatiConsentitiService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class MuiSpStatiConsentitiServiceImpl
        extends AbstractDbPromoService<MuiSpStatiConsentitiEntity>
        implements MuiSpStatiConsentitiService {

    private static final long serialVersionUID = 1L;

    @Inject
    @Getter
    private MuiSpStatiConsentitiDAO dao;
}