package com.axiante.mui.persistence.dao.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.CompratoreDAO;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GroupEntity;

public class CompratoreDAOTest extends DaoTest{

	@Inject CompratoreDAO dao;

	@Spy
	@InjectMocks
	JpaCompratoreDAOImpl compratoreDAO;

	@Rule
	public WeldInitiator weld = WeldInitiator
	.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaCompratoreDAOImpl.class)
	.activate(RequestScoped.class).inject(this).build();


	@Test
	public void testFindAll() {
		createOne(1);
		assertTrue(dao.findAll().size()>0);
	}

	@Test
	public void testFindAllByGroup() {
		CompratoreEntity c = createOneWithGroup();
		assertTrue(dao.findAllByGroup(c.getGruppi().iterator().next()).size() > 0);
	}

	@Test(expected = NullPointerException.class)
	public void testFindAllByGroupThrowsExceptionWhenGroupIsNull() {
		dao.findAllByGroup(null);
	}

	private CompratoreEntity createOne(Integer id) {
		CompratoreEntity c = new CompratoreEntity();
		c.setId(id);
		c.setDescrizione(RandomStringUtils.random(20));
		c.setCodiceCompratore(RandomStringUtils.random(3));
		c.setFlagAttivo(0L);
		try {
			getEm().getTransaction().begin();
			c = getEm().merge(c);

			getEm().flush();
			getEm().getTransaction().commit();
			return c;
		} catch ( Exception e) {
			fail(e.getMessage());
		}
		return null;
	}

	private CompratoreEntity createOneWithGroup() {
		CompratoreEntity c = createOne(1);
		GroupEntity g = new GroupEntity();
		g.setCodiceGruppo(RandomStringUtils.random(3));
		g.setDescrizione(RandomStringUtils.random(20));
		try {
			getEm().getTransaction().begin();
			g.addCompratore(c);
			g = getEm().merge(g);
			getEm().flush();
			getEm().getTransaction().commit();
			return c;
		} catch ( Exception e) {
			fail(e.getMessage());
		}
		return null;

	}
}
