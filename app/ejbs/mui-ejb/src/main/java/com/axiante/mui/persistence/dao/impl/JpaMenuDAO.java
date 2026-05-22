package com.axiante.mui.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.axiante.mui.persistence.dao.MenuDAO;
import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.RolesEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

@Stateless
public class JpaMenuDAO implements MenuDAO {
    @Inject
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    private final Comparator<MenuEntity> menuSorter = (@NonNull final MenuEntity o1, @NonNull final MenuEntity o2) -> {
        if (o1.getParent() == o2.getParent()) {
            return o1.getOrderId().compareTo(o2.getOrderId());
        }
        if (o1.getParent() == null) {
            return 1;
        }
        if (o2.getParent() == null) {
            return -1;
        }
        return o1.getParent().getIdMenu().compareTo(o2.getParent().getIdMenu());
    };

    @Override
    public List<MenuEntity> readAll() {
        return getEm().createNamedQuery("MenuEntity.readAll", MenuEntity.class).getResultList();
    }

    @Override
    public List<MenuEntity> readAll(final RolesEntity rolesEntity) {
        // return results;
        final List<MenuEntity> list = new ArrayList<>();
        // if admin, add everything
        boolean isNotAdmin = (rolesEntity != null) && !rolesEntity.isAdmin();
        if (isNotAdmin) {
            final TypedQuery<MenuEntity> query = getEm().createNamedQuery("MenuEntity.readAllByRole", MenuEntity.class);
            query.setParameter("role", rolesEntity);
            list.addAll(query.getResultList());
        } else {
            final TypedQuery<MenuEntity> query = getEm().createNamedQuery("MenuEntity.readAll", MenuEntity.class);
            list.addAll(query.getResultList());
        }
        list.sort(menuSorter);
        return list;
    }

    @Override
    public List<MenuEntity> persist(final List<MenuEntity> list) throws Exception {
        for (MenuEntity menu : list) {
            menu = getEm().merge(menu);
        }
        getEm().flush();
        return list;
    }

    @Override
    public MenuEntity persist(MenuEntity entity) throws Exception {
        try {
            entity = getEm().merge(entity);
            getEm().flush();
        } catch (final EntityExistsException e) {
            throw new Exception("Nodo di menu già presente.");
        }
        return entity;
    }

    @Override
    public void remove(MenuEntity entity) throws Exception {
        try {
            entity = getEm().merge(entity);
            getEm().remove(entity);
        } catch (final PersistenceException e) {
            throw new Exception("Impossibile rimuovere oggetto in uso.");
        }
    }

    @Override
    public MenuEntity findById(final Integer id) {
        return getEm().find(MenuEntity.class, id);
    }

    @Override
    public List<MenuEntity> readByParentId(final Integer id) {
        return getEm().createNamedQuery("MenuEntity.readAllByParent", MenuEntity.class).setParameter("parentId", id)
                .getResultList();
    }

    @Override
    public List<MenuEntity> readNullParent() {
        return getEm().createNamedQuery("MenuEntity.readNullParent", MenuEntity.class).getResultList();
    }

    @Override
    public List<MenuEntity> getChildren(MenuEntity parent, RolesEntity role) {
        boolean isNotAdmin = (role != null) && !role.isAdmin();
        if (isNotAdmin) {
            TypedQuery<MenuEntity> query = getEm().createNamedQuery("MenuEntity.getChildrenByRole", MenuEntity.class);
            query.setParameter("idParent", parent.getIdMenu());
            query.setParameter("idRole", role.getId());
            return query.getResultList();
        }
        return parent.getChildren(); // admin reads all
    }

    @Override
    public boolean menuBelongsToRole(MenuEntity menu, RolesEntity role) {
        TypedQuery<Long> query = getEm().createNamedQuery("MenuEntity.belongsToRole", Long.class);
        query.setParameter("menu", menu);
        query.setParameter("role", role);
        Long result = query.getSingleResult();
        return result > 0;
    }

    @Override
    public boolean menuBelongsToRoles(MenuEntity menu, Set<RolesEntity> roles) {
        if (roles == null) {
            return false;
        }
        if (roles.size() == 0) {
            return false;
        }
        TypedQuery<Long> query = getEm().createNamedQuery("MenuEntity.belongsToRoles", Long.class);
        query.setParameter("menu", menu);
        query.setParameter("roles", Arrays.asList(roles.toArray()));
        Long result = query.getSingleResult();
        return result > 0;
    }
}
