package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.MenuDAO;
import com.axiante.mui.persistence.entity.MenuEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import org.hamcrest.CoreMatchers;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.contains;

public class MenuDAOIntegrationTest extends DaoTest {

//	@Inject
//	EntityManager em;

	@Inject
	MenuDAO dao;

	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaMenuDAO.class)
			.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();
	@Spy
	@InjectMocks
	private JpaMenuDAO mockedDao;

	@Test
	public void readMenus() {
		assertNotNull(dao.readAll());
	}

	@Test
	public void testPersist() throws Exception {
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			// Add User
			MenuEntity menuEntity = createEntity(null);
			menuEntity = dao.persist(menuEntity);

			Assert.assertNotNull(menuEntity.getIdMenu());

		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Test
	public void testDelete() throws Exception {
		synchronized (em) {
			try {
				final List<MenuEntity> list = dao.readAll();
				final MenuEntity menuEntity = list.stream()
						.filter(m -> m.getRoles().size() == 0)
						.findAny()
						.orElse(null);
				Assert.assertNotNull(menuEntity);
				if (!em.getTransaction().isActive()) {
					em.getTransaction().begin();
				}
				// Delete User
				dao.remove(menuEntity);

				final int expected = dao.readAll().size();
				assertThat(list.size() - 1, CoreMatchers.equalTo(expected));
			} finally {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			}
		}
	}

	@Test
	public void testFindById() {
		final Integer id = dao.readAll().stream().map(MenuEntity::getIdMenu).distinct().findAny().orElse(null);
		assertNotNull(id);
		final MenuEntity e = dao.findById(id);
		assertNotNull(e);
		assertThat(e.getIdMenu(), CoreMatchers.equalTo(id));
	}

	@Test
	public void testFindByIdReturnsNull() {
		final Integer id = 100000000;
		final MenuEntity e = dao.findById(id);
		assertNull(e);
	}

	@Test
	public void testReadAllByRoles() {
		final List<MenuEntity> list = dao.readAll();
		final RolesEntity e = list.stream().map(MenuEntity::getRoles) // stream of List<RolesEntity>
				.flatMap(Collection::stream)// stream of Roles
				.distinct().findAny().orElse(null);

		final List<MenuEntity> test = list.stream().filter(menuEntity -> menuEntity.getRoles().contains(e))
				.collect(Collectors.toList());

		assertNotNull(test);
		final List<MenuEntity> result = dao.readAll(e);
		assertNotNull(result);
		test.forEach(x -> assertTrue(result.contains(x)));
		result.forEach(x -> assertTrue(test.contains(x)));
	}

	@Test
	public void testPersistThrowsException() throws Exception {
		ex.expect(Exception.class);
		ex.expectMessage(contains("già presente"));

		mockedDao.persist(createEntity(10000));
	}

	@Test
	public void testRemoveThrowsException() throws Exception {
		Mockito.doThrow(PersistenceException.class).when(mockedEm).merge(ArgumentMatchers.any());
		ex.expect(Exception.class);
		ex.expectMessage(contains("Impossibile rimuovere"));
		mockedDao.remove(createEntity(10000));
	}

	private MenuEntity createEntity(final Integer id) {
		final MenuEntity menuEntity = new MenuEntity();
		menuEntity.setIdMenu(id);
		menuEntity.setParent(null);
		menuEntity.setBean("bean");
		menuEntity.setUrl("url");
		menuEntity.setLabel("label");
		menuEntity.setOrderId(0);
		menuEntity.setExternalLink(false);
		return menuEntity;
	}

	@Test
	public void testMenuHasConfigurations() {
		final MenuEntity menu = dao.findById(2);
		assertNotNull(menu.getConfigurations());
		assertThat(menu.getConfigurations().size(), CoreMatchers.equalTo(1));
		assertThat(menu.getConfigurations().iterator().next().getIdConfiguration(), CoreMatchers.equalTo(7));
	}

	@Test
	public void persistList_givenList_shouldSaveEntitiesToDB() throws Exception {
		final List<MenuEntity> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			list.add(createEntity("MENU-" + i, null));
		}
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			int before = dao.readAll().size();
			dao.persist(list);
			int after = dao.readAll().size();
			em.getTransaction().commit();
			assertEquals(before + 10, after);
		}
	}

	@Test
	public void readByParentId_givenId_shouldReturnChildsMenu() throws Exception {
		final List<MenuEntity> list = new ArrayList<>();
		MenuEntity parentMenu = createEntity("MENU-PARENT", null);
		for (int i = 1; i <= 5; i++) {
			list.add(createEntity("MENU-" + i, parentMenu));
		}
		parentMenu.setChildren(list);
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			parentMenu = dao.persist(dao.persist(parentMenu));
			final List<MenuEntity> childs = dao.readByParentId(parentMenu.getIdMenu());
			em.getTransaction().commit();
			assertEquals(5, childs.size());
		}
	}

	@Test
	public void readNullParent_shouldReturnOnlyRootMenu() throws Exception {
		MenuEntity parentMenu = createEntity("MENU-PARENT", null);
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			int before = dao.readNullParent().size();
			dao.persist(parentMenu);
			final List<MenuEntity> list = dao.readNullParent();
			em.getTransaction().commit();
			assertFalse(list.isEmpty());
			assertEquals(before + 1, list.size());
		}
	}

	private MenuEntity createEntity(String label, final MenuEntity parentMenu) {
		final MenuEntity entity = new MenuEntity();
		entity.setLabel(label);
		entity.setParent(parentMenu);
		entity.setOrderId(0);
		entity.setExternalLink(false);
		return entity;
	}
}