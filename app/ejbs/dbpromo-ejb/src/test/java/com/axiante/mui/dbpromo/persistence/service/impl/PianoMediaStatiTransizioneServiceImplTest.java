package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaStatiTransizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaStatiTransizioneServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PianoMediaStatiTransizioneEntity entity1;

    @Mock
    private PianoMediaStatiTransizioneEntity entity2;

    @Mock
    private PianoMediaEntity pianoMedia;

    @Mock
    private StatoPromozioneEntity fromStatus;

    @Mock
    private StatoPromozioneEntity toStatus;

    @Mock
    private PianoMediaStatiTransizioneDAO dao;

    @InjectMocks
    private PianoMediaStatiTransizioneServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findByIdAndStatus(1L, 1L)).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findAllNotAutomaticByIdAndStatus(1L, 1L)).thenReturn(Collections.singletonList(entity1));
        when(dao.findAllAutomaticByIdAndStatus(1L, 1L)).thenReturn(Collections.singletonList(entity2));
        when(dao.findByPianoAndStatuses(pianoMedia, fromStatus, toStatus)).thenReturn(entity1);
        when(dao.findByPianoAndStatusesAndFlagAutomatico(pianoMedia, fromStatus, toStatus, true))
                .thenReturn(entity2);
        when(pianoMedia.getId()).thenReturn(1L);
    }

    @Test
    public void findByIdAndStatus() {
        final List<PianoMediaStatiTransizioneEntity> result = service.findByIdAndStatus(1L, 1L);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findByIdAndStatus(1L, 1L);
    }

    @Test
    public void findAllNotAutomaticByIdAndStatus() {
        final List<PianoMediaStatiTransizioneEntity> result = service.findAllNotAutomaticByIdAndStatus(1L, 1L);
        assertEquals(1, result.size());
        assertTrue(result.contains(entity1));
        assertFalse(result.contains(entity2));
        verify(dao, times(1)).findAllNotAutomaticByIdAndStatus(1L, 1L);
    }

    @Test
    public void findAllAutomaticByIdAndStatus() {
        final List<PianoMediaStatiTransizioneEntity> result = service.findAllAutomaticByIdAndStatus(1L, 1L);
        assertEquals(1, result.size());
        assertFalse(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findAllAutomaticByIdAndStatus(1L, 1L);
    }

    @Test
    public void findByPianoMedia_whenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByPianoMedia(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByPianoMedia_whenNoLastStatus_shouldReturnEmptyList() {
        PianoMediaStatoEntity pianoMediaStato = mock(PianoMediaStatoEntity.class);
        when(pianoMedia.getPianoMediaStati()).thenReturn(Collections.singleton(pianoMediaStato));
        when(pianoMediaStato.getDataFineStato()).thenReturn(new Date());
        final List<PianoMediaStatiTransizioneEntity> result = service.findByPianoMedia(pianoMedia);
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByPianoMedia_whenLastStatusButNoTransizioni_shouldReturnEmptyList() {
        PianoMediaStatoEntity pianoMediaStato = mock(PianoMediaStatoEntity.class);
        StatoPromozioneEntity stato = mock(StatoPromozioneEntity.class);
        when(pianoMedia.getPianoMediaStati()).thenReturn(Collections.singleton(pianoMediaStato));
        when(pianoMediaStato.getStato()).thenReturn(stato);
        when(stato.getId()).thenReturn(42L);
        final List<PianoMediaStatiTransizioneEntity> result = service.findByPianoMedia(pianoMedia);
        assertTrue(result.isEmpty());
        verify(dao, times(1)).findByIdAndStatus(1L, 42L);
    }

    @Test
    public void findByPianoMedia_whenLastStatusAndTransizioni_shouldReturnList() {
        PianoMediaStatoEntity pianoMediaStato = mock(PianoMediaStatoEntity.class);
        StatoPromozioneEntity stato = mock(StatoPromozioneEntity.class);
        when(pianoMedia.getPianoMediaStati()).thenReturn(Collections.singleton(pianoMediaStato));
        when(pianoMediaStato.getStato()).thenReturn(stato);
        when(stato.getId()).thenReturn(1L);
        final List<PianoMediaStatiTransizioneEntity> result = service.findByPianoMedia(pianoMedia);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findByIdAndStatus(1L, 1L);
    }

    @Test
    public void findByPianoAndStatuses_whenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByPianoAndStatuses(null, fromStatus, toStatus);
        verify(dao, never()).findByPianoAndStatuses(any(PianoMediaEntity.class), any(StatoPromozioneEntity.class), any(StatoPromozioneEntity.class));
    }

    @Test
    public void findByPianoAndStatuses_whenNullFromStatus_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByPianoAndStatuses(pianoMedia, null, toStatus);
        verify(dao, never()).findByPianoAndStatuses(any(PianoMediaEntity.class), any(StatoPromozioneEntity.class), any(StatoPromozioneEntity.class));
    }

    @Test
    public void findByPianoAndStatuses_whenNullToStatus_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByPianoAndStatuses(pianoMedia, fromStatus, null);
        verify(dao, never()).findByPianoAndStatuses(any(PianoMediaEntity.class), any(StatoPromozioneEntity.class), any(StatoPromozioneEntity.class));
    }

    @Test
    public void findByPianoAndStatuses() {
        final PianoMediaStatiTransizioneEntity result = service.findByPianoAndStatuses(pianoMedia, fromStatus, toStatus);
        assertEquals(entity1, result);
        verify(dao, times(1)).findByPianoAndStatuses(pianoMedia, fromStatus, toStatus);
    }

    @Test
    public void findManualiByPianoAndStatuses() {
        final PianoMediaStatiTransizioneEntity result = service.findManualiByPianoAndStatuses(pianoMedia, fromStatus, toStatus, true);
        assertEquals(entity2, result);
        verify(dao, times(1)).findByPianoAndStatusesAndFlagAutomatico(pianoMedia, fromStatus, toStatus, true);
    }
}