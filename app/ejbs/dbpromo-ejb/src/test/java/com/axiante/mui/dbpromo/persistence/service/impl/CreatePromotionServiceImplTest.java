package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CreaPromozioneDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreatePromotionServiceImplTest {
    @Mock
    private CreaPromozioneDAO dao;

    @InjectMocks
    private CreatePromotionServiceImpl service;

    private Date dataInizio = new GregorianCalendar(2025, Calendar.JULY, 1).getTime();
    private Date dataFine = new GregorianCalendar(2025, Calendar.JULY, 30).getTime();

    @Before
    public void setUp() throws Exception {
        when(dao.runFunctionCountTestate(1L, 1L, dataInizio, dataFine)).thenReturn(true);
        when(dao.runFunctionCountTestate(1L, 2L, dataInizio, dataFine))
                .thenThrow(new RuntimeException("FOO EXCEPTION"));
        when(dao.runFunctionCountTestate(1L, dataInizio, dataFine, 10L)).thenReturn(true);
        when(dao.runFunctionCountTestate(2L, dataInizio, dataFine, 10L))
                .thenThrow(new RuntimeException("FOO EXCEPTION"));
    }

    @Test
    public void runFunctionCountTestate_whenException_shouldReturnFalse() throws Exception {
        assertFalse(service.runFunctionCountTestate(1L, 2L, dataInizio, dataFine));
        verify(dao, times(1)).runFunctionCountTestate(1L, 2L, dataInizio, dataFine);
    }

    @Test
    public void runFunctionCountTestate() throws Exception {
        assertTrue(service.runFunctionCountTestate(1L, 1L, dataInizio, dataFine));
        verify(dao, times(1)).runFunctionCountTestate(1L, 1L, dataInizio, dataFine);
    }

    @Test
    public void testRunFunctionCountTestate_whenException_shouldReturnFalse() throws Exception {
        assertFalse(service.runFunctionCountTestate(2L, dataInizio, dataFine, 10L));
        verify(dao, times(1)).runFunctionCountTestate(2L, dataInizio, dataFine, 10L);
    }

    @Test
    public void testRunFunctionCountTestate_whenMaxTestateNullOrNegative_shouldReturnTrue() throws Exception {
        assertTrue(service.runFunctionCountTestate(1L, dataInizio, dataFine, null));
        assertTrue(service.runFunctionCountTestate(1L, dataInizio, dataFine, -1L));
        verifyZeroInteractions(dao);
    }

    @Test
    public void testRunFunctionCountTestate() throws Exception {
        assertTrue(service.runFunctionCountTestate(1L, dataInizio, dataFine, 10L));
        verify(dao, times(1)).runFunctionCountTestate(1L, dataInizio, dataFine, 10L);
    }
}