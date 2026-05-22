package com.axiante.mui.webapp.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class MacrospaziDateUtilsTest {

    private MacrospaziDateUtils utils;

    @Before
    public void setUp() {
        utils = new MacrospaziDateUtils();
    }

    private Date today() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    @Test
    public void testNullDates() {
        assertTrue(utils.isValid(null, null, false));
    }

    @Test
    public void testInizioNullFineValid() {
        Date fine = addDays(today(), 1);
        assertTrue(utils.isValid(null, fine, false));
    }

    @Test
    public void testInizioNullFineToday() {
        Date fine = today();
        assertFalse(utils.isValid(null, fine, false));
    }

    @Test
    public void testInizioTodayFineNull() {
        Date inizio = today();
        assertFalse(utils.isValid(inizio, null, false));
    }

    @Test
    public void testInizioTomorrowFineNull() {
        Date inizio = addDays(today(), 1);
        assertTrue(utils.isValid(inizio, null, false));
    }

    @Test
    public void testInizioTomorrowFineTomorrow() {
        Date inizio = addDays(today(), 1);
        Date fine = addDays(today(), 1);
        assertFalse(utils.isValid(inizio, fine, false));
    }

    @Test
    public void testInizioTomorrowFineInizioPlusOne() {
        Date inizio = addDays(today(), 1);
        Date fine = addDays(inizio, 1);
        assertTrue(utils.isValid(inizio, fine, false));
    }

    @Test
    public void testInizioTomorrowFineBeforeInizioPlusOne() {
        Date inizio = addDays(today(), 1);
        Date fine = addDays(inizio, 0);
        assertFalse(utils.isValid(inizio, fine, false));
    }

    @Test
    public void testInizioTomorrowFineAfterInizioPlusOne() {
        Date inizio = addDays(today(), 1);
        Date fine = addDays(inizio, 2);
        assertTrue(utils.isValid(inizio, fine, false));
    }

    @Test
    public void testInizioBeforeTomorrow() {
        Date inizio = new Date(System.currentTimeMillis());
        assertFalse(utils.isValid(inizio, null, false));
        Date inizioYesterday = addDays(today(), -1);
        assertFalse(utils.isValid(inizioYesterday, null, false));
    }

    @Test
    public void testFineBeforeTomorrow() {
        Date fine = today();
        assertFalse(utils.isValid(null, fine, false));
        Date fineYesterday = addDays(today(), -1);
        assertFalse(utils.isValid(null, fineYesterday, false));
    }
}