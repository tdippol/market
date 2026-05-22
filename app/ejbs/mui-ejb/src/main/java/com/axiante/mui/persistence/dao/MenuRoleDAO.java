package com.axiante.mui.persistence.dao;

import java.util.List;
import java.util.Set;

import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.MenuRoleEntity;
import com.axiante.mui.persistence.entity.RolesEntity;

public interface MenuRoleDAO {
    MenuRoleEntity findByMenuAndRole(MenuEntity menuEntity, RolesEntity roleEntity);

    List<MenuEntity> findAllVisibleByRole(RolesEntity roleEntity);

    boolean toggleAdminModeVisible(MenuRoleEntity menuRoleEntity);

    void changeAdminModeVisible(List<MenuRoleEntity> menuRoleEntities, boolean adminModeVisible);

    void resetAllExceptions();

    MenuRoleEntity findByMenuAndRoles(MenuEntity menuEntity, Set<RolesEntity> roleEntity);
}
