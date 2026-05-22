package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiFontStileDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiFontStileService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Dependent
@Transactional
public class MuiFontStileServiceImpl implements MuiFontStileService, Serializable {

    private static final long serialVersionUID = 1372790578492091445L;
    @Getter
    @Inject
    private MuiFontStileDAO dao;

    @Override
    public List<MuiFontStileEntity> findAll() {
        return getDao().findAll();
    }

    @Override
    public MuiFontStileEntity findById(@NonNull String id) {
        return dao.findById(id);
    }
}
