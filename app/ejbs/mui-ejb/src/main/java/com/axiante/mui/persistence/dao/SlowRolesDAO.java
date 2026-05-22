package com.axiante.mui.persistence.dao;

import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.SlowRolesEntity;

import java.util.List;

public interface SlowRolesDAO {
    SlowRolesEntity persist(SlowRolesEntity entity) throws Exception;
    void remove(SlowRolesEntity entity) throws Exception;
    SlowRolesEntity findById(Integer id);
    void resetAllExceptions();

}

