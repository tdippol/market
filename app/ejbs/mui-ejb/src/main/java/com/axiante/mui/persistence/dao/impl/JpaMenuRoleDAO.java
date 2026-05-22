package com.axiante.mui.persistence.dao.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.axiante.mui.persistence.dao.MenuRoleDAO;
import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.MenuRoleEntity;
import com.axiante.mui.persistence.entity.MenuRoleId;
import com.axiante.mui.persistence.entity.RolesEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Slf4j
public class JpaMenuRoleDAO implements MenuRoleDAO {
    @Inject
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    /**
     * Get a single MenuRoleEntity given menu and role, or null if record does not
     * exist
     * 
     * @param menuEntity menu
     * @param roleEntity role
     * @return single MenuRoleEntity or null if record does not exist
     */
    @Override
    public MenuRoleEntity findByMenuAndRole(MenuEntity menuEntity, RolesEntity roleEntity) {
        // Find by PK (CompositeKey)
        final MenuRoleId pk = new MenuRoleId(menuEntity.getIdMenu(), roleEntity.getId());
        return em.find(MenuRoleEntity.class, pk);
    }

    @Override
    public MenuRoleEntity findByMenuAndRoles(MenuEntity menuEntity, @NonNull Set<RolesEntity> roleEntity) {
        if (roleEntity.size() == 0) {
            return null;
        }
        TypedQuery<MenuRoleEntity> query = getEm().createNamedQuery("MenuRoleEntity.findByMenuAndRoles",
                MenuRoleEntity.class);

        query.setParameter("menu", menuEntity);
        query.setParameter("roles", Arrays.asList(roleEntity.toArray()));

        List<MenuRoleEntity> l = query.getResultList();
        if (l.size() > 1) {
            throw new IllegalStateException("Expected 1 result, found " + l.size());
        }
        if (l.size() == 0) {
            return null;
        }
        return l.get(0);
    }

    /**
     * Get all menu entities with flag adminModeVisible and given role
     * 
     * @param roleEntity role
     * @return list of menu entities with flag adminModeVisible and given role
     */
    @Override
    public List<MenuEntity> findAllVisibleByRole(RolesEntity roleEntity) {
        final String sql = "SELECT e FROM MenuRoleEntity e WHERE e.role=:role AND e.adminModeVisible=true";
        final List<MenuRoleEntity> entities = em.createQuery(sql, MenuRoleEntity.class).setParameter("role", roleEntity)
                .getResultList();
        return entities.stream().map(MenuRoleEntity::getMenu).collect(Collectors.toList());
    }

    /**
     * Toggle adminModeVisible flag of given entity
     * 
     * @param menuRoleEntity menuRole
     * @return the changed adminModeVisible flag
     */
    @Override
    public boolean toggleAdminModeVisible(MenuRoleEntity menuRoleEntity) {
        menuRoleEntity.setAdminModeVisible(!menuRoleEntity.getAdminModeVisible());
        em.merge(menuRoleEntity);
        em.flush();
        return menuRoleEntity.getAdminModeVisible();
    }

    /**
     * Change flag adminModeVisible to given value for given entity
     * 
     * @param menuRoleEntities list of menuRole
     * @param adminModeVisible boolean value to set
     */
    @Override
    @Transactional
    public void changeAdminModeVisible(List<MenuRoleEntity> menuRoleEntities, boolean adminModeVisible) {
        menuRoleEntities.forEach(e -> {
            e.setAdminModeVisible(adminModeVisible);
            em.merge(e);
        });
        em.flush();
    }

    /**
     * Reset flag adminModeVisible to default value (true) where is false
     */
    @Override
    public void resetAllExceptions() {
        final String sql = "UPDATE MenuRoleEntity SET adminModeVisible=true WHERE adminModeVisible=false";
        em.createQuery(sql).executeUpdate();
    }
}
