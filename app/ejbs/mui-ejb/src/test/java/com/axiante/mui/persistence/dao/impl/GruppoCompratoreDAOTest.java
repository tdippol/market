package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.GruppoCompratoreDAO;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoCompratoreEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class GruppoCompratoreDAOTest extends DaoTest{
	@Inject GruppoCompratoreDAO dao;

	@Spy
	@InjectMocks
	JpaGruppoCompratoreDAOImpl compratoreDAO;

	@Rule
	public WeldInitiator weld = WeldInitiator
	.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaGruppoCompratoreDAOImpl.class)
	.activate(RequestScoped.class).inject(this).build();


	@Test
	public void testFindAll() {
		createOneCompratoreWithGroup();
		assertTrue(dao.findAll().size() > 0);
	}

	@Test
	public void testFindAllByGroup() {
		GroupEntity g = createOneGroup();
		assertTrue(dao.findAllByGroup(g).size()>0);
	}
	@Test(expected = NullPointerException.class)
	public void testFindAllByGroupThrowsExceptionWhenGroupIsNull() {
		dao.findAllByGroup(null);
	}

	@Test
	public void testFindByGroupAndCompratore() {
		GruppoCompratoreEntity e = createOne();
		GruppoCompratoreEntity t = dao.findByGroupAndCompratore(e.getGruppo(), e.getCompratore());
		assertNull(e.getId());
		assertNotNull(t);
		assertNotNull(t.getId());
	}

	@Test(expected = NullPointerException.class)
	public void testFindByGroupAndCompratoreThrowsExceptionWhenGroupIsNull() {
		dao.findByGroupAndCompratore(null, mock(CompratoreEntity.class));
	}

	@Test(expected = NullPointerException.class)
	public void testFindByGroupAndCompratoreThrowsExceptionWhenCompratoreIsNull() {
		dao.findByGroupAndCompratore(mock(GroupEntity.class), null);
	}

	@Test
	public void testSave() {
		GruppoCompratoreEntity e = new GruppoCompratoreEntity();
		e.setGruppo(createOneGroup());
		e.setCompratore(createOneCompratore(1));
		e.setTipoAccesso(PianificazioneSecurityEnum.READ);
		assertNull(e.getId());
		getEm().getTransaction().begin();
		e = dao.save(e);
		getEm().getTransaction().commit();
		assertNotNull(e.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void findAllCodiciCompratoreByGroupAndTipoAccesso_givenNullGroup_shouldThrowException() {
		dao.findAllCodiciCompratoreByGroupAndTipoAccesso(null, PianificazioneSecurityEnum.WRITE);
	}

	@Test(expected = NullPointerException.class)
	public void findAllCodiciCompratoreByGroupAndTipoAccesso_givenNullSecurity_shouldThrowException() {
		final List<GroupEntity> group = Collections.singletonList(createOneGroup());
		dao.findAllCodiciCompratoreByGroupAndTipoAccesso(group, null);
	}

	@Test
	public void findAllCodiciCompratoreByGroupAndTipoAccesso_givenValidGroupAndSecurity_shouldReturnListOfEntities() {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			GroupEntity group = createGroup("SW", "Star Wars");
			final List<GroupEntity> groups = Collections.singletonList(group);
			final CompratoreEntity buyer1 = createCompratore(42, "Luke Skywalker");
			final CompratoreEntity buyer2 = createCompratore(23, "Ahsoka Tano");
			GruppoCompratoreEntity gc1 = createGruppoCompratore(group, buyer1, PianificazioneSecurityEnum.WRITE);
			GruppoCompratoreEntity gc2 = createGruppoCompratore(group, buyer2, PianificazioneSecurityEnum.READ);
			em.persist(group);
			em.persist(buyer1);
			em.persist(buyer2);
			em.persist(gc1);
			em.persist(gc2);
			em.getTransaction().commit();
			List<String> codes = dao.findAllCodiciCompratoreByGroupAndTipoAccesso(groups, PianificazioneSecurityEnum.WRITE);
			assertEquals(1, codes.size());
			assertEquals("S42", codes.get(0));
			codes = dao.findAllCodiciCompratoreByGroupAndTipoAccesso(groups, PianificazioneSecurityEnum.READ);
			assertEquals(1, codes.size());
			assertEquals("S23", codes.get(0));
		}
	}

	private GroupEntity createGroup(String code, String desc) {
		final GroupEntity entity = new GroupEntity();
		entity.setCodiceGruppo(code);
		entity.setDescrizione(desc);
		return entity;
	}

	private CompratoreEntity createCompratore(int code, String desc) {
		final CompratoreEntity entity = new CompratoreEntity();
		entity.setId(code);
		entity.setCodiceCompratore(String.format("S%02d", code));
		entity.setDescrizione(desc);
		entity.setFlagAttivo(1L);
		return entity;
	}

	private GruppoCompratoreEntity createGruppoCompratore(GroupEntity group, CompratoreEntity buyer,
			PianificazioneSecurityEnum security) {
		final GruppoCompratoreEntity entity = new GruppoCompratoreEntity();
		entity.setGruppo(group);
		entity.setCompratore(buyer);
		entity.setTipoAccesso(security);
		return entity;
	}

	private CompratoreEntity createOneCompratore(Integer id) {
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

	GroupEntity createOneGroup() {
		return createOneCompratoreWithGroup().getGruppi().iterator().next();
	}

	private CompratoreEntity createOneCompratoreWithGroup() {
		CompratoreEntity c = createOneCompratore(1);
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

	GruppoCompratoreEntity createOne() {
		CompratoreEntity e = createOneCompratoreWithGroup();
		GruppoCompratoreEntity g = new GruppoCompratoreEntity();
		g.setCompratore(e);
		g.setGruppo(e.getGruppi().iterator().next());

		return g;
	}
}
