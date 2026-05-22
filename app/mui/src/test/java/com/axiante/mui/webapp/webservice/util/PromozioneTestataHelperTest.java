package com.axiante.mui.webapp.webservice.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromozionePianificazioneEntityHelper;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromozioneTestataHelperTest {

	@Spy
	private PromozioneTestataEntity testata;

	@Spy
	private PromozioneNegozioEntity shop1;

	@Spy
	private PromozioneNegozioEntity shop2;

	@Spy
	private PromozionePianificazioneEntity pianificazione1;

	@Spy
	private PromozionePianificazioneEntity pianificazione2;

	@Mock
	private Instance<PromozionePianificazioneEntityHelper> entityHelperInstance;

	@Mock
	private PromozionePianificazioneEntityHelper entityHelper;

	@Mock
	private Instance<StatoPromoService> statoPromoServiceInstance;

	@Mock
	private StatoPromoService statoPromoService;

	@Mock
	private Instance<PromoService> promoServiceInstance;

	@Mock
	private PromoService promoService;

	@InjectMocks
	private PromozioneTestataHelper helper;

	private int currentYear;
	private Date newStartDate;
	private Date newEndDate;

	@Before
	public void setUp() {
		currentYear = Calendar.getInstance().get(Calendar.YEAR);
		newStartDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 5).getTime();
		newEndDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 25).getTime();
		Date startDateTestata = new GregorianCalendar(currentYear, Calendar.JANUARY, 1).getTime();
		Date endDateTestata = new GregorianCalendar(currentYear, Calendar.JANUARY, 30).getTime();
		Date startDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 1).getTime();
		Date endDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 30).getTime();
		Date startDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 10).getTime();
		Date endDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 20).getTime();
		when(entityHelperInstance.get()).thenReturn(entityHelper);
		when(shop1.getDataInizio()).thenReturn(startDate1);
		when(shop1.getDataFine()).thenReturn(endDate1);
		when(shop2.getDataInizio()).thenReturn(startDate2);
		when(shop2.getDataFine()).thenReturn(endDate2);
//		when(pianificazione1.getDataInizio()).thenReturn(startDate1);
//		when(pianificazione1.getDataFine()).thenReturn(endDate1);
//		when(pianificazione2.getDataInizio()).thenReturn(startDate2);
//		when(pianificazione2.getDataFine()).thenReturn(endDate2);
		when(testata.getCodicePromozione()).thenReturn("ABC");
		when(testata.getDataInizio()).thenReturn(startDateTestata);
		when(testata.getDataFine()).thenReturn(endDateTestata);
		Set<PromozioneNegozioEntity> shops = new HashSet<>();
		shops.add(shop1);
		shops.add(shop2);
		Set<PromozionePianificazioneEntity> pianificazioni = new HashSet<>();
		pianificazioni.add(pianificazione1);
		pianificazioni.add(pianificazione2);
		when(testata.getPromozioneNegozioEntities()).thenReturn(shops);
		when(testata.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
	}

	@Test(expected = NullPointerException.class)
	public void updateEntity_givenNullTestata_shouldThrowException() {
		helper.updateEntity(null, null, null, null, null, null, null, null);
	}

	@Test
	public void updateEntity_givenSomethingChange_shouldUpdateEntity() {
		final Date date = new GregorianCalendar(currentYear, Calendar.JANUARY, 15).getTime();
		final Date dateHour = new GregorianCalendar(currentYear, Calendar.JANUARY, 15, 15, 30).getTime();
		helper.updateEntity(testata, "NEW DESCRIPTION", null, null, null, null, null, null);
		verify(testata, times(1)).setDescrizione("NEW DESCRIPTION");
		helper.updateEntity(testata, null, "NEW NOTES", null, null, null, null, null);
		verify(testata, times(1)).setNoteMarketing("NEW NOTES");
		helper.updateEntity(testata, null, null, date, null, null, null, null);
		verify(testata, times(1)).setDataInizio(date);
		helper.updateEntity(testata, null, null, null, date, null, null, null);
		verify(testata, times(1)).setDataFine(date);
		helper.updateEntity(testata, null, null, null, null, dateHour, null, null);
		verify(testata, times(1)).setOraInizio(dateHour);
		helper.updateEntity(testata, null, null, null, null, null, dateHour, null);
		verify(testata, times(1)).setOraFine(dateHour);
		final BigDecimal valorePunto = new BigDecimal("0.23");
		helper.updateEntity(testata, null, null, null, null, null, null, valorePunto);
		verify(testata, times(1)).setValorePunto(valorePunto);
	}

	@Test
	public void updateEntity_givenChangeOnStartDate_shouldUpdateExtendedDescription() {
		final Date date = new GregorianCalendar(currentYear, Calendar.JANUARY, 15).getTime();
		when(testata.getDescrizione()).thenReturn("FOO");
		when(testata.getDescrizioneEstesa()).thenReturn("EXTENDED_DESC");
		helper.updateEntity(testata, "BAR", null, date, null, null, null, null);
		verify(testata, times(1)).setDataInizio(date);
		verify(testata, times(1)).setDescrizioneEstesa(anyString());
	}

	@Test
	public void updateEntity_givenChangeOnEndDate_shouldUpdateExtendedDescription() {
		final Date date = new GregorianCalendar(currentYear, Calendar.JANUARY, 15).getTime();
		when(testata.getDescrizione()).thenReturn("FOO");
		when(testata.getDescrizioneEstesa()).thenReturn("EXTENDED_DESC");
		helper.updateEntity(testata, "BAR", null, null, date, null, null, null);
		verify(testata, times(1)).setDataFine(date);
		verify(testata, times(1)).setDescrizioneEstesa(anyString());
	}

	@Test
	public void updateEntity_givenChangeOnDescription_shouldUpdateExtendedDescription() {
		when(testata.getDescrizione()).thenReturn("FOO");
		when(testata.getDescrizioneEstesa()).thenReturn("EXTENDED_DESC");
		helper.updateEntity(testata, "BAR", null, null, null, null, null, null);
		verify(testata, times(1)).setDescrizione("BAR");
		verify(testata, times(1)).setDescrizioneEstesa(anyString());
	}

	@Test(expected = NullPointerException.class)
	public void adjustDates_givenNullTestata_shouldThrowException() {
		helper.adjustDates(null, newStartDate, newEndDate);
	}

	@Test
	public void adjustDates_givenNullBothStartEndDate_shouldNotAdjustDates() {
		helper.adjustDates(testata, null, null);
		verify(shop1, never()).setDataInizio(any(Date.class));
		verify(shop2, never()).setDataInizio(any(Date.class));
		verify(shop1, never()).setDataFine(any(Date.class));
		verify(shop2, never()).setDataFine(any(Date.class));
		verify(pianificazione1, never()).setDataInizio(any(Date.class));
		verify(pianificazione2, never()).setDataInizio(any(Date.class));
		verify(pianificazione1, never()).setDataFine(any(Date.class));
		verify(pianificazione2, never()).setDataFine(any(Date.class));
		verify(entityHelperInstance, never()).get();
		verify(entityHelper, never()).adjustDates(any(PromozionePianificazioneEntity.class), any(Date.class),
				any(Date.class), any(Boolean.class), any(Boolean.class), any(Date.class), any(Date.class), eq(null));
	}

	@Test
	public void adjustDates_givenNullStartDate_shouldNotAdjustStartDates() {
		helper.adjustDates(testata, null, newEndDate);
		verify(shop1, never()).setDataInizio(any(Date.class));
		verify(shop2, never()).setDataInizio(any(Date.class));
		verify(shop1, times(1)).setDataFine(newEndDate);
		verify(shop2, never()).setDataFine(newEndDate);
		verify(pianificazione1, never()).setDataInizio(any(Date.class));
		verify(pianificazione2, never()).setDataInizio(any(Date.class));
		verify(pianificazione1, never()).setDataFine(newEndDate);
		verify(pianificazione2, never()).setDataFine(newEndDate);
		verify(entityHelperInstance, times(1)).get();
		//public void adjustDates(@NonNull PromozionePianificazioneEntity pianificazione, Date vecchioInizio, Date vecchiaFine, boolean aggiornaInizio, boolean aggiornaFine, Date dataInizio, Date dataFine, final List<PromozionePianificazioneEntity> updatedPromo) {
		verify(entityHelper, times(1)).adjustDates(pianificazione1, testata.getDataInizio(),
				testata.getDataFine(), false, true, null, newEndDate, null);
		verify(entityHelper, times(1)).adjustDates(pianificazione2,
				testata.getDataInizio(), testata.getDataFine(), false, true, null, newEndDate, null);
		
	}

	@Test
	public void adjustDates_givenNullEndDate_shouldNotAdjustEndDates() {
		helper.adjustDates(testata, newStartDate, null);
		verify(shop1, never()).setDataFine(any(Date.class));
		verify(shop2, never()).setDataFine(any(Date.class));
		verify(shop1, times(1)).setDataInizio(newStartDate);
		verify(shop2, never()).setDataInizio(newStartDate);
		verify(pianificazione1, never()).setDataFine(any(Date.class));
		verify(pianificazione2, never()).setDataFine(any(Date.class));
		verify(pianificazione1, never()).setDataInizio(newStartDate);
		verify(pianificazione2, never()).setDataInizio(newStartDate);
		verify(entityHelper, times(1)).adjustDates(pianificazione1, testata.getDataInizio(),
				testata.getDataFine(), true, false, newStartDate, null, null);
		verify(entityHelper, times(1)).adjustDates(pianificazione2, testata.getDataInizio(),
				testata.getDataFine(), true, false, newStartDate, null, null);
	}

	@Test
	public void adjustDates_givenBothStartEndDate_shouldAdjustBothDates() {
		helper.adjustDates(testata, newStartDate, newEndDate);
		verify(shop1, times(1)).setDataInizio(newStartDate);
		verify(shop2, never()).setDataInizio(newStartDate);
		verify(shop1, times(1)).setDataFine(newEndDate);
		verify(shop2, never()).setDataFine(newEndDate);
		verify(pianificazione1, never()).setDataInizio(newStartDate);
		verify(pianificazione2, never()).setDataInizio(newStartDate);
		verify(pianificazione1, never()).setDataFine(newEndDate);
		verify(pianificazione2, never()).setDataFine(newEndDate);
		final boolean updateStartDate = newStartDate != null && testata.getDataInizio() != null
				&& !testata.getDataInizio().equals(newStartDate);
		final boolean updateEndDate = newEndDate != null && testata.getDataFine() != null
				&& !testata.getDataFine().equals(newEndDate);
		verify(entityHelper, times(1)).adjustDates(pianificazione1, testata.getDataInizio(),
				testata.getDataFine(), updateStartDate, updateEndDate, newStartDate, newEndDate, null);
		verify(entityHelper, times(1)).adjustDates(pianificazione2, testata.getDataInizio(),
				testata.getDataFine(), updateStartDate, updateEndDate, newStartDate, newEndDate, null);
	}

	@Test(expected = NullPointerException.class)
	public void adjustShopsDates_givenNullTestata_shouldThrowException() {
		helper.adjustShopsDates(null, true, true, newStartDate, newEndDate);
	}

	@Test
	public void adjustShopsDates_givenUpdateStartDate_shouldAdjustOnlyNeededStartDates() {
		helper.adjustShopsDates(testata, true, false, newStartDate, newEndDate);
		verify(shop1, times(1)).setDataInizio(newStartDate);
		verify(shop2, never()).setDataInizio(newStartDate);
		verify(shop1, never()).setDataFine(any(Date.class));
		verify(shop2, never()).setDataFine(any(Date.class));
	}

	@Test
	public void adjustShopsDates_givenUpdateEndDate_shouldAdjustOnlyNeededEndDates() {
		helper.adjustShopsDates(testata, false, true, newStartDate, newEndDate);
		verify(shop1, times(1)).setDataFine(newEndDate);
		verify(shop2, never()).setDataFine(newEndDate);
		verify(shop1, never()).setDataInizio(any(Date.class));
		verify(shop2, never()).setDataInizio(any(Date.class));
	}

	@Test
	public void adjustShopsDates_givenUpdateBothStartEndDate_shouldAdjustOnlyNeededBothStartEndDates() {
		helper.adjustShopsDates(testata, true, true, newStartDate, newEndDate);
		verify(shop1, times(1)).setDataInizio(newStartDate);
		verify(shop2, never()).setDataInizio(newStartDate);
		verify(shop1, times(1)).setDataFine(newEndDate);
		verify(shop2, never()).setDataFine(newEndDate);
	}

	@Test(expected = NullPointerException.class)
	public void adjustPlannigDates_givenNullTestata_shouldThrowException() {
		helper.adjustPlannigDates(null, true, true, newStartDate, newEndDate);
	}

	@Test
	public void adjustPlannigDates_givenUpdateStartDate_shouldAdjustOnlyNeededStartDates() {
		helper.adjustPlannigDates(testata, true, false, newStartDate, newEndDate);
		verify(pianificazione1, never()).setDataInizio(newStartDate);
		verify(pianificazione2, never()).setDataInizio(newStartDate);
		verify(pianificazione1, never()).setDataFine(any(Date.class));
		verify(pianificazione2, never()).setDataFine(any(Date.class));
		verify(entityHelperInstance, times(1)).get();
		verify(entityHelper, times(1)).adjustDates(pianificazione1, testata.getDataInizio(),
				testata.getDataFine(), true, false, newStartDate, newEndDate, null);
		verify(entityHelper, times(1)).adjustDates(pianificazione2, testata.getDataInizio(),
				testata.getDataFine(), true, false, newStartDate, newEndDate, null);
	}

	@Test
	public void adjustPlannigDates_givenUpdateEndDate_shouldAdjustOnlyNeededEndDates() {
		helper.adjustPlannigDates(testata, false, true, newStartDate, newEndDate);
		verify(pianificazione1, never()).setDataFine(newEndDate);
		verify(pianificazione2, never()).setDataFine(newEndDate);
		verify(pianificazione1, never()).setDataInizio(any(Date.class));
		verify(pianificazione2, never()).setDataInizio(any(Date.class));
		final boolean updateEndDate = newEndDate != null && testata.getDataFine() != null
				&& !testata.getDataFine().equals(newEndDate);
		//public void adjustDates(@NonNull PromozionePianificazioneEntity pianificazione, Date vecchioInizio, Date vecchiaFine, boolean aggiornaInizio, boolean aggiornaFine, Date dataInizio, Date dataFine, final List<PromozionePianificazioneEntity> updatedPromo) {
		verify(entityHelper, times(1)).adjustDates(pianificazione1, testata.getDataInizio(),
				testata.getDataFine(), false, updateEndDate, newStartDate, newEndDate, null);
		verify(entityHelper, times(1)).adjustDates(pianificazione2, testata.getDataInizio(),
				testata.getDataFine(), false, updateEndDate, newStartDate, newEndDate, null);
	}

	@Test
	public void adjustPlannigDates_givenUpdateBothStartEndDate_shouldAdjustOnlyNeededBothStartEndDates() {
		helper.adjustPlannigDates(testata, true, true, newStartDate, newEndDate);
		verify(pianificazione1, never()).setDataInizio(newStartDate);
		verify(pianificazione2, never()).setDataInizio(newStartDate);
		verify(pianificazione1, never()).setDataFine(newEndDate);
		verify(pianificazione2, never()).setDataFine(newEndDate);
		final boolean updateStartDate = newStartDate != null && testata.getDataInizio() != null
				&& !testata.getDataInizio().equals(newStartDate);
		final boolean updateEndDate = newEndDate != null && testata.getDataFine() != null
				&& !testata.getDataFine().equals(newEndDate);
		//public void adjustDates(@NonNull PromozionePianificazioneEntity pianificazione, Date vecchioInizio, Date vecchiaFine, boolean aggiornaInizio, boolean aggiornaFine, Date dataInizio, Date dataFine, final List<PromozionePianificazioneEntity> updatedPromo) {
		verify(entityHelper, times(1)).adjustDates(pianificazione1, testata.getDataInizio(),
				testata.getDataFine(), updateStartDate, updateEndDate, newStartDate, newEndDate, null);
		verify(entityHelper, times(1)).adjustDates(pianificazione2, testata.getDataInizio(),
				testata.getDataFine(), updateStartDate, updateEndDate, newStartDate, newEndDate, null);
	}

	@Test(expected = NullPointerException.class)
	public void delete_givenNullTestata_shouldThrowException() {
		helper.delete(null, "junit");
	}

	@Test(expected = NullPointerException.class)
	public void delete_givenNullUsername_shouldThrowException() {
		helper.delete(testata, null);
	}

	@Test
	public void delete_shouldReturnFalse_whenTestataHasNullLastStatus() throws Exception {
		when(testata.getPromozioneStatoEntities()).thenReturn(Collections.emptySet());
		assertFalse(helper.delete(testata, "junit"));
		verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
	}

	@Test
	public void delete_shouldReturnFalse_whenTestataIsNotDeletable() throws Exception {
		final PromozioneStatoEntity promoStato = mock(PromozioneStatoEntity.class);
		final StatoPromozioneEntity statoPromo = mock(StatoPromozioneEntity.class);
		when(promoStato.getStatoPromozioneEntity()).thenReturn(statoPromo);
		when(statoPromo.getCodiceStato()).thenReturn(PromoStatusEnum.PIANIFICAZIONE_FINALIZZATA.getKey());
		when(testata.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
		assertFalse(helper.delete(testata, "junit"));
		verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
	}

	@Test
	public void delete_shouldReturnFalse_whenSomethingWentWrong() throws Exception {
		final PromozioneStatoEntity promoStato = mock(PromozioneStatoEntity.class);
		final StatoPromozioneEntity statoPromo = mock(StatoPromozioneEntity.class);
		when(promoStato.getStatoPromozioneEntity()).thenReturn(statoPromo);
		when(statoPromo.getCodiceStato()).thenReturn(PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey());
		when(testata.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
		when(statoPromoServiceInstance.get()).thenReturn(statoPromoService);
		when(statoPromoService.findByCode(PromoStatusEnum.CANCELLATA_00.getKey())).thenReturn(null);
		assertFalse(helper.delete(testata, "junit"));
		verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
	}

	@Test
	public void delete_shouldReturnTrue_whenEverythingIsOk() throws Exception {
		final PromozioneStatoEntity promoStato = mock(PromozioneStatoEntity.class);
		final StatoPromozioneEntity statoPromo = mock(StatoPromozioneEntity.class);
		final StatoPromozioneEntity deletedStatus = mock(StatoPromozioneEntity.class);
		final Set<PromozioneStatoEntity> statiPromo = new HashSet<>();
		statiPromo.add(promoStato);
		when(promoStato.getStatoPromozioneEntity()).thenReturn(statoPromo);
		when(statoPromo.getCodiceStato()).thenReturn(PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey());
		when(testata.getPromozioneStatoEntities()).thenReturn(statiPromo);
		when(statoPromoServiceInstance.get()).thenReturn(statoPromoService);
		when(statoPromoService.findByCode(PromoStatusEnum.CANCELLATA_00.getKey())).thenReturn(deletedStatus);
		when(promoServiceInstance.get()).thenReturn(promoService);
		assertTrue(helper.delete(testata, "junit"));
		verify(promoService, times(1)).persist(testata, "junit");
	}
}