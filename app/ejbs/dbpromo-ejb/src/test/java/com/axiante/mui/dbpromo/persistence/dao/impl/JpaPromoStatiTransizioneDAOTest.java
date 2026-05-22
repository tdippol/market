package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromoStatiTransizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaPromoStatiTransizioneDAOTest extends AbstractDaoTest {

	@Inject
	private PromoStatiTransizioneDAO dao;

	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaPromoStatiTransizioneDAOImpl.class)
			.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	private final static Long PROMOZIONETESTATAENTITY1_ID = 7L;
	private final static Long PROMOZIONETESTATAENTITY2_ID = 8L;
	private final static Long PROMOZIONETESTATAENTITY3_ID = 9L;
	private final static Long PROMOZIONETESTATAENTITY4_ID = 10L;
	private final static Long STATOPROMOZIONENTITY1_ID = 9L;
	private final static Long STATOPROMOZIONENTITY2_ID = 10L;

	private PromoStatiTransizioneEntity promoStatiTransizioneEntity1;
	private PromoStatiTransizioneEntity promoStatiTransizioneEntity2;
	private PromozioneTestataEntity testata1;
	private PromozioneTestataEntity testata3;
	private PromozioneTestataEntity testata4;
	private StatoPromozioneEntity statoPromozione;
	private StatoPromozioneEntity statoTransizione;

	@Before
	public void setUp() throws Exception {
		GruppoPromozioneEntity gruppoPromo = createGruppoPromozioneEntity();
		CanalePromozioneEntity canalePromo = createCanalePromozioneEntity(gruppoPromo);
		statoPromozione = createStatoPromozioneEntity(STATOPROMOZIONENTITY1_ID,"1234", PromoStatusEnum.CANCELLATA_00.getKey());
		statoTransizione = createStatoPromozioneEntity(STATOPROMOZIONENTITY2_ID,"5678", PromoStatusEnum.CANCELLATA_00.getKey());
		PromozioneStatoEntity promozioneStatoEntity1 = createPromozioneStatoEntity(1L, Calendar.getInstance().getTime(), statoPromozione);
		PromozioneStatoEntity promozioneStatoEntity2 = createPromozioneStatoEntity(2L, null, statoTransizione);
		Set<PromozioneStatoEntity> promozioneStatoEntities1 = new HashSet<>();
		promozioneStatoEntities1.add(promozioneStatoEntity1);
		Set<PromozioneStatoEntity> promozioneStatoEntities2 = new HashSet<>();
		promozioneStatoEntities2.add(promozioneStatoEntity2);
		testata1 = createPromozioneTestataEntity(PROMOZIONETESTATAENTITY1_ID, "cod-120", canalePromo, promozioneStatoEntities1);
		PromozioneTestataEntity testata2 = createPromozioneTestataEntity(PROMOZIONETESTATAENTITY2_ID, "cod-457", canalePromo, promozioneStatoEntities2);
		testata3 = createPromozioneTestataEntity(PROMOZIONETESTATAENTITY3_ID, "cod-666", canalePromo, promozioneStatoEntities2);
		testata4 = createPromozioneTestataEntity(PROMOZIONETESTATAENTITY4_ID, "cod-999", canalePromo, promozioneStatoEntities2);

		promoStatiTransizioneEntity1 = createPromoStatiTransizioneEntity(11L, testata1, statoTransizione, statoPromozione );
		promoStatiTransizioneEntity2 = createPromoStatiTransizioneEntity(12L, testata2, statoPromozione, statoTransizione, Boolean.TRUE);
		PromoStatiTransizioneEntity promoStatiTransizioneEntity3 = createPromoStatiTransizioneEntity(13L, testata3, statoPromozione, statoTransizione);
		PromoStatiTransizioneEntity promoStatiTransizioneEntity4 = createPromoStatiTransizioneEntity(14L, testata4, statoPromozione, statoTransizione);
		PromoStatiTransizioneEntity promoStatiTransizioneEntity5 = createPromoStatiTransizioneEntity(15L, testata4, statoPromozione, statoTransizione);
		testata1.addPromoStatiTransizioneEntity(promoStatiTransizioneEntity1);
		testata2.addPromoStatiTransizioneEntity(promoStatiTransizioneEntity2);
		testata3.addPromoStatiTransizioneEntity(promoStatiTransizioneEntity3);
		testata4.addPromoStatiTransizioneEntity(promoStatiTransizioneEntity4);
		testata4.addPromoStatiTransizioneEntity(promoStatiTransizioneEntity5);

		openTransaction();
		testata1 = getEm().merge(testata1);
		getEm().merge(testata2);
		testata3 = getEm().merge(testata3);
		testata4 = getEm().merge(testata4);
		getEm().flush();
		commitTransaction();
	}

	@Test
	public void shouldFindAllByIdAndStatus() {
		List<PromoStatiTransizioneEntity> promoStatiTransizioneEntities = dao.findByIdAndStatus(null, null);
		assertNotNull(promoStatiTransizioneEntities);
		assertTrue(promoStatiTransizioneEntities.isEmpty());
		promoStatiTransizioneEntities = dao.findByIdAndStatus(PROMOZIONETESTATAENTITY1_ID, null);
		assertNotNull(promoStatiTransizioneEntities);
		assertTrue(promoStatiTransizioneEntities.isEmpty());
		promoStatiTransizioneEntities = dao.findByIdAndStatus(null, STATOPROMOZIONENTITY2_ID);
		assertNotNull(promoStatiTransizioneEntities);
		assertTrue(promoStatiTransizioneEntities.isEmpty());
		promoStatiTransizioneEntities = dao.findByIdAndStatus(9999L, 9999L);
		assertNotNull(promoStatiTransizioneEntities);
		assertTrue(promoStatiTransizioneEntities.isEmpty());

		promoStatiTransizioneEntities = dao.findByIdAndStatus(PROMOZIONETESTATAENTITY1_ID, STATOPROMOZIONENTITY2_ID);
		assertNotNull(promoStatiTransizioneEntities);
		assertFalse(promoStatiTransizioneEntities.isEmpty());
		assertEquals(promoStatiTransizioneEntities.size(), 1);
		assertEquals(promoStatiTransizioneEntity1.getId(), promoStatiTransizioneEntities.get(0).getId());
		assertNotNull(promoStatiTransizioneEntities.get(0).getPromozioneTestataEntity());
		PromozioneTestataEntity promozioneTestataEntity = promoStatiTransizioneEntities.get(0).getPromozioneTestataEntity();
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getId(), promozioneTestataEntity.getId());
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getCodicePromozione(), promozioneTestataEntity.getCodicePromozione());
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getDataInizio(), promozioneTestataEntity.getDataInizio());
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getDataFine(), promozioneTestataEntity.getDataFine());
		assertNotNull(promozioneTestataEntity.getMuiCanalePromozione());
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getMuiCanalePromozione().getId(), promozioneTestataEntity.getMuiCanalePromozione().getId());
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getMuiCanalePromozione().getCodiceCanale(), promozioneTestataEntity.getMuiCanalePromozione().getCodiceCanale());
		assertNotNull(promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity());
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getMuiCanalePromozione().getGruppoPromozioneEntity().getId(),
				promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity().getId());
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo(),
				promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo());
		assertNotNull(promozioneTestataEntity.getPromozioneStatoEntities());
		assertFalse(promozioneTestataEntity.getPromozioneStatoEntities().isEmpty());
		assertEquals(promozioneTestataEntity.getPromozioneStatoEntities().size(), 1);
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator().next().getId(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getId());
		assertNotNull(promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getDataFineStato());
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator().next().getDataFineStato(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getDataFineStato());
		assertNotNull(promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity());
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getId(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getId());
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getCodiceStato(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getCodiceStato());
		assertEquals(promoStatiTransizioneEntity1.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getDescrizione(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getDescrizione());

		promoStatiTransizioneEntities = dao.findByIdAndStatus(PROMOZIONETESTATAENTITY2_ID, STATOPROMOZIONENTITY1_ID);
		assertNotNull(promoStatiTransizioneEntities);
		assertFalse(promoStatiTransizioneEntities.isEmpty());
		assertEquals(promoStatiTransizioneEntities.size(), 1);
		assertEquals(promoStatiTransizioneEntity2.getId(), promoStatiTransizioneEntities.get(0).getId());
		assertNotNull(promoStatiTransizioneEntities.get(0).getPromozioneTestataEntity());
		assertNotNull(promoStatiTransizioneEntities.get(0).getPromozioneTestataEntity());
		promozioneTestataEntity = promoStatiTransizioneEntities.get(0).getPromozioneTestataEntity();
		assertNotNull(promozioneTestataEntity);
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getId(), promozioneTestataEntity.getId());
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getCodicePromozione(), promozioneTestataEntity.getCodicePromozione());
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getDataInizio(), promozioneTestataEntity.getDataInizio());
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getDataFine(), promozioneTestataEntity.getDataFine());
		assertNotNull(promozioneTestataEntity.getMuiCanalePromozione());
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getMuiCanalePromozione().getId(), promozioneTestataEntity.getMuiCanalePromozione().getId());
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getMuiCanalePromozione().getCodiceCanale(), promozioneTestataEntity.getMuiCanalePromozione().getCodiceCanale());
		assertNotNull(promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity());
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getMuiCanalePromozione().getGruppoPromozioneEntity().getId(),
				promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity().getId());
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo(),
				promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo());
		assertNotNull(promozioneTestataEntity.getPromozioneStatoEntities());
		assertFalse(promozioneTestataEntity.getPromozioneStatoEntities().isEmpty());
		assertEquals(promozioneTestataEntity.getPromozioneStatoEntities().size(), 1);
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator().next().getId(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getId());
		assertNull(promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getDataFineStato());
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator().next().getDataFineStato(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getDataFineStato());
		assertNotNull(promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity());
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getId(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getId());
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getCodiceStato(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getCodiceStato());
		assertEquals(promoStatiTransizioneEntity2.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getDescrizione(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getDescrizione());
	}

	@Test
	public void findByPromoAndStatuses_givenNullTestata_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findByPromoAndStatuses(null, mock(StatoPromozioneEntity.class), mock(StatoPromozioneEntity.class));
	}

	@Test
	public void findByPromoAndStatuses_givenNullFromStatus_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findByPromoAndStatuses(mock(PromozioneTestataEntity.class), null, mock(StatoPromozioneEntity.class));
	}

	@Test
	public void findByPromoAndStatuses_givenNullToStatus_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findByPromoAndStatuses(mock(PromozioneTestataEntity.class), mock(StatoPromozioneEntity.class), null);
	}

	@Test
	public void findByPromoAndStatuses_givenValidTestataAndStatuses_shouldReturnSingleEntity() {
		final PromoStatiTransizioneEntity entity = dao.findByPromoAndStatuses(testata1, statoTransizione, statoPromozione);
		assertNotNull(entity);
		assertEquals(promoStatiTransizioneEntity1.getId(), entity.getId());
	}

	@Test
	public void findByPromoAndStatuses_whenValidTestataAndStatusesAreMoreThanOne_shouldReturnNull() {
		PromoStatiTransizioneEntity entity = dao.findByPromoAndStatuses(testata4, statoPromozione, statoTransizione);
		assertNull(entity);
	}

	@Test
	public void findByPromoAndStatusesAndFlagAutomatico() {
		PromoStatiTransizioneEntity entity = dao.findByPromoAndStatusesAndFlagAutomatico(testata1,
				statoTransizione, statoPromozione, Boolean.FALSE);
		assertNotNull(entity);
		assertEquals(promoStatiTransizioneEntity1.getId(), entity.getId());
		entity = dao.findByPromoAndStatusesAndFlagAutomatico(testata3,
				statoTransizione, statoPromozione, Boolean.FALSE);
		assertNull(entity);
	}

	@Test
	public void findByPromoAndStatusesAndFlagAutomatico_whenValidTestataAndStatusesAreMoreThanOne_shouldReturnNull() {
		PromoStatiTransizioneEntity entity = dao.findByPromoAndStatusesAndFlagAutomatico(testata4,
				statoPromozione, statoTransizione, Boolean.FALSE);
		assertNull(entity);
	}

	@Test
	public void findAllNotAutomaticByIdAndStatus() {
		List<PromoStatiTransizioneEntity> result = dao.findAllNotAutomaticByIdAndStatus(PROMOZIONETESTATAENTITY1_ID,
				STATOPROMOZIONENTITY2_ID);
		assertEquals(1, result.size());
	}

	@Test
	public void findAllAutomaticByIdAndStatus() {
		List<PromoStatiTransizioneEntity> result = dao.findAllAutomaticByIdAndStatus(PROMOZIONETESTATAENTITY2_ID,
				STATOPROMOZIONENTITY1_ID);
		assertEquals(1, result.size());
	}

	private PromoStatiTransizioneEntity createPromoStatiTransizioneEntity(Long id, PromozioneTestataEntity testata,
																		  StatoPromozioneEntity statoPromozione,
																		  StatoPromozioneEntity statoTransizione) {
		return createPromoStatiTransizioneEntity(id, testata, statoPromozione, statoTransizione, Boolean.FALSE);
	}

	private PromoStatiTransizioneEntity createPromoStatiTransizioneEntity(Long id, PromozioneTestataEntity testata,
																		  StatoPromozioneEntity statoPromozione,
																		  StatoPromozioneEntity statoTransizione,
																		  Boolean flagAutomatico) {
		PromoStatiTransizioneEntity promoStatiTransizioneEntity = new PromoStatiTransizioneEntity();
		promoStatiTransizioneEntity.setId(id);
		promoStatiTransizioneEntity.setPromozioneTestataEntity(testata);
		promoStatiTransizioneEntity.setStatoPromozione(statoPromozione);
		promoStatiTransizioneEntity.setStatoTransizione(statoTransizione);
		promoStatiTransizioneEntity.setStatoErrore(statoPromozione);
		promoStatiTransizioneEntity.setFlagAutomatico(flagAutomatico);
		return promoStatiTransizioneEntity;
	}

	private StatoPromozioneEntity createStatoPromozioneEntity(Long id, String descrizione, String codice) {
		StatoPromozioneEntity statoPromozioneEntity = new StatoPromozioneEntity();
		statoPromozioneEntity.setId(id);
		statoPromozioneEntity.setDescrizione(descrizione);
		statoPromozioneEntity.setCodiceStato(codice);
		return statoPromozioneEntity;
	}

	private PromozioneStatoEntity createPromozioneStatoEntity(Long id, Date dataFineStato, StatoPromozioneEntity statoPromozioneEntity) {
		PromozioneStatoEntity promozioneStatoEntity = new PromozioneStatoEntity();
		promozioneStatoEntity.setId(id);
		promozioneStatoEntity.setDataFineStato(dataFineStato);
		promozioneStatoEntity.setStatoPromozioneEntity(statoPromozioneEntity);
		return promozioneStatoEntity;
	}

	private GruppoPromozioneEntity createGruppoPromozioneEntity() {
		GruppoPromozioneEntity gruppoPromozioneEntity = new GruppoPromozioneEntity();
		gruppoPromozioneEntity.setId(1L);
		gruppoPromozioneEntity.setCodiceGruppo("g987");
		return gruppoPromozioneEntity;
	}

	private CanalePromozioneEntity createCanalePromozioneEntity(GruppoPromozioneEntity gruppoPromozioneEntity) {
		CanalePromozioneEntity canalePromozioneEntity = new CanalePromozioneEntity();
		canalePromozioneEntity.setId(1L);
		canalePromozioneEntity.setCodiceCanale(1L);
		canalePromozioneEntity.setGruppoPromozioneEntity(gruppoPromozioneEntity);
		return canalePromozioneEntity;
	}

	private PromozioneTestataEntity createPromozioneTestataEntity(Long id, String codice, CanalePromozioneEntity canalePromozioneEntity, Set<PromozioneStatoEntity> promozioneStatoEntities) {
		PromozioneTestataEntity promozioneTestataEntity = new PromozioneTestataEntity();
		promozioneTestataEntity.setId(id);
		promozioneTestataEntity.setCodicePromozione(codice);
		promozioneTestataEntity.setDataInizio(Calendar.getInstance().getTime());
		promozioneTestataEntity.setDataFine(Calendar.getInstance().getTime());
		promozioneTestataEntity.setMuiCanalePromozione(canalePromozioneEntity);
		promozioneTestataEntity.setPromozioneStatoEntities(promozioneStatoEntities);
		return promozioneTestataEntity;
	}
}
