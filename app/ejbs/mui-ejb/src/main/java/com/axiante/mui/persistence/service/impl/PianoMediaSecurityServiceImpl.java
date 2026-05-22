package com.axiante.mui.persistence.service.impl;

import com.axiante.mui.persistence.dao.PianoMediaSecurityDAO;
import com.axiante.mui.persistence.entity.PianoMediaSecurityEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.PianoMediaSecurityService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;

@Slf4j
@Dependent
public class PianoMediaSecurityServiceImpl implements PianoMediaSecurityService {

    private static final long serialVersionUID = -7948758290017181310L;
    @Inject
    private Instance<PianoMediaSecurityDAO> daoInstance;

    @Override
    public List<PianoMediaSecurityEntity> readAll(){
        return daoInstance.get().readAll();
    }
    @Override
    public PianoMediaSecurityEntity persist(PianoMediaSecurityEntity entity) throws Exception{
        return daoInstance.get().persist(entity);
    }
    @Override
    public void remove(PianoMediaSecurityEntity entity) throws Exception{
        daoInstance.get().remove(entity);
    }
    @Override
    public PianoMediaSecurityEntity findById(Integer id){
        return daoInstance.get().findById(id);
    }
    @Override
    public List<PianoMediaSecurityEntity> findByUser(UsersEntity user) throws Exception{
        return daoInstance.get().findByUser(user);
    }

}
