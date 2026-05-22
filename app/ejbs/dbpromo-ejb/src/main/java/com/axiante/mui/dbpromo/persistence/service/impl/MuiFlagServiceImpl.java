package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiFalgDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFlagEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiFlagService;
import lombok.Getter;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Default
@Transactional
public class MuiFlagServiceImpl extends AbstractDbPromoService<MuiFlagEntity> implements MuiFlagService {
    private static final long serialVersionUID = -6605807664104119124L;

    @Inject
    @Getter
    MuiFalgDAO dao;
}
