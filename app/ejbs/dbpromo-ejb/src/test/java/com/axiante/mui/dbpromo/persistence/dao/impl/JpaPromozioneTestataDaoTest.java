package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgPianificazTipoRigaEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.MeccanicaEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozionePianificazioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozioneStatoEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozioneTestataEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.StatoPromozioneEntityBuilder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
public class JpaPromozioneTestataDaoTest extends AbstractDaoTest {

	@Inject
	private PromozioneTestataDAO dao;

	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
					JpaPromozioneTestataDaoImpl.class, DbPromo.class)
			.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	private CanalePromozioneEntity canalePromozione;
	private PromozioneTestataEntity testataCancellata1;
	private PromozioneTestataEntity testataCancellata2;
	private PromozioneTestataEntity promozioneRif;
	private CfgPianificazTipoRigaEntity tipoRigaSet;

	@Before
	public void setUp() throws Exception {
		GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G01")
				.withDescrizione("GRUPPO 01").build();
		canalePromozione = new CanalePromozioneEntityBuilder(1L).withCodice(22L).withGruppo(gruppo)
				.withDescrizione("CANALE 01").build();
		StatoPromozioneEntity statoPromozione1 = StatoPromozioneEntityBuilder.buildCancellata(1L);
		StatoPromozioneEntity statoPromozione2 = StatoPromozioneEntityBuilder.buildCancellata(2L);
		StatoPromozioneEntity statoPromozione3 = StatoPromozioneEntityBuilder.buildCreata(3L);
		StatoPromozioneEntity statoPromozione4 = StatoPromozioneEntityBuilder.buildDisponibile(4L);
		StatoPromozioneEntity statoPromozione5 = StatoPromozioneEntityBuilder.buildInEsecuzione(5L);
		StatoPromozioneEntity statoPromozione6 = StatoPromozioneEntityBuilder.buildFinalizzata(6L);
		StatoPromozioneEntity statoPromozione7 = StatoPromozioneEntityBuilder.buildCreata(7L);
		PromozioneStatoEntity promozioneStato1 = new PromozioneStatoEntityBuilder(1L).withStatoPromozione(statoPromozione1)
				.withDataFineStato(Calendar.getInstance().getTime()).build();
		PromozioneStatoEntity promozioneStato2 = new PromozioneStatoEntityBuilder(2L).withStatoPromozione(statoPromozione2)
				.build();
		PromozioneStatoEntity promozioneStato3 = new PromozioneStatoEntityBuilder(3L).withStatoPromozione(statoPromozione3)
				.build();
		PromozioneStatoEntity promozioneStato4 = new PromozioneStatoEntityBuilder(4L).withStatoPromozione(statoPromozione4)
				.build();
		PromozioneStatoEntity promozioneStato5 = new PromozioneStatoEntityBuilder(5L).withStatoPromozione(statoPromozione5)
				.build();
		PromozioneStatoEntity promozioneStato6 = new PromozioneStatoEntityBuilder(6L).withStatoPromozione(statoPromozione6)
				.build();
		PromozioneStatoEntity promozioneStato7 = new PromozioneStatoEntityBuilder(7L).withStatoPromozione(statoPromozione7)
				.build();
		Set<PromozioneStatoEntity> promozioneStati1 = new HashSet<>();
		promozioneStati1.add(promozioneStato1);
		Set<PromozioneStatoEntity> promozioneStati2 = new HashSet<>();
		promozioneStati2.add(promozioneStato2);
		Set<PromozioneStatoEntity> promozioneStati3 = new HashSet<>();
		promozioneStati3.add(promozioneStato3);
		Set<PromozioneStatoEntity> promozioneStati4 = new HashSet<>();
		promozioneStati4.add(promozioneStato4);
		Set<PromozioneStatoEntity> promozioneStati5 = new HashSet<>();
		promozioneStati5.add(promozioneStato5);
		Set<PromozioneStatoEntity> promozioneStati6 = new HashSet<>();
		promozioneStati5.add(promozioneStato6);
		Set<PromozioneStatoEntity> promozioneStati7 = new HashSet<>();
		promozioneStati7.add(promozioneStato7);
		MeccanicheEntity meccanica1 = new MeccanicaEntityBuilder(1L).withCodice("M141")
				.withDescrizione("Meccanica M141").build();
		MeccanicheEntity meccanica2 = new MeccanicaEntityBuilder(2L).withCodice("M666")
				.withDescrizione("Meccanica M666").build();
		tipoRigaSet = CfgPianificazTipoRigaEntityBuilder.buildRigaSet(1L);
		PromozionePianificazioneEntity pianificazione1 = new PromozionePianificazioneEntityBuilder(1L)
				.withMeccanica(meccanica1).withTipoRiga(tipoRigaSet).build();
		PromozionePianificazioneEntity pianificazione2 = new PromozionePianificazioneEntityBuilder(2L)
				.withMeccanica(meccanica1).withTipoRiga(tipoRigaSet).build();
		PromozionePianificazioneEntity pianificazione3 = new PromozionePianificazioneEntityBuilder(3L)
				.withMeccanica(meccanica1).withTipoRiga(tipoRigaSet).build();
		PromozionePianificazioneEntity pianificazione4 = new PromozionePianificazioneEntityBuilder(4L)
				.withMeccanica(meccanica2).withTipoRiga(tipoRigaSet).build();
		testataCancellata1 = new PromozioneTestataEntityBuilder(1L).withCodice("cod-123").withCanale(canalePromozione)
				.withPromozioneStati(promozioneStati1)
				.withDataInizio(new GregorianCalendar(2024, Calendar.SEPTEMBER, 1).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.SEPTEMBER, 10).getTime())
				.build();
		testataCancellata2 = new PromozioneTestataEntityBuilder(2L).withCodice("cod-456").withCanale(canalePromozione)
				.withPromozioneStati(promozioneStati2)
				.withDataInizio(new GregorianCalendar(2024, Calendar.SEPTEMBER, 1).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.SEPTEMBER, 10).getTime())
				.build();
		PromozioneTestataEntity promozione1 = new PromozioneTestataEntityBuilder(3L).withCodice("PROMO-01")
				.withCanale(canalePromozione).withPromozioneStati(promozioneStati3).withAnno("2024")
				.withDataInizio(new GregorianCalendar(2024, Calendar.SEPTEMBER, 1).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.SEPTEMBER, 10).getTime())
				.withPianificazione(pianificazione1).build();
		PromozioneTestataEntity promozione2 = new PromozioneTestataEntityBuilder(4L).withCodice("PROMO-02")
				.withCanale(canalePromozione).withPromozioneStati(promozioneStati4).withAnno("2024")
				.withDataInizio(new GregorianCalendar(2024, Calendar.SEPTEMBER, 5).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.SEPTEMBER, 12).getTime())
				.withPianificazione(pianificazione2).build();
		PromozioneTestataEntity promozione3 = new PromozioneTestataEntityBuilder(5L).withCodice("PROMO-03")
				.withCanale(canalePromozione).withPromozioneStati(promozioneStati5).withAnno("2024")
				.withDataInizio(new GregorianCalendar(2024, Calendar.SEPTEMBER, 15).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.SEPTEMBER, 20).getTime())
				.withPianificazione(pianificazione3).build();
		PromozioneTestataEntity promozione4 = new PromozioneTestataEntityBuilder(6L).withCodice("PROMO-04")
				.withCanale(canalePromozione).withPromozioneStati(promozioneStati6).withAnno("2024")
				.withDataInizio(new GregorianCalendar(2024, Calendar.SEPTEMBER, 7).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.SEPTEMBER, 10).getTime())
				.withPianificazione(pianificazione4).build();
		promozioneRif = new PromozioneTestataEntityBuilder(10L).withCodice("PROMO-10").withCanale(canalePromozione)
				.withPromozioneStati(promozioneStati7).withAnno("2024")
				.withDataInizio(new GregorianCalendar(2024, Calendar.SEPTEMBER, 3).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.SEPTEMBER, 14).getTime())
				.build();

		promozioneStato1.setPromozioneTestataEntity(testataCancellata1);
		statoPromozione1.setPromozioneStatoEntities(promozioneStati1);
		promozioneStato2.setPromozioneTestataEntity(testataCancellata2);
		statoPromozione2.setPromozioneStatoEntities(promozioneStati2);
		promozioneStato3.setPromozioneTestataEntity(promozione1);
		statoPromozione3.setPromozioneStatoEntities(promozioneStati3);
		promozioneStato4.setPromozioneTestataEntity(promozione2);
		statoPromozione4.setPromozioneStatoEntities(promozioneStati4);
		promozioneStato5.setPromozioneTestataEntity(promozione3);
		statoPromozione5.setPromozioneStatoEntities(promozioneStati5);
		promozioneStato6.setPromozioneTestataEntity(promozione4);
		statoPromozione6.setPromozioneStatoEntities(promozioneStati6);
		promozioneStato7.setPromozioneTestataEntity(promozioneRif);
		statoPromozione7.setPromozioneStatoEntities(promozioneStati7);
		pianificazione1.setPromozioneTestataEntity(promozione1);
		pianificazione2.setPromozioneTestataEntity(promozione2);
		pianificazione3.setPromozioneTestataEntity(promozione3);
		pianificazione4.setPromozioneTestataEntity(promozione4);

		openTransaction();
		dao.persist(testataCancellata1);
		dao.persist(testataCancellata2);
		dao.persist(promozione1);
		dao.persist(promozione2);
		dao.persist(promozione3);
		dao.persist(promozione4);
		dao.persist(promozioneRif);
		commitTransaction();
	}

	@Test
	public void shouldFindByPromoCode() {
		PromozioneTestataEntity testata = dao.findByPromoCode(null);
		assertNull(testata);
		testata = dao.findByPromoCode("test");
		assertNull(testata);
		testata = dao.findByPromoCode(testataCancellata1.getCodicePromozione());
		assertNotNull(testata);
		assertEquals(testataCancellata1.getId(), testata.getId());
		assertEquals(testataCancellata1.getCodicePromozione(), testata.getCodicePromozione());
		assertEquals(testataCancellata1.getDataInizio(), testata.getDataInizio());
		assertEquals(testataCancellata1.getDataFine(), testata.getDataFine());
		assertNotNull(testata.getMuiCanalePromozione());
		assertEquals(testataCancellata1.getMuiCanalePromozione().getId(), testata.getMuiCanalePromozione().getId());
		assertEquals(testataCancellata1.getMuiCanalePromozione().getCodiceCanale(),
				testata.getMuiCanalePromozione().getCodiceCanale());
		assertNotNull(testata.getMuiCanalePromozione().getGruppoPromozioneEntity());
		assertEquals(testataCancellata1.getMuiCanalePromozione().getGruppoPromozioneEntity().getId(),
				testata.getMuiCanalePromozione().getGruppoPromozioneEntity().getId());
		assertEquals(testataCancellata1.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo(),
				testata.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo());
		assertNotNull(testata.getPromozioneStatoEntities());
		assertFalse(testata.getPromozioneStatoEntities().isEmpty());
		assertEquals(testata.getPromozioneStatoEntities().size(), 1);
		assertEquals(testataCancellata1.getPromozioneStatoEntities().iterator().next().getId(),
				testata.getPromozioneStatoEntities().iterator().next().getId());
		assertNotNull(testata.getPromozioneStatoEntities().iterator().next().getDataFineStato());
		assertEquals(testataCancellata1.getPromozioneStatoEntities().iterator().next().getDataFineStato(),
				testata.getPromozioneStatoEntities().iterator().next().getDataFineStato());
		assertNotNull(testata.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity());
		assertEquals(
				testataCancellata1.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getId(),
				testata.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getId());
		assertEquals(
				testataCancellata1.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity()
						.getCodiceStato(),
				testata.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getCodiceStato());
		assertEquals(
				testataCancellata1.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity()
						.getDescrizione(),
				testata.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getDescrizione());

		testata = dao.findByPromoCode(testataCancellata2.getCodicePromozione());
		assertNotNull(testata);
		assertEquals(testataCancellata2.getId(), testata.getId());
		assertEquals(testataCancellata2.getCodicePromozione(), testata.getCodicePromozione());
		assertEquals(testataCancellata2.getDataInizio(), testata.getDataInizio());
		assertEquals(testataCancellata2.getDataFine(), testata.getDataFine());
		assertNotNull(testata.getMuiCanalePromozione());
		assertEquals(testataCancellata2.getMuiCanalePromozione().getId(), testata.getMuiCanalePromozione().getId());
		assertEquals(testataCancellata2.getMuiCanalePromozione().getCodiceCanale(),
				testata.getMuiCanalePromozione().getCodiceCanale());
		assertNotNull(testata.getMuiCanalePromozione().getGruppoPromozioneEntity());
		assertEquals(testataCancellata2.getMuiCanalePromozione().getGruppoPromozioneEntity().getId(),
				testata.getMuiCanalePromozione().getGruppoPromozioneEntity().getId());
		assertEquals(testataCancellata2.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo(),
				testata.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo());
		assertNotNull(testata.getPromozioneStatoEntities());
		assertFalse(testata.getPromozioneStatoEntities().isEmpty());
		assertEquals(testata.getPromozioneStatoEntities().size(), 1);
		assertEquals(testataCancellata2.getPromozioneStatoEntities().iterator().next().getId(),
				testata.getPromozioneStatoEntities().iterator().next().getId());
		assertNull(testata.getPromozioneStatoEntities().iterator().next().getDataFineStato());
		assertEquals(testataCancellata2.getPromozioneStatoEntities().iterator().next().getDataFineStato(),
				testata.getPromozioneStatoEntities().iterator().next().getDataFineStato());
		assertNotNull(testata.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity());
		assertEquals(
				testataCancellata2.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getId(),
				testata.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getId());
		assertEquals(
				testataCancellata2.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity()
						.getCodiceStato(),
				testata.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getCodiceStato());
		assertEquals(
				testataCancellata2.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity()
						.getDescrizione(),
				testata.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity().getDescrizione());
	}

	@Test
	public void findOverlappingByCodiciMeccanica_givenNullPromozione_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findOverlappingByCodiciMeccanica(null, Arrays.asList("M141", "M142"));
	}

	@Test
	public void findOverlappingByCodiciMeccanica_givenNullCodiciMeccanica_shouldThrowException() {
		ex.expect(IllegalArgumentException.class);
		dao.findOverlappingByCodiciMeccanica(promozioneRif, null);
	}

	@Test
	public void findOverlappingByCodiciMeccanica_givenEmptyCodiciMeccanica_shouldThrowException() {
		ex.expect(IllegalArgumentException.class);
		dao.findOverlappingByCodiciMeccanica(promozioneRif, Collections.emptyList());
	}

	@Test
	public void findOverlappingByCodiciMeccanica() {
		List<PromozioneTestataEntity> overlapping = dao.findOverlappingByCodiciMeccanica(promozioneRif,
				Arrays.asList("M141", "M142"));
		assertEquals(2, overlapping.size());
	}

	@Test
	public void findAllByAnnoAndCanale() {
		List<PromozioneTestataEntity> result = dao.findAllByAnnoAndCanale("2024", canalePromozione);
		assertEquals(5, result.size());
	}

	@Test
	public void findOverlappingByAnnoAndCodiceMeccanicaWithOffset_givenNullPromozione_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(null, Arrays.asList("M141", "M142"), null, null);
	}

	@Test
	public void findOverlappingByAnnoAndCodiceMeccanicaWithOffset_givenNullCodiciMeccanica_shouldThrowException() {
		ex.expect(IllegalArgumentException.class);
		dao.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(promozioneRif, null, null, null);
	}

	@Test
	public void findOverlappingByAnnoAndCodiceMeccanicaWithOffset_givenEmptyCodiciMeccanica_shouldThrowException() {
		ex.expect(IllegalArgumentException.class);
		dao.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(promozioneRif, Collections.emptyList(), null, null);
	}

	@Test
	public void findOverlappingByAnnoAndCodiceMeccanicaWithOffset() {
		StatoPromozioneEntity statoPromozione = StatoPromozioneEntityBuilder.buildCreata(42L);
		PromozioneStatoEntity promozioneStato = new PromozioneStatoEntityBuilder(42L).withStatoPromozione(statoPromozione)
				.build();
		PromozioneTestataEntity promoRif = new PromozioneTestataEntityBuilder(42L).withCodice("PROMO-RIF")
				.withCanale(canalePromozione).withAnno("2024").withPromozioneStato(promozioneStato)
				.withDataInizio(new GregorianCalendar(2024, Calendar.OCTOBER, 5).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.OCTOBER, 25).getTime())
				.build();
		promozioneStato.setPromozioneTestataEntity(promoRif);
		persist(statoPromozione, promozioneStato, promoRif);
		createPromozioniTestate();
		List<PromozioneTestataEntity> result = dao.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(promoRif,
				Arrays.asList("M141", "M142"), 5, 5);
		assertEquals(5, result.size());
		List<String> overlappedCodici = result.stream().map(PromozioneTestataEntity::getCodicePromozione)
				.collect(Collectors.toList());
		assertFalse(overlappedCodici.contains("PROMO A"));
		assertTrue(overlappedCodici.contains("PROMO B"));
		assertTrue(overlappedCodici.contains("PROMO C"));
		assertTrue(overlappedCodici.contains("PROMO D"));
		assertTrue(overlappedCodici.contains("PROMO E"));
		assertTrue(overlappedCodici.contains("PROMO F"));
		assertFalse(overlappedCodici.contains("PROMO G"));
		// overlap set to 0
		result = dao.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(promoRif,
				Arrays.asList("M141", "M142"), 0, 0);
		assertEquals(3, result.size());
		overlappedCodici = result.stream().map(PromozioneTestataEntity::getCodicePromozione)
				.collect(Collectors.toList());
		assertFalse(overlappedCodici.contains("PROMO A"));
		assertFalse(overlappedCodici.contains("PROMO B"));
		assertTrue(overlappedCodici.contains("PROMO C"));
		assertTrue(overlappedCodici.contains("PROMO D"));
		assertTrue(overlappedCodici.contains("PROMO E"));
		assertFalse(overlappedCodici.contains("PROMO F"));
		assertFalse(overlappedCodici.contains("PROMO G"));
	}

	private void createPromozioniTestate() {
		StatoPromozioneEntity statoPromozione = StatoPromozioneEntityBuilder.buildCreata(11L);
		PromozioneStatoEntity promozioneStatoA = new PromozioneStatoEntityBuilder(11L)
				.withStatoPromozione(statoPromozione).build();
		PromozioneStatoEntity promozioneStatoB = new PromozioneStatoEntityBuilder(12L)
				.withStatoPromozione(statoPromozione).build();
		PromozioneStatoEntity promozioneStatoC = new PromozioneStatoEntityBuilder(13L)
				.withStatoPromozione(statoPromozione).build();
		PromozioneStatoEntity promozioneStatoD = new PromozioneStatoEntityBuilder(14L)
				.withStatoPromozione(statoPromozione).build();
		PromozioneStatoEntity promozioneStatoE = new PromozioneStatoEntityBuilder(15L)
				.withStatoPromozione(statoPromozione).build();
		PromozioneStatoEntity promozioneStatoF = new PromozioneStatoEntityBuilder(16L)
				.withStatoPromozione(statoPromozione).build();
		PromozioneStatoEntity promozioneStatoG = new PromozioneStatoEntityBuilder(17L)
				.withStatoPromozione(statoPromozione).build();
		PromozioneTestataEntity promoA = new PromozioneTestataEntityBuilder(11L).withCodice("PROMO A")
				.withCanale(canalePromozione).withAnno("2024").withPromozioneStato(promozioneStatoA)
				.withDataInizio(new GregorianCalendar(2024, Calendar.SEPTEMBER, 20).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.SEPTEMBER, 25).getTime())
				.build();
		PromozioneTestataEntity promoB = new PromozioneTestataEntityBuilder(12L).withCodice("PROMO B")
				.withCanale(canalePromozione).withAnno("2024").withPromozioneStato(promozioneStatoB)
				.withDataInizio(new GregorianCalendar(2024, Calendar.SEPTEMBER, 22).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.OCTOBER, 3).getTime())
				.build();
		PromozioneTestataEntity promoC = new PromozioneTestataEntityBuilder(13L).withCodice("PROMO C")
				.withCanale(canalePromozione).withAnno("2024").withPromozioneStato(promozioneStatoC)
				.withDataInizio(new GregorianCalendar(2024, Calendar.OCTOBER, 1).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.OCTOBER, 12).getTime())
				.build();
		PromozioneTestataEntity promoD = new PromozioneTestataEntityBuilder(14L).withCodice("PROMO D")
				.withCanale(canalePromozione).withAnno("2024").withPromozioneStato(promozioneStatoD)
				.withDataInizio(new GregorianCalendar(2024, Calendar.OCTOBER, 8).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.OCTOBER, 20).getTime())
				.build();
		PromozioneTestataEntity promoE = new PromozioneTestataEntityBuilder(15L).withCodice("PROMO E")
				.withCanale(canalePromozione).withAnno("2024").withPromozioneStato(promozioneStatoE)
				.withDataInizio(new GregorianCalendar(2024, Calendar.OCTOBER, 15).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.OCTOBER, 29).getTime())
				.build();
		PromozioneTestataEntity promoF = new PromozioneTestataEntityBuilder(16L).withCodice("PROMO F")
				.withCanale(canalePromozione).withAnno("2024").withPromozioneStato(promozioneStatoF)
				.withDataInizio(new GregorianCalendar(2024, Calendar.OCTOBER, 27).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.NOVEMBER, 5).getTime())
				.build();
		PromozioneTestataEntity promoG = new PromozioneTestataEntityBuilder(17L).withCodice("PROMO G")
				.withCanale(canalePromozione).withAnno("2024").withPromozioneStato(promozioneStatoG)
				.withDataInizio(new GregorianCalendar(2024, Calendar.NOVEMBER, 3).getTime())
				.withDataFine(new GregorianCalendar(2024, Calendar.NOVEMBER, 8).getTime())
				.build();
		MeccanicheEntity meccanica = new MeccanicaEntityBuilder(11L).withCodice("M142")
				.withDescrizione("Meccanica M142").build();
		PromozionePianificazioneEntity pianificazioneA = new PromozionePianificazioneEntityBuilder(1L)
				.withMeccanica(meccanica).withTipoRiga(tipoRigaSet).build();
		PromozionePianificazioneEntity pianificazioneB = new PromozionePianificazioneEntityBuilder(2L)
				.withMeccanica(meccanica).withTipoRiga(tipoRigaSet).build();
		PromozionePianificazioneEntity pianificazioneC = new PromozionePianificazioneEntityBuilder(3L)
				.withMeccanica(meccanica).withTipoRiga(tipoRigaSet).build();
		PromozionePianificazioneEntity pianificazioneD = new PromozionePianificazioneEntityBuilder(4L)
				.withMeccanica(meccanica).withTipoRiga(tipoRigaSet).build();
		PromozionePianificazioneEntity pianificazioneE = new PromozionePianificazioneEntityBuilder(5L)
				.withMeccanica(meccanica).withTipoRiga(tipoRigaSet).build();
		PromozionePianificazioneEntity pianificazioneF = new PromozionePianificazioneEntityBuilder(6L)
				.withMeccanica(meccanica).withTipoRiga(tipoRigaSet).build();
		PromozionePianificazioneEntity pianificazioneG = new PromozionePianificazioneEntityBuilder(7L)
				.withMeccanica(meccanica).withTipoRiga(tipoRigaSet).build();
		promozioneStatoA.setPromozioneTestataEntity(promoA);
		promozioneStatoB.setPromozioneTestataEntity(promoB);
		promozioneStatoC.setPromozioneTestataEntity(promoC);
		promozioneStatoD.setPromozioneTestataEntity(promoD);
		promozioneStatoE.setPromozioneTestataEntity(promoE);
		promozioneStatoF.setPromozioneTestataEntity(promoF);
		promozioneStatoG.setPromozioneTestataEntity(promoG);
		pianificazioneA.setPromozioneTestataEntity(promoA);
		pianificazioneB.setPromozioneTestataEntity(promoB);
		pianificazioneC.setPromozioneTestataEntity(promoC);
		pianificazioneD.setPromozioneTestataEntity(promoD);
		pianificazioneE.setPromozioneTestataEntity(promoE);
		pianificazioneF.setPromozioneTestataEntity(promoF);
		pianificazioneG.setPromozioneTestataEntity(promoG);
		persist(meccanica, pianificazioneA, pianificazioneB, pianificazioneC, pianificazioneD, pianificazioneE,
				pianificazioneF, pianificazioneG, promoA, promoB, promoC, promoD, promoE, promoF, promoG, promoG);
	}
}
