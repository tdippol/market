package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiFontEntitiesDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import com.axiante.mui.dbpromo.persistence.service.MuiFontEntitiesService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Default
@Transactional
public class MuiFontEntitiesServiceImpl implements MuiFontEntitiesService, Serializable {


    @Inject
    @Getter
    MuiFontEntitiesDAO dao;

    /**
     * @param id
     * @return
     */
    @Override
    public MuiFontEntities find(String id) {
        return getDao().findById(id);
    }

    /**
     * @return
     */
    @Override
    public List<MuiFontEntities> findAll() {
        return getDao().findAll();
    }
}
