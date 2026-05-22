package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.enumeration.DBPromoUserFilterEnum;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
public class JpaPromozioneTestataDaoWithChannelsFilterTest extends AbstractDaoTest {
	@Inject
	private PromozioneTestataDAO promozioneTestataDAO;

	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
					JpaPromozioneTestataDaoImpl.class, DbPromo.class)
			.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	private PromozioneTestataEntity testata1; // CANCELLATA canale1
	private PromozioneTestataEntity testata2; // CREATA canale1
	private PromozioneTestataEntity testata3; // DISPONIBILE canale2
	private PromozioneTestataEntity testata4; // CREATA canale3
	private PromozioneTestataEntity testata5; // DISPONIBILE canale1
	private CanalePromozioneEntity canale1;
	private CanalePromozioneEntity canale2;
	private CanalePromozioneEntity canale3;
	private CanalePromozioneEntity canale4;

	@Before
	public void setUp() {
		// Useful dates
		final Date date1 = new GregorianCalendar(2021, Calendar.MARCH, 1).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.MARCH, 31).getTime();

		// Useful states
		StatoPromozioneEntity statoCancellata = createStatoPromozioneEntity(1L, "Cancellata",
				PromoStatusEnum.CANCELLATA_00.getKey());
		StatoPromozioneEntity statoCreata = createStatoPromozioneEntity(2L, "Creata",
				PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey());
		StatoPromozioneEntity statoDisponibile = createStatoPromozioneEntity(3L, "Disponibile",
				PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey());

		// Useful groups and channels
		final GruppoPromozioneEntity gruppoPromozioneEntity1 = createGruppoPromozioneEntity(1L, "G001", "Gruppo 1");
		canale1 = createCanalePromozioneEntity(1L, 1L, "Canale 1", gruppoPromozioneEntity1);
		canale2 = createCanalePromozioneEntity(2L, 2L, "Canale 2", gruppoPromozioneEntity1);
		canale3 = createCanalePromozioneEntity(3L, 3L, "Canale 3", gruppoPromozioneEntity1);
		canale4 = createCanalePromozioneEntity(4L, 4L, "Canale 4", gruppoPromozioneEntity1);

		// Testata in stato "CANCELLATA" su Canale1
		PromozioneStatoEntity promozioneStato1 = createPromozioneStatoEntity(1L, null, statoCancellata);
		Set<PromozioneStatoEntity> promozioneStati1 = new HashSet<>();
		promozioneStati1.add(promozioneStato1);
		testata1 = createPromozioneTestataEntity(1L, "Promo1", date1, date2, "2021", "Promozione 1", canale1,
				promozioneStati1, "junit");
		promozioneStato1.setPromozioneTestataEntity(testata1);
		statoCancellata.setPromozioneStatoEntities(promozioneStati1);

		// Testata in stato "CREATA" su Canale1
		PromozioneStatoEntity promozioneStato2 = createPromozioneStatoEntity(2L, null, statoCreata);
		Set<PromozioneStatoEntity> promozioneStati2 = new HashSet<>();
		promozioneStati2.add(promozioneStato2);
		testata2 = createPromozioneTestataEntity(2L, "Promo2", date1, date2, "2021", "Promozione 2", canale1,
				promozioneStati2, "foo");
		promozioneStato2.setPromozioneTestataEntity(testata2);
		statoCreata.setPromozioneStatoEntities(promozioneStati2);

		// Testata in stato "DISPONIBILE" su Canale2
		PromozioneStatoEntity promozioneStato3 = createPromozioneStatoEntity(3L, null, statoDisponibile);
		Set<PromozioneStatoEntity> promozioneStati3 = new HashSet<>();
		promozioneStati3.add(promozioneStato3);
		testata3 = createPromozioneTestataEntity(3L, "Promo3", date1, date2, "2021", "Promozione 3", canale2,
				promozioneStati3, "bar");
		promozioneStato3.setPromozioneTestataEntity(testata3);
		statoDisponibile.setPromozioneStatoEntities(promozioneStati3);

		// Testata in stato "CREATA" su Canale3
		PromozioneStatoEntity promozioneStato4 = createPromozioneStatoEntity(4L, null, statoCreata);
		Set<PromozioneStatoEntity> promozioneStati4 = new HashSet<>();
		promozioneStati4.add(promozioneStato4);
		testata4 = createPromozioneTestataEntity(4L, "Promo4", date1, date2, "2021", "Promozione 4", canale3,
				promozioneStati4, "baz");
		promozioneStato4.setPromozioneTestataEntity(testata4);
		statoCreata.setPromozioneStatoEntities(promozioneStati4);

		// Testata in stato "DISPONIBILE" su Canale1
		PromozioneStatoEntity promozioneStato5 = createPromozioneStatoEntity(5L, null, statoDisponibile);
		Set<PromozioneStatoEntity> promozioneStati5 = new HashSet<>();
		promozioneStati5.add(promozioneStato5);
		testata5 = createPromozioneTestataEntity(5L, "Promo5", date1, date2, "2021", "Promozione 5", canale1,
				promozioneStati5, "junit");
		promozioneStato5.setPromozioneTestataEntity(testata5);
		statoDisponibile.setPromozioneStatoEntities(promozioneStati5);

		openTransaction();
		testata1 = promozioneTestataDAO.persist(testata1);
		testata2 = promozioneTestataDAO.persist(testata2);
		testata3 = promozioneTestataDAO.persist(testata3);
		testata4 = promozioneTestataDAO.persist(testata4);
		testata5 = promozioneTestataDAO.persist(testata5);
		commitTransaction();
	}

	/**
	 * Quando il filtro sui canali è null, il filtro "non esiste" e devono essere
	 * considerati tutti i canali
	 */
	@Test
	public void findAllSecured_givenNullChannelsFilter_shouldIgnoreFilter() {
		final List<PromozioneTestataEntity> entities = promozioneTestataDAO.findAllSecured(null);
		assertFalse(entities.isEmpty());
		assertEquals(5, entities.size());
		assertListContains(entities, testata1, testata2, testata3, testata4, testata5);
	}

	/**
	 * Quando il filtro sui canali è lista vuota, non si ha "visibilità" su nessuna
	 * testata
	 */
	@Test
	public void findAllSecured_givenEmptyListChannelsFilter_shouldReturnEmptyList() {
		final List<PromozioneTestataEntity> entities = promozioneTestataDAO.findAllSecured(Collections.emptyList());
		assertTrue(entities.isEmpty());
	}

	/**
	 * Quando il filtro sui canali è impostato, solo le testate di quei canali sono
	 * visibili
	 */
	@Test
	public void findAllSecured_givenChannelsFilter_shouldReturnPermittedTestate() {
		// Testate su canale1 e canale2
		List<PromozioneTestataEntity> entities = promozioneTestataDAO.findAllSecured(Arrays.asList(canale1, canale2));
		assertFalse(entities.isEmpty());
		assertEquals(4, entities.size());
		assertListContains(entities, testata1, testata2, testata3, testata5);
		assertListNotContains(entities, testata4);
		// Testate su canale3
		entities = promozioneTestataDAO.findAllSecured(Collections.singletonList(canale3));
		assertFalse(entities.isEmpty());
		assertEquals(1, entities.size());
		assertListContains(entities, testata4);
		assertListNotContains(entities, testata1, testata2, testata3, testata5);
		// Testate su canale4
		entities = promozioneTestataDAO.findAllSecured(Collections.singletonList(canale4));
		assertTrue(entities.isEmpty());
	}

	@Test
	public void findAllNotCancelled_givenNullChannelsFilter_shouldIgnoreFilter() {
		final List<PromozioneTestataEntity> entities = promozioneTestataDAO.findAllNotCancelled(null);
		assertFalse(entities.isEmpty());
		assertEquals(4, entities.size());
		assertListContains(entities, testata2, testata3, testata4, testata5);
		assertListNotContains(entities, testata1);
	}

	@Test
	public void findAllNotCancelled_givenEmptyListChannelsFilter_shouldReturnEmptyList() {
		final List<PromozioneTestataEntity> entities = promozioneTestataDAO
				.findAllNotCancelled(Collections.emptyList());
		assertTrue(entities.isEmpty());
	}

	@Test
	public void findAllNotCancelled_givenChannelsFilter_shouldReturnPermittedTestate() {
		// Testate su canale1 e canale2
		List<PromozioneTestataEntity> entities = promozioneTestataDAO
				.findAllNotCancelled(Arrays.asList(canale1, canale2));
		assertFalse(entities.isEmpty());
		assertEquals(3, entities.size());
		assertListContains(entities, testata2, testata3, testata5);
		assertListNotContains(entities, testata1, testata4);
		// Testate su canale3
		entities = promozioneTestataDAO.findAllNotCancelled(Collections.singletonList(canale3));
		assertFalse(entities.isEmpty());
		assertEquals(1, entities.size());
		assertListContains(entities, testata4);
		assertListNotContains(entities, testata1, testata2, testata3, testata5);
		// Testate su canale4
		entities = promozioneTestataDAO.findAllNotCancelled(Collections.singletonList(canale4));
		assertTrue(entities.isEmpty());
	}

	@Test
	public void findAllNotCancelled_givenNullUserFiltersAndNullChannelsFilter_shouldIgnoreChannelsFilter() {
		final List<PromozioneTestataEntity> entities = promozioneTestataDAO.findAllNotCancelled(null, null);
		assertFalse(entities.isEmpty());
		assertEquals(4, entities.size());
		assertListContains(entities, testata2, testata3, testata4, testata5);
		assertListNotContains(entities, testata1);
	}

	@Test
	public void findAllNotCancelled_givenUserFiltersAndEmptyListChannelsFilter_shouldReturnEmptyList() {
		String filterAnno = "2021";
		Map<String, String> userFilters = new HashMap<>();
		userFilters.put(DBPromoUserFilterEnum.ANNO.name(), filterAnno);
		final List<PromozioneTestataEntity> entities = promozioneTestataDAO.findAllNotCancelled(userFilters,
				Collections.emptyList());
		assertTrue(entities.isEmpty());
	}

	@Test
	public void findAllNotCancelled_givenUserFiltersAndChannelsFilter_shouldReturnPermittedTestate() {
		Map<String, String> userFilters = new HashMap<>();
		userFilters.put(DBPromoUserFilterEnum.ANNO.name(), "2021");
		// Testate su canale1 e canale2
		List<PromozioneTestataEntity> entities = promozioneTestataDAO.findAllNotCancelled(userFilters,
				Arrays.asList(canale1, canale2));
		assertFalse(entities.isEmpty());
		assertEquals(3, entities.size());
		assertListContains(entities, testata2, testata3, testata5);
		assertListNotContains(entities, testata1, testata4);
		// Testate su canale3
		userFilters.put(DBPromoUserFilterEnum.CANALE.name(), "Canale 3");
		entities = promozioneTestataDAO.findAllNotCancelled(userFilters, Collections.singletonList(canale3));
		assertFalse(entities.isEmpty());
		assertEquals(1, entities.size());
		assertListContains(entities, testata4);
		assertListNotContains(entities, testata1, testata2, testata3, testata5);
		// Testate su canale4
		entities = promozioneTestataDAO.findAllNotCancelled(userFilters, Collections.singletonList(canale4));
		assertTrue(entities.isEmpty());
		// Filtro utente non ottiene risultati
		userFilters.put(DBPromoUserFilterEnum.GRUPPO.name(), "foo");
		userFilters.put(DBPromoUserFilterEnum.PROMOZIONE.name(), "bar");
		userFilters.put(DBPromoUserFilterEnum.SEMESTRE.name(), "baz");
		entities = promozioneTestataDAO.findAllNotCancelled(userFilters, Collections.singletonList(canale1));
		assertTrue(entities.isEmpty());
		// Filto utente con valori multipli
		userFilters.clear();
		userFilters.put(DBPromoUserFilterEnum.ANNO.name(), "2021,2022");
		entities = promozioneTestataDAO.findAllNotCancelled(userFilters, Collections.singletonList(canale1));
		assertFalse(entities.isEmpty());
		assertEquals(2, entities.size());
		assertListContains(entities, testata2, testata5);
		assertListNotContains(entities, testata1, testata3, testata4);
		// Filtro utente su canale non "visibile"
		userFilters.clear();
		userFilters.put(DBPromoUserFilterEnum.CANALE.name(), "Canale 3");
		entities = promozioneTestataDAO.findAllNotCancelled(userFilters, Collections.singletonList(canale1));
		assertTrue(entities.isEmpty());
		// Filtro utente su codice utente inserimento
		userFilters.clear();
		userFilters.put(DBPromoUserFilterEnum.UTENTE.name(), "junit");
		entities = promozioneTestataDAO.findAllNotCancelled(userFilters, Collections.singletonList(canale1));
		assertEquals(1, entities.size());
		// Filtro utente su stato
		userFilters.clear();
		userFilters.put(DBPromoUserFilterEnum.STATO.name(), "10");
		entities = promozioneTestataDAO.findAllNotCancelled(userFilters, Collections.singletonList(canale1));
		assertEquals(1, entities.size());
		entities = promozioneTestataDAO.findAllNotCancelled(userFilters, Collections.singletonList(canale2));
		assertTrue(entities.isEmpty());
		// Filtro utente con valore non valido
		userFilters.clear();
		userFilters.put("foo", "bar");
		entities = promozioneTestataDAO.findAllNotCancelled(userFilters, Collections.singletonList(canale1));
		assertFalse(entities.isEmpty());
		assertEquals(2, entities.size());
		assertListContains(entities, testata2, testata5);
		assertListNotContains(entities, testata1, testata3, testata4);
	}

	@Test
	public void findAllNotCancelled_givenUserFiltersWithDates_shouldReturnPermittedTestate() {
		final Date dataInizio = new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime();
		final Date dataFine = new GregorianCalendar(2021, Calendar.MAY, 4).getTime();
		final DateTimeUtils dtUtils = new DateTimeUtils();
		final String dataInizioExcel = dtUtils.toExcelDate(dataInizio);
		final String dataFineExcel = dtUtils.toExcelDate(dataFine);
		// Filtro utente su solo data inizio
		Map<String, String> userFilters = new HashMap<>();
		userFilters.put(DBPromoUserFilterEnum.DATA_INIZIO.name(), dataInizioExcel);
		List<PromozioneTestataEntity> entities = promozioneTestataDAO
				.findAllNotCancelled(userFilters, Collections.singletonList(canale1));
		assertEquals(2, entities.size());
		// Filtro utente su solo data fine
		userFilters.clear();
		userFilters.put(DBPromoUserFilterEnum.DATA_FINE.name(), dataFineExcel);
		entities = promozioneTestataDAO
				.findAllNotCancelled(userFilters, Collections.singletonList(canale1));
		assertEquals(2, entities.size());
		// Filtro utente su entrambe le date
		userFilters.clear();
		userFilters.put(DBPromoUserFilterEnum.DATA_INIZIO.name(), dataInizioExcel);
		userFilters.put(DBPromoUserFilterEnum.DATA_FINE.name(), dataFineExcel);
		entities = promozioneTestataDAO
				.findAllNotCancelled(userFilters, Collections.singletonList(canale1));
		assertEquals(2, entities.size());
	}

	@Test
	public void findOverlappingPromo_givenNullChannelsFilter_shouldIgnoreFilter() {
		// Arrange
		// testata2 e' quella di riferimento
		testata2.setDataInizio(new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime());
		testata2.setDataFine(new GregorianCalendar(2021, Calendar.APRIL, 30).getTime());
		// testata3 e testata4 sono in overlap
		testata3.setDataInizio(new GregorianCalendar(2021, Calendar.MARCH, 1).getTime());
		testata3.setDataFine(new GregorianCalendar(2021, Calendar.MARCH, 31).getTime());
		testata4.setDataInizio(new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime());
		testata4.setDataFine(new GregorianCalendar(2021, Calendar.MAY, 31).getTime());
		// testata5 inizia dopo
		testata5.setDataInizio(new GregorianCalendar(2021, Calendar.JULY, 1).getTime());
		testata5.setDataFine(new GregorianCalendar(2021, Calendar.DECEMBER, 31).getTime());
		// persist udpated testate
		openTransaction();
		testata2 = promozioneTestataDAO.persist(testata2);
		testata3 = promozioneTestataDAO.persist(testata3);
		testata4 = promozioneTestataDAO.persist(testata4);
		testata5 = promozioneTestataDAO.persist(testata5);
		commitTransaction();
		// Act
		final List<PromozioneTestataEntity> entities = promozioneTestataDAO.findOverlappingPromo(testata2, null);
		// Assert
		assertFalse(entities.isEmpty());
		assertEquals(2, entities.size());
		assertListContains(entities, testata3, testata4);
		assertListNotContains(entities, testata1, testata2, testata5);
	}

	@Test
	public void findOverlappingPromo_givenEmptyListChannelsFilter_shouldReturnEmptyList() {
		// Arrange
		// testata2 e' quella di riferimento
		testata2.setDataInizio(new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime());
		testata2.setDataFine(new GregorianCalendar(2021, Calendar.APRIL, 30).getTime());
		// testata3 e testata4 sono in overlap
		testata3.setDataInizio(new GregorianCalendar(2021, Calendar.MARCH, 1).getTime());
		testata3.setDataFine(new GregorianCalendar(2021, Calendar.MARCH, 31).getTime());
		testata4.setDataInizio(new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime());
		testata4.setDataFine(new GregorianCalendar(2021, Calendar.MAY, 31).getTime());
		// testata5 inizia dopo
		testata5.setDataInizio(new GregorianCalendar(2021, Calendar.JULY, 1).getTime());
		testata5.setDataFine(new GregorianCalendar(2021, Calendar.DECEMBER, 31).getTime());
		// persist udpated testate
		openTransaction();
		testata2 = promozioneTestataDAO.persist(testata2);
		testata3 = promozioneTestataDAO.persist(testata3);
		testata4 = promozioneTestataDAO.persist(testata4);
		testata5 = promozioneTestataDAO.persist(testata5);
		commitTransaction();
		// Act & Assert
		assertTrue(promozioneTestataDAO.findOverlappingPromo(testata2, Collections.emptyList()).isEmpty());
	}

	@Test
	public void findOverlappingPromo_givenChannelsFilter_shouldReturnPermittedTestate() {
		// Arrange
		// testata2 e' quella di riferimento
		testata2.setDataInizio(new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime());
		testata2.setDataFine(new GregorianCalendar(2021, Calendar.APRIL, 30).getTime());
		// testata3 e testata4 sono in overlap
		testata3.setDataInizio(new GregorianCalendar(2021, Calendar.MARCH, 1).getTime());
		testata3.setDataFine(new GregorianCalendar(2021, Calendar.MARCH, 31).getTime());
		testata4.setDataInizio(new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime());
		testata4.setDataFine(new GregorianCalendar(2021, Calendar.MAY, 31).getTime());
		// testata5 inizia dopo
		testata5.setDataInizio(new GregorianCalendar(2021, Calendar.JULY, 1).getTime());
		testata5.setDataFine(new GregorianCalendar(2021, Calendar.DECEMBER, 31).getTime());
		// persist udpated testate
		openTransaction();
		testata2 = promozioneTestataDAO.persist(testata2);
		testata3 = promozioneTestataDAO.persist(testata3);
		testata4 = promozioneTestataDAO.persist(testata4);
		testata5 = promozioneTestataDAO.persist(testata5);
		commitTransaction();
		// Act
		List<PromozioneTestataEntity> entities = promozioneTestataDAO.findOverlappingPromo(testata2,
				Collections.singletonList(canale2));
		// Assert
		assertFalse(entities.isEmpty());
		assertEquals(1, entities.size());
		assertListContains(entities, testata3);
		assertListNotContains(entities, testata1, testata2, testata4, testata5);
		// Act
		entities = promozioneTestataDAO.findOverlappingPromo(testata2, Arrays.asList(canale1, canale3));
		// Assert
		assertFalse(entities.isEmpty());
		assertEquals(1, entities.size());
		assertListContains(entities, testata4);
		assertListNotContains(entities, testata1, testata2, testata3, testata5);
		// Act & Assert
		assertTrue(promozioneTestataDAO.findOverlappingPromo(testata2, Collections.singletonList(canale4)).isEmpty());
	}

	private void assertListContains(List<PromozioneTestataEntity> list, PromozioneTestataEntity... testate) {
		Arrays.stream(testate).forEach(t -> assertTrue(list.contains(t)));
	}

	private void assertListNotContains(List<PromozioneTestataEntity> list, PromozioneTestataEntity... testate) {
		Arrays.stream(testate).forEach(t -> assertFalse(list.contains(t)));
	}

	private PromozioneTestataEntity createPromozioneTestataEntity(Long id, String codicePromo, Date dataInizio,
			Date dataFine, String anno, String descrizione, CanalePromozioneEntity canalePromozioneEntity,
			Set<PromozioneStatoEntity> promozioneStati, String username) {
		final PromozioneTestataEntity testataEntity = new PromozioneTestataEntity();
		testataEntity.setId(id);
		testataEntity.setCodicePromozione(codicePromo);
		testataEntity.setDataInizio(dataInizio);
		testataEntity.setDataFine(dataFine);
		testataEntity.setAnno(anno);
		testataEntity.setDescrizione(descrizione);
		testataEntity.setMuiCanalePromozione(canalePromozioneEntity);
		testataEntity.setPromozioneStatoEntities(promozioneStati);
		testataEntity.setCodiceUtenteInserimento(username);
		return testataEntity;
	}

	private StatoPromozioneEntity createStatoPromozioneEntity(Long id, String descrizione, String codice) {
		StatoPromozioneEntity statoPromozione = new StatoPromozioneEntity();
		statoPromozione.setId(id);
		statoPromozione.setDescrizione(descrizione);
		statoPromozione.setCodiceStato(codice);
		return statoPromozione;
	}

	private PromozioneStatoEntity createPromozioneStatoEntity(Long id, Date dataFineStato,
			StatoPromozioneEntity statoPromozioneEntity) {
		PromozioneStatoEntity promozioneStato = new PromozioneStatoEntity();
		promozioneStato.setId(id);
		promozioneStato.setDataFineStato(dataFineStato);
		promozioneStato.setStatoPromozioneEntity(statoPromozioneEntity);
		return promozioneStato;
	}

	private CanalePromozioneEntity createCanalePromozioneEntity(Long id, Long codiceCanale, String descrizione,
			GruppoPromozioneEntity gruppoPromozioneEntity) {
		CanalePromozioneEntity canalePromozione = new CanalePromozioneEntity();
		canalePromozione.setId(id);
		canalePromozione.setCodiceCanale(codiceCanale);
		canalePromozione.setDescrizione(descrizione);
		canalePromozione.setGruppoPromozioneEntity(gruppoPromozioneEntity);
		return canalePromozione;
	}

	private GruppoPromozioneEntity createGruppoPromozioneEntity(Long id, String codiceGruppo, String descrizione) {
		GruppoPromozioneEntity gruppoPromozione = new GruppoPromozioneEntity();
		gruppoPromozione.setId(id);
		gruppoPromozione.setCodiceGruppo(codiceGruppo);
		gruppoPromozione.setDescrizione(descrizione);
		return gruppoPromozione;
	}
}
