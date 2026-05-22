package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PianoMediaDAO dao;

    @Mock
    private PianoMediaEntity entity1;

    @Mock
    private PianoMediaEntity entity2;

    @Mock
    private PianoMediaEntity entity3;

    @InjectMocks
    private PianoMediaServiceImpl service;

    private Date dataInizio = new GregorianCalendar(2025, Calendar.JULY, 1).getTime();
    private Date dataFine = new GregorianCalendar(2025, Calendar.JULY, 30).getTime();

    @Before
    public void setUp() throws Exception {
        when(dao.findByDataInizioAndDataFine(dataInizio, dataFine)).thenReturn(Collections.singletonList(entity1));
        when(dao.findNonPubblicatiByDataInizioAndDataFine(dataInizio, dataFine)).thenReturn(Collections.singletonList(entity2));
        when(dao.findOpenPianiMedia()).thenReturn(Collections.singletonList(entity3));
        when(dao.findNonPubblicatiPianiMedia()).thenReturn(Collections.singletonList(entity1));
        when(dao.findNotCancelled()).thenReturn(Collections.singletonList(entity2));
        when(dao.findOpenAvailableYears()).thenReturn(Collections.singletonList(2025));
        when(dao.findPubblicatiByDataInizio(dataInizio)).thenReturn(Collections.singletonList(entity3));
        when(dao.findOpenByDataFine(dataFine)).thenReturn(Collections.singletonList(entity1));
        when(dao.findPianiMediaAccessibiliInPianificazione()).thenReturn(Collections.singletonList(entity2));
    }

    @Test
    public void findByDataInizioAndDataFine_givenNullDataInizio_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByDataInizioAndDataFine(null, dataFine);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByDataInizioAndDataFine_givenNullDataFine_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByDataInizioAndDataFine(dataInizio, null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByDataInizioAndDataFine() {
        final List<PianoMediaEntity> result = service.findByDataInizioAndDataFine(dataInizio, dataFine);
        assertEquals(1, result.size());
        assertTrue(result.contains(entity1));
        verify(dao, times(1)).findByDataInizioAndDataFine(dataInizio, dataFine);
    }

    @Test
    public void findNonPubblicatiByDataInizioAndDataFine_givenNullDataInizio_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findNonPubblicatiByDataInizioAndDataFine(null, dataFine);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findNonPubblicatiByDataInizioAndDataFine_givenNullDataFine_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findNonPubblicatiByDataInizioAndDataFine(dataInizio, null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findNonPubblicatiByDataInizioAndDataFine() {
        final List<PianoMediaEntity> result = service.findNonPubblicatiByDataInizioAndDataFine(dataInizio, dataFine);
        assertEquals(1, result.size());
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findNonPubblicatiByDataInizioAndDataFine(dataInizio, dataFine);
    }

    @Test
    public void findOpenPianiMedia() {
        final List<PianoMediaEntity> result = service.findOpenPianiMedia();
        assertEquals(1, result.size());
        assertTrue(result.contains(entity3));
        verify(dao, times(1)).findOpenPianiMedia();
    }

    @Test
    public void findNonPubblicatiPianiMedia() {
        final List<PianoMediaEntity> result = service.findNonPubblicatiPianiMedia();
        assertEquals(1, result.size());
        assertTrue(result.contains(entity1));
        verify(dao, times(1)).findNonPubblicatiPianiMedia();
    }

    @Test
    public void findNotCancelled() {
        final List<PianoMediaEntity> result = service.findNotCancelled();
        assertEquals(1, result.size());
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findNotCancelled();
    }

    @Test
    public void findOpenAvailableYears() {
        final List<Integer> result = service.findOpenAvailableYears();
        assertEquals(1, result.size());
        assertTrue(result.contains(2025));
        verify(dao, times(1)).findOpenAvailableYears();
    }

    @Test
    public void findPubblicatiByDataInizio_givenNullDataInizio_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findPubblicatiByDataInizio(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findPubblicatiByDataInizio() {
        final List<PianoMediaEntity> result = service.findPubblicatiByDataInizio(dataInizio);
        assertEquals(1, result.size());
        assertTrue(result.contains(entity3));
        verify(dao, times(1)).findPubblicatiByDataInizio(dataInizio);
    }

    @Test
    public void findOpenByDataFine_givenNullDataFine_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findOpenByDataFine(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findOpenByDataFine() {
        final List<PianoMediaEntity> result = service.findOpenByDataFine(dataFine);
        assertEquals(1, result.size());
        assertTrue(result.contains(entity1));
        verify(dao, times(1)).findOpenByDataFine(dataFine);
    }

    @Test
    public void findPianiMediaAccessibiliInPianificazione() {
        final List<PianoMediaEntity> result = service.findPianiMediaAccessibiliInPianificazione();
        assertEquals(1, result.size());
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findPianiMediaAccessibiliInPianificazione();
    }
}