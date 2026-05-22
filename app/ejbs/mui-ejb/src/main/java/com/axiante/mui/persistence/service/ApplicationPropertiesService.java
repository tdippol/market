package com.axiante.mui.persistence.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.entity.RolesEntity;

public interface ApplicationPropertiesService extends Serializable {

    ApplicationPropertiesEntity getPropertiesEntity(String key);

    ApplicationPropertiesEntity saveProperty(ApplicationPropertiesEntity entity);

    boolean saveProperty(ApplicationPropertiesEntity entity, String newValue);

    List<ApplicationPropertiesEntity> findAll();

    boolean calculateAdminMode();

    ApplicationPropertiesEntity getOrCreateProperty(String key, String value);

    List<ApplicationPropertiesEntity> findAnyById(String key) throws Exception;

    boolean hasAccessAsAdmin(RolesEntity role);

    boolean hasAccessAsAdmin(final Set<RolesEntity> roles);

}
