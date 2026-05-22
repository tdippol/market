package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CanalePromozioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.GruppoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class JpaCanalePromozioneDAOTest extends AbstractDaoTest {

	@Inject
	private GruppoPromozioneDAO groupDao;

	@Inject
	private CanalePromozioneDAO channelDao;

	@Inject
	private PromozioneTestataDAO promoDao;

	private GruppoPromozioneEntity group;
	private CanalePromozioneEntity channel;

	@Rule
	public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
			EntityManagerFactoryProducer.class, JpaPromozioneTestataDaoImpl.class, JpaCanalePromozioneDaoImpl.class,
			JpaGruppoPromozioneDaoImpl.class).activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		final String promoCode = "2020_00091";
		final String year = "2020";

		PromozioneTestataEntity promo = new PromozioneTestataEntity();
		promo.setCodicePromozione(promoCode);
		promo.setDataInizio(Date.from(LocalDate.of(2020, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		promo.setDataFine(Date.from(LocalDate.of(2020, 11, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		promo.setAnno(year);
		promo.setDescrizione("test crea descrizione");
		promo.setDescrizioneEstesa("test crea descrizione estesa");
		promo.setSemestre("II Semestre");
		promo.setNoteMarketing("test note crea marketing");

		openTransaction();
		group = createPromoGroup("0991", "GRUPPO-TEST-991");
		group = groupDao.persist(group);
		channel = createPromoChannel("channel92", 0L, 100L, null, group);
		channel = channelDao.persist(channel);
		promo.setMuiCanalePromozione(channel);
		CanalePromozioneEntity canale = createCanalePromozione(20L, 100L, 10L, 100L,
				"Descrizione test", group);
		channelDao.persist(canale);
		promoDao.persist(promo);
		commitTransaction();
	}

	@Test
	public void testFindAllByGroup() {
		try {
			List<CanalePromozioneEntity> canalPromotionEntities = channelDao.findAllByGroup(null);
			assertNotNull(canalPromotionEntities);
			assertTrue(canalPromotionEntities.isEmpty());

			canalPromotionEntities = channelDao.findAllByGroup(group);
			assertNotNull(canalPromotionEntities);
			assertEquals(2, canalPromotionEntities.size());
			assertEquals(channel, canalPromotionEntities.get(0));
		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}

	@Test
	public void testFindAllByDescription() {
		List<CanalePromozioneEntity> canalePromozione = channelDao.findByDescription(channel.getDescrizione());
		assertNotNull(canalePromozione);
		assertEquals(1, canalePromozione.size());
		assertEquals(channel.getDescrizione(), canalePromozione.get(0).getDescrizione());
		assertEquals(channel.getCodiceCanale(), canalePromozione.get(0).getCodiceCanale());
		assertEquals(channel.getCodeRangeMax(), canalePromozione.get(0).getCodeRangeMax());
		assertEquals(channel.getCodeRangeMin(), canalePromozione.get(0).getCodeRangeMin());
		assertEquals(channel.getGruppoPromozioneEntity(), canalePromozione.get(0).getGruppoPromozioneEntity());
	}
	
	@Test
	public void testFindByGroupId() {
		List<CanalePromozioneEntity> canaliPromozione = channelDao.findByGroupId(group.getId());
		assertNotNull(canaliPromozione);
		assertFalse(canaliPromozione.isEmpty());
		assertEquals(channel.getDescrizione(), canaliPromozione.get(0).getDescrizione());
		assertEquals(channel.getCodiceCanale(), canaliPromozione.get(0).getCodiceCanale());
		assertEquals(channel.getCodeRangeMax(), canaliPromozione.get(0).getCodeRangeMax());
		assertEquals(channel.getCodeRangeMin(), canaliPromozione.get(0).getCodeRangeMin());
		assertEquals(channel.getGruppoPromozioneEntity(), canaliPromozione.get(0).getGruppoPromozioneEntity());
	}

	@Test(expected = NullPointerException.class)
	public void testFindByGroupIdException() {
		channelDao.findByGroupId(null);
	}

	private CanalePromozioneEntity createPromoChannel(String channelName, Long rangeMin, Long rangeMax,
			CanaleLastProgEntity canaleLastProg, GruppoPromozioneEntity group) {
		CanalePromozioneEntity canale = new CanalePromozioneEntity();
		canale.setDescrizione(channelName);
		canale.setCodiceCanale(901L);
		canale.setCodeRangeMin(rangeMin);
		canale.setCodeRangeMax(rangeMax);
		canale.setGruppoPromozioneEntity(group);
		if (canaleLastProg != null) {
			Set<CanaleLastProgEntity> lastProg = new HashSet<>();
			lastProg.add(canaleLastProg);
			canale.setMuiCanaleLastProgs(lastProg);
		}
		return canale;
	}

	@Test
	public void testFindByCodiceCanale() {
		CanalePromozioneEntity canalePromozione = channelDao.findByCodiceCanale(channel.getCodiceCanale());
		assertNotNull(canalePromozione);
		assertEquals(channel.getDescrizione(), canalePromozione.getDescrizione());
		assertEquals(channel.getCodiceCanale(), canalePromozione.getCodiceCanale());
		assertEquals(channel.getCodeRangeMax(), canalePromozione.getCodeRangeMax());
		assertEquals(channel.getCodeRangeMin(), canalePromozione.getCodeRangeMin());
		assertEquals(channel.getGruppoPromozioneEntity(), canalePromozione.getGruppoPromozioneEntity());
	}

	@Test
	public void testFindByCodiceCanaleNoResultException() {
		CanalePromozioneEntity canalePromozione = channelDao.findByCodiceCanale(null);
		assertNull(canalePromozione);
	}

	private GruppoPromozioneEntity createPromoGroup(String code, String name) {
		GruppoPromozioneEntity gruppo = new GruppoPromozioneEntity();
		gruppo.setCodiceGruppo(code);
		gruppo.setDescrizione(name);
		return gruppo;
	}
	
	@Test
	public void testFindByListaCodiciCanale() {
		List<CanalePromozioneEntity> canalePromozione = channelDao.findByDescription(channel.getDescrizione());
		assertNotNull(canalePromozione);
		assertEquals(1, canalePromozione.size());
		assertEquals(channel.getDescrizione(), canalePromozione.get(0).getDescrizione());
		assertEquals(channel.getCodiceCanale(), canalePromozione.get(0).getCodiceCanale());
		assertEquals(channel.getCodeRangeMax(), canalePromozione.get(0).getCodeRangeMax());
		assertEquals(channel.getCodeRangeMin(), canalePromozione.get(0).getCodeRangeMin());
		assertEquals(channel.getGruppoPromozioneEntity(), canalePromozione.get(0).getGruppoPromozioneEntity());
	}
	
	@Test(expected = NullPointerException.class)
    public void FindByListaCodiciCanale_givenNullValue_shouldThrowException() {
		channelDao.findByCodiciCanale(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByCodiciCanale_givenEmptyList_shouldThrowException() {
		channelDao.findByCodiciCanale(Collections.emptyList());
	}

	@Test
    public void shouldFindById() {
		final CanalePromozioneEntity canalePromozioneEntity = channelDao.findById(20L);
        assertNotNull(canalePromozioneEntity);
		assertEquals(20L, (long) canalePromozioneEntity.getId());
		assertEquals(100L, (long) canalePromozioneEntity.getCodiceCanale());
    }

    @Test
    public void shouldReadAll() {
        List<CanalePromozioneEntity> canalePromozioneList = channelDao.findAll();
        assertNotNull(canalePromozioneList);
        assertFalse(canalePromozioneList.isEmpty());
    }

	private CanalePromozioneEntity createCanalePromozione(Long id, Long codiceCanale, Long rangeMin, Long rangeMax,
														  String descrizione, GruppoPromozioneEntity group) {
		CanalePromozioneEntity entity = createPromoChannel(descrizione, rangeMin, rangeMax, null, group);
		entity.setId(id);
		entity.setCodiceCanale(codiceCanale);
		return entity;
	}
}
