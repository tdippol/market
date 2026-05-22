package com.axiante.mui.persistence.dao;

import java.util.List;

import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;

public interface ConfigurationsDAO {
	List<ConfigurationEntity> readAll();
	ConfigurationEntity persist(ConfigurationEntity entity) throws Exception;
	void remove(ConfigurationEntity entity) throws Exception;
	ConfigurationEntity findById(Integer id);
	ConfigurationEntity findByPath(String path,ConfigurationTypes type);
	List<ConfigurationEntity> findByType(ConfigurationTypes type);
	List<ConfigurationEntity> findByIdMenu(Integer idMenu);
	ConfigurationEntity findByIdMenuAndType(Integer idMenu, ConfigurationTypes type);
}

