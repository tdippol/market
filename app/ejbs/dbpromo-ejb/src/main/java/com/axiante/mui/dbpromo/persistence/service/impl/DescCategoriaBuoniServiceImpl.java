package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.DescCategoriaBuoniDAO;
import com.axiante.mui.dbpromo.persistence.entities.DescCategoriaBuoniEntity;
import com.axiante.mui.dbpromo.persistence.service.DescCategoriaBuoniService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class DescCategoriaBuoniServiceImpl extends AbstractDbPromoService<DescCategoriaBuoniEntity>
        implements DescCategoriaBuoniService {
    private static final long serialVersionUID = -911427840071029268L;

    @Inject
    @Getter
    DescCategoriaBuoniDAO dao;

    @Override
    public DescCategoriaBuoniEntity findByIdPromozione(Long idPromozione) {
        return getDao().findByIdPromozione(idPromozione);
    }
}
