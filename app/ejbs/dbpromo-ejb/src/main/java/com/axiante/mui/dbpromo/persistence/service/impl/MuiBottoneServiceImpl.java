package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiBottoneDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiBottoneService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Dependent
@Transactional
public class MuiBottoneServiceImpl implements MuiBottoneService, Serializable {
    private static final long serialVersionUID = 8418094325850513819L;

    @Getter
    @Inject
    private MuiBottoneDAO dao;

    @Override
    public List<MuiBottoneEntity> findAll() {
        return getDao().findAll();
    }

    @Override
    public MuiBottoneEntity findById(Long id) {
        return getDao().findById(id);
    }
}
