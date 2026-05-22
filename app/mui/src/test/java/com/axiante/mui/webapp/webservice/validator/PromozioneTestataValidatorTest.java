package com.axiante.mui.webapp.webservice.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromozioneTestataValidatorTest {

    @Spy
    private PromozioneTestataEntity testata;

    @Mock
    private PromozioneNegozioEntity shop1;

    @Mock
    private PromozioneNegozioEntity shop2;

    @Mock
    private PromozionePianificazioneEntity pianificazione1;

    @Mock
    private PromozionePianificazioneEntity pianificazione2;

    @InjectMocks
    private PromozioneTestataValidator validator;

    private int currentYear;
    private Date newStartDate;
    private Date newEndDate;

    @Before
    public void setUp() {
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
        newStartDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 5).getTime();
        newEndDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 25).getTime();
        Date startDateTestata = new GregorianCalendar(currentYear, Calendar.JANUARY, 10).getTime();
        Date endDateTestata = new GregorianCalendar(currentYear, Calendar.JANUARY, 20).getTime();
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
    public void validateDates_givenNullTestata_shouldThrowException() {
        validator.validateDates(null, newStartDate, newEndDate);
    }

    @Test
    public void validateDates_givenShopStartDateBeforeTestata_shouldReturnErrorMessage() {
        Date startDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 1).getTime();
        Date startDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 7).getTime();
        when(shop1.getDataInizio()).thenReturn(startDate1);
        when(shop2.getDataInizio()).thenReturn(startDate2);
        newStartDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 12).getTime();
        final String message = validator.validateDates(testata, newStartDate, null);
        assertNotNull(message);
        assertEquals("Data inizio non coerente con i negozi", message);
    }

    @Test
    public void validateDates_givenShopEndDateAfterTestata_shouldReturnErrorMessage() {
        Date endDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 27).getTime();
        Date endDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 23).getTime();
        when(shop1.getDataFine()).thenReturn(endDate1);
        when(shop2.getDataFine()).thenReturn(endDate2);
        newEndDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 19).getTime();
        final String message = validator.validateDates(testata, null, newEndDate);
        assertNotNull(message);
        assertEquals("Data fine non coerente con i negozi", message);
    }

    @Test
    public void validateDates_givenShopStartDateBeforeAndEndDateAfterTestata_shouldReturnErrorMessage() {
        Date startDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 1).getTime();
        Date startDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 7).getTime();
        Date endDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 27).getTime();
        Date endDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 23).getTime();
        when(shop1.getDataInizio()).thenReturn(startDate1);
        when(shop2.getDataInizio()).thenReturn(startDate2);
        when(shop1.getDataFine()).thenReturn(endDate1);
        when(shop2.getDataFine()).thenReturn(endDate2);
        newStartDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 12).getTime();
        newEndDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 25).getTime();
        final String message = validator.validateDates(testata, newStartDate, newEndDate);
        assertNotNull(message);
        assertEquals("Data inizio e fine non coerente con i negozi", message);
    }

    @Test
    public void validateDates_givenPlanningStartDateBeforeTestata_shouldReturnErrorMessage() {
        Date startDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 10).getTime();
        Date endDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 23).getTime();
        Date startDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 1).getTime();
        Date startDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 7).getTime();
        when(shop1.getDataInizio()).thenReturn(startDate);
        when(shop2.getDataInizio()).thenReturn(startDate);
        when(pianificazione1.getDataInizio()).thenReturn(startDate1);
        when(pianificazione2.getDataInizio()).thenReturn(startDate2);
        newStartDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 12).getTime();
        final String message = validator.validateDates(testata, newStartDate, null);
        assertNotNull(message);
        assertEquals("Data inizio non coerente con la pianificazione", message);
    }

    @Test
    public void validateDates_givenPlanningEndDateAfterTestata_shouldReturnErrorMessage() {
        Date startDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 7).getTime();
        Date endDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 20).getTime();
        Date endDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 27).getTime();
        Date endDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 23).getTime();
        when(shop1.getDataFine()).thenReturn(endDate);
        when(shop2.getDataFine()).thenReturn(endDate);
        when(pianificazione1.getDataFine()).thenReturn(endDate1);
        when(pianificazione2.getDataFine()).thenReturn(endDate2);
        newEndDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 19).getTime();
        final String message = validator.validateDates(testata, null, newEndDate);
        assertNotNull(message);
        assertEquals("Data fine non coerente con la pianificazione", message);
    }

    @Test
    public void validateDates_givenPlanningStartDateBeforeAndEndDateAfterTestata_shouldReturnErrorMessage() {
        Date startDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 10).getTime();
        Date endDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 20).getTime();
        Date startDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 1).getTime();
        Date startDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 7).getTime();
        Date endDate1 = new GregorianCalendar(currentYear, Calendar.JANUARY, 27).getTime();
        Date endDate2 = new GregorianCalendar(currentYear, Calendar.JANUARY, 23).getTime();
        when(shop1.getDataInizio()).thenReturn(startDate);
        when(shop2.getDataInizio()).thenReturn(startDate);
        when(shop1.getDataFine()).thenReturn(endDate);
        when(shop2.getDataFine()).thenReturn(endDate);
        when(pianificazione1.getDataInizio()).thenReturn(startDate1);
        when(pianificazione2.getDataInizio()).thenReturn(startDate2);
        when(pianificazione1.getDataFine()).thenReturn(endDate1);
        when(pianificazione2.getDataFine()).thenReturn(endDate2);
        newStartDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 12).getTime();
        newEndDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 25).getTime();
        final String message = validator.validateDates(testata, newStartDate, newEndDate);
        assertNotNull(message);
        assertEquals("Data inizio e fine non coerente con la pianificazione", message);
    }

    @Test
    public void validateDates_givenCompatibleDates_shouldReturnNull() {
        Date startDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 7).getTime();
        Date endDate = new GregorianCalendar(currentYear, Calendar.JANUARY, 23).getTime();
        when(shop1.getDataInizio()).thenReturn(startDate);
        when(shop2.getDataInizio()).thenReturn(startDate);
        when(shop1.getDataFine()).thenReturn(endDate);
        when(shop2.getDataFine()).thenReturn(endDate);
        final String message = validator.validateDates(testata, newStartDate, newEndDate);
        assertNull(message);
    }
}