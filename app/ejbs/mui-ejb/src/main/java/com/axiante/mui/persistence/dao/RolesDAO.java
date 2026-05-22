package com.axiante.mui.persistence.dao;

import java.util.List;

import com.axiante.mui.persistence.entity.RolesEntity;

public interface RolesDAO {
    List<RolesEntity> readAll();
    RolesEntity persist(RolesEntity entity) throws Exception;
    void remove(RolesEntity entity) throws Exception;
    RolesEntity findById(Integer id);
    void resetAllExceptions();
}

