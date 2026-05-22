package com.axiante.mui.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.entity.GruppoCanaleEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.GruppoGrmDAO;
import com.axiante.mui.persistence.entity.GrmEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoGrmEntity;
import com.axiante.mui.persistence.entity.GruppoGrmId;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GruppoGrmDAOTest extends DaoTest{

	@Inject
	GruppoGrmDAO dao;

	@Spy
	JpaGruppoGrmDAOImpl mockedDao;

	@Rule
	public WeldInitiator weld = WeldInitiator
	.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaGruppoGrmDAOImpl.class)
	.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	@Test
	public void testFindAll() {
		saveOne(createOne(1));
		assertTrue(dao.findAll().size() > 0);
	}

	@Test
	public void testFindByGroup() {
		GruppoGrmEntity expected = saveOne(createOne(1));
		assertTrue(dao.findByGroup(expected.getGruppo()).size()>0);
	}

	@Test(expected = NullPointerException.class)
	public void testFindByGroupThrowsExceptionWhenGruppoIsNull() {
		dao.findByGroup(null);
	}

	@Test(expected = NullPointerException.class)
	public void testFindByGroupAndGrmThrowsExceptionWhenGruppoIsNull() {
		dao.findByGroupAndGrm(null, mock(GrmEntity.class));
	}

	@Test(expected = NullPointerException.class)
	public void testFindByGroupAndGrmThrowsExceptionWhenGrmIsNull() {
		dao.findByGroupAndGrm(mock(GroupEntity.class), null);
	}

	@Test
	public void testFindByGroupAndGrm() {
		GruppoGrmEntity expected = saveOne(createOne(1));
		GruppoGrmEntity test = dao.findByGroupAndGrm(expected.getGruppo(), expected.getGrm());
		assertNotNull(test);
		assertTrue(test.getId().equals(expected.getId()));
	}

	@Test
	public void testFindByGroupAndGrmReturnsNullWhenNoDataFound(){
		EntityManager em = mock(EntityManager.class);
		when(mockedDao.getEm()).thenReturn(em);
		TypedQuery q = mock(TypedQuery.class);
		when(em.createNamedQuery("GruppoGrmEntity.countByGroupAndGrm", Long.class)).thenReturn(q);
		when(q.setParameter(anyString(), any())).thenReturn(q);
		when(q.getSingleResult()).thenReturn(0L);
		assertNull(mockedDao.findByGroupAndGrm(mock(GroupEntity.class), mock(GrmEntity.class)));


	}

	@Test
	public void testFindByGroupAndGrmReturnsNullWhenTooManyValuesFound(){
		EntityManager em = mock(EntityManager.class);
		when(mockedDao.getEm()).thenReturn(em);
		TypedQuery q = mock(TypedQuery.class);
		when(em.createNamedQuery("GruppoGrmEntity.countByGroupAndGrm", Long.class)).thenReturn(q);
		when(q.setParameter(anyString(), any())).thenReturn(q);
//		when(q.setParameter("gruppo", any(GroupEntity.class))).thenReturn(q);
		when(q.getSingleResult()).thenReturn(3L);
		assertNull(mockedDao.findByGroupAndGrm(mock(GroupEntity.class), mock(GrmEntity.class)));

		verify(q, times(1)).setParameter(any(String.class), any(GrmEntity.class));
		verify(q, times(1)).setParameter(any(String.class), any(GroupEntity.class));
		verify(q, times(1)).getSingleResult();
		verify(em, times(1)).createNamedQuery("GruppoGrmEntity.countByGroupAndGrm", Long.class);
		verify(em, times(0)).createNamedQuery("GruppoGrmEntity.findByGroupAndGrm", GruppoGrmEntity.class);
	}
	@Test(expected = NullPointerException.class)
	public void testSaveThrowsExceptionWhenEntityIsNull (){
		dao.save((GruppoGrmEntity) null);
	}

	@Test
	public void testSave(){
		GruppoGrmEntity test = createOne(1);
		try {
			getEm().getTransaction().begin();
			test = dao.save(test);
			getEm().getTransaction().commit();
		} catch ( Exception e) {
			fail(e.getMessage());
		}
		GruppoGrmId id = test.getId();
		test = getEm().find(GruppoGrmEntity.class, id);
		assertNotNull(test);
		assertTrue(test.getGrm().getId().equals(id.getGrmId()));
		assertTrue(test.getGruppo().getId().equals(id.getGroupId()));
	}
	@Test
	public void testSaveCollection() {
		List<GruppoGrmEntity> list = new ArrayList<>();
		for ( int i = 0 ; i < 10 ; i++ ) {
			list.add(mock(GruppoGrmEntity.class));
		}
		list.add(null);
		EntityManager em = mock(EntityManager.class);

		when(mockedDao.getEm()).thenReturn(em);
		when(em.merge(Mockito.any(GruppoGrmEntity.class))).thenReturn(null);
		Collection<GruppoGrmEntity> result = mockedDao.save(list);
		verify(em, times(list.size()-1)).merge(Mockito.any(GruppoGrmEntity.class));
		verify(em, times(1)).flush();
		assertNotNull(result);
		assertThat(result, org.hamcrest.Matchers.not (org.hamcrest.Matchers.empty()));
		assertThat(result.size(), org.hamcrest.Matchers.is(list.size()));
	}

	@Test
	public void testRemove(){
		GruppoGrmEntity test = saveOne(createOne(1));
		try {
			getEm().getTransaction().begin();
			dao.remove(test);
			getEm().getTransaction().commit();
		} catch ( Exception e) {
			fail(e.getMessage());
		}
		GruppoGrmId id = test.getId();
		test = getEm().find(GruppoGrmEntity.class, id);
		assertNull(test);
	}
	@Test
	public void testRemoveThrowsExceptionWhenSomethingGoesBadInEntityManager(){
		EntityManager em = mock(EntityManager.class);
		when(mockedDao.getEm()).thenReturn(em);
		GruppoGrmEntity entity = mock(GruppoGrmEntity.class);
		GroupEntity groupEntity = mock(GroupEntity.class);
		GrmEntity grmEntity = mock(GrmEntity.class);

		doThrow(new RuntimeException()).when(em).merge(Mockito.any(GruppoGrmEntity.class));
		try {
			mockedDao.remove(mock(GruppoGrmEntity.class));
			fail("Exception not thrown");
		} catch ( Exception e) {
			assertTrue(e instanceof RuntimeException);
		}
	}

	@Test(expected = NullPointerException.class)
	public void countByRepartoIdAndCodiciGruppo_givenNullRepartoId_shouldThrowException() {
		dao.countByGrmIdAndCodiciGruppo(null, Arrays.asList("GR1", "GR2"));
	}

	@Test(expected = NullPointerException.class)
	public void countByRepartoIdAndCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
		dao.countByGrmIdAndCodiciGruppo(1, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void countByRepartoIdAndCodiciGruppo_givenEmptyCodiciGruppo_shouldThrowException() {
		dao.countByGrmIdAndCodiciGruppo(1, Collections.emptyList());
	}

	@Test
	public void countByRepartoIdAndCodiciGruppo_givenRepartoIdAndCodiciGruppo_shouldReturnCount() {
		final GruppoGrmEntity entity = create("GRF", 1, "XY");
		assertEquals(1, (long) dao.countByGrmIdAndCodiciGruppo(entity.getGrm().getId(), Arrays.asList("GRF", "GRX")));
	}

	@Test(expected = NullPointerException.class)
	public void countWritableByGrmIdAndCodiciGruppo_givenNullRepartoId_shouldThrowException() {
		dao.countWritableByGrmIdAndCodiciGruppo(null, Arrays.asList("GR1", "GR2"));
	}

	@Test(expected = NullPointerException.class)
	public void countWritableByGrmIdAndCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
		dao.countWritableByGrmIdAndCodiciGruppo(1, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void countWritableByGrmIdAndCodiciGruppo_givenEmptyCodiciGruppo_shouldThrowException() {
		dao.countWritableByGrmIdAndCodiciGruppo(1, Collections.emptyList());
	}

	@Test
	public void countWriteableByRepartoIdAndCodiciGruppo_givenRepartoIdAndCodiciGruppo_shouldReturnCount() {
		GrmEntity grm = createGrm(1, "RP");
		GroupEntity gruppo1 = createGruppo("GR-A");
		GroupEntity gruppo2 = createGruppo("GR-B");
		createGruppoGrm(gruppo1, grm, null);
		createGruppoGrm(gruppo2, grm, PianificazioneSecurityEnum.WRITE);
		assertEquals(1, (long) dao.countWritableByGrmIdAndCodiciGruppo(grm.getId(),
				Arrays.asList("GR-B", "GR-X")));
		assertEquals(0, (long) dao.countWritableByGrmIdAndCodiciGruppo(grm.getId(),
				Arrays.asList("GR-A", "GR-X")));
	}

	@Test
	public void testFindAllByCodiciGruppoAndTipoAccessoWhenPassingEmptyList(){
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("codiciGruppo cannot be null or empty");
		dao.findAllByCodiciGruppoAndTipoAccesso(Collections.emptyList(), PianificazioneSecurityEnum.WRITE);
	}

	@Test
	public void findAllByCodiciGruppoAndTipoAccesso(){
		EntityManager em = mock(EntityManager.class);
		when(mockedDao.getEm()).thenReturn(em);
		TypedQuery<String> mockedQuery = mock(TypedQuery.class);
		when(em.createNamedQuery("GruppoGrmEntity.findAllByCodiciGruppoAndTipoAccesso", String.class))
				.thenReturn(mockedQuery);
		when(mockedQuery.setParameter("codiciGruppo", Arrays.asList("GR1", "GR2"))).thenReturn(mockedQuery);
		when(mockedQuery.setParameter("tipoAccesso", PianificazioneSecurityEnum.WRITE)).thenReturn(mockedQuery);
		mockedDao.findAllByCodiciGruppoAndTipoAccesso(Arrays.asList("GR1", "GR2"), PianificazioneSecurityEnum.WRITE);

		verify(mockedQuery).getResultList();
		verify(mockedQuery).setParameter("codiciGruppo", Arrays.asList("GR1", "GR2"));
		verify(mockedQuery).setParameter("tipoAccesso", PianificazioneSecurityEnum.WRITE);
		verify(em).createNamedQuery("GruppoGrmEntity.findAllByCodiciGruppoAndTipoAccesso", String.class);

	}

	@Test
	public void testRemoveAllByGruppo(){
		GroupEntity g = createGruppo("GR1");
		EntityManager em = mock(EntityManager.class);
		when(mockedDao.getEm()).thenReturn(em);
		TypedQuery query = mock(TypedQuery.class);
		when(em.createNamedQuery("GruppoGrmEntity.findByGroup", GruppoGrmEntity.class)).thenReturn(query);
		when(query.setParameter("gruppo", g)).thenReturn(query);
		GruppoGrmEntity GrGrm1 = mock(GruppoGrmEntity.class);
		GruppoGrmEntity GrGrm2 = mock(GruppoGrmEntity.class);
		List<GruppoGrmEntity> list = Arrays.asList(GrGrm1, GrGrm2);
		when(query.getResultList()).thenReturn(list);
		when(em.merge(any(GruppoGrmEntity.class))).thenReturn(GrGrm1, GrGrm2);
		mockedDao.removeAllByGruppo(g);
		// I have 2 return values, have to check that the method is called twice
		verify(em, times(2)).merge(any(GruppoGrmEntity.class));
		verify(em, times(2)).remove(any(GruppoGrmEntity.class));
	}

	private void createGruppoGrm(GroupEntity gruppo, GrmEntity grm, PianificazioneSecurityEnum security) {
		GruppoGrmEntity entity = new GruppoGrmEntity();
		entity.setGruppo(gruppo);
		entity.setGrm(grm);
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

	private GrmEntity createGrm(Integer id, String codice) {
		GrmEntity entity = new GrmEntity();
		entity.setId(id);
		entity.setCodice(codice);
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

	private GruppoGrmEntity create(String codiceGruppo, Integer idGrm, String codiceGrm) {
		GroupEntity gruppo = new GroupEntity();
		gruppo.setCodiceGruppo(codiceGruppo);
		GrmEntity grm = new GrmEntity();
		grm.setId(idGrm);
		grm.setCodice(codiceGrm);
		GruppoGrmEntity gruppoGrm = new GruppoGrmEntity();
		try {
			getEm().getTransaction().begin();
			gruppo.addGrm(grm);
			gruppo = getEm().merge(gruppo);
			getEm().persist(gruppo);
			getEm().getTransaction().commit();
			gruppoGrm.setGruppo(gruppo);
			gruppoGrm.setGrm(gruppo.getGrm().iterator().next());
			return gruppoGrm;
		} catch (Exception ex) {
			fail(ex.getMessage());
			return null;
		}
	}

	GruppoGrmEntity createOne(Integer idGrm) {
		GruppoGrmEntity e = new GruppoGrmEntity();
		GroupEntity g = new GroupEntity();
		GrmEntity grm = new GrmEntity();

		grm.setId(idGrm);
		grm.setCodice(RandomStringUtils.random(3));
		grm.setDescrizione(RandomStringUtils.random(30));

		g.setCodiceGruppo(RandomStringUtils.random(3));
		g.setDescrizione(RandomStringUtils.random(20));

		g.addGrm(grm);

		getEm().getTransaction().begin();
		g  = getEm().merge(g);
		getEm().persist(g);
		getEm().flush();
		getEm().getTransaction().commit();

		e.setGrm(grm);
		e.setGruppo(g);

		return e;
	}

	GruppoGrmEntity saveOne(GruppoGrmEntity g) {
		getEm().getTransaction().begin();
		g  = getEm().merge(g);
		getEm().persist(g);
		getEm().flush();
		getEm().getTransaction().commit();
		return g;
	}


}

