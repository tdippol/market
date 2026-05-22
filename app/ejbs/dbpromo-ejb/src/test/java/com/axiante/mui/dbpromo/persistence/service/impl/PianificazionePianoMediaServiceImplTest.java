package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianificazionePianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianificazionePianoMediaServiceImplTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PianificazionePianoMediaDAO dao;

    @InjectMocks
    @Spy
    private PianificazionePianoMediaServiceImpl service;

    @Mock
    private PianoMediaEntity pianoMedia;

    @Mock
    private PianificazionePianoMediaEntity ppm1;

    @Mock
    private PianificazionePianoMediaEntity ppm2;

    @Before
    public void setUp() throws Exception {
        final List<PianificazionePianoMediaEntity> pianificazioni = Arrays.asList(ppm1, ppm2);
        when(dao.findByPianoMedia(pianoMedia)).thenReturn(pianificazioni);
        when(dao.findAttiviByPianoMedia(pianoMedia)).thenReturn(pianificazioni);
    }

    @Test
    public void findByPianoMedia_givenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByPianoMedia(null);
        verify(dao, never()).findByPianoMedia(any());
    }

    @Test
    public void findByPianoMedia() {
        final List<PianificazionePianoMediaEntity> pianificazioni = service.findByPianoMedia(pianoMedia);
        assertEquals(2, pianificazioni.size());
        verify(dao, times(1)).findByPianoMedia(pianoMedia);
    }

    @Test
    public void remove() {
        final PianificazionePianoMediaEntity ppm1 = mock(PianificazionePianoMediaEntity.class);
        final PianificazionePianoMediaEntity ppm2 = mock(PianificazionePianoMediaEntity.class);
        Set<PianificazionePianoMediaEntity> entities = Stream.of(ppm1, ppm2).collect(Collectors.toSet());
        service.remove(entities);
        verify(dao, times(1)).remove(entities, 100);
        verify(service, times(1)).remove(entities, 100);
    }

    @Test
    public void findAttiviByPianoMedia_whenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAttiviByPianoMedia(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAttiviByPianoMedia() {
        final List<PianificazionePianoMediaEntity> result = service.findAttiviByPianoMedia(pianoMedia);
        assertEquals(2, result.size());
        assertTrue(result.contains(ppm1));
        assertTrue(result.contains(ppm2));
        verify(dao, times(1)).findAttiviByPianoMedia(pianoMedia);
    }
}