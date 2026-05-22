package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiSottoclasseDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSottoclasseEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSottoclasseService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Dependent
@Transactional
public class MuiSottoclasseServiceImpl implements MuiSottoclasseService, Serializable {
    private static final long serialVersionUID = 3214817190712584127L;
    
    @Inject
    @Getter
    private MuiSottoclasseDAO dao;

    @Override
    public MuiSottoclasseEntity findByCode(String code) {
        return getDao().findByCode(code);
    }

    @Override
    public MuiSottoclasseEntity findActiveByCode(String code) {
        return getDao().findActiveByCode(code);
    }

    @Override
    public MuiSottoclasseEntity findById(@NonNull String id) {
        return getDao().findById(id);
    }

    @Override
    public List<MuiSottoclasseEntity> findAll() {
        return getDao().findAll();
    }

    @Override
    public List<MuiSottoclasseEntity> findAllAttive() {
        return getDao().findAllAttive();
    }
}
