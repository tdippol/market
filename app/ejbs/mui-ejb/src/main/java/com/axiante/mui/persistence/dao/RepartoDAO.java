package com.axiante.mui.persistence.dao;

import java.util.List;

import com.axiante.mui.persistence.entity.RepartoEntity;

public interface RepartoDAO {

	List<RepartoEntity> findAll();

    RepartoEntity findById(Integer id);
}
