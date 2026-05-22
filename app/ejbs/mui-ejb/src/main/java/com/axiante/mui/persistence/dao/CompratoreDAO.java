package com.axiante.mui.persistence.dao;

import java.util.List;

import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GroupEntity;

public interface CompratoreDAO {

	List<CompratoreEntity> findAll();
	List<CompratoreEntity> findAllByGroup(GroupEntity group);
    CompratoreEntity findById(Integer id);
}
