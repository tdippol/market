package com.axiante.mui.webapp.webservice.util;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasNoJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.webapp.business.OwnershipService;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ViewPromoUtilTest {

	@Mock
	private Instance<OwnershipService> ownershipServiceInstance;

	@Mock
	private OwnershipService ownershipService;

	@InjectMocks
	private ViewPromoUtil viewPromoUtil;

	@Before
	public void setUp() {
		when(ownershipServiceInstance.get()).thenReturn(ownershipService);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void createRowData_givenOwnership_shouldSetOwnerFlagOnTestateWithUserGroupInOwnershipList() {
		final DateTimeUtils dateTimeUtils = new DateTimeUtils();
		final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		// Testata numero 1
		final Date startDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 1).getTime();
		final String startDate1Excel = dateTimeUtils.toExcelDate(startDate1);
		final Date endDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 5).getTime();
		final String endDate1Excel = dateTimeUtils.toExcelDate(endDate1);
		final String stato1 = String.format("%s - %s",PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey(),
				PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getDescription());
		final CanalePromozioneEntity canale1 = mockCanalePromo("CANALE 1");
		final StatoPromozioneEntity statoPromo1 = mockStatoPromo(PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey(),
				PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getDescription());
		final PromozioneStatoEntity promoStato1 = mockPromoStato(statoPromo1);
		final PromozioneTestataEntity testata1 = mockTestata(1L, String.valueOf(currentYear), "PROMO-1",
				"TESTATA 1", startDate1, endDate1, canale1, promoStato1);
		// Testata numero 2
		final Date startDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 10).getTime();
		final String startDate2Excel = dateTimeUtils.toExcelDate(startDate2);
		final Date endDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 15).getTime();
		final String endDate2Excel = dateTimeUtils.toExcelDate(endDate2);
		final String stato2 = String.format("%s - %s",PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey(),
				PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getDescription());
		final CanalePromozioneEntity canale2 = mockCanalePromo("CANALE 2");
		final StatoPromozioneEntity statoPromo2 = mockStatoPromo(PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey(),
				PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getDescription());
		final PromozioneStatoEntity promoStato2 = mockPromoStato(statoPromo2);
		final PromozioneTestataEntity testata2 = mockTestata(2L, String.valueOf(currentYear), "PROMO-2",
				"TESTATA 2", startDate2, endDate2, canale2, promoStato2);
		// Testata numero 3 - creata da ADMIN
		final PromozioneTestataEntity testata3 = mockTestata(3L, String.valueOf(currentYear), "PROMO-3",
				"TESTATA 3", startDate2, endDate2, canale2, promoStato2);
		// Arrange
		final List<String> groups = Arrays.asList("FOO", "BAR");
		when(ownershipService.hasOwnership(testata1, groups)).thenReturn(true);
		when(ownershipService.hasOwnership(testata2, groups)).thenReturn(false);
		when(ownershipService.hasOwnership(testata3, groups)).thenReturn(false);
		// Act and assert
		final String rowData = viewPromoUtil.createRowData(Arrays.asList(testata1, testata2, testata3), groups, false);
		assertThat(rowData, isJson(allOf(
				// rowData ha 3 elementi
				withJsonPath("$.rowData", hasSize(3)),
				// assertions sul primo elemento - testata1
				withJsonPath("$.rowData[0].canale.value", equalTo("CANALE 1")),
				withJsonPath("$.rowData[0].canale.type", equalTo("string")),
				withJsonPath("$.rowData[0].canale.editable", equalTo(false)),
				withJsonPath("$.rowData[0].dataInizio.value", equalTo(startDate1Excel)),
				withJsonPath("$.rowData[0].dataInizio.type", equalTo("date")),
				withJsonPath("$.rowData[0].dataInizio.editable", equalTo(false)),
				withJsonPath("$.rowData[0].dataFine.value", equalTo(endDate1Excel)),
				withJsonPath("$.rowData[0].dataFine.type", equalTo("date")),
				withJsonPath("$.rowData[0].dataFine.editable", equalTo(false)),
				hasNoJsonPath("$.rowData[0].noteMarketing.value"),
				withJsonPath("$.rowData[0].noteMarketing.type", equalTo("string")),
				withJsonPath("$.rowData[0].noteMarketing.editable", equalTo(false)),
				withJsonPath("$.rowData[0].codicePromozione.value", equalTo("PROMO-1")),
				withJsonPath("$.rowData[0].codicePromozione.type", equalTo("string")),
				withJsonPath("$.rowData[0].codicePromozione.editable", equalTo(false)),
				hasNoJsonPath("$.rowData[0].oraInizio.value"),
				withJsonPath("$.rowData[0].oraInizio.type", equalTo("time")),
				withJsonPath("$.rowData[0].oraInizio.editable", equalTo(false)),
				hasNoJsonPath("$.rowData[0].oraFine.value"),
				withJsonPath("$.rowData[0].oraFine.type", equalTo("time")),
				withJsonPath("$.rowData[0].oraFine.editable", equalTo(false)),
				withJsonPath("$.rowData[0].descrizioneEstesa.value", equalTo("TESTATA 1")),
				withJsonPath("$.rowData[0].descrizioneEstesa.type", equalTo("string")),
				withJsonPath("$.rowData[0].descrizioneEstesa.editable", equalTo(false)),
				withJsonPath("$.rowData[0].stato.value", equalTo(stato1)),
				withJsonPath("$.rowData[0].stato.type", equalTo("string")),
				withJsonPath("$.rowData[0].stato.editable", equalTo(false)),
				withJsonPath("$.rowData[0].anno.value", equalTo(String.valueOf(currentYear))),
				withJsonPath("$.rowData[0].anno.type", equalTo("string")),
				withJsonPath("$.rowData[0].anno.editable", equalTo(false)),
				withJsonPath("$.rowData[0].id.value", equalTo("1")),
				withJsonPath("$.rowData[0].id.type", equalTo("string")),
				withJsonPath("$.rowData[0].id.editable", equalTo(false)),
				withJsonPath("$.rowData[0].owner.value", equalTo(String.valueOf(true))),
				withJsonPath("$.rowData[0].owner.type", equalTo("checkbox")),
				withJsonPath("$.rowData[0].owner.editable", equalTo(false)),
				// assertions sul secondo elemento - testata2
				withJsonPath("$.rowData[1].canale.value", equalTo("CANALE 2")),
				withJsonPath("$.rowData[1].canale.type", equalTo("string")),
				withJsonPath("$.rowData[1].canale.editable", equalTo(false)),
				withJsonPath("$.rowData[1].dataInizio.value", equalTo(startDate2Excel)),
				withJsonPath("$.rowData[1].dataInizio.type", equalTo("date")),
				withJsonPath("$.rowData[1].dataInizio.editable", equalTo(false)),
				withJsonPath("$.rowData[1].dataFine.value", equalTo(endDate2Excel)),
				withJsonPath("$.rowData[1].dataFine.type", equalTo("date")),
				withJsonPath("$.rowData[1].dataFine.editable", equalTo(false)),
				hasNoJsonPath("$.rowData[1].noteMarketing.value"),
				withJsonPath("$.rowData[1].noteMarketing.type", equalTo("string")),
				withJsonPath("$.rowData[1].noteMarketing.editable", equalTo(false)),
				withJsonPath("$.rowData[1].codicePromozione.value", equalTo("PROMO-2")),
				withJsonPath("$.rowData[1].codicePromozione.type", equalTo("string")),
				withJsonPath("$.rowData[1].codicePromozione.editable", equalTo(false)),
				hasNoJsonPath("$.rowData[1].oraInizio.value"),
				withJsonPath("$.rowData[1].oraInizio.type", equalTo("time")),
				withJsonPath("$.rowData[1].oraInizio.editable", equalTo(false)),
				hasNoJsonPath("$.rowData[1].oraFine.value"),
				withJsonPath("$.rowData[1].oraFine.type", equalTo("time")),
				withJsonPath("$.rowData[1].oraFine.editable", equalTo(false)),
				withJsonPath("$.rowData[1].descrizioneEstesa.value", equalTo("TESTATA 2")),
				withJsonPath("$.rowData[1].descrizioneEstesa.type", equalTo("string")),
				withJsonPath("$.rowData[1].descrizioneEstesa.editable", equalTo(false)),
				withJsonPath("$.rowData[1].stato.value", equalTo(stato2)),
				withJsonPath("$.rowData[1].stato.type", equalTo("string")),
				withJsonPath("$.rowData[1].stato.editable", equalTo(false)),
				withJsonPath("$.rowData[1].anno.value", equalTo(String.valueOf(currentYear))),
				withJsonPath("$.rowData[1].anno.type", equalTo("string")),
				withJsonPath("$.rowData[1].anno.editable", equalTo(false)),
				withJsonPath("$.rowData[1].id.value", equalTo("2")),
				withJsonPath("$.rowData[1].id.type", equalTo("string")),
				withJsonPath("$.rowData[1].id.editable", equalTo(false)),
				withJsonPath("$.rowData[1].owner.value", equalTo(String.valueOf(false))),
				withJsonPath("$.rowData[1].owner.type", equalTo("checkbox")),
				withJsonPath("$.rowData[1].owner.editable", equalTo(false)),
				// assertions sul terzo elemento - testata3
				withJsonPath("$.rowData[2].owner.value", equalTo(String.valueOf(false))),
				withJsonPath("$.rowData[2].owner.type", equalTo("checkbox")),
				withJsonPath("$.rowData[2].owner.editable", equalTo(false))
				)));
	}

	private PromozioneTestataEntity mockTestata(Long id, String year, String promoCode, String description,
			Date startDate, Date endDate, CanalePromozioneEntity canale, PromozioneStatoEntity promoStato) {
		final PromozioneTestataEntity e = mock(PromozioneTestataEntity.class);
		when(e.getId()).thenReturn(id);
		when(e.getAnno()).thenReturn(year);
		when(e.getCodicePromozione()).thenReturn(promoCode);
		when(e.getDescrizioneEstesa()).thenReturn(description);
		when(e.getDataInizio()).thenReturn(startDate);
		when(e.getDataFine()).thenReturn(endDate);
		when(e.getMuiCanalePromozione()).thenReturn(canale);
		when(e.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
		return e;
	}

	private CanalePromozioneEntity mockCanalePromo(String description) {
		final CanalePromozioneEntity e = mock(CanalePromozioneEntity.class);
		when(e.getDescrizione()).thenReturn(description);
		return e;
	}

	private PromozioneStatoEntity mockPromoStato(StatoPromozioneEntity statoPromo) {
		final PromozioneStatoEntity e = mock(PromozioneStatoEntity.class);
		when(e.getStatoPromozioneEntity()).thenReturn(statoPromo);
		return e;
	}

	private StatoPromozioneEntity mockStatoPromo(String code, String label) {
		final StatoPromozioneEntity e = mock(StatoPromozioneEntity.class);
		when(e.getCodiceStato()).thenReturn(code);
		when(e.getLabel()).thenReturn(label);
		return e;
	}
}
