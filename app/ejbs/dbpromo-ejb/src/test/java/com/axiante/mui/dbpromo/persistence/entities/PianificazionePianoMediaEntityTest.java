package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianificazionePianoMediaEntityTest {
    @Mock
    private PianificazioneAnagraficaPianoMediaEntity pianificazione;

    private PianificazionePianoMediaEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new PianificazionePianoMediaEntity();
    }

    @Test
    public void addPianificazioneAnagrafica_whenPianificazioniNull() {
        doNothing().when(pianificazione).setPianificazioneMedia(entity);
        entity.setAnagrafichePianificazione(null);
        assertEquals(pianificazione, entity.addPianificazioneAnagrafica(pianificazione));
        verify(pianificazione, times(1)).setPianificazioneMedia(entity);
    }

    @Test
    public void addPianificazioneAnagrafica() {
        doNothing().when(pianificazione).setPianificazioneMedia(entity);
        entity.setAnagrafichePianificazione(new HashSet<>());
        assertEquals(pianificazione, entity.addPianificazioneAnagrafica(pianificazione));
        verify(pianificazione, times(1)).setPianificazioneMedia(entity);
    }

    @Test
    public void removePianificazioneAnagrafica_whenPianificazioniNull() {
        entity.setAnagrafichePianificazione(null);
        assertEquals(pianificazione, entity.removePianificazioneAnagrafica(pianificazione));
        verify(pianificazione, never()).setPianificazioneMedia(null);
    }

    @Test
    public void removePianificazioneAnagrafica() {
        Set<PianificazioneAnagraficaPianoMediaEntity> pianificazioni = Stream.of(pianificazione).collect(Collectors.toSet());
        entity.setAnagrafichePianificazione(pianificazioni);
        doNothing().when(pianificazione).setPianificazioneMedia(null);
        when(pianificazione.getPianificazioneMedia()).thenReturn(entity);
        assertEquals(pianificazione, entity.removePianificazioneAnagrafica(pianificazione));
        verify(pianificazione, times(1)).setPianificazioneMedia(null);
    }
}