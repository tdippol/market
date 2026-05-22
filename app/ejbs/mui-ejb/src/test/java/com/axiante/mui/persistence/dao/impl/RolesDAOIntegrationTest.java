package com.axiante.mui.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.axiante.mui.persistence.dao.SlowRolesDAO;
import com.axiante.mui.persistence.entity.SlowRolesEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.RolesDAO;
import com.axiante.mui.persistence.entity.RolesEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RolesDAOIntegrationTest extends DaoTest {
	@Inject
	private RolesDAO dao;
	@Inject
	private SlowRolesDAO slowRolesDAO;

	@Mock
	RolesEntity role;
	@Spy
	@InjectMocks
	JpaRolesDAO mockedDao;
	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaRolesDAO.class, JpaSlowRolesDAO.class)
			.activate(RequestScoped.class).inject(this).build();

	@Test
	public void readRoles() throws Exception {
		assertNotNull(dao.readAll());
	}

	@Test
	public void persistDelete() {
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			// Add Role
			RolesEntity role = new RolesEntity();
			role.setName("ROLE_NAME_TO_DELETE");
			role.setDescription("ROLE_DESCRIPTION");
			role = dao.persist(role);

			assertNotNull(role.getId());

			// Delete acl
			dao.remove(role);
			assertNull(dao.findById(role.getId()));
		} catch (final Exception e) {
			log.error("error testing deletion ", e);
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Test
	public void validateUniqueKeysOnAclObject() {
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}

			// Add Role
			final SlowRolesEntity role = new SlowRolesEntity();
			role.setName("ROLE_NAME");
			role.setDescription("ROLE_DESCRIPTION");
			slowRolesDAO.persist(role);

			// Add Role to break UKs
			final SlowRolesEntity role1 = new SlowRolesEntity();
			role1.setName("ROLE_NAME");
			role1.setDescription("ROLE_DESCRIPTION");
			slowRolesDAO.persist(role1);

		} catch (final Exception e) {
			assertEquals("Ruolo già presente.", e.getMessage());
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Test(expected = Exception.class)
	public void testRemoveTransformsPersistenceException() throws Exception {
		mockedDao.remove(role);
	}

}