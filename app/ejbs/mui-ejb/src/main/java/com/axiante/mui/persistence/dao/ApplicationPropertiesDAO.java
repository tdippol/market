package com.axiante.mui.persistence.dao;

import java.util.List;

import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;

public interface ApplicationPropertiesDAO {
	ApplicationPropertiesEntity findByCode(String code) throws Exception;

	List<ApplicationPropertiesEntity> findAnyByCode(String code) throws Exception;

	ApplicationPropertiesEntity persist(ApplicationPropertiesEntity entity) throws Exception;

	List<ApplicationPropertiesEntity> readAll();

	boolean delete(String key) throws  Exception;


	void refreshProperties();
}
