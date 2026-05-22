package com.axiante.mui.dbpromo.business.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromoHelperServiceTest {

	@Spy
	@InjectMocks
	private PromoHelperServiceImpl service;

//	@Test
//	public void shouldReturnFirstSemestre() {
//		String semestre = service.calculateSemestre(LocalDate.of(2020, 4, 4), "2020");
//		Assert.assertEquals(semestre, Constants.I_SEMESTRE);
//
//		semestre = service.calculateSemestre(LocalDate.of(2020, 6, 30), "2020");
//		Assert.assertEquals(semestre, Constants.I_SEMESTRE);
//
//		semestre = service.calculateSemestre(LocalDate.of(2020, 1, 4), "2020");
//		Assert.assertEquals(semestre, Constants.I_SEMESTRE);
//	}
//
//	@Test
//	public void shouldReturnSecondSemestre() {
//		String semestre = service.calculateSemestre(LocalDate.of(2020, 9, 4), "2020");
//		Assert.assertEquals(semestre, Constants.II_SEMESTRE);
//
//		semestre = service.calculateSemestre(LocalDate.of(2020, 7, 1), "2020");
//		Assert.assertEquals(semestre, Constants.II_SEMESTRE);
//
//		semestre = service.calculateSemestre(LocalDate.of(2020, 12, 30), "2020");
//		Assert.assertEquals(semestre, Constants.II_SEMESTRE);
//	}

//	@Test
//	public void shouldReturnTrueWith10StatusPromotion() {
//		PromozioneTestataEntity promo = new PromozioneTestataEntity();
//
//		final Set<PromozioneStatoEntity> status = new HashSet<>();
//		PromozioneStatoEntity promoStatus = new PromozioneStatoEntity();
//		promoStatus.setDataInizioStato(new Date());
//
//		StatoPromozioneEntity st = new StatoPromozioneEntity();
//		st.setDescrizione("Testata Promozione Creata");
//		st.setCodiceStato("10");
//		promoStatus.setStatoPromozioneEntity(st);
//
//		status.add(promoStatus);
//		promo.setPromozioneStatoEntities(status);
//		assertThat(service.promoCheckStatus10(promo), CoreMatchers.equalTo(Boolean.TRUE));
//	}

//	@Test
//	public void shouldReturnFalseWithout10StatusPromotion() {
//		PromozioneTestataEntity promo = new PromozioneTestataEntity();
//		final Set<PromozioneStatoEntity> status = new HashSet<>();
//		PromozioneStatoEntity promoStatus = new PromozioneStatoEntity();
//		promoStatus.setDataInizioStato(new Date());
//
//		StatoPromozioneEntity st = new StatoPromozioneEntity();
//		st.setDescrizione("Pianificazione Disponibile");
//		st.setCodiceStato("30");
//		promoStatus.setStatoPromozioneEntity(st);
//
//		status.add(promoStatus);
//		promo.setPromozioneStatoEntities(status);
//		final boolean statusResult = service.promoCheckStatus10(promo);
//		Assert.assertSame(statusResult, false);
//	}
//
//	@Test
//	public void shouldReturnFalseWithoutAnyStatusPromotion() {
//		final boolean statusResult = service.promoCheckStatus10(new PromozioneTestataEntity());
//		Assert.assertSame(statusResult, false);
//	}

//	@Test
//	public void shouldConvertExcelDateIntoDateAndViceversa() {
//		LocalDate now = LocalDate.now();
//		Date dt = java.util.Date.from(now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//		String excelDate = service.convertToExcelDateFormat(dt);
//		Date date = service.convertToDateFormat(excelDate);
//		Assert.assertTrue(date.equals(dt));
//	}

//	@Test
//	public void verifyConversionWithApachePOI() {
//		LocalDate now = LocalDate.now();
//		Date current = Date.from(now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//		String excelString = service.convertToExcelDateFormat(current);
//		Double poiDate = Math.floor(DateUtil.getExcelDate(current));
//
//		assertEquals(excelString, "" + poiDate.intValue());
//	}

//	@Test
//	public void whenCalculateSemestreWithNullValuesThenReturnNull() {
//		assertNull(service.calculateSemestre(null, null));
//	}
//
//	@Test
//	public void whenCalculateSemestreWithStartDateBeforeCurrentYearThenReturnI_SEMESTRE() {
//		final LocalDate testDate = LocalDate.now().minusYears(1);
//		final String year = "" + LocalDate.now().getYear();
//		final String result = service.calculateSemestre(testDate, year);
//		assertThat(result, CoreMatchers.equalTo("I SEMESTRE"));
//	}
//
//	@Test
//	public void whenCalculateSemestreWithStartDateAfterCurrentYearThenReturnI_SEMESTRE() {
//		final LocalDate testDate = LocalDate.now().plusYears(1);
//		final String year = "" + LocalDate.now().getYear();
//		final String result = service.calculateSemestre(testDate, year);
//		assertThat(result, CoreMatchers.equalTo("II SEMESTRE"));
//	}
//
//	@Test
//	public void whenPromoCheckStatus10WithNullParamThenReturnsnull() {
//		assertFalse(service.promoCheckStatus10(null));
//	}
//
//	@Test
//	public void whenRetrievePromoEditFieldsWithNullThenReturnNull() {
//		assertNull(service.retrievePromoEditFields(null));
//	}
//
//	@Test
//	public void RetrievePromoEditFieldsWithIncorrectStatusThenReturnNull() {
//		assertNull(service.retrievePromoEditFields(Integer.MAX_VALUE));
//		assertNull(service.retrievePromoEditFields(Integer.MIN_VALUE));
//	}
//
//	@Test
//	public void RetrievePromoEditFieldsWithCorrectStatusThenReturnEnum() {
//		String[] validValues = { "00", "10", "30", "31", "311", "400", "410", "500" };
//		for (String value : validValues)
//			assertNotNull(service.retrievePromoEditFields(new Integer(value)));
//	}

	@Test
	public void whenPrepareToDeleteWithNullPromoThenReturnNull() {
		assertNull(service.prepareToDelete(null));
	}

	@Test
	public void whenPrepareToDeleteWithNullStatusPromoThenReturnNull() {
		PromozioneTestataEntity test = mock(PromozioneTestataEntity.class);
		when(test.getPromozioneStatoEntities()).thenReturn(null);
		assertNull(service.prepareToDelete(test));
	}

	@Test
	public void whenPrepareToDeleteWithEmptyStatusPromoThenReturnNull() {
		PromozioneTestataEntity test = mock(PromozioneTestataEntity.class);
		when(test.getPromozioneStatoEntities()).thenReturn(Collections.emptySet());
		assertNull(service.prepareToDelete(test));
	}

	@Test
	public void whenPrepareToDeleteWithAllFinishedStatusPromoThenReturnNull() {
		PromozioneTestataEntity test = mock(PromozioneTestataEntity.class);
		PromozioneStatoEntity stato = mock(PromozioneStatoEntity.class);
		when(stato.getDataFineStato()).thenReturn(new Date(System.currentTimeMillis()));
		when(test.getPromozioneStatoEntities()).thenReturn(new HashSet<>(Arrays.asList(stato)));
		assertNull(service.prepareToDelete(test));
	}

	@Test
	public void whenPrepareToDeleteWithWrongStatusPromoThenReturnNull() {
		PromozioneTestataEntity test = mock(PromozioneTestataEntity.class);
		PromozioneStatoEntity stato = mock(PromozioneStatoEntity.class);
		StatoPromozioneEntity _stato = mock(StatoPromozioneEntity.class);

		when(stato.getDataFineStato()).thenReturn(null);
		when(stato.getStatoPromozioneEntity()).thenReturn(_stato);
		when(_stato.getCodiceStato()).thenReturn(PromoStatusEnum.PROMOZIONIE_CONCLUSA.getKey());
		when(test.getPromozioneStatoEntities()).thenReturn(new HashSet<>(Arrays.asList(stato)));
		assertNull(service.prepareToDelete(test));
	}

	@Test
	public void whenPrepareToDeleteWithCorrectStatusPromoThenReturnData() {
		PromozioneTestataEntity test = mock(PromozioneTestataEntity.class);
		PromozioneStatoEntity stato = mock(PromozioneStatoEntity.class);
		StatoPromozioneEntity _stato = mock(StatoPromozioneEntity.class);

		when(stato.getDataFineStato()).thenReturn(null);
		when(stato.getStatoPromozioneEntity()).thenReturn(_stato);
		when(_stato.getCodiceStato()).thenReturn(PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey());
		when(test.getPromozioneStatoEntities()).thenReturn(new HashSet<>(Arrays.asList(stato)));
		assertNotNull(service.prepareToDelete(test));
	}

//	@Test
//	public void whenConvertToExcelDateFormatWithNullDateThenReturnNull() {
//		assertNull(service.convertToExcelDateFormat(null));
//	}

//	@Test
//	public void whenConvertStringDateFormatToWithNullValueThenReturnNull() {
//		assertNull(service.convertStringDateFormatTo(null));
//	}
//
//	@Test
//	public void whenConvertStringDateFormatToWithEmptyValueThenReturnNull() {
//		assertNull(service.convertStringDateFormatTo(""));
//	}
//
//	@Test
//	public void whenConvertStringDateFormatToWithWrongDateThenReturnNull() {
//		assertNull(service.convertStringDateFormatTo("1/1"));
//	}
//
//	@Test
//	public void whenConvertStringDateFormatToWithWrongSeparatorThenReturnNull() {
//		assertNull(service.convertStringDateFormatTo("1-1-2000"));
//	}

//	@Test
//	public void whenConvertStringDateFormatToWithWrongDateThenReturnData() {
//		assertNotNull(service.convertStringDateFormatTo("1/1/2000"));
//	}
//
//	@Test
//	public void whenConvertToDateFormatWithNullValueThenReturnNull() {
//		assertNull(service.convertToDateFormat(null));
//	}
//
//	@Test
//	public void whenConvertStringDateFormatToDateWithNullValueThenReturnNull() {
//		assertNull(service.convertStringDateFormatToDate(null, mock(SimpleDateFormat.class)));
//	}
//
//	@Test
//	public void whenConvertStringDateFormatToDateWithEmptyValueThenReturnNull() {
//		assertNull(service.convertStringDateFormatToDate("", mock(SimpleDateFormat.class)));
//	}
//
//	@Test
//	public void whenConvertStringDateFormatToDateWithNullFormatThenReturnNull() {
//		assertNull(service.convertStringDateFormatToDate("something", null));
//	}
//
//	@Test
//	public void whenConvertStringDateFormatToDateWithWrongValueThenReturnNull() {
//		assertNull(service.convertStringDateFormatToDate("something", new SimpleDateFormat()));
//	}
//
//	@Test
//	public void whenConvertStringDateFormatToDateWithCorrectValueThenReturnData() {
//		assertNotNull(service.convertStringDateFormatToDate("01-01-2000", new SimpleDateFormat("dd-MM-yyyy")));
//	}
}
