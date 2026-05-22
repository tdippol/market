package com.axiante.mui.persistence.dao;

import com.axiante.mui.persistence.entity.PianoMediaSecurityEntity;
import com.axiante.mui.persistence.entity.UsersEntity;

import java.util.List;

public interface PianoMediaSecurityDAO {
    List<PianoMediaSecurityEntity> readAll();
    PianoMediaSecurityEntity persist(PianoMediaSecurityEntity entity) throws Exception;
    void remove(PianoMediaSecurityEntity entity) throws Exception;
    PianoMediaSecurityEntity findById(Integer id);
    List<PianoMediaSecurityEntity> findByUser(UsersEntity user) throws Exception;
}

