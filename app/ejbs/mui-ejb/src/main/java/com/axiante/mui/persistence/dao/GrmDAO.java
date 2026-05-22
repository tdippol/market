package com.axiante.mui.persistence.dao;

import java.util.List;

import com.axiante.mui.persistence.entity.GrmEntity;
import com.axiante.mui.persistence.entity.GroupEntity;

public interface GrmDAO {
	List<GrmEntity> findAll();
	List<GrmEntity> findAllByGroup(GroupEntity group);
    GrmEntity findById(Integer id);
}
