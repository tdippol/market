package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.GruppoCanaleDAO;
import com.axiante.mui.persistence.entity.CanaleEntity;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoCanaleEntity;
import com.axiante.mui.persistence.entity.GruppoCanaleId;
import com.axiante.mui.persistence.entity.GruppoCompratoreEntity;
import org.hamcrest.CoreMatchers;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.matchers.Any;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GruppoCanaleDAOTest extends DaoTest{
	@Inject
	GruppoCanaleDAO dao;

	@Spy
	@InjectMocks
	JpaGruppoCanaleDAO gruppoCanaleDAO;

	@Rule
	public WeldInitiator weld  = WeldInitiator
	.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaGruppoCanaleDAO.class)
	.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	@Test(expected = NullPointerException.class)

	public void whenGruppoAndCanaleIsNullfindByGruppoAndCanaleThrowsException() {
		dao.findByGruppoAndCanale(null, null);
	}

	@Test(expected = NullPointerException.class)
	public void whenGruppoIsNullfindByGruppoAndCanaleThrowsException() {
		GroupEntity g = Mockito.mock(GroupEntity.class);
		dao.findByGruppoAndCanale(g, null);
	}

	@Test(expected = NullPointerException.class)
	public void whenCanaleIsNullfindByGruppoAndCanaleThrowsException() {
		CanaleEntity c = Mockito.mock(CanaleEntity.class);
		dao.findByGruppoAndCanale(null, c);
	}

	@Test(expected = NullPointerException.class)
	public void whenGruppoIsNullfindCreatableCanaliByGruppoThrowsException() {
		dao.findCreatableCanaliByGruppo(null);
	}

	@Test(expected = NullPointerException.class)
	public void save_givenNullEntity_shouldThrowException() {
		dao.save(null);
	}

	@Test(expected = NullPointerException.class)
	public void findOwnedCanaliByGruppo_givenNullGroup_shouldThrowException() {
		dao.findOwnedCanaliByGruppi(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void findOwnedCanaliByGruppo_givenEmptyGroupList_shouldThrowException() {
		dao.findOwnedCanaliByGruppi(Collections.emptyList());
	}

	@Test
	public void findOwnedCanaliByGruppo_givenGroupList_shouldReturnOnlyChannelsWithFlagOwnerTrue() {
		// Group1: channel1 [T], channel4 [F]
		// Group2: channel2 [F]
		// Group3: channel3 [T], channel4 [T]
		GroupEntity group1 = createGroup("GR1", "Gruppo 1");
		GroupEntity group2 = createGroup("GR2", "Gruppo 2");
		GroupEntity group3 = createGroup("GR3", "Gruppo 3");
		CanaleEntity channel1 = createChannel(10L, "Canale 10");
		CanaleEntity channel2 = createChannel(20L, "Canale 20");
		CanaleEntity channel3 = createChannel(30L, "Canale 30");
		CanaleEntity channel4 = createChannel(40L, "Canale 40");
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			final GruppoCanaleEntity gruppoCanale1a = createGruppoCanale(group1, channel1, true);
			final GruppoCanaleEntity gruppoCanale1b = createGruppoCanale(group1, channel4, false);
			final GruppoCanaleEntity gruppoCanale2 = createGruppoCanale(group2, channel2, false);
			final GruppoCanaleEntity gruppoCanale3a = createGruppoCanale(group3, channel3, true);
			final GruppoCanaleEntity gruppoCanale3b = createGruppoCanale(group3, channel4, true);
			persist(channel1, channel2, channel3, channel4, group1, group2, group3,
					gruppoCanale1a, gruppoCanale1b, gruppoCanale2, gruppoCanale3a, gruppoCanale3b);
			em.getTransaction().commit();
			List<CanaleEntity> canali = dao.findOwnedCanaliByGruppi(Arrays.asList(group1, group2));
			assertFalse(canali.isEmpty());
			assertEquals(1, canali.size());
			assertEquals(channel1, canali.get(0));
			canali = dao.findOwnedCanaliByGruppi(Collections.singletonList(group2));
			assertTrue(canali.isEmpty());
			canali = dao.findOwnedCanaliByGruppi(Arrays.asList(group1, group2, group3));
			assertFalse(canali.isEmpty());
			assertEquals(3, canali.size());
			assertTrue(canali.contains(channel1));
			assertTrue(canali.contains(channel3));
			assertTrue(canali.contains(channel4));
		}
	}

	@Test
	public void testWhenToggleCreateFlagIsChanged() {
		List<GruppoCanaleEntity> g = dao.findAll();
		GruppoCanaleEntity test = null;
		if ( (g != null) && (g.size() > 0) ) {
			test = g.get(0);
		} else {
			test = createOne();
		}
		boolean prec = test.getCreate();
		getEm().getTransaction().begin();
		dao.toggleFlagCreate(test);
		test = dao.findByGruppoAndCanale(test.getGruppo(), test.getCanale());
		assertNotEquals(prec, test.getCreate());
	}

	@Test(expected = NullPointerException.class)
	public void findOwnershipCodiciCanaleByGruppi_givenNullList_shouldThrowException() {
		dao.findOwnershipCodiciCanaleByGruppi(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void findOwnershipCodiciCanaleByGruppi_givenEmptyList_shouldThrowException() {
		dao.findOwnershipCodiciCanaleByGruppi(new ArrayList<>());
	}

	@Test
	public void findOwnershipCodiciCanaleByGruppi_givenList_shouldReturnOnlyAvailableChannels() {
		// Group1: channel1 [T], channel4 [F]
		// Group2: channel2 [F]
		// Group3: channel3 [T], channel4 [T]
		GroupEntity group1 = createGroup("GR1", "Gruppo 1");
		GroupEntity group2 = createGroup("GR2", "Gruppo 2");
		GroupEntity group3 = createGroup("GR3", "Gruppo 3");
		GroupEntity group4 = createGroup("GR4", "Gruppo 4");
		CanaleEntity channel1 = createChannel(10L, "Canale 10");
		CanaleEntity channel2 = createChannel(20L, "Canale 20");
		CompratoreEntity buyer1 = createBuyer(1, "S01", "Buyer 1");
		CompratoreEntity buyer2 = createBuyer(2, "S02", "Buyer 2");
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			// persist
			final GruppoCanaleEntity gc1a = createGruppoCanale(group1, channel1, false, true);
			final GruppoCanaleEntity gc1b = createGruppoCanale(group1, channel2, false, true);
			final GruppoCanaleEntity gc2a = createGruppoCanale(group2, channel1, false, true);
			final GruppoCanaleEntity gc2b = createGruppoCanale(group2, channel2, false, true);
			final GruppoCanaleEntity gc3a = createGruppoCanale(group3, channel1, false, false);
			final GruppoCanaleEntity gc3b = createGruppoCanale(group3, channel2, false, false);
			final GruppoCanaleEntity gc4a = createGruppoCanale(group4, channel1, false, true);
			final GruppoCanaleEntity gc4b = createGruppoCanale(group4, channel2, false, true);
			final GruppoCompratoreEntity gb1 = createGruppoCompratore(group1, buyer1, PianificazioneSecurityEnum.WRITE);
			final GruppoCompratoreEntity gb2 = createGruppoCompratore(group2, buyer2, PianificazioneSecurityEnum.WRITE);
			final GruppoCompratoreEntity gb3a = createGruppoCompratore(group3, buyer1, PianificazioneSecurityEnum.WRITE);
			final GruppoCompratoreEntity gb3b = createGruppoCompratore(group3, buyer2, PianificazioneSecurityEnum.WRITE);
			final GruppoCompratoreEntity gb4a = createGruppoCompratore(group4, buyer1, PianificazioneSecurityEnum.READ);
			final GruppoCompratoreEntity gb4b = createGruppoCompratore(group4, buyer2, PianificazioneSecurityEnum.WRITE);
			persist(channel1, channel2, group1, group2, group3, group4, buyer1, buyer2,
					gc1a, gc1b, gc2a, gc2b, gc3a, gc3b, gc4a, gc4b,
					gb1, gb2, gb3a, gb3b, gb4a, gb4b);
			em.getTransaction().commit();
			List<Long> canali = dao.findOwnershipCodiciCanaleByGruppi(Arrays.asList(group1, group2));
			assertEquals(2, canali.size());
			assertTrue(canali.contains(10L));
			assertTrue(canali.contains(20L));
			canali = dao.findOwnershipCodiciCanaleByGruppi(Collections.singletonList(group3));
			assertTrue(canali.isEmpty());
			canali = dao.findOwnershipCodiciCanaleByGruppi(Collections.singletonList(group4));
			assertEquals(2, canali.size());
			assertTrue(canali.contains(10L));
			assertTrue(canali.contains(20L));
		}
	}

	@Test(expected = NullPointerException.class)
	public void findOwnershipCodiciCanaleByGruppiAndCompratori_givenNullGruppi_shouldThrowException() {
		dao.findOwnershipCodiciCanaleByGruppiAndCompratori(null, Arrays.asList("FOO", "BAR"));
	}

	@Test(expected = NullPointerException.class)
	public void findOwnershipCodiciCanaleByGruppiAndCompratori_givenNullCompratori_shouldThrowException() {
		dao.findOwnershipCodiciCanaleByGruppiAndCompratori(Arrays.asList("FOO", "BAR"), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void findOwnershipCodiciCanaleByGruppiAndCompratori_givenEmptyGruppi_shouldReturnEmptyList() {
		dao.findOwnershipCodiciCanaleByGruppiAndCompratori(new ArrayList<>(), Arrays.asList("FOO", "BAR"));
	}

	@Test
	public void findOwnershipCodiciCanaleByGruppiAndCompratori_shouldReturnCanali_whenFlagOwnerInGruppoCanale() {
		GroupEntity group1 = createGroup("GR1", "Gruppo 1");
		GroupEntity group2 = createGroup("GR2", "Gruppo 2");
		GroupEntity group3 = createGroup("GR3", "Gruppo 3");
		GroupEntity group4 = createGroup("GR4", "Gruppo 4");
		GroupEntity group5 = createGroup("GR5", "Gruppo 5");
		GroupEntity group6 = createGroup("GR6", "Gruppo 6");
		GroupEntity group7 = createGroup("GR7", "Gruppo 7");
		CanaleEntity channel1 = createChannel(10L, "Canale 10");
		CanaleEntity channel2 = createChannel(20L, "Canale 20");
		CompratoreEntity buyer1 = createBuyer(1, "S01", "Buyer 1");	// S91
		CompratoreEntity buyer2 = createBuyer(2, "S02", "Buyer 2");	// S20
		CompratoreEntity buyer3 = createBuyer(3, "S03", "Buyer 3");	// S16
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			// persist
			final GruppoCanaleEntity gc1a = createGruppoCanale(group1, channel1, false, true);
			final GruppoCanaleEntity gc1b = createGruppoCanale(group1, channel2, false, true);
			final GruppoCanaleEntity gc2a = createGruppoCanale(group2, channel1, false, true);
			final GruppoCanaleEntity gc2b = createGruppoCanale(group2, channel2, false, true);
			final GruppoCanaleEntity gc3a = createGruppoCanale(group3, channel1, false, false);
			final GruppoCanaleEntity gc3b = createGruppoCanale(group3, channel2, false, false);
			final GruppoCanaleEntity gc4a = createGruppoCanale(group4, channel1, true, true);
			final GruppoCanaleEntity gc4b = createGruppoCanale(group4, channel2, true, true);
			final GruppoCanaleEntity gc5a = createGruppoCanale(group5, channel1, false, true);
			final GruppoCanaleEntity gc5b = createGruppoCanale(group5, channel2, false, true);
			final GruppoCanaleEntity gc6a = createGruppoCanale(group6, channel1, false, true);
			final GruppoCanaleEntity gc6b = createGruppoCanale(group6, channel2, false, true);
			final GruppoCanaleEntity gc7a = createGruppoCanale(group7, channel1, false, true);
			final GruppoCanaleEntity gc7b = createGruppoCanale(group7, channel2, false, true);
			final GruppoCompratoreEntity gb1 = createGruppoCompratore(group1, buyer1, PianificazioneSecurityEnum.WRITE);
			final GruppoCompratoreEntity gb2 = createGruppoCompratore(group2, buyer2, PianificazioneSecurityEnum.WRITE);
			final GruppoCompratoreEntity gb3a = createGruppoCompratore(group3, buyer1, PianificazioneSecurityEnum.WRITE);
			final GruppoCompratoreEntity gb3b = createGruppoCompratore(group3, buyer2, PianificazioneSecurityEnum.WRITE);
			final GruppoCompratoreEntity gb4a = createGruppoCompratore(group4, buyer1, PianificazioneSecurityEnum.READ);
			final GruppoCompratoreEntity gb4b = createGruppoCompratore(group4, buyer2, PianificazioneSecurityEnum.READ);
			final GruppoCompratoreEntity gb4c = createGruppoCompratore(group4, buyer3, PianificazioneSecurityEnum.READ);
			final GruppoCompratoreEntity gb5 = createGruppoCompratore(group5, buyer3, PianificazioneSecurityEnum.WRITE);
			final GruppoCompratoreEntity gb6 = createGruppoCompratore(group6, buyer2, PianificazioneSecurityEnum.READ);
			final GruppoCompratoreEntity gb7a = createGruppoCompratore(group7, buyer3, PianificazioneSecurityEnum.READ);
			final GruppoCompratoreEntity gb7b = createGruppoCompratore(group7, buyer2, PianificazioneSecurityEnum.WRITE);
			persist(channel1, channel2, group1, group2, group3, group4, group5, group6, group7, buyer1, buyer2, buyer3,
					gc1a, gc1b, gc2a, gc2b, gc3a, gc3b, gc4a, gc4b, gc5a, gc5b, gc6a, gc6b, gc7a, gc7b,
					gb1, gb2, gb3a, gb3b, gb4a, gb4b, gb4c, gb5, gb6, gb7a, gb7b);
			em.getTransaction().commit();
		}
		assertEquals(2, dao.findOwnershipCodiciCanaleByGruppiAndCompratori(
				Collections.singletonList("GR5"), Collections.singletonList("S03")).size());
		assertEquals(2, dao.findOwnershipCodiciCanaleByGruppiAndCompratori(
				Collections.singletonList("GR1"), Collections.singletonList("S01")).size());
		assertEquals(2, dao.findOwnershipCodiciCanaleByGruppiAndCompratori(
				Arrays.asList("GR1", "GR2"), Arrays.asList("S01", "S02")).size());
		assertEquals(2, dao.findOwnershipCodiciCanaleByGruppiAndCompratori(
				Collections.singletonList("GR4"), Collections.emptyList()).size());
	}
	// given a mock of my entity manager that returns 0 elements when createnamedquery is called
	// then a call to findByCodiceGruppoAndCodiceCanale should throw a NoResultException and the message should contain "Nessun valore trovato per gruppo "
	@Test
	public void testFindByCodiceGruppoAndCodiceCanaleThrowsNoResultExceptionWhenNoGruppoAndCanaleFound() {
		TypedQuery q = Mockito.mock(TypedQuery.class);
		when(q.getSingleResult()).thenReturn(0L);
		when(q.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(q);
		when(q.setParameter(Mockito.anyString(), Mockito.anyLong())).thenReturn(q);
		when(mockedEm.createNamedQuery("GruppoCanaleEntity.countByCodiceGruppoAndCodiceCanale", Long.class)).thenReturn(q);
		when(gruppoCanaleDAO.getEm()).thenReturn(mockedEm);
		expectedException.expect(NoResultException.class);
		expectedException.expectMessage("Nessun valore trovato per gruppo = GR1 e canale = 1");
		gruppoCanaleDAO.findByCodiceGruppoAndCodiceCanale("GR1", 1L);
	}

	@Test
	public void testFindByCodiceGruppoAndCodiceCanaleThrowsNonUniqueExceptionWhenMultipleGruppoAndCanaleFound() {
		TypedQuery q = Mockito.mock(TypedQuery.class);
		when(q.getSingleResult()).thenReturn(2L);
		when(q.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(q);
		when(q.setParameter(Mockito.anyString(), Mockito.anyLong())).thenReturn(q);
		when(mockedEm.createNamedQuery("GruppoCanaleEntity.countByCodiceGruppoAndCodiceCanale", Long.class)).thenReturn(q);
		when(gruppoCanaleDAO.getEm()).thenReturn(mockedEm);
		expectedException.expect(NonUniqueResultException.class);
		expectedException.expectMessage("Trovati 2 valori per gruppo = GR1 e canale = 1");
		gruppoCanaleDAO.findByCodiceGruppoAndCodiceCanale("GR1", 1L);
	}

	@Test
	public void testFindByCodiceGruppoAndCodiceCanaleReturnsGruppoCanaleEntityWhenSingleResultFound() {
		GruppoCanaleEntity expected = new GruppoCanaleEntity();
		GruppoCanaleId expectedId = mock(GruppoCanaleId.class);
		expected.setId(expectedId);
		// make sure to return 1 result
		TypedQuery q = Mockito.mock(TypedQuery.class);
		when(q.getSingleResult()).thenReturn(1L);
		when(q.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(q);
		when(q.setParameter(Mockito.anyString(), Mockito.anyLong())).thenReturn(q);
		when(mockedEm.createNamedQuery("GruppoCanaleEntity.countByCodiceGruppoAndCodiceCanale", Long.class)).thenReturn(q);
		// make sure to return the expected entity
		TypedQuery q1 = Mockito.mock(TypedQuery.class);
		when(q1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(q1);
		when(q1.setParameter(Mockito.anyString(), Mockito.anyLong())).thenReturn(q1);
		when(mockedEm.createNamedQuery("GruppoCanaleEntity.findByCodiceGruppoAndCodiceCanale", GruppoCanaleEntity.class)).thenReturn(q1);
		when(q1.getSingleResult()).thenReturn(expected);

		when(gruppoCanaleDAO.getEm()).thenReturn(mockedEm);

		assertNotNull(gruppoCanaleDAO.findByCodiceGruppoAndCodiceCanale("GR1", 1L));
		verify(q1).setParameter("gruppo", "GR1");
		verify(q1).setParameter("canale", 1L);
		verify(q1).getSingleResult();
		verify(gruppoCanaleDAO, times(2)).getEm();
		verify(mockedEm).createNamedQuery("GruppoCanaleEntity.countByCodiceGruppoAndCodiceCanale", Long.class);
		verify(mockedEm).createNamedQuery("GruppoCanaleEntity.findByCodiceGruppoAndCodiceCanale", GruppoCanaleEntity.class);
	}

	@Test
	public void testFindAllByGruppo() {
		GroupEntity g = new GroupEntity();
		g.setId(1);
		TypedQuery q = Mockito.mock(TypedQuery.class);
		when(q.setParameter("gruppo", g)).thenReturn(q);
		when(gruppoCanaleDAO.getEm()).thenReturn(mockedEm);
		when(mockedEm.createNamedQuery("GruppoCanaleEntity.findAllByGruppo", GruppoCanaleEntity.class)).thenReturn(q);

		gruppoCanaleDAO.findAllByGruppo(g);
		verify(q).setParameter("gruppo", g);
		verify(q).getResultList();
		verify(gruppoCanaleDAO).getEm();
		verify(mockedEm).createNamedQuery("GruppoCanaleEntity.findAllByGruppo", GruppoCanaleEntity.class);
	}

	@Test
	public void testFindCreatableCanaliByGruppo() {
		GroupEntity g = new GroupEntity();
		g.setId(1);
		List<GroupEntity> list = Collections.singletonList(g);

		TypedQuery q = Mockito.mock(TypedQuery.class);
		when(q.setParameter("gruppi", list)).thenReturn(q);
		when(gruppoCanaleDAO.getEm()).thenReturn(mockedEm);
		when(mockedEm.createNamedQuery("GruppoCanaleEntity.findCreatableCanaliByGruppo", CanaleEntity.class)).thenReturn(q);

		gruppoCanaleDAO.findCreatableCanaliByGruppo(list);
		verify(q).setParameter("gruppi", list);
		verify(q).getResultList();
		verify(gruppoCanaleDAO).getEm();
		verify(mockedEm).createNamedQuery("GruppoCanaleEntity.findCreatableCanaliByGruppo", CanaleEntity.class);
	}

	@Test
	public void testSave(){
		GruppoCanaleEntity entity = mock(GruppoCanaleEntity.class);
		EntityManager mockedEm = mock(EntityManager.class); // mock the entity manager to override DaoTest's em
		when(gruppoCanaleDAO.getEm()).thenReturn(mockedEm);

		gruppoCanaleDAO.save(entity);

		verify(gruppoCanaleDAO, times(2)).getEm();
		verify(mockedEm).merge(entity);
		verify(mockedEm).flush();
	}

	private void persist(Object... entities) {
		for (Object e : entities) {
			em.persist(e);
		}
	}

	private GruppoCompratoreEntity createGruppoCompratore(GroupEntity group, CompratoreEntity buyer, PianificazioneSecurityEnum security) {
		final GruppoCompratoreEntity entity = new GruppoCompratoreEntity();
		entity.setGruppo(group);
		entity.setCompratore(buyer);
		entity.setTipoAccesso(security);
		return entity;
	}

	private GruppoCanaleEntity createGruppoCanale(GroupEntity group, CanaleEntity channel, boolean owner) {
		return createGruppoCanale(group, channel, owner, false);
	}

	private GruppoCanaleEntity createGruppoCanale(GroupEntity group, CanaleEntity channel, boolean owner, boolean create) {
		final GruppoCanaleEntity entity = new GruppoCanaleEntity();
		entity.setGruppo(group);
		entity.setCanale(channel);
		entity.setOwner(owner);
		entity.setCreate(create);
		return entity;
	}

	private GroupEntity createGroup(String code, String desc) {
		final GroupEntity entity = new GroupEntity();
		entity.setCodiceGruppo(code);
		entity.setDescrizione(desc);
		return entity;
	}

	private CanaleEntity createChannel(Long code, String desc) {
		final CanaleEntity entity = new CanaleEntity();
		entity.setId(code.intValue());
		entity.setCodiceCanale(code);
		entity.setDescrizione(desc);
		return entity;
	}

	private CompratoreEntity createBuyer(Integer id, String code, String desc) {
		final CompratoreEntity entity = new CompratoreEntity();
		entity.setId(id);
		entity.setCodiceCompratore(code);
		entity.setDescrizione(desc);
		entity.setFlagAttivo(1L);
		return entity;
	}

	private GruppoCanaleEntity createOne() {
		GruppoCanaleEntity e = null;
		GroupEntity gruppo = new GroupEntity();

		gruppo.setDescrizione("test toggle");
		gruppo.setCodiceGruppo("CCDD");

		CanaleEntity canale = new CanaleEntity();
		canale.setId(1234567);
		canale.setCodiceCanale(1234567L);
		canale.setDescrizione("test toggle");
		canale.setSigla("sigla");
		canale.addGroup(gruppo);

		getEm().getTransaction().begin();
		canale = getEm().merge(canale);
		getEm().persist(canale);
		getEm().getTransaction().commit();

		e = dao.findByGruppoAndCanale(canale.getGroups().iterator().next() , canale);

		return e;
	}

}
