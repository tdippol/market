package com.axiante.mui.persistence.entity;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MenuRoleEntityTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void init() {
        emf = Persistence.createEntityManagerFactory("aclTestPU");
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void destroy() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void persistMenuRolesWithCompositeKey() {
        MenuEntity menu = createMenu("Foo Menu");
        RolesEntity role = createRole("Bar Role");
        persist(menu, role);
        MenuRoleEntity menuRole = createMenuRole(menu, role);
        persist(menuRole);
        final String sql = "SELECT e FROM MenuRoleEntity e WHERE e.menu.label=:menuLabel";
        final List<MenuRoleEntity> entities = em.createQuery(sql, MenuRoleEntity.class)
                .setParameter("menuLabel", "Foo Menu")
                .getResultList();
        assertEquals(1, entities.size());
        final MenuRoleEntity entity = entities.get(0);
        assertEquals((int) menu.getIdMenu(), (int) entity.getId().getMenuId());
        assertEquals((int) role.getId(), (int) entity.getId().getRoleId());
        assertEquals(menu.getLabel(), entity.getMenu().getLabel());
        assertEquals(role.getName(), entity.getRole().getName());
        assertTrue(entity.getAdminModeVisible());
    }

    private void persist(Object... entities) {
        em.getTransaction().begin();
        for (Object entity : entities) {
            em.persist(entity);
        }
        em.getTransaction().commit();
        em.clear();
    }

    private MenuRoleEntity createMenuRole(final MenuEntity menu, final RolesEntity role) {
        final MenuRoleEntity entity = new MenuRoleEntity();
        entity.setMenu(menu);
        entity.setRole(role);
        return entity;
    }

    private MenuEntity createMenu(String label) {
        final MenuEntity entity = new MenuEntity();
        entity.setLabel(label);
        entity.setExternalLink(false);
        entity.setOrderId(0);
        return entity;
    }

    private RolesEntity createRole(String name) {
        final SlowRolesEntity entity = new SlowRolesEntity();
        entity.setName(name);
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return em.find(RolesEntity.class, entity.getId());
    }
}
