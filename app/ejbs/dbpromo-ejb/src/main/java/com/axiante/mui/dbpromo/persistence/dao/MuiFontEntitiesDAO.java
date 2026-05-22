package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import java.util.List;

public interface MuiFontEntitiesDAO {
    MuiFontEntities findById(String id);
    List<MuiFontEntities> findAll();
}
