package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.MenuRoleDAO;
import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.MenuRoleEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.SlowRolesEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MenuRoleDAOTest extends DaoTest {
    @Inject
    MenuRoleDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaMenuRoleDAO.class)
            .activate(RequestScoped.class).inject(this).build();

    @Test
    public void findByMenuAndRole() {
        // Arrange
        // Menu-FOO: Role-BAZ, Role-ABC
        // Menu-BAR: Role-BAZ, Role-XYZ
        final MenuEntity menu1 = createMenu("Menu-FOO");
        menu1.setIdMenu(666);
        final MenuEntity menu2 = createMenu("Menu-BAR");
        menu2.setIdMenu(999);
        final RolesEntity role1 = createSlowRole("Role-BAZ");
        final RolesEntity role2 = createSlowRole("Role-ABC");
        final RolesEntity role3 = createSlowRole("Role-XYZ");


        menu1.addRole(role1);
        menu1.addRole(role2);
        menu2.addRole(role1);
        menu2.addRole(role3);
        em.getTransaction().begin();
        em.persist(menu1);
        em.persist(menu2);
        em.getTransaction().commit();
        // Act & Assert
        final MenuRoleEntity entity1 = dao.findByMenuAndRole(menu1, role1);
        assertNotNull(entity1);
        assertEquals("Menu-FOO", entity1.getMenu().getLabel());
        assertEquals("Role-BAZ", entity1.getRole().getName());
        final MenuRoleEntity entity2 = dao.findByMenuAndRole(menu1, role2);
        assertNotNull(entity2);
        assertEquals("Menu-FOO", entity2.getMenu().getLabel());
        assertEquals("Role-ABC", entity2.getRole().getName());
        final MenuRoleEntity entity3 = dao.findByMenuAndRole(menu2, role1);
        assertNotNull(entity3);
        assertEquals("Menu-BAR", entity3.getMenu().getLabel());
        assertEquals("Role-BAZ", entity3.getRole().getName());
        final MenuRoleEntity entity4 = dao.findByMenuAndRole(menu2, role3);
        assertNotNull(entity4);
        assertEquals("Menu-BAR", entity4.getMenu().getLabel());
        assertEquals("Role-XYZ", entity4.getRole().getName());
        // Not found
        assertNull(dao.findByMenuAndRole(menu1, role3));
        assertNull(dao.findByMenuAndRole(menu2, role2));
    }

    @Test
    public void findAllVisibleByRole_givenRole_shouldReturnAssociatedEntities() {
        // Menu-0, Menu-3, Menu-6, Menu-9           with adminModeVisible
        // Menu-0, Menu-2, Menu-4, Menu-6, Menu-8   with roleFoo
        // Menu-1, Menu-3, Menu-5, Menu-7, Menu-9   with roleBar
        RolesEntity roleFoo = createSlowRole("FOO");
        RolesEntity roleBar = createSlowRole("BAR");
        RolesEntity roleBaz = createSlowRole("BAZ");
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            MenuEntity menuEntity = createMenu("Menu-" + i);
            em.persist(menuEntity);
            boolean visible = i % 3 == 0;
            MenuRoleEntity menuRoleEntity = createMenuRole(menuEntity, i % 2 == 0 ? roleFoo : roleBar, visible);
            em.persist(menuRoleEntity);
        }
        em.getTransaction().commit();
        // Assert on roleFoo
        final List<MenuEntity> menusFoo = dao.findAllVisibleByRole(roleFoo);
        assertEquals(2, menusFoo.size());
        final List<String> menuFooLabels = menusFoo.stream().map(MenuEntity::getLabel).collect(Collectors.toList());
        assertTrue(menuFooLabels.contains("Menu-0"));
        assertTrue(menuFooLabels.contains("Menu-6"));
        assertFalse(menuFooLabels.contains("Menu-1"));
        assertFalse(menuFooLabels.contains("Menu-2"));
        assertFalse(menuFooLabels.contains("Menu-3"));
        assertFalse(menuFooLabels.contains("Menu-4"));
        assertFalse(menuFooLabels.contains("Menu-5"));
        assertFalse(menuFooLabels.contains("Menu-7"));
        assertFalse(menuFooLabels.contains("Menu-8"));
        assertFalse(menuFooLabels.contains("Menu-9"));
        // Assert on roleBar
        final List<MenuEntity> menusBar = dao.findAllVisibleByRole(roleBar);
        assertEquals(2, menusBar.size());
        final List<String> menuBarLabels = menusBar.stream().map(MenuEntity::getLabel).collect(Collectors.toList());
        assertTrue(menuBarLabels.contains("Menu-3"));
        assertTrue(menuBarLabels.contains("Menu-9"));
        assertFalse(menuBarLabels.contains("Menu-0"));
        assertFalse(menuBarLabels.contains("Menu-1"));
        assertFalse(menuBarLabels.contains("Menu-2"));
        assertFalse(menuBarLabels.contains("Menu-4"));
        assertFalse(menuBarLabels.contains("Menu-5"));
        assertFalse(menuBarLabels.contains("Menu-6"));
        assertFalse(menuBarLabels.contains("Menu-7"));
        assertFalse(menuBarLabels.contains("Menu-8"));
        // Assert on roleBaz
        final List<MenuEntity> menusBaz = dao.findAllVisibleByRole(roleBaz);
        assertTrue(menusBaz.isEmpty());
    }

    @Test
    public void toggle_givenEntityWithVisibleTrue_shouldSetToFalse_andReturnIt() {
        // Arrange
        final RolesEntity roleEntity = createSlowRole("Role Foo");
        final MenuEntity menuEntity = createMenu("Menu Foo");
        final MenuRoleEntity menuRoleEntity = createMenuRole(menuEntity, roleEntity, true);
        persist(menuEntity, menuRoleEntity);
        // Act & Assert
        em.getTransaction().begin();
        assertFalse(dao.toggleAdminModeVisible(menuRoleEntity));
        assertFalse(menuRoleEntity.getAdminModeVisible());
        em.getTransaction().commit();
    }

    @Test
    public void toggle_givenEntityWithVisibleFalse_shouldSetToTrue_andReturnIt() {
        // Arrange
        final RolesEntity roleEntity = createSlowRole("Role Bar");
        final MenuEntity menuEntity = createMenu("Menu Bar");
        final MenuRoleEntity menuRoleEntity = createMenuRole(menuEntity, roleEntity, false);
        persist(menuEntity, menuRoleEntity);
        // Act & Assert
        em.getTransaction().begin();
        assertTrue(dao.toggleAdminModeVisible(menuRoleEntity));
        assertTrue(menuRoleEntity.getAdminModeVisible());
        em.getTransaction().commit();
    }

    @Test
    public void changeAdminModeVisible_givenList_shouldSetAdminModeVisibleAccordingToFlag() {
        final RolesEntity roleEntity1 = createSlowRole("Role FooFoo");
        final RolesEntity roleEntity2 = createSlowRole("Role BarBar");

        final MenuEntity menuEntity1 = createMenu("Menu BarBar");
        final MenuRoleEntity menuRoleEntity1 = createMenuRole(menuEntity1, roleEntity1, false);
        final MenuEntity menuEntity2 = createMenu("Menu FooFoo");
        final MenuRoleEntity menuRoleEntity2 = createMenuRole(menuEntity2, roleEntity2, false);
        persist(menuEntity1, menuRoleEntity1, menuEntity2, menuRoleEntity2);
        List<MenuRoleEntity> list = new ArrayList<>();
        list.add(menuRoleEntity1);
        list.add(menuRoleEntity2);
        em.getTransaction().begin();
        dao.changeAdminModeVisible(list, true);
        final List<MenuRoleEntity> res = em
                .createQuery("SELECT e FROM MenuRoleEntity e WHERE e.menu IN :menu", MenuRoleEntity.class)
                .setParameter("menu", Arrays.asList(menuEntity1, menuEntity2))
                .getResultList();
        em.getTransaction().commit();
        assertEquals(2, res.size());
        final List<Boolean> flags = res.stream().map(MenuRoleEntity::getAdminModeVisible).distinct()
                .collect(Collectors.toList());
        assertEquals(1, flags.size());
        assertTrue(flags.get(0));
    }

    private RolesEntity createSlowRole(String name) {
        final SlowRolesEntity entity = new SlowRolesEntity();
        entity.setName(name);
        em.getTransaction().begin();
        em.persist(entity);
        em.flush();
        em.getTransaction().commit();
        return em.find(RolesEntity.class, entity.getId());
    }

    private MenuEntity createMenu(String label) {
        final MenuEntity entity = new MenuEntity();
        entity.setLabel(label);
        entity.setOrderId(0);
        entity.setExternalLink(false);
        return entity;
    }

    private MenuRoleEntity createMenuRole(MenuEntity menuEntity, RolesEntity roleEntity, boolean visible) {
        final MenuRoleEntity entity = new MenuRoleEntity();
        entity.setMenu(menuEntity);
        entity.setRole(roleEntity);
        entity.setAdminModeVisible(visible);
        return entity;
    }

    private void persist(Object... entities) {
        em.getTransaction().begin();
        for (Object entity : entities) {
            em.persist(entity);
        }
        em.flush();
        em.getTransaction().commit();
    }
}
