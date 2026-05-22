package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
public class PianoMediaPianificazioneDettaglioEntityTest {
    @Mock
    private PianificazioneAnagraficaPianoMediaEntity pianificazione;

    @Rule
    public final ExpectedException ex = ExpectedException.none();

    private PianoMediaPianificazioneDettaglioEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new PianoMediaPianificazioneDettaglioEntity();
    }

    @Test
    public void addPianificazioneAnagrafica_whenNullPianificazioneAnagrafica_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addPianificazioneAnagrafica(null);
    }

    @Test
    public void addPianificazioneAnagrafica_whenNullPianificazioni() {
        entity.setPianificazioniAnagrafiche(null);
        doNothing().when(pianificazione).setPianificazioneDettaglio(entity);
        assertEquals(pianificazione, entity.addPianificazioneAnagrafica(pianificazione));
        verify(pianificazione, times(1)).setPianificazioneDettaglio(entity);
    }

    @Test
    public void addPianificazioneAnagrafica() {
        entity.setPianificazioniAnagrafiche(new HashSet<>());
        doNothing().when(pianificazione).setPianificazioneDettaglio(entity);
        assertEquals(pianificazione, entity.addPianificazioneAnagrafica(pianificazione));
        verify(pianificazione, times(1)).setPianificazioneDettaglio(entity);
    }

    @Test
    public void removePianificazioneAnagrafica_whenNullPianificazioneAnagrafica_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removePianificazioneAnagrafica(null);
    }

    @Test
    public void removePianificazioneAnagrafica_whenNullPianificazioni() {
        entity.setPianificazioniAnagrafiche(null);
        assertEquals(pianificazione, entity.removePianificazioneAnagrafica(pianificazione));
        verify(pianificazione, never()).setPianificazioneDettaglio(null);
    }

    @Test
    public void removePianificazioneAnagrafica() {
        Set<PianificazioneAnagraficaPianoMediaEntity> pianificazioni = Stream.of(pianificazione).collect(Collectors.toSet());
        when(pianificazione.getPianificazioneDettaglio()).thenReturn(entity);
        entity.setPianificazioniAnagrafiche(pianificazioni);
        assertEquals(pianificazione, entity.removePianificazioneAnagrafica(pianificazione));
        verify(pianificazione, times(1)).setPianificazioneDettaglio(null);
    }
}