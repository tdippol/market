package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CausaliRetailDAO;
import com.axiante.mui.dbpromo.persistence.entities.CausaliRetailEntity;
import com.axiante.mui.dbpromo.persistence.service.CausaliRetailService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Dependent
@Transactional
public class CausaliRetailServiceImpl implements CausaliRetailService, Serializable {
    private static final long serialVersionUID = -1368073525539536984L;
    @Inject()
    CausaliRetailDAO dao;

    @Override
    public CausaliRetailEntity getCausaliRetail(String code) {
        return dao.getCausalRetail(code);
    }

    @Override
    public List<CausaliRetailEntity> getCausaliRetail() {
        return dao.getAllCausalRetail();
    }

    @Override
    public List<CausaliRetailEntity> getCausaliRetailByCode(String code) {
        return dao.getCausaleRetailByIdLike(code);
    }

    @Override
    public List<CausaliRetailEntity> getCausaliRetailByDescription(String description) {
        return dao.getCausaleRetailDescrizioneLike(description);
    }
}
