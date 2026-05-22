package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaControlliDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaControlliEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
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
public class PianoMediaControlliServiceImplTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PianoMediaControlliDAO dao;

    @Mock
    private PianificazionePianoMediaEntity pianificazioneMedia;

    @Mock
    private PianoMediaEntity pianoMedia;

    @Mock
    private PianoMediaControlliEntity chk1;

    @Mock
    private PianoMediaControlliEntity chk2;

    @InjectMocks
    private PianoMediaControlliServiceImpl service;

    @Before
    public void setUp() throws Exception {
        final List<PianoMediaControlliEntity> checks = Stream.of(chk1, chk2).collect(Collectors.toList());
        when(dao.findByPianificazioneMedia(pianificazioneMedia)).thenReturn(checks);
        when(dao.findByPianoMedia(pianoMedia)).thenReturn(checks);
        when(dao.findByCodiceSupportoConfigurato("CC01")).thenReturn(checks);
        when(dao.countByCodiceSupportoConfigurato("CC01")).thenReturn(2L);
    }

    @Test
    public void findByPianificazioneMedia_givenNullPianificazionePiano_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByPianificazioneMedia(null);
        verify(dao, never()).findByPianificazioneMedia(any());
    }

    @Test
    public void findByPianificazioneMedia() {
        final List<PianoMediaControlliEntity> checks = service.findByPianificazioneMedia(pianificazioneMedia);
        assertEquals(2, checks.size());
        assertTrue(checks.contains(chk1));
        assertTrue(checks.contains(chk2));
        verify(dao, times(1)).findByPianificazioneMedia(pianificazioneMedia);
    }

    @Test
    public void findByIdPianificazioniMedia_givenNullIds_shouldThrownException() {
        ex.expect(NullPointerException.class);
        service.findByIdPianificazioniMedia(null);
        verify(dao, never()).findByIdPianificazioniMedia(anyList());
    }

    @Test
    public void findByIdPianificazioniMedia_givenEmptyIds_shouldReturnEmptyList() {
        final List<PianoMediaControlliEntity> checks = service.findByIdPianificazioniMedia(Collections.emptyList());
        assertTrue(checks.isEmpty());
        verify(dao, never()).findByIdPianificazioniMedia(anyList());
    }

    @Test
    public void findByIdPianificazioniMedia() {
        final List<PianoMediaControlliEntity> controlli = Stream.of(mock(PianoMediaControlliEntity.class),
                        mock(PianoMediaControlliEntity.class), mock(PianoMediaControlliEntity.class))
                .collect(Collectors.toList());
        final List<Long> ids = Stream.of(1L, 2L).collect(Collectors.toList());
        when(dao.findByIdPianificazioniMedia(ids)).thenReturn(controlli);
        final List<PianoMediaControlliEntity> checks = service.findByIdPianificazioniMedia(ids);
        assertEquals(3, checks.size());
        verify(dao, times(1)).findByIdPianificazioniMedia(ids);
    }

    @Test
    public void findByPianoMedia_givenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByPianoMedia(null);
        verify(dao, never()).findByPianoMedia(any());
    }

    @Test
    public void findByPianoMedia() {
        final List<PianoMediaControlliEntity> checks = service.findByPianoMedia(pianoMedia);
        assertEquals(2, checks.size());
        assertTrue(checks.contains(chk1));
        assertTrue(checks.contains(chk2));
        verify(dao, times(1)).findByPianoMedia(pianoMedia);
    }

    @Test
    public void findByCodiceSupportoConfigurato_whenNullCodiceControllo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByCodiceSupportoConfigurato(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByCodiceSupportoConfigurato() {
        final List<PianoMediaControlliEntity> result = service.findByCodiceSupportoConfigurato("CC01");
        assertEquals(2, result.size());
        verify(dao, times(1)).findByCodiceSupportoConfigurato("CC01");
    }

    @Test
    public void countByCodiceSupportoConfigurato_whenNullCodiceControllo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.countByCodiceSupportoConfigurato(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void countByCodiceSupportoConfigurato() {
        assertEquals(2L, service.countByCodiceSupportoConfigurato("CC01"));
    }
}