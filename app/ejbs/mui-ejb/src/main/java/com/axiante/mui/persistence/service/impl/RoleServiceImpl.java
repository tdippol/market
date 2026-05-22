package com.axiante.mui.persistence.service.impl;

import com.axiante.mui.persistence.dao.SlowRolesDAO;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.SlowRolesEntity;
import com.axiante.mui.persistence.service.RolesService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Slf4j
@Dependent
public class RoleServiceImpl implements RolesService {
    @Inject
    SlowRolesDAO dao;


    public SlowRolesEntity persist(SlowRolesEntity entity) throws Exception{
        return dao.persist(entity);
    }
    public void remove(SlowRolesEntity entity) throws Exception{
        dao.remove(entity);
    }

    public SlowRolesEntity findById(Integer id){
        return dao.findById(id);
    }
    public void resetAllExceptions(){
        dao.resetAllExceptions();
    }

}
