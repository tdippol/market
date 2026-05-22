package com.axiante.mui.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.GruppoRepartoDAO;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoRepartoEntity;
import com.axiante.mui.persistence.entity.RepartoEntity;

import java.util.Arrays;
import java.util.Collections;

public class JpaGruppoRepartoDAOTest extends DaoTest{

	@Inject GruppoRepartoDAO dao;

	@Rule
	public WeldInitiator weld  = WeldInitiator
	.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaGruppoRepartoDAO.class)
	.activate(RequestScoped.class).inject(this).build();


	@Test
	public void testFindByGruppoAndReparto() {
		final GruppoRepartoEntity e = createOne(1, 1);
		assertNotNull(e);
		final GruppoRepartoEntity t = dao.findByGruppoAndReparto(e.getGruppo(), e.getReparto());
		assertNotNull(t);
		assertNotNull(t.getId());
		assertEquals(t.getGruppo().getId(), e.getGruppo().getId());
		assertEquals(t.getReparto().getId(), e.getReparto().getId());
	}

	@Test
	public void testSave() {
		GruppoRepartoEntity e = createOne(1, 1);
		getEm().getTransaction().begin();
		e = dao.save(e);
		getEm().getTransaction().commit();

		GruppoRepartoEntity t = dao.findByGruppoAndReparto(e.getGruppo(), e.getReparto());
		assertNotNull(t);
		assertEquals(t.getTipoAccesso(), PianificazioneSecurityEnum.READ);

		t.setTipoAccesso(PianificazioneSecurityEnum.WRITE);
		getEm().getTransaction().begin();
		t = dao.save(t);
		getEm().getTransaction().commit();
		e = dao.findByGruppoAndReparto(e.getGruppo(), e.getReparto());
		assertEquals(e.getTipoAccesso(), PianificazioneSecurityEnum.WRITE);
	}

	@Test
	public void testFindAllByGruppo() {
		final GruppoRepartoEntity e = createOne(1, 1);
		assertNotNull(e);
		assertTrue(dao.findAllByGruppo(e.getGruppo()).size() > 0);
	}

	@Test(expected = NullPointerException.class)
	public void remove_givenNullEntity_shouldThrowException() {
		dao.remove(null);
	}

	@Test
	public void remove_givenExistingEntity_shouldRemoveIt() {
		if (!em.getTransaction().isActive()) {
			final String sql = "SELECT COUNT(e) FROM GruppoRepartoEntity e";
			final GruppoRepartoEntity entity1 = create(101, "G1", "GRUPPO1",
					101, "R1", "REPARTO1");
			final GruppoRepartoEntity entity2 = create(102, "G2", "GRUPPO2",
					102, "R2", "REPARTO2");
			em.getTransaction().begin();
			long before = em.createQuery(sql, Long.class).getSingleResult();
			dao.remove(entity1);
			long after = em.createQuery(sql, Long.class).getSingleResult();
			em.getTransaction().commit();
			assertEquals(before - 1, after);
		}
	}

	@Test(expected = NullPointerException.class)
	public void countByRepartoIdAndCodiciGruppo_givenNullRepartoId_shouldThrowException() {
		dao.countByRepartoIdAndCodiciGruppo(null, Arrays.asList("GR1", "GR2"));
	}

	@Test(expected = NullPointerException.class)
	public void countByRepartoIdAndCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
		dao.countByRepartoIdAndCodiciGruppo(1, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void countByRepartoIdAndCodiciGruppo_givenEmptyCodiciGruppo_shouldThrowException() {
		dao.countByRepartoIdAndCodiciGruppo(1, Collections.emptyList());
	}

	@Test
	public void countByRepartoIdAndCodiciGruppo_givenRepartoIdAndCodiciGruppo_shouldReturnCount() {
		final GruppoRepartoEntity entity = create(null, "GRF", "GRUPPO FOO",
				1, "RB", "REPARTO BAR");
		assertEquals(1, (long) dao.countByRepartoIdAndCodiciGruppo(entity.getReparto().getId(), Arrays.asList("GRF", "GRX")));
	}

	@Test(expected = NullPointerException.class)
	public void countWriteableByRepartoIdAndCodiciGruppo_givenNullRepartoId_shouldThrowException() {
		dao.countWriteableByRepartoIdAndCodiciGruppo(null, Arrays.asList("GR1", "GR2"));
	}

	@Test(expected = NullPointerException.class)
	public void countWriteableByRepartoIdAndCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
		dao.countWriteableByRepartoIdAndCodiciGruppo(1, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void countWriteableByRepartoIdAndCodiciGruppo_givenEmptyCodiciGruppo_shouldThrowException() {
		dao.countWriteableByRepartoIdAndCodiciGruppo(1, Collections.emptyList());
	}

	@Test
	public void countWriteableByRepartoIdAndCodiciGruppo_givenRepartoIdAndCodiciGruppo_shouldReturnCount() {
		RepartoEntity reparto = createReparto(1, "RP");
		GroupEntity gruppo1 = createGruppo("GR-A");
		GroupEntity gruppo2 = createGruppo("GR-B");
		createGruppoReparto(gruppo1, reparto, null);
		createGruppoReparto(gruppo2, reparto, PianificazioneSecurityEnum.WRITE);
		assertEquals(1, (long) dao.countWriteableByRepartoIdAndCodiciGruppo(reparto.getId(),
				Arrays.asList("GR-B", "GR-X")));
		assertEquals(0, (long) dao.countWriteableByRepartoIdAndCodiciGruppo(reparto.getId(),
				Arrays.asList("GR-A", "GR-X")));
	}

	private void createGruppoReparto(GroupEntity gruppo, RepartoEntity reparto, PianificazioneSecurityEnum security) {
		GruppoRepartoEntity entity = new GruppoRepartoEntity();
		entity.setGruppo(gruppo);
		entity.setReparto(reparto);
		if (security != null) {
			entity.setTipoAccesso(security);
		}
		try {
			getEm().getTransaction().begin();
			entity = getEm().merge(entity);
			getEm().persist(entity);
			getEm().getTransaction().commit();
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	private GroupEntity createGruppo(String codice) {
		GroupEntity entity = new GroupEntity();
		entity.setCodiceGruppo(codice);
		try {
			getEm().getTransaction().begin();
			entity = getEm().merge(entity);
			getEm().persist(entity);
			getEm().getTransaction().commit();
			return entity;
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		return null;
	}

	private RepartoEntity createReparto(Integer id, String codice) {
		RepartoEntity entity = new RepartoEntity();
		entity.setId(id);
		entity.setCodiceReparto(codice);
		try {
			getEm().getTransaction().begin();
			entity = getEm().merge(entity);
			getEm().persist(entity);
			getEm().getTransaction().commit();
			return entity;
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		return null;
	}

	private GruppoRepartoEntity create(Integer idGruppo, String codiceGruppo, String descGruppo,
									   Integer idReparto, String codiceReparto, String descReparto) {
		GroupEntity gruppo = new GroupEntity();
		gruppo.setId(idGruppo);
		gruppo.setCodiceGruppo(codiceGruppo);
		gruppo.setDescrizione(descGruppo);
		RepartoEntity reparto = new RepartoEntity();
		reparto.setId(idReparto);
		reparto.setCodiceReparto(codiceReparto);
		reparto.setDescrizione(descReparto);
		GruppoRepartoEntity gruppoReparto = new GruppoRepartoEntity();
		try {
			getEm().getTransaction().begin();
			gruppo.addReparto(reparto);
			gruppo = getEm().merge(gruppo);
			getEm().persist(gruppo);
			getEm().getTransaction().commit();
			gruppoReparto.setGruppo(gruppo);
			gruppoReparto.setReparto(gruppo.getReparti().iterator().next());
			return gruppoReparto;
		} catch (Exception e) {
			fail(e.getMessage());
		}
		return null;
	}

	private GruppoRepartoEntity createOne(Integer idGruppo, Integer idReparto) {
		GruppoRepartoEntity ret = new GruppoRepartoEntity();

		GroupEntity gruppo = new GroupEntity();
		gruppo.setId(idGruppo);
		gruppo.setCodiceGruppo(RandomStringUtils.random(10,true,false));
		gruppo.setDescrizione(RandomStringUtils.random(30,true,false));

		RepartoEntity reparto = new RepartoEntity();
		reparto.setId(idReparto);
		reparto.setCodiceReparto(RandomStringUtils.random(2,true,false));
		reparto.setDescrizione(RandomStringUtils.random(30,true,false));

		gruppo.addReparto(reparto);
		try {
			getEm().getTransaction().begin();
			gruppo.addReparto(reparto);
			gruppo = getEm().merge(gruppo);
			getEm().persist(gruppo);
			getEm().getTransaction().commit();
			ret.setGruppo(gruppo);
			ret.setReparto(gruppo.getReparti().iterator().next());
			return ret;
		} catch (Exception e) {
			fail(e.getMessage());
		}
		return null;
	}
}

