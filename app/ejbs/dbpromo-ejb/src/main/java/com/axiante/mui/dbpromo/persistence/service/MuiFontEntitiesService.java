package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.dao.MuiFontEntitiesDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import java.util.List;

public interface MuiFontEntitiesService {
    MuiFontEntitiesDAO getDao();

    MuiFontEntities find(String id);

    List<MuiFontEntities> findAll();

}
