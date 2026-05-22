package com.axiante.mui.common.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Test;

public class AuditLogUtilsTest {
    private AuditLogUtils auditLogUtils = new AuditLogUtils();
    private static final String GIVEN_YEAR = "2021";
    private static final String GIVEN_DATE = "15.02.2021";
    private static final String GIVEN_DATE_TIME = "15.02.2021 10";

    @Test
    public void checkFilterLogDate_givenValidYearString_shouldReturnTrue() {
        assertTrue(auditLogUtils.checkFilterLogDate(GIVEN_YEAR));
    }

    @Test
    public void checkFilterLogDate_givenWrongYearString_shouldReturnFalse() {
        assertFalse(auditLogUtils.checkFilterLogDate("abcd"));
    }

    @Test
    public void checkFilterLogDate_givenValidDateString_shouldReturnTrue() {
        assertTrue(auditLogUtils.checkFilterLogDate(GIVEN_DATE));
    }

    @Test
    public void checkFilterLogDate_givenWrongDateString_shouldReturnFalse() {
        assertFalse(auditLogUtils.checkFilterLogDate("15-02-2021"));
        assertFalse(auditLogUtils.checkFilterLogDate("15.02 2021"));
    }

    @Test
    public void checkFilterLogDate_givenValidDateTimeString_shouldReturnTrue() {
        assertTrue(auditLogUtils.checkFilterLogDate(GIVEN_DATE_TIME));
    }

    @Test
    public void checkFilterLogDate_givenWrongDateTimeString_shouldReturnFalse() {
        assertFalse(auditLogUtils.checkFilterLogDate("15-02-2021 10"));
        assertFalse(auditLogUtils.checkFilterLogDate("15.02-2021_10"));
    }

    @Test
    public void checkFilterLogDate_givenWrongdFilterString_shouldReturnFalse() {
        assertFalse(auditLogUtils.checkFilterLogDate("abcdef"));
    }

    @Test
    public void composeLogDateQueryCondition_givenValidYearStringFirstDateTrue_shouldReturnDateAtStartOfYear() {
        Date date = new GregorianCalendar(2021, Calendar.JANUARY, 1, 0, 0, 0).getTime();
        assertEquals(date, auditLogUtils.composeLogDateQueryCondition(GIVEN_YEAR, true));
    }

    @Test
    public void composeLogDateQueryCondition_givenValidYearStringFirstDateFalse_shouldReturnDateAtEndOfYear() {
        Date date = new GregorianCalendar(2021, Calendar.DECEMBER, 31, 23, 59, 59).getTime();
        assertEquals(date, auditLogUtils.composeLogDateQueryCondition(GIVEN_YEAR, false));
    }

    @Test
    public void composeLogDateQueryCondition_givenWrongYear_shouldReturnNull() {
        assertNull(auditLogUtils.composeLogDateQueryCondition("42", true));
        assertNull(auditLogUtils.composeLogDateQueryCondition("abcd", true));
    }

    @Test
    public void composeLogDateQueryCondition_givenValidDateFirstDateTrue_shouldReturnDateAtStartOfDay() {
        Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 15, 0, 0, 0).getTime();
        assertEquals(date, auditLogUtils.composeLogDateQueryCondition(GIVEN_DATE, true));
    }

    @Test
    public void composeLogDateQueryCondition_givenValidDateFirstDateFalse_shouldReturnDateAtEndOfDay() {
        Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 15, 23, 59, 59).getTime();
        assertEquals(date, auditLogUtils.composeLogDateQueryCondition(GIVEN_DATE, false));
    }

    @Test
    public void composeLogDateQueryCondition_givenWrongDate_shouldReturnNull() {
        assertNull(auditLogUtils.composeLogDateQueryCondition("15-02 2021", true));
        assertNull(auditLogUtils.composeLogDateQueryCondition("15.02 2021", true));
    }

    @Test
    public void composeLogDateQueryCondition_givenValidDateTimeFirstDateTrue_shouldReturnDateAtStartOfHour() {
        Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 15, 10, 0, 0).getTime();
        assertEquals(date, auditLogUtils.composeLogDateQueryCondition(GIVEN_DATE_TIME, true));
    }

    @Test
    public void composeLogDateQueryCondition_givenValidDateTimeFirstDateFalse_shouldReturnDateAtEndOfHour() {
        Date date = new GregorianCalendar(2021, Calendar.FEBRUARY, 15, 10, 59, 59).getTime();
        assertEquals(date, auditLogUtils.composeLogDateQueryCondition(GIVEN_DATE_TIME, false));
    }

    @Test
    public void composeLogDateQueryCondition_givenWrongDateTime_shouldReturnNull() {
        assertNull(auditLogUtils.composeLogDateQueryCondition("15.02/2021_10", true));
        assertNull(auditLogUtils.composeLogDateQueryCondition("15/02/2021 10", true));
    }
}
