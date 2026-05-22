package com.axiante.mui.persistence.dao.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.contains;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.axiante.connection.AuthType;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.ConnectionSetupDAO;
import com.axiante.mui.persistence.entity.ConnectionSetupEntity;

public class ConnectionSetupDaoTest extends DaoTest {
	@Inject
	private ConnectionSetupDAO dao;

	@Spy
	@InjectMocks
	private JpaConnectionSetupDAO mokedDao;
	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaConnectionSetupDAO.class)
			.activate(RequestScoped.class).inject(this).build();

	@Test
	public void testReadProducesData() {
		List<ConnectionSetupEntity> list = dao.readAll();
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}

	@Test
	public void testConstructor() {
		assertNotNull(dao);
	}

	@Test
	public void testPersist() throws Exception {
		ConnectionSetupEntity entity = createEntity((Integer) null);
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}

		entity = dao.persist(entity);

		if (em.getTransaction().isActive()) {
			em.getTransaction().commit();
		}
		assertNotNull(entity);
		assertNotNull(entity.getIdConnectionSetup());
	}

	@Test
	public void testUpsert() throws Exception {
		int size = dao.readAll().size();
		ConnectionSetupEntity entity = createEntity(100);
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}

		entity = dao.persist(entity);

		assertNotNull(entity);
		assertThat(entity.getIdConnectionSetup(), equalTo(100));

		ConnectionSetupEntity entity2 = createEntity(100);

		assertEquals(entity, entity2);
		entity2.setConnectionName("test2");
		assertEquals(entity, entity2);
		entity2 = dao.persist(entity2); // this is actually an upsert

		if (em.getTransaction().isActive()) {
			em.getTransaction().commit();
		}
		int size2 = dao.readAll().size();
		assertThat(size, not(equalTo(size2 - 2))); // verify the update instead of the insert
		List<ConnectionSetupEntity> list = dao.readAll();
		final int id = entity.getIdConnectionSetup();
		entity = list.stream().filter(c -> c.getIdConnectionSetup() == id).findAny().orElse(null);
		assertNotNull(entity);
		assertEquals(entity, entity2);
	}

	@Test
	public void testPersistThrowsException() throws Exception {
		ex.expect(Exception.class);
		ex.expectMessage(contains("già presente"));
		mokedDao.persist(createEntity((Integer) null));
	}

	@Test
	public void remove_givenValidEntity_shouldRemoveIt() throws Exception {
		final ConnectionSetupEntity entity = createEntity("CONN-1");
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(entity);
			int before = dao.readAll().size();
			dao.remove(entity);
			int after = dao.readAll().size();
			em.getTransaction().commit();
			assertEquals(before - 1, after);
		}
	}

	private ConnectionSetupEntity createEntity(Integer id) {
		ConnectionSetupEntity entity = new ConnectionSetupEntity();
		entity.setIdConnectionSetup(id);
		entity.setAuthType(AuthType.BASIC.name());
		entity.setConnectionName("test");
		entity.setDomain("AX");
		entity.setHost("test");
		entity.setPassword("test");
		entity.setUsername("test");
		entity.setPort(1234);
		entity.setPath("path");
		entity.setValidateSsl(false);
		return entity;
	}

	private ConnectionSetupEntity createEntity(String name) {
		final ConnectionSetupEntity entity = new ConnectionSetupEntity();
		entity.setConnectionName(name);
		entity.setHost("host");
		entity.setPort(666);
		entity.setPath("path");
		entity.setValidateSsl(false);
		entity.setAuthType(AuthType.BASIC.name());
		return entity;
	}
}
