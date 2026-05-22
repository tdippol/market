package com.axiante.mui.persistence.dao;

import java.util.List;

import com.axiante.mui.persistence.entity.ConnectionSetupEntity;

public interface ConnectionSetupDAO {
    List<ConnectionSetupEntity> readAll();
    ConnectionSetupEntity persist(ConnectionSetupEntity entity) throws Exception;
    void remove(ConnectionSetupEntity entity) throws Exception;

}

