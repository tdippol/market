package com.axiante.mui.persistence.dao;

import java.util.List;

import com.axiante.mui.persistence.entity.UsersEntity;

public interface UsersDAO {
    List<UsersEntity> readAll();
    UsersEntity persist(UsersEntity entity) throws Exception;
    void remove(UsersEntity entity) throws Exception;
    UsersEntity findById(Integer id);
    UsersEntity findByUsername(String username);
}

