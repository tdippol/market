package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgMeccanicaMissioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgMeccanicaMissioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgMeccanicaMissioneService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Dependent
@Transactional
public class CfgMeccanicaMissioneServiceImpl implements CfgMeccanicaMissioneService, Serializable {
    private static final long serialVersionUID = 7579744401904859743L;

    @Inject
    @Getter
    CfgMeccanicaMissioneDAO dao;

    @Override
    public List<CfgMeccanicaMissioneEntity> findAll() {
        return dao.findAll();
    }
}
