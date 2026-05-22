package com.axiante.mui.persistence.dao;

import java.util.List;

import com.axiante.mui.persistence.entity.AclEntity;

public interface AclDAO {
    List<AclEntity> readAll();
    AclEntity persist(AclEntity entity) throws Exception;
    void remove(AclEntity entity) throws Exception;
    AclEntity findById(Integer id);
    List<String> readComponentGroups() throws Exception;
}

