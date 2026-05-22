package com.axiante.mui.webapp.utils;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.MuiEventoRetailEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventoRetailUtilsTest {

    private EventoRetailUtils utils;
    private MacrospaziDateUtils macrospaziDateUtils;
    private DateTimeUtils dateTimeUtils;

    @Before
    public void setUp() {
        macrospaziDateUtils = Mockito.spy(MacrospaziDateUtils.class);
        dateTimeUtils = Mockito.spy(DateTimeUtils.class);
        utils = Mockito.spy(new EventoRetailUtils(macrospaziDateUtils, dateTimeUtils));
    }

    private MuiEventoRetailEntity validEvent(Date inizio, Date fine) {
        MuiEventoRetailEntity e = mock(MuiEventoRetailEntity.class);
        when(e.getValoreContributo()).thenReturn(BigDecimal.ONE);
        when(e.getCodiceCausale()).thenReturn("CAUSE");
        when(e.getCodiceFornitore()).thenReturn("SUPPLIER");
        when(e.getDataInizio()).thenReturn(inizio);
        when(e.getDataFine()).thenReturn(fine);
        when(e.getMacrospazio()).thenReturn(mock(MuiMacrospazioMediaEntity.class));
        return e;
    }

    @Test
    public void testCanAdd_validFutureEvent() {

        Date afterTomorrow = dateTimeUtils.getDateWithoutTime(java.sql.Date.valueOf(
                        LocalDateTime.now().plusDays(2).toLocalDate()
                )

        );
        MuiEventoRetailEntity event = validEvent(afterTomorrow, afterTomorrow);
        when(macrospaziDateUtils.isValid(any(), any(), anyBoolean())).thenReturn(true);
        when(event.getId()).thenReturn(null);
        assertTrue(utils.canAdd(event));
    }

    @Test
    public void testCanAdd_invalidEvent() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        when(macrospaziDateUtils.isValid(any(), any(), anyBoolean())).thenReturn(false);
        assertFalse(utils.canAdd(event));
    }

    @Test
    public void testCanAdd_existingId() {
        MuiEventoRetailEntity event = validEvent(new Date(), new Date());
        when(macrospaziDateUtils.isValid(any(), any(), anyBoolean())).thenReturn(true);
        when(event.getId()).thenReturn(1L);
        assertFalse(utils.canAdd(event));
    }

    @Test
    public void testCanAdd_notFuture() {
        MuiEventoRetailEntity event = validEvent(new Date(), new Date());
        when(macrospaziDateUtils.isValid(any(), any(), anyBoolean())).thenReturn(true);
        when(event.getId()).thenReturn(null);
        doReturn(true).when(dateTimeUtils).isAfter(any(Date.class), any(Date.class), anyBoolean());
        boolean result = utils.canAdd(event);
        assertFalse(result);
    }

    @Test
    public void testCanEdit_validActiveEvent() {
        Date today = new Date();
        MuiEventoRetailEntity original = validEvent(today, today);
        Date tomorrow = dateTimeUtils.getDateWithoutTime(java.sql.Date.valueOf(
                LocalDateTime.now().plusDays(1).toLocalDate()
                )
        );
        Date afterTomorrow = dateTimeUtils.getDateWithoutTime(java.sql.Date.valueOf(
                LocalDateTime.now().plusDays(2).toLocalDate()
                )

        );
        MuiEventoRetailEntity event = validEvent(today, tomorrow);

        when(event.getDataInizio()).thenReturn(tomorrow);
        when(event.getDataFine()).thenReturn(afterTomorrow);
        when(original.getId()).thenReturn(1L);
        doReturn(true).when(utils).isActive(any(MuiEventoRetailEntity.class));
        doReturn(true).when(utils).isValid(any(MuiEventoRetailEntity.class));
        // isActive = true, so check fine >= today
        when(dateTimeUtils.isBefore(today, original.getDataFine(), true)).thenReturn(true);
        when(dateTimeUtils.isBefore(today, event.getDataFine(), true)).thenReturn(false);

        assertTrue(utils.canEdit(original, event));
    }

    @Test
    public void testCanEdit_invalidEvent() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        doReturn(false).when(utils).isValid(event);
        assertFalse(utils.canEdit(mock(MuiEventoRetailEntity.class),event));
    }

    @Test
    public void testCanEdit_nullId() {
        MuiEventoRetailEntity event = validEvent(new Date(), new Date());
        doReturn(true).when(utils).isValid(event);
        when(event.getId()).thenReturn(null);
        assertFalse(utils.canEdit(mock(MuiEventoRetailEntity.class),event));
    }

    @Test
    public void testCanDelete_validFutureEvent() {
        Date afterTomorrow = dateTimeUtils.getDateWithoutTime(java.sql.Date.valueOf(
                        LocalDateTime.now().plusDays(2).toLocalDate()
                )

        );
        MuiEventoRetailEntity event = validEvent(afterTomorrow, afterTomorrow);
        when(macrospaziDateUtils.isValid(any(), any(), anyBoolean())).thenReturn(true);
        when(event.getId()).thenReturn(1L);
        assertTrue(utils.canDelete(event));
    }

    @Test
    public void testCanDelete_invalidEvent() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        when(macrospaziDateUtils.isValid(any(), any(), anyBoolean())).thenReturn(false);
        assertFalse(utils.canDelete(event));
    }

    @Test
    public void testCanDelete_nullId() {
        MuiEventoRetailEntity event = validEvent(new Date(), new Date());
        when(macrospaziDateUtils.isValid(any(), any(), anyBoolean())).thenReturn(true);
        when(event.getId()).thenReturn(null);
        assertFalse(utils.canDelete(event));
    }

    @Test
    public void testIsValid_allFieldsValid() {
        MuiEventoRetailEntity event = validEvent(new Date(), new Date());
        when(macrospaziDateUtils.isValid(any(), any(), anyBoolean())).thenReturn(true);
        assertTrue(utils.isValid(event));
    }

    @Test
    public void testIsValid_nullEvent() {
        assertFalse(utils.isValid(null));
    }

    @Test
    public void testIsValid_missingFields() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        when(event.getValoreContributo()).thenReturn(null);
        assertFalse(utils.isValid(event));
        when(event.getValoreContributo()).thenReturn(BigDecimal.ONE);
        when(event.getCodiceCausale()).thenReturn("");
        assertFalse(utils.isValid(event));
        when(event.getCodiceCausale()).thenReturn("CAUSE");
        when(event.getCodiceFornitore()).thenReturn("");
        assertFalse(utils.isValid(event));
    }

    @Test
    public void testIsCoherent_valid() {
        MuiEventoRetailEntity event = validEvent(new Date(2000000000000L), new Date(2000000001000L));
        MuiMacrospazioMediaEntity macro = mock(MuiMacrospazioMediaEntity.class);
        when(macrospaziDateUtils.isValid(any(Date.class), any(Date.class),anyBoolean())).thenReturn(true);
        when(macro.getDataInizio()).thenReturn(new Date(1999999999000L));
        when(macro.getDataFine()).thenReturn(new Date(2000000002000L));
        when(dateTimeUtils.isAfter(event.getDataInizio(), macro.getDataInizio(), true)).thenReturn(true);
        when(dateTimeUtils.isAfter(macro.getDataFine(), event.getDataFine(), true)).thenReturn(true);
        assertTrue(utils.isCoherent(event, macro, false));
    }

    @Test
    public void testWhenEventoStartsBeforeMacrospazio_thenIsNotCoherent() {
        MuiEventoRetailEntity event = validEvent(new Date(2000000000000L), new Date(2000000001000L));
        MuiMacrospazioMediaEntity macro = mock(MuiMacrospazioMediaEntity.class);
        when(macro.getDataInizio()).thenReturn(new Date(2000000005000L));
        when(dateTimeUtils.isAfter(event.getDataInizio(), macro.getDataInizio(), true)).thenReturn(false);
        assertFalse(utils.isCoherent(event, macro));
    }

    @Test
    public void testWhenEventoEndsAfterMacrospazio_thenIsNotCoherent() {
        MuiEventoRetailEntity event = validEvent(new Date(2000000000000L), new Date(2000000006000L));
        MuiMacrospazioMediaEntity macro = mock(MuiMacrospazioMediaEntity.class);
        when(macro.getDataInizio()).thenReturn(new Date(1999999999000L));
        when(macro.getDataFine()).thenReturn(new Date(2000000005000L));
        when(dateTimeUtils.isAfter(event.getDataInizio(), macro.getDataInizio(), true)).thenReturn(true);
        when(dateTimeUtils.isAfter(macro.getDataFine(), event.getDataFine(), true)).thenReturn(false);
        assertFalse(utils.isCoherent(event, macro));
    }

    @Test
    public void testWhenEventoEndsBeforeMacrospazioBegins_thenIsNotCoherent() {
        MuiEventoRetailEntity event = validEvent(new Date(2000000000000L), new Date(2000000001000L));
        MuiMacrospazioMediaEntity macro = mock(MuiMacrospazioMediaEntity.class);
        when(macro.getDataInizio()).thenReturn(new Date(2000000005000L));
        when(dateTimeUtils.isAfter(event.getDataInizio(), macro.getDataInizio(), true)).thenReturn(false);
        assertFalse(utils.isCoherent(event, macro));
    }

    @Test
    public void testWhenEventoStartsAfterMacrospazioEnds_thenIsNotCoherent() {
        MuiEventoRetailEntity event = validEvent(new Date(2000000010000L), new Date(2000000011000L));
        MuiMacrospazioMediaEntity macro = mock(MuiMacrospazioMediaEntity.class);
        when(macro.getDataFine()).thenReturn(new Date(2000000005000L));
        when(dateTimeUtils.isAfter(macro.getDataFine(), event.getDataFine(), true)).thenReturn(false);
        assertFalse(utils.isCoherent(event, macro));
    }

    @Test
    public void testIsCoherent_nullMacrospazio() {
        MuiEventoRetailEntity event = validEvent(new Date(), new Date());
        when(macrospaziDateUtils.isValid(any(), any(), anyBoolean())).thenReturn(true);
        assertFalse(utils.isCoherent(event, null));
    }

// --- Add to EventoRetailUtilsTest.java ---

    @Test
    public void testIsFuture_eventStartsAfterToday() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + 24 * 60 * 60 * 1000);
        when(event.getDataInizio()).thenReturn(tomorrow);
        doReturn(today).when(dateTimeUtils).getDateWithoutTime(any(Date.class));
        doReturn(true).when(dateTimeUtils).isAfter(tomorrow, today, false);
        assertTrue(utils.isFuture(event));
    }

    @Test
    public void testIsFuture_eventStartsToday() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        Date today = new Date();
        when(event.getDataInizio()).thenReturn(today);
        doReturn(today).when(dateTimeUtils).getDateWithoutTime(any(Date.class));
        doReturn(false).when(dateTimeUtils).isAfter(today, today, false);
        assertFalse(utils.isFuture(event));
    }

    @Test
    public void testIsFuture_eventStartedInPast() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        Date today = new Date();
        Date yesterday = new Date(today.getTime() - 24 * 60 * 60 * 1000);
        when(event.getDataInizio()).thenReturn(yesterday);
        doReturn(today).when(dateTimeUtils).getDateWithoutTime(any(Date.class));
        when(dateTimeUtils.isAfter(yesterday, today, false)).thenReturn(false);
        assertFalse(utils.isFuture(event));
    }

    @Test
    public void testIsActive_eventActiveToday() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        Date today = new Date();
        when(event.getDataInizio()).thenReturn(today);
        when(event.getDataFine()).thenReturn(today);
        doReturn(today).when(dateTimeUtils).getDateWithoutTime(any(Date.class));
        when(dateTimeUtils.isAfter(today, today, true)).thenReturn(true);
        when(dateTimeUtils.isBefore(today, today, true)).thenReturn(true);
        assertTrue(utils.isActive(event));
    }

    @Test
    public void testIsActive_eventNotStartedYet() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + 24 * 60 * 60 * 1000);
        when(event.getDataInizio()).thenReturn(tomorrow);
        when(event.getDataFine()).thenReturn(tomorrow);
        doReturn(today).when(dateTimeUtils).getDateWithoutTime(any(Date.class));
        doReturn(false).when(dateTimeUtils).isAfter(today, tomorrow, true);
        doReturn(true).when(dateTimeUtils).isBefore(today, tomorrow, true);
        assertFalse(utils.isActive(event));
    }

    @Test
    public void testIsActive_eventEndedYesterday() {
        MuiEventoRetailEntity event = mock(MuiEventoRetailEntity.class);
        Date today = new Date();
        Date yesterday = new Date(today.getTime() - 24 * 60 * 60 * 1000);
        when(event.getDataInizio()).thenReturn(yesterday);
        when(event.getDataFine()).thenReturn(yesterday);
        doReturn(today).when(dateTimeUtils).getDateWithoutTime(any(Date.class));
        doReturn(true).when(dateTimeUtils).isAfter(today, yesterday, true);
        doReturn(false).when(dateTimeUtils).isBefore(today, yesterday, true);
        assertFalse(utils.isActive(event));
    }

}