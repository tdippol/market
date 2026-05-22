package com.axiante.mui.persistence.dao.impl;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.AclDAO;
import com.axiante.mui.persistence.entity.AclEntity;
import com.axiante.mui.persistence.entity.RolesEntity;

public class AclDAOIntegrationTest extends DaoTest {
	@Inject
	private AclDAO aclDAO;

	@Mock
	AclEntity entity;
	@Spy
	@InjectMocks
	JpaAclDAO mockedDao;

	@Rule
	public WeldInitiator weld = WeldInitiator.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class,
			JpaAclDAO.class, BooleanConverter.class).activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void readACLs() {
		assertNotNull(aclDAO.readAll());
	}

	@Test
	public void readComponentGroups() {
		try {
			assertNotEquals(0, aclDAO.readComponentGroups().size());
		} catch (Exception e) {
			fail("Error while reading Acl " + e.getMessage());
		}
	}

	@Test
	public void testFindById() {
		assertNotNull(aclDAO.findById(1));
	}

	@Test
	public void persistDelete() {
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}

			// Role 1
			RolesEntity roleMock1 = em.find(RolesEntity.class, 1);

			// Add AclEntity
			AclEntity acl = createEntity(null, roleMock1);
			acl = aclDAO.persist(acl);

			assertNotNull(acl.getId());

			// Delete acl
			aclDAO.remove(acl);
			assertNull(aclDAO.findById(acl.getId()));
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			fail("Database exception during persist operation " + e.getMessage());
		}
	}

	@Test(expected = Exception.class)
	public void testRemoveTransformsPersistenceException() throws Exception {
		mockedDao.remove(entity);
	}

	@Test(expected = Exception.class)
	public void testPersistThrowsException() throws Exception {
		mockedDao.persist(Mockito.mock(AclEntity.class));
	}

	private AclEntity createEntity(Integer id,  RolesEntity role) {
		AclEntity acl = new AclEntity();
		acl.setId(id);
		acl.setComponentClass("COMPONENT_CLASS");
		acl.setComponent("COMPONENT");
		acl.setVisible(true);
		acl.setEditable(true);
		acl.setEnabled(true);
		acl.setRolesByRoleId(role);

		return acl;
	}

}