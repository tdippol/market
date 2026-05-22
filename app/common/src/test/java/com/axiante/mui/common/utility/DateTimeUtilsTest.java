package com.axiante.mui.common.utility;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DateTimeUtilsTest {
	// Rappresenta la data 10/02/2021 in formato stringa Excel
	private static final String EXCEL_DATE_AS_STRING = "44237.0";
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

	DateTimeUtils dateTimeUtils = new DateTimeUtils();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void testTodayAt() {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
		Date ora = new Date(System.currentTimeMillis());
		String time = String.format("%02d:%02d", now.getHour(), now.getMinute());
		LocalDateTime result = null;
		try {
			result = dateTimeUtils.todayAt(time);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(result);
		assertThat(result.getHour(), CoreMatchers.equalTo(now.getHour()));
		assertThat(result.getMinute(), CoreMatchers.equalTo(now.getMinute()));
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
		time = fmt.format(ora);
		try {
			result = dateTimeUtils.todayAt(time);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(result);
		assertThat(result.getHour(), CoreMatchers.equalTo(now.getHour()));
		assertThat(result.getMinute(), CoreMatchers.equalTo(now.getMinute()));
	}

	@Test(expected = NullPointerException.class)
	public void todayAt_givenNullTime_shouldThrowException() throws Exception {
		dateTimeUtils.todayAt(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void dateAt_givenInvalidTime_shouldThrowException() throws Exception {
		dateTimeUtils.dateAt(LocalDateTime.now(), "10:10:10");
	}

	@Test(expected = NullPointerException.class)
	public void dateAt_givenNullDate_shouldThrowException() throws Exception {
		dateTimeUtils.dateAt(null, "10:10");
	}

	@Test(expected = NullPointerException.class)
	public void dateAt_givenNullTime_shouldThrowException() throws Exception {
		dateTimeUtils.dateAt(LocalDateTime.now(), null);
	}

	@Test
	public void calculateSemester_givenDateBeforeJulySameYear_shouldReturnFirstSemester() {
		final Date date = new GregorianCalendar(2021, Calendar.JANUARY, 10).getTime();
		String semester = dateTimeUtils.calculateSemester("2021", date);
		assertEquals(DateTimeUtils.I_SEMESTRE, semester);
	}

	@Test
	public void calculateSemester_givenDateAfterJulySameYear_shouldReturnSecondSemester() {
		final Date date = new GregorianCalendar(2021, Calendar.NOVEMBER, 10).getTime();
		String semester = dateTimeUtils.calculateSemester("2021", date);
		assertEquals(DateTimeUtils.II_SEMESTRE, semester);
	}

	@Test
	public void calculateSemester_givenDateAfterYear_shouldReturnSecondSemester() {
		final Date date = new GregorianCalendar(2022, Calendar.NOVEMBER, 10).getTime();
		String semester = dateTimeUtils.calculateSemester("2021", date);
		assertEquals(DateTimeUtils.II_SEMESTRE, semester);
	}

	@Test
	public void calculateSemester_givenDateBeforeYear_shouldReturnFirstSemester() {
		final Date date = new GregorianCalendar(2020, Calendar.NOVEMBER, 10).getTime();
		String semester = dateTimeUtils.calculateSemester("2021", date);
		assertEquals(DateTimeUtils.I_SEMESTRE, semester);
	}

	@Test(expected = NullPointerException.class)
	public void calculateSemester_givenNullDate_shouldThrowException() {
		dateTimeUtils.calculateSemester("2021", null);
	}

	@Test(expected = NullPointerException.class)
	public void calculateSemester_givenNullYear_shouldThrowException() {
		dateTimeUtils.calculateSemester(null, new Date());
	}

	@Test
	public void calculateExtendedDescription_givenValidParameters_shouldFormatCorrectly() {
		final Date startDate = new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime();
		final Date endDate = new GregorianCalendar(2021, Calendar.FEBRUARY, 15).getTime();
		String description = dateTimeUtils.calculateExtendedDescription("ABC", startDate, endDate, "MY DESC");
		assertEquals("[ABC Lun 01-02-21 - 15gg] MY DESC", description);
	}

	@Test(expected = NullPointerException.class)
	public void calculateExtendedDescription_givenNullCodicePromozione_shouldThrowException() {
		dateTimeUtils.calculateExtendedDescription(null, new Date(), new Date(), "MY DESC");
	}

	@Test(expected = NullPointerException.class)
	public void calculateExtendedDescription_givenNullDataInizio_shouldThrowException() {
		dateTimeUtils.calculateExtendedDescription("ABC", null, new Date(), "MY DESC");
	}

	@Test(expected = NullPointerException.class)
	public void calculateExtendedDescription_givenNullDataFine_shouldThrowException() {
		dateTimeUtils.calculateExtendedDescription("ABC", new Date(), null, "MY DESC");
	}

	@Test
	public void toExcelDate_givenNullDate_shouldReturnNull() {
		String excelDate = dateTimeUtils.toExcelDate(null);
		assertNull(excelDate);
	}

	@Test
	public void toExcelDate_givenValidDate_shouldReturnFormattedDate() {
		final Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		String excelDate = dateTimeUtils.toExcelDate(date);
		assertEquals(EXCEL_DATE_AS_STRING, excelDate);
	}

	@Test
	public void toDate_givenInvalidDateString_shouldReturnNull() {
		DateTimeUtils mock = mock(DateTimeUtils.class);
		when(mock.toDate(anyString(), any())).thenThrow(RuntimeException.class);
		when(mock.toDate(anyString())).thenCallRealMethod();
		Date date = mock.toDate("10-02-2021");
		assertNull(date);
	}

	@Test
	public void toDate_givenValidDateString_shouldReturnValidDate() {
		Date date = dateTimeUtils.toDate("10/02/2021");
		final Date expectedDate = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		assertEquals(expectedDate, date);
	}

	@Test
	public void toDate_givenInvalidDateAndNotLenient_shouldReturnNull() {
		assertNull(dateTimeUtils.toDate("42/02/2021", false));
	}

	@Test
	public void toDate_givenInvalidDateAndLenient_shouldReturnDate() {
		assertNotNull(dateTimeUtils.toDate("42/02/2021", true));
	}

	@Test
	public void toDate_givenValidDateAndNotLenient_shouldReturnDate() {
		assertEquals(new GregorianCalendar(2021, Calendar.MAY, 4).getTime(),
				dateTimeUtils.toDate("04/05/2021", false));
	}

	@Test
	public void excelToDate_givenNullString_shouldReturnNull() {
		assertNull(dateTimeUtils.excelToDate(null));
	}

	@Test
	public void excelToDate_givenEmptyString_shouldReturnNull() {
		assertNull(dateTimeUtils.excelToDate(""));
	}

	@Test
	public void excelToDate_givenValidString_shouldReturnDate() {
		Date date = dateTimeUtils.excelToDate(EXCEL_DATE_AS_STRING);
		final Date expectedDate = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		assertEquals(expectedDate, date);
	}

	@Test
	public void toDate_givenNullString_shouldReturnNull() {
		assertNull(dateTimeUtils.toDate(null, sdf));
	}

	@Test
	public void toDate_givenEmptyString_shouldReturnNull() {
		assertNull(dateTimeUtils.toDate("", sdf));
	}

	@Test
	public void toDate_givenNullDateFormat_shouldReturnNull() {
		assertNull(dateTimeUtils.toDate("10-02-2021", null));
	}

	@Test
	public void toDate_givenInvalidDate_shouldReturnNull() {
		assertNull(dateTimeUtils.toDate("42x02x21", sdf));
	}

	@Test(expected = NullPointerException.class)
	public void timeAt_givenNullTime_shouldThrowException() throws Exception {
		dateTimeUtils.timeAt(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void timeAt_givenInvalidTime_shouldThrowException() throws Exception {
		dateTimeUtils.timeAt("10:10:10");
	}

	@Test
	public void timeAt_givenValidTime_shouldReturnLocalTime() throws Exception {
		LocalTime time = dateTimeUtils.timeAt("10:10");
		final LocalTime expectedTime = LocalTime.of(10, 10);
		assertEquals(expectedTime, time);
	}

	@Test(expected = NullPointerException.class)
	public void equalsNoTime_givenNullDate_shouldThrowException() {
		final Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 10, 10, 10).getTime();
		dateTimeUtils.equalsNoTime(date, null);
	}

	@Test(expected = NullPointerException.class)
	public void equalsNoTime_givenNullDate_shouldThrowException2() {
		final Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 10, 10, 10).getTime();
		dateTimeUtils.equalsNoTime(null, date);
	}

	@Test
	public void equalsNoTime_givenDateTimesToCompare_shouldDiscardTime() {
		final Date date1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 10, 10, 10).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.FEBRUARY, 10, 20, 20).getTime();
		assertNotEquals(date1, date2);
		assertTrue(dateTimeUtils.equalsNoTime(date1, date2));
	}

	@Test
	public void isBefore_givenTwoDates_shouldReturnTrue_whenFirstIsBeforeSecond() {
		final Date date1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.FEBRUARY, 20).getTime();
		assertTrue(dateTimeUtils.isBefore(date1, date2, false, true));
	}

	@Test
	public void isBefore_givenTwoDates_shouldReturnTrue_whenFirstIsBeforeOrEqualsSecond() {
		final Date date1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		assertTrue(dateTimeUtils.isBefore(date1, date2, true, true));
	}

	@Test
	public void isBefore_givenTwoDates_shouldReturnFalse_whenFirstIsNotBeforeSecond() {
		final Date date1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime();
		assertFalse(dateTimeUtils.isBefore(date1, date2, false, true));
	}

	@Test
	public void isBefore_givenTwoDatesAndNullableFlag_shouldReturnTrue_whenFirstIsNull() {
		final Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		assertTrue(dateTimeUtils.isBefore(null, date, true, true));
	}

	@Test
	public void isBefore_givenTwoDatesAndNullableFlag_shouldReturnTrue_whenSecondIsNull() {
		final Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		assertTrue(dateTimeUtils.isBefore(date, null, true, true));
	}

	@Test(expected = NullPointerException.class)
	public void isBefore_givenTwoDatesAndNotNullableFlag_shouldThrowException_whenAreEitherNull() {
		assertTrue(dateTimeUtils.isBefore(null, null, true, false));
	}

	@Test
	public void isAfter_givenTwoDates_shouldReturnTrue_whenFirstIsAfterSecond() {
		final Date date1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 25).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.FEBRUARY, 15).getTime();
		assertTrue(dateTimeUtils.isAfter(date1, date2, false, true));
	}

	@Test
	public void isAfter_givenTwoDates_shouldReturnTrue_whenFirstIsAfterOrEqualsSecond() {
		final Date date1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		assertTrue(dateTimeUtils.isAfter(date1, date2, true, true));
	}

	@Test
	public void isAfter_givenTwoDates_shouldReturnFalse_whenFirstIsNotAfterSecond() {
		final Date date1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.FEBRUARY, 15).getTime();
		assertFalse(dateTimeUtils.isAfter(date1, date2, false, true));
	}

	@Test
	public void isAfter_givenTwoDatesAndNullableFlag_shouldReturnTrue_whenFirstIsNull() {
		final Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		assertTrue(dateTimeUtils.isAfter(null, date, true, true));
	}

	@Test
	public void isAfter_givenTwoDatesAndNullableFlag_shouldReturnTrue_whenSecondIsNull() {
		final Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		assertTrue(dateTimeUtils.isAfter(date, null, true, true));
	}

	@Test(expected = NullPointerException.class)
	public void isAfter_givenTwoDatesAndNotNullableFlag_shouldThrowException_whenAreEitherNull() {
		assertTrue(dateTimeUtils.isAfter(null, null, true, false));
	}

	@Test
	public void compatibleDates_givenTwoDates_shouldReturnTrue_whenFirstIsAfterSecondAndFlagInizioIsTrue() {
		final Date date1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.FEBRUARY, 5).getTime();
		assertTrue(dateTimeUtils.compatibleDates(date1, date2, true));
	}

	@Test
	public void compatibleDates_givenTwoDates_shouldReturnFalse_whenFirstIsNotAfterSecondAndFlagInizioIsTrue() {
		final Date date1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.FEBRUARY, 5).getTime();
		assertFalse(dateTimeUtils.compatibleDates(date1, date2, true));
	}

	@Test
	public void compatibleDates_givenTwoDates_shouldReturnTrue_whenFirstIsBeforeSecondAndFlagInizioIsFalse() {
		final Date date1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.FEBRUARY, 5).getTime();
		assertTrue(dateTimeUtils.compatibleDates(date1, date2, false));
	}

	@Test
	public void compatibleDates_givenTwoDates_shouldReturnFalse_whenFirstIsNotBeforeSecondAndFlagInizioIsFalse() {
		final Date date1 = new GregorianCalendar(2021, Calendar.FEBRUARY, 10).getTime();
		final Date date2 = new GregorianCalendar(2021, Calendar.FEBRUARY, 5).getTime();
		assertFalse(dateTimeUtils.compatibleDates(date1, date2, false));
	}

	@Test(expected = NullPointerException.class)
	public void compatibleDates_givenTwoDates_shouldThrowException_whenFirstIsNull() {
		final Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 5).getTime();
		assertFalse(dateTimeUtils.compatibleDates(null, date, false));
	}

	@Test(expected = NullPointerException.class)
	public void compatibleDates_givenTwoDates_shouldThrowException_whenSecondIsNull() {
		final Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 5).getTime();
		assertFalse(dateTimeUtils.compatibleDates(date, null, false));
	}

	@Test(expected = NullPointerException.class)
	public void toTime_givenNullTimeString_shouldThrowException() {
		DateTimeUtils.toTime(null, false);
	}

	@Test
	public void toTime_givenInvalidTimeAndNotLenient_shouldReturnNull() {
		assertNull(DateTimeUtils.toTime("42:69", false));
	}

	@Test
	public void toTime_givenInvalidTimeAndLenient_shouldReturnDate() {
		assertNotNull(DateTimeUtils.toTime("42:69", true));
	}

	@Test
	public void toTime_givenValidTimeAndNotLenient_shouldReturnDateAtEpoch() {
		assertEquals(new GregorianCalendar(1970, Calendar.JANUARY, 1, 10, 10).getTime(),
				DateTimeUtils.toTime("10:10", false));
	}

	@Test
	public void daysBetween_givenNullStartDate_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dateTimeUtils.daysBetween(null, new Date());
	}

	@Test
	public void daysBetween_givenNullEndDate_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dateTimeUtils.daysBetween(new Date(), null);
	}

	@Test
	public void daysBetween_givenStartDateAfterEndDate_shouldThrowException() {
		ex.expect(RuntimeException.class);
		Date startDate = new GregorianCalendar(2026, Calendar.APRIL, 1).getTime();
		Date endDate = new GregorianCalendar(2026, Calendar.FEBRUARY, 1).getTime();
		dateTimeUtils.daysBetween(startDate, endDate);
	}

	@Test
	public void daysBetween() {
		Date startDate = new GregorianCalendar(2026, Calendar.JANUARY, 1).getTime();
		Date endDate = new GregorianCalendar(2026, Calendar.FEBRUARY, 1).getTime();
		assertEquals(31, (long) dateTimeUtils.daysBetween(startDate, endDate));
	}
}
