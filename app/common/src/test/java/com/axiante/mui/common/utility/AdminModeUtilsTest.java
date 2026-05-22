package com.axiante.mui.common.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class AdminModeUtilsTest {
	final LocalDateTime timeToCheck = LocalDateTime.now();
	AdminModeUtils utils = new AdminModeUtils();

	@Test
	public void testSimpleCaseReturnsTrueWhenExpected() {
		String start = "09:00";
		String end = "14:00";
		LocalTime time = LocalTime.MIDNIGHT;
		LocalTime minTrue = LocalTime.of(9, 0);
		LocalTime maxTrue = LocalTime.of(14, 0);
		boolean expected = false;
		boolean result = false;
		int quarters = 15*4*24;
		for (int test = 0 ; test < quarters ; ++test ) {
			if ( time.equals(minTrue) || time.equals(maxTrue)) {
				expected = true;
			} else if (time.isAfter(minTrue) && time.isBefore(maxTrue) ) {
				expected = true;
			} else { 
				expected = false;
			}
			result = utils.isTimeBetween(LocalDateTime.of(LocalDate.now(), time), start, end);
			assertThat(String.format("alle ore %s admin mode e' %b ma l'admin mode e' tra le %s e le %s : dovrebbe essere %b", time.toString(), result, start, end, expected), expected, CoreMatchers.equalTo(result));
			time = time.plusMinutes(15);
		}
	}

	@Test
	public void testOvernightCaseReturnsTrueWhenExpected() {
		String start = "23:30";
		String end = "06:30";
		LocalTime time = LocalTime.MIDNIGHT;
		LocalTime minTrue = LocalTime.of(6, 30);
		LocalTime maxTrue = LocalTime.of(23, 30);
		boolean expected = false;
		boolean result = false;
		int quarters = 15*4*24;
		for (int test = 0 ; test < quarters ; ++test ) {
			if ( time.equals(LocalTime.MIDNIGHT) || time.equals(minTrue) || time.equals(maxTrue)) {
				expected = true;
			} else if ( time.isAfter(LocalTime.MIDNIGHT) && time.isBefore(minTrue)) {
				expected = true;
			} else if ( time.isAfter(maxTrue) && time.isBefore(LocalTime.MAX)) {
				expected = true;
			} else { 
				expected = false;
			}
			result = utils.isTimeBetween(LocalDateTime.of(LocalDate.now(), time), start, end);
			assertThat(String.format("alle ore %s admin mode e' %b ma l'admin mode e' tra le %s e le %s : dovrebbe essere %b", time.toString(), result, start, end, expected), expected, CoreMatchers.equalTo(result));
			time = time.plusMinutes(15);
		}
	}

	@Test(expected = NullPointerException.class)
	public void isCheckDay_givenNullDayOfWeek_shouldThrowException() {
		utils.isCheckDay(null, true, new ArrayList<>());
	}

	@Test(expected = NullPointerException.class)
	public void isCheckDay_givenNullAllDays_shouldThrowException() {
		utils.isCheckDay(1, null, new ArrayList<>());
	}

	@Test
	public void isCheckDay__givenValidParameters_shouldReturnTrue_whenAllDaysTrue() {
		assertTrue(utils.isCheckDay(1, true, Arrays.asList(1, 2, 3, 4, 5)));
	}

	@Test
	public void isCheckDay__givenValidParameters_shouldReturnTrue_whenAllDaysFalseAndDaysContainsDayOfWeek() {
		assertTrue(utils.isCheckDay(1, false, Arrays.asList(1, 2, 3, 4, 5)));
	}

	@Test
	public void isCheckDay__givenValidParameters_shouldReturnFalse_whenAllDaysFalseAndDaysNotContainsDayOfWeek() {
		assertFalse(utils.isCheckDay(7, false, Arrays.asList(1, 2, 3, 4, 5)));
	}

	@Test
	public void isCheckDay__givenValidParameters_shouldReturnFalse_whenDaysIsNullOrEmpty() {
		assertFalse(utils.isCheckDay(7, false, null));
		assertFalse(utils.isCheckDay(7, false, new ArrayList<>()));
	}

	@Test(expected = NullPointerException.class)
	public void isTimeBetween_givenNullTimeToCheck_shouldThrowException() {
		utils.isTimeBetween(null, "01-02-2021", "28-02-2021");
	}

	@Test(expected = NullPointerException.class)
	public void isTimeBetween_givenNullStartDate_shouldThrowException() {
		utils.isTimeBetween(timeToCheck, null, "28-02-2021");
	}

	@Test(expected = NullPointerException.class)
	public void isTimeBetween_givenNullEndDate_shouldThrowException() {
		utils.isTimeBetween(timeToCheck, "01-02-2021", null);
	}

	@Test
	public void isTimeBetween_givenWrongDates_shouldReturnFalse() {
		assertFalse(utils.isTimeBetween(timeToCheck, "01-02-2021", "42-42-42"));
	}
}
