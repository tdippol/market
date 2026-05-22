package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiIniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.MuiIniziativaService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromoConfigurationHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.VisualizzaPianificazioneHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianificazionePromoUtilTest {
	private static final String USERNAME = "junit";

	@Mock
	private PianificazioneService pianificazioneService;

	@Mock
	private PromoService promoService;

	@Mock
	private CanalePromozioneService canalePromozioneService;

	@Mock
	private VisualizzaPianificazioneHelper visualizzaPianificazioneHelper;

	@Spy
	private PromozionePianificazioneEntity entity;

	@Mock
	private PromoConfigurationHelper pianificazioneHelper;

	@Mock
	private Instance<MuiIniziativaService> muiIniziativaServiceInstance;

	@Mock
	private MuiIniziativaService muiIniziativaService;

	@Spy
	@InjectMocks
	private PianificazionePromoUtil util;

	@Test
	public void deletePromozionePianificazione_givenValidPianificazioneWithoutMaster_shouldReturnSuccessMessage() throws Exception {
		// Arrange
		final PromozioneTestataEntity testataEntity = spy(PromozioneTestataEntity.class);
		final MeccanicheEntity meccanicheEntity =spy(MeccanicheEntity.class);
		final CfgPianificazTipoRigaEntity tiporiga = spy(CfgPianificazTipoRigaEntity.class);
		when(tiporiga.getCodiceTipo()).thenReturn("E");
		meccanicheEntity.setDescrizione("MeccanichaEntity1");
		entity.setMeccanicaEntity(meccanicheEntity);

		entity.setTipoRiga(tiporiga);
		entity.setParent(null);
		entity.setUploadFidaty(new HashSet<>());
		entity.setChildren(new HashSet<>());
		for (int i = 0; i < 3; i++) {
			entity.addUploadFidaty(mock(UploadFidayEntity.class));
			entity.addDetail(mock(PromozionePianificazioneEntity.class));
		}
		entity.setPromozioneTestataEntity(testataEntity);
		doNothing().when(pianificazioneService).removePromozionePianificazioneEntity(any());
		when(entity.getElemento()).thenReturn("elemento");
		when(entity.getPromozioneTestataEntity()).thenReturn(testataEntity);
		// Act
		//        final String msg = util.deletePromozionePianificazione(entity, USERNAME);
		final String msg = util.delete(entity, USERNAME);
		// Assert
		verify(testataEntity, times(1)).removePromozionePianificazioneEntity(entity);
		assertNotNull(msg);
		assertTrue(msg.matches("^Cancellazione dell'elemento .* con meccanica .* avvenuta con successo$"));
	}

	@Test
	public void deletePromozionePianificazione_givenValidPianificazioneWithMaster_shouldReturnSuccessMessage() throws Exception {
		// Arrange
		final PromozioneTestataEntity testataEntity = mock(PromozioneTestataEntity.class);
		final PromozionePianificazioneEntity master = mock(PromozionePianificazioneEntity.class);
		final MeccanicheEntity meccanicheEntity =spy(MeccanicheEntity.class);
		final CfgPianificazTipoRigaEntity tiporiga = mock(CfgPianificazTipoRigaEntity.class);
		when(tiporiga.getCodiceTipo()).thenReturn("E");
		final CfgConfHeaderEntity configHeader = spy(CfgConfHeaderEntity.class);
		configHeader.setMinRaggruppamento(0);
		configHeader.setMeccanicaEntity(meccanicheEntity);

		meccanicheEntity.setDescrizione("MeccanichaEntity1");
		entity.setParent(master);
		entity.setTipoRiga(tiporiga);
		entity.setMeccanicaEntity(meccanicheEntity);
		when(entity.getPromozioneTestataEntity()).thenReturn(testataEntity);
		when(entity.getElemento()).thenReturn("elemento");
		//        when(master.getPromozioneTestataEntity()).thenReturn(testataEntity);
		when(pianificazioneHelper.getHeaderFromTestataAndMeccanica(Mockito.any(PromozioneTestataEntity.class),Mockito.any(MeccanicheEntity.class)))
				.thenReturn(configHeader);
		// Act
		final String msg = util.delete(entity, USERNAME);
		// Assert
		verify(testataEntity, times(1)).removePromozionePianificazioneEntity(entity);
		verify(promoService, times(1)).persist(testataEntity, USERNAME);
		assertNotNull(msg);
		assertTrue(msg.matches("^Cancellazione dell'elemento .* con meccanica .* avvenuta con successo$"));
	}

	@Test
	public void deletePromozionePianificazione_givenValidPianificazioneWithMasterAndSingleDetail_shouldReturnSuccessMessage() {
		// Arrange
		final PromozionePianificazioneEntity master = spy(PromozionePianificazioneEntity.class);
		final MeccanicheEntity meccanicheEntity =spy(MeccanicheEntity.class);
		final CfgPianificazTipoRigaEntity tiporiga = spy(CfgPianificazTipoRigaEntity.class);
		final CfgPianificazTipoRigaEntity tiporigaSet = spy(CfgPianificazTipoRigaEntity.class);
		final PromozionePianificazioneEntity set = spy(PromozionePianificazioneEntity.class);
		final PromozioneTestataEntity testata = spy(PromozioneTestataEntity.class);
		when(tiporiga.getCodiceTipo()).thenReturn("E");
		tiporigaSet.setCodiceTipo("S");
		meccanicheEntity.setDescrizione("MeccanichaEntity1");
		master.setChildren(new HashSet<>());
		master.addDetail(mock(PromozionePianificazioneEntity.class));
		entity.setParent(master);
		entity.setTipoRiga(tiporiga);
		entity.setMeccanicaEntity(meccanicheEntity);
		when(entity.getElemento()).thenReturn("elemento");
		final CfgPianificazTipoRigaEntity ragg = mock(CfgPianificazTipoRigaEntity.class);
		when(master.getTipoRiga()).thenReturn (ragg);
		when(ragg.getCodiceTipo()).thenReturn("R");

		set.setTipoRiga(tiporigaSet);
		set.setChildren(new HashSet<>());
		set.addDetail(master);

		master.setParent(set);

		when(master.getParent()).thenReturn(set);
		testata.setPromozionePianificazioneEntities(new HashSet<>());
		testata.addPromozionePianificazioneEntity(entity);
		testata.addPromozionePianificazioneEntity(master);
		testata.addPromozionePianificazioneEntity(set);

		when(master.getElemento()).thenReturn("Raggrruppamento1");
		when(master.getMeccanicaEntity()).thenReturn(meccanicheEntity);
		when(meccanicheEntity.getDescrizione()).thenReturn("Meccanica1");
		// Act
		final String msg = util.delete(entity, USERNAME);
		// Assert
		assertNotNull(msg);
		assertTrue(msg.matches("^Cancellazione dell'elemento .* con meccanica .* e del raggruppamento .* avvenuta con successo"));
	}

	@Test
	public void deletePromozionePianificazione_shouldReturnNull_whenRemoveMasterFail() {
		// Arrange
		final CfgConfHeaderEntity configHeader = spy(CfgConfHeaderEntity.class);
		configHeader.setMinRaggruppamento(2);

		final PromozionePianificazioneEntity master = spy(PromozionePianificazioneEntity.class);
		final PromozionePianificazioneEntity set = spy(PromozionePianificazioneEntity.class);
		final MeccanicheEntity meccanicheEntity =spy(MeccanicheEntity.class);
		meccanicheEntity.setDescrizione("MeccanichaEntity1");
		configHeader.setMeccanicaEntity(meccanicheEntity);
		master.setChildren(new HashSet<>());
		master.addDetail(entity);
		when(entity.getParent()).thenReturn(master);
		set.setChildren(new HashSet<>());
		set.addDetail(master);
		when(master.getParent()).thenReturn(set);
		entity.setMeccanicaEntity(meccanicheEntity);


		CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
		when(tipoRiga.getCodiceTipo()).thenReturn("E");
		when(entity.getTipoRiga()).thenReturn(tipoRiga);

		CfgPianificazTipoRigaEntity ragg = mock(CfgPianificazTipoRigaEntity.class);
		when(ragg.getCodiceTipo()).thenReturn("R");
		when(master.getTipoRiga()).thenReturn(ragg);


		doReturn(null).when(util).delete(master, USERNAME);

		assertNull(util.delete(entity, USERNAME));
	}

	@Test
	public void validateUpdatedPlanningData_givenWrongField_shouldReturnFalse() {
		// Arrange
		final CfgPianificazioneEntity pianificazioneEntity = mockPianificazioneWithType("Wrong");
		// Act & Assert
		assertFalse(util.validateUpdatedPlanningData(pianificazioneEntity, "Luke", "[Luke;Leia]"));
	}

	@Test
	public void validateUpdatedPlanningData_givenStringField_shouldReturnTrue_whenValueInListOrListIsNull() {
		// Arrange
		final CfgPianificazioneEntity pianificazioneEntity = mockPianificazioneWithType("String");
		// Act & Assert
		assertTrue(util.validateUpdatedPlanningData(pianificazioneEntity, "Luke", null));
		assertTrue(util.validateUpdatedPlanningData(pianificazioneEntity, "Luke", "[Luke;Leia]"));
		assertTrue(util.validateUpdatedPlanningData(pianificazioneEntity, "Luke||Anakin", "[Anakin;Luke;Leia]"));
	}

	@Test
	public void validateUpdatedPlanningData_givenStringField_shouldReturnFalse_whenValueNotInList() {
		// Arrange
		final CfgPianificazioneEntity pianificazioneEntity = mockPianificazioneWithType("String");
		// Act & Assert
		assertFalse(util.validateUpdatedPlanningData(pianificazioneEntity, "Anakin", "[Luke;Leia]"));
		assertFalse(util.validateUpdatedPlanningData(pianificazioneEntity, "Luke||Han Solo", "[Anakin;Luke;Leia]"));
	}

	@Test
	public void validateUpdatedPlanningData_givenIntegerField_shouldReturnTrue_whenValueInList() {
		// Arrange
		final CfgPianificazioneEntity pianificazioneEntity = mockPianificazioneWithType("Integer");
		// Act & Assert
		assertTrue(util.validateUpdatedPlanningData(pianificazioneEntity, 42, "[40..50]"));
		assertTrue(util.validateUpdatedPlanningData(pianificazioneEntity, "42||23", "[20..50]"));
	}

	@Test
	public void validateUpdatedPlanningData_givenIntegerField_shouldReturnFalse_whenValueNotInList() {
		// Arrange
		final CfgPianificazioneEntity pianificazioneEntity = mockPianificazioneWithType("Integer");
		// Act & Assert
		assertFalse(util.validateUpdatedPlanningData(pianificazioneEntity, 69, "[40..50]"));
		assertFalse(util.validateUpdatedPlanningData(pianificazioneEntity, "42||69", "[20..50]"));
	}

	@Test
	public void validateUpdatedPlanningData_givenNumberField_shouldReturnTrue_whenValueInList() {
		// Arrange
		final CfgPianificazioneEntity pianificazioneEntity = mockPianificazioneWithType("Number");
		// Act & Assert
		assertTrue(util.validateUpdatedPlanningData(pianificazioneEntity, 42.0, "[42.0;23.23]"));
		assertTrue(util.validateUpdatedPlanningData(pianificazioneEntity, "42.0||23.23", "[42.0;23.23]"));
	}

	@Test
	public void validateUpdatedPlanningData_givenNumberField_shouldReturnTrue_whenValueNotInList() {
		// Arrange
		final CfgPianificazioneEntity pianificazioneEntity = mockPianificazioneWithType("Number");
		// Act & Assert
		assertFalse(util.validateUpdatedPlanningData(pianificazioneEntity, 69.0, "[42.0;23.23]"));
		assertFalse(util.validateUpdatedPlanningData(pianificazioneEntity, "42.0||69.69", "[42.0;23.23]"));
	}

	@Test
	public void validateUpdatedPlanningData_givenDateField_shouldReturnTrue() {
		// Arrange
		final CfgPianificazioneEntity pianificazioneEntity = mockPianificazioneWithType("Date");
		final Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 15).getTime();
		// Act & Assert
		assertTrue(util.validateUpdatedPlanningData(pianificazioneEntity, date, "[44242.0]"));
	}

	@Test
	public void getCorrectFormatPickListValues_givenEmptyOrInvalidStringList_shouldReturnEmptyList() {
		assertTrue(util.getCorrectFormatPickListValues(null).isEmpty());
		assertTrue(util.getCorrectFormatPickListValues("").isEmpty());
		assertTrue(util.getCorrectFormatPickListValues("hello").isEmpty());
		assertTrue(util.getCorrectFormatPickListValues("[hello").isEmpty());
		assertTrue(util.getCorrectFormatPickListValues("[Anakin..Luke..Leia]").isEmpty());
		assertTrue(util.getCorrectFormatPickListValues("[Anakin..Luke]").isEmpty());
		assertTrue(util.getCorrectFormatPickListValues("[42..Luke]").isEmpty());
	}

	@Test
	public void checkValueAvailability_shouldReturnTrue_whenValueToBeUpdatedIsDifferentOrNull() {
		// Arrange
		final List<PromozioneTestataEntity> testate = mockTestate(2, 2);
		final MeccanicheEntity meccanicaEntity = mock(MeccanicheEntity.class);
		when(meccanicaEntity.getId()).thenReturn(1L);
		when(visualizzaPianificazioneHelper.invokeGetterEntity(eq("COLUMN"), any(PromozionePianificazioneEntity.class)))
				.thenReturn("42");
		// Act & Assert
		assertTrue(util.checkValueAvailability(testate, "10", "COLUMN", meccanicaEntity));
		assertTrue(util.checkValueAvailability(testate, null, "COLUMN", meccanicaEntity));
	}

	@Test
	public void checkValueAvailability_shouldReturnFalse_whenValueToBeUpdatedIsEqual() {
		// Arrange
		final List<PromozioneTestataEntity> testate = mockTestate(2, 2);
		final MeccanicheEntity meccanicaEntity = mock(MeccanicheEntity.class);
		when(meccanicaEntity.getId()).thenReturn(1L);
		when(visualizzaPianificazioneHelper.invokeGetterEntity(eq("COLUMN"), any(PromozionePianificazioneEntity.class)))
				.thenReturn("10");
		// Act & Assert
		assertFalse(util.checkValueAvailability(testate, "10", "COLUMN", meccanicaEntity));
	}

	@Test
	public void checkValueAvailability_shouldReturnTrue_whenMeccanicaInPromozioneIsNull() {
		// Arrange
		final List<PromozioneTestataEntity> testate = mockTestate(1, 1);
		testate.get(0).getPromozionePianificazioneEntities().iterator().next().setMeccanicaEntity(null);
		final MeccanicheEntity meccanicaEntity = mock(MeccanicheEntity.class);
		// Act & Assert
		assertTrue(util.checkValueAvailability(testate, "10", "COLUMN", meccanicaEntity));
	}

	@Test
	public void checkValueAvailability_shouldReturnTrue_whenMeccanicaIsNotAlreadyPresent() {
		// Arrange
		final List<PromozioneTestataEntity> testate = mockTestate(1, 1);
		final MeccanicheEntity meccanicheEntity = mock(MeccanicheEntity.class);
		when(meccanicheEntity.getId()).thenReturn(42L);
		// Act & Assert
		assertTrue(util.checkValueAvailability(testate, "10", "COLUMN", meccanicheEntity));
	}

	@Test
	public void createColumnValueArray_shouldReturnListOfUnusedValues() {
		// Arrange
		final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
		final List<PromozioneTestataEntity> testataEntities = mockTestate(2, 2);
		final MeccanicheEntity meccanicheEntity = mock(MeccanicheEntity.class);
		final List<CanalePromozioneEntity> canali = new ArrayList<>();
		canali.add(mock(CanalePromozioneEntity.class));
		when(meccanicheEntity.getId()).thenReturn(1L);
		when(promoService.findOverlappingPromo(any(PromozioneTestataEntity.class), any())).thenReturn(testataEntities);
		when(visualizzaPianificazioneHelper.invokeGetterEntity(eq("COLUMN"), any(PromozionePianificazioneEntity.class)))
				.thenReturn("Han");
		// Act
		final String[] values = util.createColumnValueArray(entity, new String[] { "Luke", "Leia", "Han" },
				"COLUMN", meccanicheEntity, canali);
		// Assert
		assertNotNull(values);
		assertEquals(2, values.length);
	}

	@Test
	public void createColumnValueArray_shouldReturnOriginalList_whenValuesNotInPromo() {
		// Arrange
		final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
		final List<PromozioneTestataEntity> testataEntities = mockTestate(2, 2);
		final MeccanicheEntity meccanicheEntity = mock(MeccanicheEntity.class);
		final List<CanalePromozioneEntity> canali = new ArrayList<>();
		canali.add(mock(CanalePromozioneEntity.class));
		when(meccanicheEntity.getId()).thenReturn(1L);
		when(promoService.findOverlappingPromo(any(PromozioneTestataEntity.class), any())).thenReturn(testataEntities);
		when(visualizzaPianificazioneHelper.invokeGetterEntity(eq("COLUMN"), any(PromozionePianificazioneEntity.class)))
				.thenReturn(null);
		// Act
		final String[] values = util.createColumnValueArray(entity, new String[] { "Luke", "Leia", "Han" },
				"COLUMN", meccanicheEntity, canali);
		// Assert
		assertNotNull(values);
		assertEquals(3, values.length);
	}

	@Test
	public void createColumnValueArray_shouldReturnOriginalList_whenValueInPromoNotInList() {
		// Arrange
		final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
		final MeccanicheEntity meccanicheEntity = mock(MeccanicheEntity.class);
		final List<PromozioneTestataEntity> testataEntities = mockTestate(2, 2);
		final List<CanalePromozioneEntity> canali = new ArrayList<>();
		canali.add(mock(CanalePromozioneEntity.class));
		when(meccanicheEntity.getId()).thenReturn(1L);
		when(promoService.findOverlappingPromo(any(PromozioneTestataEntity.class), any())).thenReturn(testataEntities);
		when(visualizzaPianificazioneHelper.invokeGetterEntity(eq("COLUMN"), any(PromozionePianificazioneEntity.class)))
				.thenReturn("Anakin");
		// Act
		final String[] values = util.createColumnValueArray(entity, new String[] { "Luke", "Leia", "Han" },
				"COLUMN", meccanicheEntity, canali);
		// Assert
		assertNotNull(values);
		assertEquals(3, values.length);
	}

	@Test
	public void isDateUpdatedValid_shouldReturnTrue_whenGivenDatesAreBetweenPromotionDates() {
		// Arrange
		PromozionePianificazioneEntity promoEntity = spy(PromozionePianificazioneEntity.class);
		PromozioneTestataEntity testataEntity = mock(PromozioneTestataEntity.class);
		promoEntity.setPromozioneTestataEntity(testataEntity);
		LocalDate now = LocalDate.now();
		LocalDate refStartLocalDate = now.minusDays(30);
		LocalDate refEndLocalDate = now.plusDays(30);
		Date refStartDate = Date.from(refStartLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date refEndDate = Date.from(refEndLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		LocalDate startLocalDate = now.plusDays(1);
		LocalDate endLocalDate = now.plusDays(10);
		Date startDate = Date.from(startLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(endLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		// Act & Assert
		assertTrue(util.isDateUpdatedValid(startDate, endDate, refStartDate, refEndDate));
	}

	@Test
	public void isDateUpdatedValid_shouldReturnFalse_whenGivenDatesAreNotBetweenPromotionDates() {
		// Arrange
		PromozionePianificazioneEntity promoEntity = spy(PromozionePianificazioneEntity.class);
		PromozioneTestataEntity testataEntity = mock(PromozioneTestataEntity.class);
		promoEntity.setPromozioneTestataEntity(testataEntity);
		Date startDate = new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime();
		Date endDate = new GregorianCalendar(2021, Calendar.APRIL, 30).getTime();
		// Act & Assert
		assertFalse(util.isDateUpdatedValid(
				new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime(),
				new GregorianCalendar(2021, Calendar.MAY, 31).getTime(),
				startDate, endDate
		));
		assertFalse(util.isDateUpdatedValid(
				new GregorianCalendar(2021, Calendar.MARCH, 1).getTime(),
				new GregorianCalendar(2021, Calendar.MAY, 31).getTime(),
				startDate, endDate
		));
		assertFalse(util.isDateUpdatedValid(
				new GregorianCalendar(2021, Calendar.FEBRUARY, 28).getTime(),
				new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime(),
				startDate, endDate
		));
	}

	@Test
	public void isMeccanicaAvailable_shouldReturnTrue_whenThereAreNotCanalePromozione() {
		// Arrange
		final PromozioneTestataEntity testataEntity = mock(PromozioneTestataEntity.class);
		final CanalePromozioneEntity canalePromozioneEntity = mock(CanalePromozioneEntity.class);
		when(canalePromozioneEntity.getCodiceCanale()).thenReturn(1L);
		when(testataEntity.getCanalePromozioneEntity()).thenReturn(canalePromozioneEntity);
		when(canalePromozioneService.findByCodiceCanale(anyLong())).thenReturn(null);
		// Act & Assert
		assertTrue(util.isMeccanicaAvailable(testataEntity, "MC"));
	}

	@Test
	public void isMeccanicaAvailable_shouldReturnTrue_whenCanalePromozioneDoesNotHaveRepeat() {
		// Arrange
		final PromozioneTestataEntity testataEntity = mock(PromozioneTestataEntity.class);
		final CanalePromozioneEntity canalePromozioneEntity = mock(CanalePromozioneEntity.class);
		when(canalePromozioneEntity.getRipetizioneMeccaniche()).thenReturn(null);
		when(canalePromozioneEntity.getCodiceCanale()).thenReturn(1L);
		when(testataEntity.getCanalePromozioneEntity()).thenReturn(canalePromozioneEntity);
		when(canalePromozioneService.findByCodiceCanale(anyLong())).thenReturn(canalePromozioneEntity);
		// Act & Assert
		assertTrue(util.isMeccanicaAvailable(testataEntity, "MC"));
	}

	@Test
	public void isMeccanicaAvailable_shouldReturnTrue_whenThereAreRepeatAvailable() {
		// Arrange
		final PromozioneTestataEntity testataEntity = mockTestate(1, 3).get(0);
		testataEntity.getPromozionePianificazioneEntities()
				.forEach(i -> i.getMeccanicaEntity().setCodiceMeccanica("MC"));
		testataEntity.getPromozionePianificazioneEntities().stream()
				.findFirst()
				.orElse(null)
				.setParent(mock(PromozionePianificazioneEntity.class));
		final CanalePromozioneEntity canalePromozioneEntity = mock(CanalePromozioneEntity.class);
		when(canalePromozioneEntity.getRipetizioneMeccaniche()).thenReturn(5);
		when(canalePromozioneEntity.getCodiceCanale()).thenReturn(1L);
		when(testataEntity.getCanalePromozioneEntity()).thenReturn(canalePromozioneEntity);
		when(canalePromozioneService.findByCodiceCanale(anyLong())).thenReturn(canalePromozioneEntity);
		// Act & Assert
		assertTrue(util.isMeccanicaAvailable(testataEntity, "MC"));
	}

	@Test
	public void isMeccanicaAvailable_shouldReturnFalse_whenThereAreNotRepeatAvailable() {
		final PromozioneTestataEntity testataEntity = mockTestate(1, 5).get(0);
		testataEntity.getPromozionePianificazioneEntities()
				.forEach(i -> i.getMeccanicaEntity().setCodiceMeccanica("MC"));
		testataEntity.getPromozionePianificazioneEntities().stream()
				.findFirst()
				.orElse(null)
				.getMeccanicaEntity()
				.setCodiceMeccanica("AB");
		final CanalePromozioneEntity canalePromozioneEntity = mock(CanalePromozioneEntity.class);
		when(canalePromozioneEntity.getRipetizioneMeccaniche()).thenReturn(4);
		when(canalePromozioneEntity.getCodiceCanale()).thenReturn(1L);
		when(testataEntity.getCanalePromozioneEntity()).thenReturn(canalePromozioneEntity);
		when(canalePromozioneService.findByCodiceCanale(anyLong())).thenReturn(canalePromozioneEntity);
		// Act & Assert
		assertFalse(util.isMeccanicaAvailable(testataEntity, "MC"));
	}

	@Test
	public void getAvailableProgressiveDiscountCodesBuoniPotenziamento_shouldReturnOnlyCodesNotAlreadyUsed() {
		// Arrange
		final PromozioneTestataEntity entity = mockTestate(1, 3).get(0);
		entity.getPromozionePianificazioneEntities().forEach(i -> i.setBuonoScontoRadice(1));
		final List<String> pickListValue = Arrays.asList("10", "20");
		final List<Integer> range = Arrays.asList(10, 30);
		// Act
		final List<Integer> ret = util.getAvailableProgressiveDiscountCodesBuoniPotenziamento(
				entity,
				1,
				new ArrayList<>(pickListValue),
				new ArrayList<>(range)
		);
		// Assert
		assertNotNull(ret);
		assertFalse(ret.isEmpty());
		assertEquals(2, ret.size());
		assertEquals(10, (int) ret.get(0));
		assertEquals(30, (int) ret.get(1));
	}

	@Test
	public void getAvailableProgressiveDiscountCodesBuoniPotenziamento_givenNullRange_shouldReturnOnlyCodesNotAlreadyUsed() {
		// Arrange
		final PromozioneTestataEntity entity = mockTestate(1, 3).get(0);
		entity.getPromozionePianificazioneEntities().forEach(i -> i.setBuonoScontoRadice(1));
		final List<String> pickListValue = Arrays.asList("10", "20", "30", "40");
		// Act
		final List<Integer> ret = util.getAvailableProgressiveDiscountCodesBuoniPotenziamento(
				entity,
				1,
				new ArrayList<>(pickListValue),
				null
		);
		// Assert
		assertNotNull(ret);
		assertFalse(ret.isEmpty());
		assertEquals(4, ret.size());
	}

	@Test(expected = NullPointerException.class)
	public void getAvailableProgressiveDiscountCodesBuoniPotenziamento_givenNullTestata_shouldThrowException() {
		util.getAvailableProgressiveDiscountCodesBuoniPotenziamento(null, 1, Arrays.asList("One", "Two"), null);
	}

	@Test(expected = NullPointerException.class)
	public void checkCodiceOnLineUniqueness_givenNullTestata_shouldThrowException() {
		util.checkCodiceOnLineUniqueness(null, "42", null);
	}

	@Test
	public void checkCodiceOnLineUniqueness_givenNullOrEmptyValue_shouldReturnTrue() {
		assertTrue(util.checkCodiceOnLineUniqueness(mock(PromozioneTestataEntity.class), null, null));
		assertTrue(util.checkCodiceOnLineUniqueness(mock(PromozioneTestataEntity.class), "", null));
	}

	@Test
	public void checkCodiceOnLineUniqueness_shouldReturnTrue_whenThereAreNotUsedCodes() {
		// Arrange
		final PromozioneTestataEntity entity = mockTestate(1, 3).get(0);
		final List<CanalePromozioneEntity> canali = new ArrayList<>();
		canali.add(mock(CanalePromozioneEntity.class));
		when(promoService.findOverlappingPromo(entity, canali)).thenReturn(new ArrayList<>());
		// Act & Assert
		assertTrue(util.checkCodiceOnLineUniqueness(entity, "42", canali));
	}

	@Test
	public void checkCodiceOnLineUniqueness_shouldReturnTrue_whenCodiceOnlineIsUnique() {
		// Arrange
		final CanalePromozioneEntity canalePromoEntity1 = spy(CanalePromozioneEntity.class);
		canalePromoEntity1.setDescrizione(PianificazioneConstants.BUONI_CATEGORIA);
		final CanalePromozioneEntity canalePromoEntity2 = spy(CanalePromozioneEntity.class);
		canalePromoEntity2.setDescrizione(PianificazioneConstants.BUONI_POTENZIAMENTO);
		final PromozioneTestataEntity entity = mockTestate(1, 3).get(0);
		final List<PromozioneTestataEntity> overlappingEntities = mockTestate(3, 2, false);
		IntStream.range(0, overlappingEntities.size())
				.forEach(i -> {
					overlappingEntities.get(i).setId((long) i + 10);
					overlappingEntities.get(i).setMuiCanalePromozione(i == 0 ? canalePromoEntity1 : canalePromoEntity2);
				});
		final List<CanalePromozioneEntity> canali = new ArrayList<>();
		canali.add(mock(CanalePromozioneEntity.class));
		when(promoService.findOverlappingPromo(entity, canali)).thenReturn(overlappingEntities);
		// Act & Assert
		assertTrue(util.checkCodiceOnLineUniqueness(entity, "ONLINE_42", canali));
	}

	@Test
	public void checkCodiceOnLineUniqueness_shouldReturnFalse_whenCodiceOnlineIsAlreadyUsed() {
		// Arrange
		final CanalePromozioneEntity canalePromoEntity1 = spy(CanalePromozioneEntity.class);
		canalePromoEntity1.setDescrizione(PianificazioneConstants.BUONI_CATEGORIA);
		final CanalePromozioneEntity canalePromoEntity2 = spy(CanalePromozioneEntity.class);
		canalePromoEntity2.setDescrizione(PianificazioneConstants.BUONI_POTENZIAMENTO);
		final PromozioneTestataEntity entity = mockTestate(1, 3).get(0);
		final List<PromozioneTestataEntity> overlappingEntities = mockTestate(3, 2, false);
		IntStream.range(0, overlappingEntities.size())
				.forEach(i -> {
					overlappingEntities.get(i).setId((long) i + 10);
					overlappingEntities.get(i).setMuiCanalePromozione(i == 0 ? canalePromoEntity1 : canalePromoEntity2);
				});
		final List<CanalePromozioneEntity> canali = new ArrayList<>();
		canali.add(mock(CanalePromozioneEntity.class));
		when(promoService.findOverlappingPromo(entity, canali)).thenReturn(overlappingEntities);
		// Act & Assert
		assertFalse(util.checkCodiceOnLineUniqueness(entity, "ONLINE_1", canali));
	}

	@Test(expected = NullPointerException.class)
	public void getNumeroRaggruppamentoPrecaricatiSuCarta_givenNullTestata_shouldThrowException() {
		util.getNumeroRaggruppamentoPrecaricatiSuCarta(null, "");
	}

	@Test
	public void getNumeroRaggruppamentoPrecaricatiSuCarta_givenTestataWithoutPromo_shouldReturnNull() {
		// Arrange
		final PromozioneTestataEntity testataEntity = mockTestate(1, 4, false).get(0);
		when(testataEntity.getPromozionePianificazioneEntities()).thenReturn(null);
		// Act & Assert
		assertNull(util.getNumeroRaggruppamentoPrecaricatiSuCarta(testataEntity, ""));
	}

	@Test
	public void getNumeroRaggruppamentoPrecaricatiSuCarta_givenTestataWithEmptyPromoAndEmptyRange_shouldReturnNull() {
		// Arrange
		final PromozioneTestataEntity testataEntity = mockTestate(1, 4, false).get(0);
		when(testataEntity.getPromozionePianificazioneEntities()).thenReturn(Collections.emptySet());
		// Act & Assert
		assertNull(util.getNumeroRaggruppamentoPrecaricatiSuCarta(testataEntity, null));
		assertNull(util.getNumeroRaggruppamentoPrecaricatiSuCarta(testataEntity, "[;]"));
	}

	@Test
	public void getNumeroRaggruppamentoPrecaricatiSuCarta_givenTestataWithEmptyPromoAndValidRange_shouldReturnFirstRangeValue() {
		// Arrange
		final PromozioneTestataEntity testataEntity = mockTestate(1, 4, false).get(0);
		when(testataEntity.getPromozionePianificazioneEntities()).thenReturn(Collections.emptySet());
		// Act & Assert
		assertEquals("10", util.getNumeroRaggruppamentoPrecaricatiSuCarta(testataEntity, "[10;20]"));
	}

	@Test
	public void getNumeroRaggruppamentoPrecaricatiSuCarta_givenTestataWithPromoWithoutLastPianificazione_shouldReturnFirstRangeValue() {
		// Arrange
		final PromozioneTestataEntity testataEntity = mockTestate(1, 4, false).get(0);
		// Act & Assert
		assertEquals("10", util.getNumeroRaggruppamentoPrecaricatiSuCarta(testataEntity, "[10;20]"));
	}

	@Test
	public void getNumeroRaggruppamentoPrecaricatiSuCarta_givenTestataWithPromo_shouldReturnFirstRangeValue() {
		// Arrange
		final PromozioneTestataEntity testataEntity = mockTestate(1, 4, false).get(0);
		testataEntity.getPromozionePianificazioneEntities().forEach(i -> i.setNumRaggruppamento("1"));
		// Act & Assert
		assertEquals("10", util.getNumeroRaggruppamentoPrecaricatiSuCarta(testataEntity, "[10;20]"));
	}

	@Test
	public void getNumeroRaggruppamentoPrecaricatiSuCarta_givenTestataWithPromoAndSingleRangeValue_shouldReturnFirstRangeValue() {
		// Arrange
		final PromozioneTestataEntity testataEntity = mockTestate(1, 4, false).get(0);
		testataEntity.getPromozionePianificazioneEntities().forEach(i -> i.setNumRaggruppamento("1"));
		// Act & Assert
		assertEquals("42", util.getNumeroRaggruppamentoPrecaricatiSuCarta(testataEntity, "[42]"));
	}

	@Test
	public void getCorrectValuesSpecialColumn_givenInvalidPickList_shouldReturnGivenValue() {
		assertEquals("42", util.getCorrectValuesSpecialColumn(entity,
				mock(PromozioneTestataEntity.class),
				"42",
				"COLUMN",
				null,
				null));
		assertEquals("42", util.getCorrectValuesSpecialColumn(entity,
				mock(PromozioneTestataEntity.class),
				"42",
				"COLUMN",
				Collections.emptyList(),
				null));
	}

	@Test
	public void getCorrectValuesSpecialColumn_givenValidPickListAndNullValue_shouldReturnFirstAvailableValue() {
		// Arrange
		final List<String> values = Arrays.asList("10", "20");
		final PromozioneTestataEntity testataEntity = mockTestate(1, 3).get(0);
		final List<CanalePromozioneEntity> canali = new ArrayList<>();
		canali.add(mock(CanalePromozioneEntity.class));
		// Act & Assert
		assertEquals("10", util.getCorrectValuesSpecialColumn(entity,
				testataEntity,
				null,
				"COLUMN",
				values,
				canali));
	}

	@Test
	public void getCorrectValuesSpecialColumn_givenValidPickListAndNotNullValue_shouldReturnFirstAvailableValue() {
		// Arrange
		final List<String> values = Arrays.asList("10", "20");
		final PromozioneTestataEntity testataEntity = mockTestate(1, 3).get(0);
		final List<CanalePromozioneEntity> canali = new ArrayList<>();
		canali.add(mock(CanalePromozioneEntity.class));
		// Act & Assert
		assertEquals("10", util.getCorrectValuesSpecialColumn(entity,
				testataEntity,
				"42",
				"COLUMN",
				values,
				canali));
	}

	@Test
	public void getCorrectValuesSpecialColumn_givenValidPickListAndNotNullValueContainedInList_shouldReturnGivenValue() {
		// Arrange
		final List<String> values = Arrays.asList("10", "42");
		final PromozioneTestataEntity testataEntity = mockTestate(1, 3).get(0);
		final List<CanalePromozioneEntity> canali = new ArrayList<>();
		canali.add(mock(CanalePromozioneEntity.class));
		// Act & Assert
		assertEquals("42", util.getCorrectValuesSpecialColumn(entity,
				testataEntity,
				"42",
				"COLUMN",
				values,
				canali));
	}

	@Test(expected = NullPointerException.class)
	public void getAvailableNumeroRaggruppamentoSuCarta_givenNullTestata_shouldThrowException() {
		util.getAvailableNumeroRaggruppamentoSuCarta(null, "1", Collections.emptyList(), Collections.emptyList());
	}

	@Test
	public void getAvailableNumeroRaggruppamentoSuCarta_givenNullRange_shouldReturnUnusedNumSetList() {
		// Arrange
		final PromozioneTestataEntity testataEntity = mockTestate(1, 4).get(0);
		testataEntity.getPromozionePianificazioneEntities().forEach(i -> i.setNumRaggruppamento("1"));
		// Act
		final List<Integer> numSetList = util.getAvailableNumeroRaggruppamentoSuCarta(testataEntity,
				"1",
				new ArrayList<>(Arrays.asList("1", "2")),
				null);
		// Assert
		assertNotNull(numSetList);
		assertFalse(numSetList.isEmpty());
		assertEquals(1, numSetList.size());
		assertEquals(2, (int) numSetList.get(0));
	}

	@Test
	public void getAvailableNumeroRaggruppamentoSuCarta_givenValidRange_shouldReturnUnusedNumSetList() {
		// Arrange
		final PromozioneTestataEntity testataEntity = mockTestate(1, 4).get(0);
		testataEntity.getPromozionePianificazioneEntities().forEach(i -> i.setNumRaggruppamento("1"));
		// Act
		final List<Integer> numSetList = util.getAvailableNumeroRaggruppamentoSuCarta(testataEntity,
				"1",
				Collections.emptyList(),
				new ArrayList<>(Arrays.asList(1, 2)));
		// Assert
		assertNotNull(numSetList);
		assertFalse(numSetList.isEmpty());
		assertEquals(1, numSetList.size());
		assertEquals(2, (int) numSetList.get(0));
	}

	/**
	 * Mock a bunch of PromozioneTestataEntity with childs, optionally with one null item
	 * @return list of entities
	 */
	private List<PromozioneTestataEntity> mockTestate(int sizeTestate, int sizePromozioni, boolean allowNull) {
		List<PromozioneTestataEntity> testate = new ArrayList<>();
		final CfgPianificazTipoRigaEntity tipoRigaEntity = mockPianificazioneRiga();
		for (int i = 1; i <= sizeTestate; i++) {
			final PromozioneTestataEntity testataEntity = spy(PromozioneTestataEntity.class);
			testataEntity.setId((long) i);
			testataEntity.setPromozionePianificazioneEntities(new HashSet<>());
			for (int j = 1; j <= sizePromozioni; j++) {
				final PromozionePianificazioneEntity promoPianificazioneEntity = spy(PromozionePianificazioneEntity.class);
				promoPianificazioneEntity.setId((long) j);
				promoPianificazioneEntity.setBuonoScontoProgressivo(i * 10);
				promoPianificazioneEntity.setCodiceOnline("ONLINE_" + j);
				promoPianificazioneEntity.setNumSet(String.valueOf(j));
				promoPianificazioneEntity.setTipoRiga(tipoRigaEntity);
				final MeccanicheEntity meccanicaEntity = spy(MeccanicheEntity.class);
				meccanicaEntity.setId(1L);
				promoPianificazioneEntity.setMeccanicaEntity(meccanicaEntity);
				testataEntity.addPromozionePianificazioneEntity(promoPianificazioneEntity);
			}
			testate.add(testataEntity);
		}
		if (allowNull) {
			testate.add(null);
		}
		return testate;
	}

	/**
	 * Mock a bunch of PromozioneTestataEntity with childs
	 * @return list of entities
	 */
	private List<PromozioneTestataEntity> mockTestate(int sizeTestate, int sizePromozioni) {
		return mockTestate(sizeTestate, sizePromozioni, true);
	}

	/**
	 * Mock a CfgPianificazioneEntity of a given type
	 * @param type the type to mock, can be Integer, String, Number or Data (also a not managed type)
	 * @return entity
	 */
	private CfgPianificazioneEntity mockPianificazioneWithType(String type) {
		final CfgPianificazioneEntity pianificazioneEntity = spy(CfgPianificazioneEntity.class);
		final CfgPianificazioneCampiEntity campiEntity = spy(CfgPianificazioneCampiEntity.class);
		campiEntity.setTipo(type);
		pianificazioneEntity.setMuiCfgPianificazioneCampi(campiEntity);
		return pianificazioneEntity;
	}

	private CfgPianificazTipoRigaEntity mockPianificazioneRiga() {
		final CfgPianificazTipoRigaEntity rigaEntity = spy(CfgPianificazTipoRigaEntity.class);
		rigaEntity.setCodiceTipo(PianificazioneRowTypeEnum.SET.getTypeCode());
		return rigaEntity;
	}


	@Test
	public void checkPianificazioneBoundToDateCheckCallsCanale(){
		// Arrange
		final PromozioneTestataEntity testata = mockTestate(1, 1).get(0);
		when(testata.getCanalePromozioneEntity()).thenReturn(mock(CanalePromozioneEntity.class));
		when(testata.getCanalePromozioneEntity().getFlCheckDate()).thenReturn(true);
		// Act
		util.isPianificazioneBoundToDateCheck(testata);
		// Assert
		verify(testata.getCanalePromozioneEntity(), times(1)).getFlCheckDate();
	}

	@Test
	public void whenIsPianificazioneLockedOnDataInizioAndDataInizioIsBeforeThenReturnsTrue(){
		// Arrange
		final PromozioneTestataEntity testata = mockTestate(1, 1).get(0);
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		int tolleranza = 1;
		when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
		when(testata.getCanalePromozioneEntity()).thenReturn(mock(CanalePromozioneEntity.class));
		when(testata.getCanalePromozioneEntity().getFlCheckDate()).thenReturn(true);
		when(testata.getCanalePromozioneEntity().getTolleranzaDataInizio()).thenReturn(tolleranza);
		// data inizio passata dalla tolleranza
		LocalDateTime now = LocalDateTime.now();
		when(pianificazione.getDataInizio()).thenReturn(java.sql.Date.valueOf(now.minusHours(tolleranza).toLocalDate()));
		// Act
		final boolean result = util.isPianificazioneLockedOnDataInizio(pianificazione);
		// Assert
		assertTrue(result);
	}
	@Test
	public void whenIsPianificazioneLockedOnDataInizioAndDataInizioIsAfterThenReturnsTrue(){
		// Arrange
		final PromozioneTestataEntity testata = mockTestate(1, 1).get(0);
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		int tolleranza = 1;
		when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
		when(testata.getCanalePromozioneEntity()).thenReturn(mock(CanalePromozioneEntity.class));
		when(testata.getCanalePromozioneEntity().getFlCheckDate()).thenReturn(true);
		when(testata.getCanalePromozioneEntity().getTolleranzaDataInizio()).thenReturn(tolleranza);
		// data inizio passata dalla tolleranza
		LocalDateTime now = LocalDateTime.now();
		when(pianificazione.getDataInizio()).thenReturn(java.sql.Date.valueOf(now.plusHours(24).toLocalDate()));
		// Act
		final boolean result = util.isPianificazioneLockedOnDataInizio(pianificazione);
		// Assert
		assertFalse(result);
	}

	@Test
	public void whenIsPianificazioneLockedOnDataFineAndDataFineIsBeforeThenReturnsTrue(){
		// Arrange
		final PromozioneTestataEntity testata = mockTestate(1, 1).get(0);
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		int tolleranza = 1;
		when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
		when(testata.getCanalePromozioneEntity()).thenReturn(mock(CanalePromozioneEntity.class));
		when(testata.getCanalePromozioneEntity().getFlCheckDate()).thenReturn(true);
		when(testata.getCanalePromozioneEntity().getTolleranzaDataFine()).thenReturn(tolleranza);
		// data fine passata dalla tolleranza
		when(pianificazione.getDataFine()).thenReturn(java.sql.Date.valueOf(LocalDate.now()));
		// Act
		final boolean result = util.isPianificazioneLockedOnDataFine(pianificazione);
		// Assert
		assertTrue(result);
	}

	@Test
	public void whenIsPianificazioneLockedOnDataFineAndDataFineIsAfterThenReturnsTrue(){
		// Arrange
		final PromozioneTestataEntity testata = mockTestate(1, 1).get(0);
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		int tolleranza = 1;
		when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
		when(testata.getCanalePromozioneEntity()).thenReturn(mock(CanalePromozioneEntity.class));
		when(testata.getCanalePromozioneEntity().getFlCheckDate()).thenReturn(true);
		when(testata.getCanalePromozioneEntity().getTolleranzaDataFine()).thenReturn(tolleranza);
		// data fine passata dalla tolleranza
		when(pianificazione.getDataFine()).thenReturn(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));
		// Act
		final boolean result = util.isPianificazioneLockedOnDataFine(pianificazione);
		// Assert
		assertFalse(result);
	}

	@Test
	public void checkCodiceIniziativa_whenCodiceIniziativaValidForDates_shouldKeep() {
		Date dataInizio = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 8).getTime();
		Date dataFine = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 20).getTime();
		List<MuiIniziativaEntity> iniziative = Stream.of(mockIniziativa("FOO"), mockIniziativa("BAR"))
				.collect(Collectors.toList());
		when(muiIniziativaServiceInstance.get()).thenReturn(muiIniziativaService);
		when(muiIniziativaService.findAllByDataInizioAndDataFine(dataInizio, dataFine))
				.thenReturn(iniziative);
		PromozionePianificazioneEntity pianificazione = spyPianificazione("FOO", dataInizio, dataFine);
		util.checkCodiceIniziativa(pianificazione);
		verify(muiIniziativaServiceInstance, times(1)).get();
		verify(muiIniziativaService, times(1)).findAllByDataInizioAndDataFine(dataInizio, dataFine);
		verify(pianificazione, never()).setCodiceIniziativa(null);
	}

	@Test
	public void checkCodiceIniziativa_whenCodiceIniziativaNoMoreValidForDates_shouldResetItsValue() {
		Date dataInizio = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 8).getTime();
		Date dataFine = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 20).getTime();
		List<MuiIniziativaEntity> iniziative = Stream.of(mockIniziativa("BAR"), mockIniziativa("BAZ"))
				.collect(Collectors.toList());
		when(muiIniziativaServiceInstance.get()).thenReturn(muiIniziativaService);
		when(muiIniziativaService.findAllByDataInizioAndDataFine(dataInizio, dataFine))
				.thenReturn(iniziative);
		PromozionePianificazioneEntity pianificazione = spyPianificazione("FOO", dataInizio, dataFine);
		util.checkCodiceIniziativa(pianificazione);
		verify(muiIniziativaServiceInstance, times(1)).get();
		verify(muiIniziativaService, times(1)).findAllByDataInizioAndDataFine(dataInizio, dataFine);
		verify(pianificazione, times(1)).setCodiceIniziativa(null);
	}

	private PromozionePianificazioneEntity spyPianificazione(String codiceIniziativa, Date dataInizio, Date dataFine) {
		PromozionePianificazioneEntity pianificazione = spy(PromozionePianificazioneEntity.class);
		when(pianificazione.getCodiceIniziativa()).thenReturn(codiceIniziativa);
		when(pianificazione.getDataInizio()).thenReturn(dataInizio);
		when(pianificazione.getDataFine()).thenReturn(dataFine);
		return pianificazione;
	}

	private MuiIniziativaEntity mockIniziativa(String codiceIniziativa) {
		MuiIniziativaEntity mock = mock(MuiIniziativaEntity.class);
		when(mock.getCodiceIniziativa()).thenReturn(codiceIniziativa);
		return mock;
	}
}
