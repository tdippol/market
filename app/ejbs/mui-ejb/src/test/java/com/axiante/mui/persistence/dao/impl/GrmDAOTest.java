package com.axiante.mui.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.axiante.mui.persistence.entity.GruppoCanaleEntity;
import com.axiante.mui.persistence.entity.GruppoGrmEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.GrmDAO;
import com.axiante.mui.persistence.entity.GrmEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import org.mockito.Mockito;
import org.mockito.Spy;

public class GrmDAOTest extends DaoTest {

	@Inject
	GrmDAO dao;

	@Rule
	public WeldInitiator weld = WeldInitiator
	.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaGrmDAOImpl.class)
	.activate(RequestScoped.class).inject(this).build();

	@Test
	public void testFindAll() {
		createOne(100);
		assertTrue(dao.findAll().size() > 0);
	}

	@Test
	public void testFindAllByGroup() {
		final GrmEntity e = createOneWithGroup(100);
		final GroupEntity g = e.getGruppi().iterator().next();
		final List<GrmEntity> test = dao.findAllByGroup(g);
		assertEquals(1, test.size());
		assertEquals(g.getId(), test.get(0).getId());
	}
	@Test(expected = NullPointerException.class)
	public void testFindAllByGroupThrowsExceptionWnenGroupIsNull() {
		dao.findAllByGroup(null);
	}


	GrmEntity createOne(int id) {
		getEm().getTransaction().begin();
		GrmEntity e = new GrmEntity();
		e.setId(id);
		e.setCodice(RandomStringUtils.random(3));
		e.setDescrizione(RandomStringUtils.random(30));
		e = getEm().merge(e);
		getEm().getTransaction().commit();
		return e;
	}

	GrmEntity createOneWithGroup(int id) {
		GrmEntity e = createOne(id);
		GroupEntity g = new GroupEntity();
		g.setId(id);
		g.setCodiceGruppo(RandomStringUtils.random(10));
		g.setDescrizione(RandomStringUtils.random(30));
		getEm().getTransaction().begin();
		g.addGrm(e);
		g = getEm().merge(g);
		getEm().getTransaction().commit();
		return e;
	}
}
