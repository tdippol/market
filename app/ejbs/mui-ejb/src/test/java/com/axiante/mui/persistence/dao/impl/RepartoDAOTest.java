package com.axiante.mui.persistence.dao.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.RepartoDAO;
import com.axiante.mui.persistence.entity.RepartoEntity;

public class RepartoDAOTest extends DaoTest{

	@Inject RepartoDAO dao;

	@Rule
	public WeldInitiator weld = WeldInitiator
	.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaRepartoDAO.class)
	.activate(RequestScoped.class).inject(this).build();


	@Test
	public void testFindAllReturnsCorrectData() {
		createOne(1);
		assertTrue(dao.findAll().size()>0);
	}

	private RepartoEntity createOne(Integer id) {
		RepartoEntity entity = new RepartoEntity();
		entity.setId(id);
		entity.setCodiceReparto(RandomStringUtils.random(2,true,false));
		entity.setDescrizione(RandomStringUtils.random(30,true,false));
		try {
			getEm().getTransaction().begin();
			entity = getEm().merge(entity);
			getEm().persist(entity);
			getEm().getTransaction().commit();
		} catch ( Exception e) {
			fail("error setting up table");
		}
		return null;
	}
}
