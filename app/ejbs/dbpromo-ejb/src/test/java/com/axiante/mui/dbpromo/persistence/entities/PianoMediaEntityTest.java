package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaEntityTest {
    @Mock
    private PianoMediaStatiConsentitiEntity statoConsentito;

    @Mock
    private PianoMediaStatiTransizioneEntity statoTransizione;

    @Mock
    private PianoMediaPianificazioneDettaglioEntity dettaglio;

    @Mock
    private PianoMediaStatoEntity stato;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private PianoMediaEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new PianoMediaEntity();
    }

    @Test
    public void getUuid_whenAlreadySetted_shouldReturnIt() {
        String uuid = "uuid";
        entity.setUuid(uuid);
        assertEquals(uuid, entity.getUuid());
    }

    @Test
    public void getUuid() {
        assertNotNull(entity.getUuid());
    }

    @Test
    public void addPianoMediaStato_whenStatiNull() {
        entity.setPianoMediaStati(null);
        doNothing().when(stato).setPianoMedia(entity);
        assertEquals(stato, entity.addPianoMediaStato(stato));
        verify(stato, times(1)).setPianoMedia(entity);
    }

    @Test
    public void addPianoMediaStato() {
        entity.setPianoMediaStati(new HashSet<>());
        doNothing().when(stato).setPianoMedia(entity);
        assertEquals(stato, entity.addPianoMediaStato(stato));
        verify(stato, times(1)).setPianoMedia(entity);
    }

    @Test
    public void removePianoMediaStato_whenStatiNull() {
        entity.setPianoMediaStati(null);
        doNothing().when(stato).setPianoMedia(null);
        assertEquals(stato, entity.removePianoMediaStato(stato));
        verify(stato, times(1)).setPianoMedia(null);
    }

    @Test
    public void removePianoMediaStato() {
        entity.setPianoMediaStati(new HashSet<>());
        doNothing().when(stato).setPianoMedia(null);
        assertEquals(stato, entity.removePianoMediaStato(stato));
        verify(stato, times(1)).setPianoMedia(null);
    }

    @Test
    public void addStatiConsentitiEntity_whenStatiConsentitiNull() {
        entity.setStatiConsentiti(null);
        doNothing().when(statoConsentito).setPianoMediaEntity(entity);
        assertEquals(statoConsentito, entity.addStatiConsentitiEntity(statoConsentito));
        verify(statoConsentito, times(1)).setPianoMediaEntity(entity);
    }

    @Test
    public void addStatiConsentitiEntity() {
        entity.setStatiConsentiti(new HashSet<>());
        doNothing().when(statoConsentito).setPianoMediaEntity(entity);
        assertEquals(statoConsentito, entity.addStatiConsentitiEntity(statoConsentito));
        verify(statoConsentito, times(1)).setPianoMediaEntity(entity);
    }

    @Test
    public void removeStatiConsentitiEntity() {
        entity.setStatiConsentiti(new HashSet<>());
        doNothing().when(statoConsentito).setPianoMediaEntity(null);
        assertEquals(statoConsentito, entity.removeStatiConsentitiEntity(statoConsentito));
        verify(statoConsentito, times(1)).setPianoMediaEntity(null);
    }

    @Test
    public void addStatiTransizione_whenStatiTransizioneNull() {
        entity.setStatiTransizione(null);
        doNothing().when(statoTransizione).setPianoMediaEntity(entity);
        assertEquals(statoTransizione, entity.addStatiTransizione(statoTransizione));
        verify(statoTransizione, times(1)).setPianoMediaEntity(entity);
    }

    @Test
    public void addStatiTransizione() {
        entity.setStatiTransizione(new HashSet<>());
        doNothing().when(statoTransizione).setPianoMediaEntity(entity);
        assertEquals(statoTransizione, entity.addStatiTransizione(statoTransizione));
        verify(statoTransizione, times(1)).setPianoMediaEntity(entity);
    }

    @Test
    public void removeStatiTransizione() {
        entity.setStatiTransizione(new HashSet<>());
        doNothing().when(statoTransizione).setPianoMediaEntity(null);
        assertEquals(statoTransizione, entity.removeStatiTransizione(statoTransizione));
        verify(statoTransizione, times(1)).setPianoMediaEntity(null);
    }

    @Test
    public void addDettagliPianificazione_whenNullDettaglio_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addDettagliPianificazione(null);
    }

    @Test
    public void addDettagliPianificazione_whenDettagliNull() {
        entity.setDettagliPianificazione(null);
        assertEquals(dettaglio, entity.addDettagliPianificazione(dettaglio));
        verify(dettaglio, times(1)).setPianoMedia(entity);
    }

    @Test
    public void addDettagliPianificazione() {
        entity.setDettagliPianificazione(new HashSet<>());
        assertEquals(dettaglio, entity.addDettagliPianificazione(dettaglio));
        verify(dettaglio, times(1)).setPianoMedia(entity);
    }

    @Test
    public void removeDettagliPianificazione_whenNullDettaglio_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeDettagliPianificazione(null);
    }

    @Test
    public void removeDettagliPianificazione_whenDettagliNull() {
        entity.setDettagliPianificazione(null);
        assertEquals(dettaglio, entity.removeDettagliPianificazione(dettaglio));
        verify(dettaglio, never()).setPianoMedia(null);
    }

    @Test
    public void removeDettagliPianificazione() {
        Set<PianoMediaPianificazioneDettaglioEntity> dettagli = Stream.of(dettaglio).collect(Collectors.toSet());
        entity.setDettagliPianificazione(dettagli);
        assertEquals(dettaglio, entity.removeDettagliPianificazione(dettaglio));
        verify(dettaglio, times(1)).setPianoMedia(null);
    }

    @Test
    public void getCurrentStatus_withStatiNull() {
        entity.setPianoMediaStati(null);
        assertNull(entity.getCurrentStatus());
    }

    @Test
    public void getCurrentStatus_withoutStati() {
        entity.setPianoMediaStati(new HashSet<>());
        assertNull(entity.getCurrentStatus());
    }

    @Test
    public void getCurrentStatus_withAllStatiWithDataFine() {
        when(stato.getDataFineStato()).thenReturn(mock(Date.class));
        entity.setPianoMediaStati(Collections.singleton(stato));
        assertNull(entity.getCurrentStatus());
    }

    @Test
    public void getCurrentStatus() {
        when(stato.getDataFineStato()).thenReturn(null);
        entity.setPianoMediaStati(Collections.singleton(stato));
        assertEquals(stato, entity.getCurrentStatus());
    }

    @Test
    public void getDescrizioneEstesa_withDataInizio() {
        Date dataInizio = new GregorianCalendar(2025, GregorianCalendar.JULY, 30).getTime();
        entity.setDataInizio(dataInizio);
        entity.setDescrizione("FOO");
        assertEquals("mer 30-07-25 FOO", entity.getDescrizioneEstesa());
    }

    @Test
    public void getDescrizioneEstesa_withoutDataInizio() {
        entity.setDescrizione("FOO");
        assertEquals(" FOO", entity.getDescrizioneEstesa());
    }
}