package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoDbpromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaPromoDbpromoServiceImplTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PianoMediaPromoDbpromoDAO dao;

    @InjectMocks
    private PianoMediaPromoDbpromoServiceImpl service;

    @Test
    public void findByDataFineGreaterThanAndCanali_givenNullDate_shouldThrowException() {
        final CanalePromozioneEntity canalePromo = mock(CanalePromozioneEntity.class);
        ex.expect(NullPointerException.class);
        service.findByDataFineGreaterThanAndCanali(null, Collections.singletonList(canalePromo));
        verify(dao, never()).findByDataFineGreaterThanAndCanali(any(), anyList());
    }

    @Test
    public void findByDataFineGreaterThanAndCanali_givenNullCanali_shouldReturnEmptyList() {
        final Date date = new GregorianCalendar(2023, Calendar.JULY, 15).getTime();
        final List<PianoMediaPromoDbpromoEntity> result = service.findByDataFineGreaterThanAndCanali(date, null);
        assertTrue(result.isEmpty());
        verify(dao, never()).findByDataFineGreaterThanAndCanali(any(), anyList());
    }

    @Test
    public void findByDataFineGreaterThanAndCanali_givenEmptyCanali_shouldReturnEmptyList() {
        final Date date = new GregorianCalendar(2023, Calendar.JULY, 15).getTime();
        final List<PianoMediaPromoDbpromoEntity> result = service.findByDataFineGreaterThanAndCanali(date, Collections.emptyList());
        assertTrue(result.isEmpty());
        verify(dao, never()).findByDataFineGreaterThanAndCanali(any(), anyList());
    }

    @Test
    public void findByDataFineGreaterThanAndCanali() {
        final CanalePromozioneEntity canalePromo = mock(CanalePromozioneEntity.class);
        final Date date = new GregorianCalendar(2023, Calendar.JULY, 15).getTime();
        final PianoMediaPromoDbpromoEntity mock = mock(PianoMediaPromoDbpromoEntity.class);
        final List<PianoMediaPromoDbpromoEntity> entities = new ArrayList<>(Collections.singleton(mock));
        when(dao.findByDataFineGreaterThanAndCanali(date, Collections.singletonList(canalePromo)))
                .thenReturn(entities);
        final List<PianoMediaPromoDbpromoEntity> result = service.findByDataFineGreaterThanAndCanali(date,
                Collections.singletonList(canalePromo));
        assertEquals(1, result.size());
        verify(dao, times(1))
                .findByDataFineGreaterThanAndCanali(date, Collections.singletonList(canalePromo));
    }

    @Test
    public void findByCodicePromo_givenNullCodicePromoMaster_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByCodicePromo(null);
        verify(dao, never()).findByCodicePromo(any());
    }

    @Test
    public void findByCodicePromo() {
        final PianoMediaPromoDbpromoEntity mock = mock(PianoMediaPromoDbpromoEntity.class);
        when(dao.findByCodicePromo("PR_001")).thenReturn(mock);
        final PianoMediaPromoDbpromoEntity pianoMediaPromo = service.findByCodicePromo("PR_001");
        assertNotNull(pianoMediaPromo);
        verify(dao, times(1)).findByCodicePromo("PR_001");
    }

    @Test
    public void findAllByCodiciPromo_givenNullCodiciPromo_shouldReturnEmptyList() {
        final List<PianoMediaPromoDbpromoEntity> result = service.findAllByCodiciPromo(null);
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByCodiciPromo_givenEmptyCodiciPromo_shouldReturnEmptyList() {
        final List<PianoMediaPromoDbpromoEntity> result = service.findAllByCodiciPromo(Collections.emptyList());
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByCodiciPromo() {
        final List<String> codiciPromo = Stream.of("PR-001", "PR-002").collect(Collectors.toList());
        final List<PianoMediaPromoDbpromoEntity> entities = Stream.of(mock(PianoMediaPromoDbpromoEntity.class), mock(PianoMediaPromoDbpromoEntity.class))
                .collect(Collectors.toList());
        when(dao.findAllByCodiciPromo(codiciPromo)).thenReturn(entities);
        final List<PianoMediaPromoDbpromoEntity> result = service.findAllByCodiciPromo(codiciPromo);
        assertEquals(2, result.size());
        verify(dao, times(1)).findAllByCodiciPromo(codiciPromo);
    }

    @Test
    public void findByDataFineGreaterThan_whenNullDate_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByDataFineGreaterThan(null);
        verify(dao, never()).findByDataFineGreaterThan(any(Date.class));
    }

    @Test
    public void findByDataFineGreaterThan() {
        final PianoMediaPromoDbpromoEntity entity = mock(PianoMediaPromoDbpromoEntity.class);
        final Date date = new GregorianCalendar(2023, Calendar.JULY, 15).getTime();
        when(dao.findByDataFineGreaterThan(date)).thenReturn(Collections.singletonList(entity));
        final List<PianoMediaPromoDbpromoEntity> result = service.findByDataFineGreaterThan(date);
        assertEquals(1, result.size());
        verify(dao, times(1)).findByDataFineGreaterThan(any(Date.class));
    }
}