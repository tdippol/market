package com.axiante.mui.persistence.dao;

import java.util.List;
import java.util.Set;

import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.RolesEntity;

public interface MenuDAO {
    List<MenuEntity> readAll();

    List<MenuEntity> readAll(RolesEntity rolesEntity);

    List<MenuEntity> readByParentId(Integer id);

    List<MenuEntity> readNullParent();

    MenuEntity persist(MenuEntity entity) throws Exception;

    List<MenuEntity> persist(List<MenuEntity> entity) throws Exception;

    void remove(MenuEntity entity) throws Exception;

    MenuEntity findById(Integer id);

    List<MenuEntity> getChildren(MenuEntity parent, RolesEntity role);

    boolean menuBelongsToRole(MenuEntity menu, RolesEntity role);

    boolean menuBelongsToRoles(MenuEntity menu, Set<RolesEntity> role);
}
