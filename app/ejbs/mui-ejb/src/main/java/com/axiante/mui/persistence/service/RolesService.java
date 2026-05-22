package com.axiante.mui.persistence.service;

import com.axiante.mui.persistence.entity.SlowRolesEntity;

import java.io.Serializable;
import java.util.List;

public interface RolesService extends Serializable {
    SlowRolesEntity persist(SlowRolesEntity entity) throws Exception;
    void remove(SlowRolesEntity entity) throws Exception;
    SlowRolesEntity findById(Integer id);
    void resetAllExceptions();

}
