package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.VMuiSpRepartoCompratoreDAO;
import com.axiante.mui.dbpromo.persistence.entities.VMuiSpRepartoCompratoreEntity;
import com.axiante.mui.dbpromo.persistence.service.VMuiSpRepartoCompratoreService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
@Transactional
public class VMuiSpRepartoCompratoreServiceImpl extends AbstractDbPromoService<VMuiSpRepartoCompratoreEntity> implements VMuiSpRepartoCompratoreService {

    private static final long serialVersionUID = 1L;

    @Inject
    @Getter
    private VMuiSpRepartoCompratoreDAO dao;

    @Override
    public List<VMuiSpRepartoCompratoreEntity> findAllOrdered() {
        return getDao().findAllOrdered();
    }
}