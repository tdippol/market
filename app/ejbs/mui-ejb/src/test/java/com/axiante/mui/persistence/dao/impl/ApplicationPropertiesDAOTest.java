package com.axiante.mui.persistence.dao.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doThrow;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.ApplicationPropertiesDAO;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;

public class ApplicationPropertiesDAOTest extends DaoTest {
	@Inject
	private ApplicationPropertiesDAO dao;

	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaApplicationPropertiesDAO.class)
			.activate(RequestScoped.class).inject(this).build();

	@Spy
	@InjectMocks
	JpaApplicationPropertiesDAO mockedDao;

	@Override
	@Before
	public void initTest() {
		doThrow(EntityExistsException.class).when(mockedEm).merge(any());
	}

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void testReadReturnsNonEmptyData() {
		assertNotNull(dao.readAll());
	}

	@Test
	public void testUpsert() throws Exception {
		ApplicationPropertiesEntity entity = new ApplicationPropertiesEntity();
		int id = 0;
		// entity.setIdApplicationProperties(id);
		entity.setKey("test");
		entity.setValue("test value");

		int size = dao.readAll().size();
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}

		entity = dao.persist(entity);
		assertNotNull(entity);
		// assertThat(entity.getIdApplicationProperties(), equalTo(id));

		if (em.getTransaction().isActive()) {
			em.getTransaction().commit();
		}
		assertThat(dao.readAll().size(), equalTo(size + 1));
		id = entity.getIdApplicationProperties();
		entity.setKey("test changed");
		size = dao.readAll().size();
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}

		entity = dao.persist(entity);
		assertNotNull(entity);
		assertThat(entity.getIdApplicationProperties(), equalTo(id));

		if (em.getTransaction().isActive()) {
			em.getTransaction().commit();
		}
		assertThat(dao.readAll().size(), equalTo(size));
	}

	@Test
	public void testPersistThrowsException() throws Exception {
		int id = 10000;
		ApplicationPropertiesEntity entity = new ApplicationPropertiesEntity();
		entity.setIdApplicationProperties(id);
		entity.setKey("test");
		entity.setValue("test value");
		ex.expect(Exception.class);
		ex.expectMessage(contains("già presente"));
		mockedDao.persist(entity);
	}

	@Test(expected = NullPointerException.class)
	public void findByCode_givenNullCode_shouldThrowException() throws Exception {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			dao.findByCode(null);
			em.getTransaction().commit();
		}
	}

	@Test
	public void findByCode_givenExistingCode_shouldReturnEntity() throws Exception {
		final ApplicationPropertiesEntity entity1 = createEntity("FOO", "BAR");
		final ApplicationPropertiesEntity entity2 = createEntity("HELLO", "WORLD");
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(entity1);
			em.persist(entity2);
			final ApplicationPropertiesEntity byCode = dao.findByCode("FOO");
			em.getTransaction().commit();
			assertNotNull(byCode);
			assertEquals("BAR", byCode.getValue());
		}
	}

	@Test
	public void findByCode_givenNotExistingCode_shouldReturnNull() throws Exception {
		final ApplicationPropertiesEntity entity1 = createEntity("FOO", "BAR");
		final ApplicationPropertiesEntity entity2 = createEntity("HELLO", "WORLD");
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			em.persist(entity1);
			em.persist(entity2);
			final ApplicationPropertiesEntity byCode = dao.findByCode("BAR");
			em.getTransaction().commit();
			assertNull(byCode);
		}
	}

	private ApplicationPropertiesEntity createEntity(String key, String value) {
		final ApplicationPropertiesEntity entity = new ApplicationPropertiesEntity();
		entity.setKey(key);
		entity.setValue(value);
		return entity;
	}
}
