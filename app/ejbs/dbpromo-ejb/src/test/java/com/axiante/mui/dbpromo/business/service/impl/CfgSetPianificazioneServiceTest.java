package com.axiante.mui.dbpromo.business.service.impl;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgSetPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgSetPianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.impl.CfgSetPianificazioneServiceImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgSetPianificazioneServiceTest {
	@Spy
	CfgSetPianificazioneDAO dao;

	@Spy
	@InjectMocks
	private final CfgSetPianificazioneService service = new CfgSetPianificazioneServiceImpl();

	private List<CfgSetPianificazioneEntity> pianificazioni;

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Before
	public void setUp() {
		pianificazioni = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			final CfgSetPianificazioneEntity mock = mock(CfgSetPianificazioneEntity.class);
			mock.setDescrizione(String.format("Pianificazione numero %d", i));
			pianificazioni.add(mock);
		}
	}

	@Test
	public void findOpenSet_shouldReturnOnlyOneSet_whenConditionMet() {
		// Arrange
		final CfgSetPianificazioneEntity mock = mock(CfgSetPianificazioneEntity.class);
		when(dao.findOpenSet()).thenReturn(mock);
		when(mock.getDataFine()).thenReturn(null);
		// Act
		final CfgSetPianificazioneEntity activeSet = service.findOpenSet();
		// Assert
		assertNotNull(activeSet);
		assertNull(activeSet.getDataFine());
	}

	@Test
	public void findOpenSet_shouldReturnNull_whenNoSetAvailable() {
		// Arrange
		when(dao.findOpenSet()).thenReturn(null);
		// Act
		final CfgSetPianificazioneEntity activeSet = service.findOpenSet();
		// Assert
		assertNull(activeSet);
	}

	@Test
	public void findOpenSet_shouldThrowException_whenMoreSetMeetCondition() {
		// Arrange
		ex.expect(RuntimeException.class);
		when(dao.findOpenSet()).thenThrow(RuntimeException.class);
		// Act
		service.findOpenSet();
	}

	@Test
	public void findOpenSet_shouldThrowException_whenAllSetHaveEndDateSetted() {
		// Arrange
		ex.expect(RuntimeException.class);
		@SuppressWarnings("unchecked")
		final List<CfgSetPianificazioneEntity> entities = mock(List.class);
		when(entities.isEmpty()).thenReturn(false);
		when(dao.findOpenSet()).thenReturn(null);
		when(dao.findAllEnabled()).thenReturn(entities);
		// Act
		service.findOpenSet();
	}

	@Test
	public void findAllShouldReturnsAllItems() {
		when(dao.findAllEnabled()).thenReturn(pianificazioni);
		final List<CfgSetPianificazioneEntity> entities = service.getAllEnabled();
		verify(dao, times(1)).findAllEnabled();
		assertThat(entities, hasSize(5));
	}

	@Test
	public void duplicateSet_givenValidParametersAndAnOpenSet_shouldCreateNewSet() {
		// Arrange
		final String description = "Set duplicato";
		final Date date = new GregorianCalendar(2021, Calendar.JUNE, 1).getTime();
		final CfgSetPianificazioneEntity openSet = spy(CfgSetPianificazioneEntity.class);
		openSet.setId(42L);
		openSet.setDataInizio(new GregorianCalendar(2021, Calendar.APRIL, 1).getTime());
		final CfgSetPianificazioneEntity baseSet = spy(CfgSetPianificazioneEntity.class);
		baseSet.setDataInizio(new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime());
		baseSet.setDataFine(new GregorianCalendar(2021, Calendar.FEBRUARY, 28).getTime());
//		baseSet.setMuiCfgPianificaziones(new HashSet<>());
//		for (int i = 1; i <= 5; i++) {
//			CfgPianificazioneEntity spy = spy(CfgPianificazioneEntity.class);
//			spy.setId((long) i);
//			spy.setDescrizione("Pianificazione " + i);
//			baseSet.getMuiCfgPianificaziones().add(spy);
//			spy.setMuiCfgSetPianificazione(baseSet);
//		}
		doNothing().when(dao).persist(anyList());
		when(dao.findById(42)).thenReturn(openSet);
		// Act
		final CfgSetPianificazioneEntity duplicateSet = service.duplicateSet(openSet, baseSet, description, date,
				"junit");
		// Assert
		verify(dao, times(1)).persist(anyList());
		assertNotNull(duplicateSet);
		assertEquals(description, duplicateSet.getDescrizione());
		assertEquals(date, duplicateSet.getDataInizio());
//		assertNotNull(duplicateSet.getMuiCfgPianificaziones());
//		assertEquals(5, duplicateSet.getMuiCfgPianificaziones().size());
		final CfgSetPianificazioneEntity byId = dao.findById(42);
		assertEquals(byId.getDataFine(), new GregorianCalendar(2021, Calendar.MAY, 31).getTime());
	}

	@Test
	public void duplicateSet_givenValidParametersAndAnOpenSetSameAsBaseSet_shouldCreateNewSet() {
		// Arrange
		final String description = "Set duplicato";
		final Date date = new GregorianCalendar(2021, Calendar.JUNE, 1).getTime();
		final CfgSetPianificazioneEntity openSet = spy(CfgSetPianificazioneEntity.class);
		openSet.setId(42L);
		openSet.setDataInizio(new GregorianCalendar(2021, Calendar.MARCH, 1).getTime());
//		openSet.setMuiCfgPianificaziones(new HashSet<>());
//		for (int i = 1; i <= 5; i++) {
//			final CfgPianificazioneEntity spy = spy(CfgPianificazioneEntity.class);
//			spy.setId((long) i);
//			spy.setDescrizione("Pianificazione " + i);
//			openSet.getMuiCfgPianificaziones().add(spy);
//			spy.setMuiCfgSetPianificazione(openSet);
//
//		}
		doNothing().when(dao).persist(anyList());
		when(dao.findById(42)).thenReturn(openSet);
		// Act
		final CfgSetPianificazioneEntity duplicateSet = service.duplicateSet(openSet, openSet, description, date,
				"junit");
		// Assert
		verify(dao, times(1)).persist(anyList());
		assertNotNull(duplicateSet);
		assertEquals(description, duplicateSet.getDescrizione());
		assertEquals(date, duplicateSet.getDataInizio());
//		assertNotNull(duplicateSet.getMuiCfgPianificaziones());
//		assertEquals(5, duplicateSet.getMuiCfgPianificaziones().size());
		final CfgSetPianificazioneEntity byId = dao.findById(42);
		assertEquals(byId.getDataFine(), new GregorianCalendar(2021, Calendar.MAY, 31).getTime());
	}

	@Test
	public void duplicateSet_givenNullBaseSet_shouldThrowException() {
		// Arrange
		ex.expect(NullPointerException.class);
		final String description = "Set duplicato";
		final Date date = new GregorianCalendar(2021, Calendar.JUNE, 1).getTime();
		final CfgSetPianificazioneEntity openSet = mock(CfgSetPianificazioneEntity.class);
		// Act
		service.duplicateSet(openSet, null, description, date, "junit");
		// Assert
		verify(dao, never()).persist(anyList());
	}

	@Test
	public void duplicateSet_givenValidParametersWithoutAnOpenSet_shouldThrowException() {
		// Arrange
		ex.expect(RuntimeException.class);
		ex.expectMessage("Unable to duplicate given set");
		final String description = "Set duplicato";
		final Date date = new GregorianCalendar(2021, Calendar.JUNE, 1).getTime();
		final CfgSetPianificazioneEntity baseSet = spy(CfgSetPianificazioneEntity.class);
		baseSet.setDataInizio(new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime());
		baseSet.setDataFine(new GregorianCalendar(2021, Calendar.FEBRUARY, 28).getTime());
//		baseSet.setMuiCfgPianificaziones(new HashSet<>());
		for (int i = 1; i <= 5; i++) {
			final CfgPianificazioneEntity spy = spy(CfgPianificazioneEntity.class);
			spy.setId((long) i);
			spy.setDescrizione("Pianificazione " + i);
//			baseSet.getMuiCfgPianificaziones().add(spy);
//			spy.setMuiCfgSetPianificazione(baseSet);
		}
		// Act
		service.duplicateSet(null, baseSet, description, date, "junit");
		// Assert
		verify(dao, never()).persist(anyList());
	}

	@Test
	public void duplicateSet_shouldThrowException_whenOpenSetHasPromozioniDatesThatExceedEndDate() {
		// Arrange
		ex.expect(RuntimeException.class);
		ex.expectMessage("Unable to duplicate given set");
		final String description = "Set duplicato";
		final Date date = new GregorianCalendar(2021, Calendar.JUNE, 1).getTime();
		final CfgSetPianificazioneEntity openSet = spy(CfgSetPianificazioneEntity.class);
		openSet.setId(42L);
		openSet.setDataInizio(new GregorianCalendar(2021, Calendar.APRIL, 1).getTime());
		openSet.setPromozioneTestataEntities(new HashSet<>());
		for (int i = 1; i <= 5; i++) {
			final PromozioneTestataEntity spy = spy(PromozioneTestataEntity.class);
			spy.setDescrizione("Promozione " + i);
			if (i == 3) {
				spy.setDataInizio(new GregorianCalendar(2021, Calendar.AUGUST, 1).getTime());
				spy.setDataFine(new GregorianCalendar(2021, Calendar.AUGUST, 31).getTime());
			} else {
				spy.setDataInizio(new GregorianCalendar(2021, Calendar.APRIL, 1).getTime());
				spy.setDataFine(new GregorianCalendar(2021, Calendar.APRIL, 30).getTime());
			}
			openSet.addPromozioneTestataEntity(spy);
		}
		final CfgSetPianificazioneEntity baseSet = spy(CfgSetPianificazioneEntity.class);
		baseSet.setDataInizio(new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime());
		baseSet.setDataFine(new GregorianCalendar(2021, Calendar.FEBRUARY, 28).getTime());
//		baseSet.setMuiCfgPianificaziones(new HashSet<>());
//		for (int i = 1; i <= 5; i++) {
//			final CfgPianificazioneEntity spy = spy(CfgPianificazioneEntity.class);
//			spy.setId((long) i);
//			spy.setDescrizione("Pianificazione " + i);
//			baseSet.getMuiCfgPianificaziones().add(spy);
//			spy.setMuiCfgSetPianificazione(baseSet);
//
//		}
		// Act
		service.duplicateSet(openSet, baseSet, description, date, "junit");
		// Assert
		verify(dao, never()).persist(anyList());
	}

	@Test
	public void createSet_givenValidParametersAndAnOpenSet_shouldCreateNewSet() {
		// Arrange
		final String description = "Nuovo set";
		final Date date = new GregorianCalendar(2021, Calendar.JUNE, 1).getTime();
		final CfgSetPianificazioneEntity openSet = spy(CfgSetPianificazioneEntity.class);
		openSet.setId(42L);
		openSet.setDataInizio(new GregorianCalendar(2021, Calendar.APRIL, 1).getTime());
		doNothing().when(dao).persist(anyList());
		when(dao.findById(42)).thenReturn(openSet);
		// Act
		final CfgSetPianificazioneEntity entity = service.createSet(openSet, description, date, "junit");
		// Assert
		verify(dao, times(1)).persist(anyList());
		assertNotNull(entity);
		assertEquals(description, entity.getDescrizione());
		assertEquals(date, entity.getDataInizio());
		final CfgSetPianificazioneEntity byId = dao.findById(42);
		assertEquals(byId.getDataFine(), new GregorianCalendar(2021, Calendar.MAY, 31).getTime());
	}

	@Test
	public void createSet_givenValidParametersWithoutOpenSet_shouldCreateNewSet() {
		// Arrange
		final String description = "Nuovo set";
		final Date date = new GregorianCalendar(2021, Calendar.JUNE, 1).getTime();
		// Act
		final CfgSetPianificazioneEntity entity = service.createSet(null, description, date, "junit");
		// Assert
		verify(dao, times(1)).persist(anyList());
		assertNotNull(entity);
		assertEquals(description, entity.getDescrizione());
		assertEquals(date, entity.getDataInizio());
	}

	@Test
	public void createSet_shouldThrowException_whenOpenSetHasPromozioniDatesThatExceedEndDate() {
		// Arrange
		ex.expect(RuntimeException.class);
		ex.expectMessage("Unable to create given set");
		final String description = "Nuovo set";
		final Date date = new GregorianCalendar(2021, Calendar.JUNE, 1).getTime();
		final CfgSetPianificazioneEntity openSet = spy(CfgSetPianificazioneEntity.class);
		openSet.setId(42L);
		openSet.setDataInizio(new GregorianCalendar(2021, Calendar.APRIL, 1).getTime());
		openSet.setPromozioneTestataEntities(new HashSet<>());
		for (int i = 1; i <= 5; i++) {
			final PromozioneTestataEntity spy = spy(PromozioneTestataEntity.class);
			spy.setDescrizione("Promozione " + i);
			if (i == 3) {
				spy.setDataInizio(new GregorianCalendar(2021, Calendar.AUGUST, 1).getTime());
				spy.setDataFine(new GregorianCalendar(2021, Calendar.AUGUST, 31).getTime());
			} else {
				spy.setDataInizio(new GregorianCalendar(2021, Calendar.APRIL, 1).getTime());
				spy.setDataFine(new GregorianCalendar(2021, Calendar.APRIL, 30).getTime());
			}
			openSet.addPromozioneTestataEntity(spy);
		}
		// Act
		service.createSet(openSet, description, date, "junit");
		// Assert
		verify(dao, never()).persist(anyList());
	}

	@Test
	public void persist_givenValidEntity_shouldSave() {
		final CfgSetPianificazioneEntity mockEntity = mock(CfgSetPianificazioneEntity.class);
		when(dao.persist(any(CfgSetPianificazioneEntity.class))).thenReturn(mockEntity);
		final CfgSetPianificazioneEntity savedEntity = service.persist(mock(CfgSetPianificazioneEntity.class), "junit");
		verify(dao, times(1)).persist(any(CfgSetPianificazioneEntity.class));
		verify(service, times(1)).persist(any(CfgSetPianificazioneEntity.class), eq("junit"));
		assertNotNull(savedEntity);
	}

	@Test
	public void testFindPianificazioneById() {
		final long idSetPianificazione = 1L;
		service.findById(idSetPianificazione);
		verify(dao).findById(idSetPianificazione);
	}

	@Test
	public void testFindById(){
		final long idSetPianificazione = 1L;
		service.findById(idSetPianificazione);
		verify(dao).findById(idSetPianificazione);
	}
}
