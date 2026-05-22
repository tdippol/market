package com.axiante.mui.business;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaStatoUtilsTest {
    @Mock
    private PianoMediaEntity pianoMedia;

    @InjectMocks
    private PianoMediaStatoUtils utils = new PianoMediaStatoUtils();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private StatoPromozioneEntity statoPromo00 = mockStatoPromo("00");
    private StatoPromozioneEntity statoPromo30 = mockStatoPromo("30");
    private StatoPromozioneEntity statoPromo300 = mockStatoPromo("300");
    private StatoPromozioneEntity statoPromo310 = mockStatoPromo("310");
    private StatoPromozioneEntity statoPromo320 = mockStatoPromo("320");
    private StatoPromozioneEntity statoPromo400 = mockStatoPromo("400");
    private StatoPromozioneEntity statoPromo410 = mockStatoPromo("410");
    private StatoPromozioneEntity statoPromo500 = mockStatoPromo("500");
    private StatoPromozioneEntity statoPromoFake = mockStatoPromo("FAKE");
    private PianoMediaStatoEntity stato00 = mockPianoMediaStato(statoPromo00);
    private PianoMediaStatoEntity stato30 = mockPianoMediaStato(statoPromo30);
    private PianoMediaStatoEntity stato300 = mockPianoMediaStato(statoPromo300);
    private PianoMediaStatoEntity stato310 = mockPianoMediaStato(statoPromo310);
    private PianoMediaStatoEntity stato320 = mockPianoMediaStato(statoPromo320);
    private PianoMediaStatoEntity stato400 = mockPianoMediaStato(statoPromo400);
    private PianoMediaStatoEntity stato410 = mockPianoMediaStato(statoPromo410);
    private PianoMediaStatoEntity stato500 = mockPianoMediaStato(statoPromo500);
    private PianoMediaStatoEntity statoFake = mockPianoMediaStato(statoPromoFake);
    private PianoMediaPianificazioneDettaglioEntity dettaglio1 = mockPianoMediaDettaglio(statoPromo310);
    private PianoMediaPianificazioneDettaglioEntity dettaglio2 = mockPianoMediaDettaglio(statoPromo320);
    private PianoMediaPianificazioneDettaglioEntity dettaglio3 = mockPianoMediaDettaglio(statoPromo300);
    private PianoMediaPianificazioneDettaglioEntity dettaglio4 = mockPianoMediaDettaglio(null);

    @Test
    public void getStato_whenCodice00or500or400or410_shouldReturnStato() {
        when(pianoMedia.getCurrentStatus()).thenReturn(stato00);
        assertEquals(statoPromo00, utils.getStato(pianoMedia));
        when(pianoMedia.getCurrentStatus()).thenReturn(stato400);
        assertEquals(statoPromo400, utils.getStato(pianoMedia));
        when(pianoMedia.getCurrentStatus()).thenReturn(stato410);
        assertEquals(statoPromo410, utils.getStato(pianoMedia));
        when(pianoMedia.getCurrentStatus()).thenReturn(stato500);
        assertEquals(statoPromo500, utils.getStato(pianoMedia));
    }

    @Test
    public void getStato_whenCodiceNot00or500or400or410_shouldCalculateStatoFromDettagli() {
        when(pianoMedia.getCurrentStatus()).thenReturn(stato300);
        when(pianoMedia.getDettagliPianificazione()).thenReturn(Collections.singleton(dettaglio1));
        assertEquals(statoPromo310, utils.getStato(pianoMedia));
        when(pianoMedia.getDettagliPianificazione()).thenReturn(Collections.singleton(dettaglio2));
        assertEquals(statoPromo320, utils.getStato(pianoMedia));
        when(pianoMedia.getDettagliPianificazione()).thenReturn(Collections.singleton(dettaglio3));
        assertEquals(statoPromo300, utils.getStato(pianoMedia));
        when(dettaglio4.getPianoMedia()).thenReturn(pianoMedia);
        when(pianoMedia.getDettagliPianificazione()).thenReturn(Collections.singleton(dettaglio4));
        assertEquals(statoPromo300, utils.getStato(pianoMedia));
    }

    @Test
    public void calcolaStato_givenPianoMediaWithStato00or30or400or410or500_shouldReturnStato() {
        when(pianoMedia.getCurrentStatus()).thenReturn(stato00);
        assertEquals(statoPromo00, utils.calcolaStato(pianoMedia));
        when(pianoMedia.getCurrentStatus()).thenReturn(stato30);
        assertEquals(statoPromo30, utils.calcolaStato(pianoMedia));
        when(pianoMedia.getCurrentStatus()).thenReturn(stato400);
        assertEquals(statoPromo400, utils.calcolaStato(pianoMedia));
        when(pianoMedia.getCurrentStatus()).thenReturn(stato410);
        assertEquals(statoPromo410, utils.calcolaStato(pianoMedia));
        when(pianoMedia.getCurrentStatus()).thenReturn(stato500);
        assertEquals(statoPromo500, utils.calcolaStato(pianoMedia));
    }

    @Test
    public void calcolaStato_givenPianoMediaWithStatoNot00or30or400or410or500_shouldReturnStatoFromDettagli() {
        when(pianoMedia.getCurrentStatus()).thenReturn(stato300);
        when(pianoMedia.getDettagliPianificazione()).thenReturn(Collections.singleton(dettaglio1));
        assertEquals(statoPromo310, utils.calcolaStato(pianoMedia));
    }

    @Test
    public void calcolaStato_givenListOfDettagli_shouldReturnFirst320() {
        List<PianoMediaPianificazioneDettaglioEntity> dettagli = Stream.of(dettaglio1, dettaglio2, dettaglio3)
                .collect(Collectors.toList());
        assertEquals(statoPromo320, utils.calcolaStato(dettagli));
    }

    @Test
    public void cambiaStato_givenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.cambiaStato(null, statoPromo30, "junit");
    }

    @Test
    public void cambiaStato_givenNullStatoPromo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.cambiaStato(pianoMedia, null, "junit");
    }

    @Test
    public void cambiaStato_givenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.cambiaStato(pianoMedia, statoPromo300, null);
    }

    @Test
    public void cambiaStato() {
        when(pianoMedia.getCurrentStatus()).thenReturn(stato300);
        when(pianoMedia.getDettagliPianificazione()).thenReturn(Collections.singleton(dettaglio3));
        when(pianoMedia.removePianoMediaStato(stato300)).thenReturn(stato300);
        pianoMedia = utils.cambiaStato(pianoMedia, statoPromo310, "junit");
        verify(stato300, times(1)).setDataFineStato(any(Date.class));
        verify(pianoMedia, times(2)).addPianoMediaStato(any(PianoMediaStatoEntity.class));
        verify(dettaglio3, times(1)).setStato(statoPromo310);
    }

    @Test
    public void cambiaStato_withPropagate_givenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.cambiaStato(null, statoPromo30, "junit", true);
    }

    @Test
    public void cambiaStato_withPropagate_givenNullStatoPromo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.cambiaStato(pianoMedia, null, "junit", true);
    }

    @Test
    public void cambiaStato_withPropagate_givenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.cambiaStato(pianoMedia, statoPromo300, null, true);
    }

    @Test
    public void cambiaStato_withoutPropagate() {
        when(pianoMedia.getCurrentStatus()).thenReturn(stato300);
        when(pianoMedia.removePianoMediaStato(stato300)).thenReturn(stato300);
        pianoMedia = utils.cambiaStato(pianoMedia, statoPromo310, "junit", false);
        verify(stato300, times(1)).setDataFineStato(any(Date.class));
        verify(pianoMedia, times(2)).addPianoMediaStato(any(PianoMediaStatoEntity.class));
        verify(dettaglio3, never()).setStato(any(StatoPromozioneEntity.class));
    }

    @Test
    public void cambiaStato_whenNoChanges() {
        when(pianoMedia.getCurrentStatus()).thenReturn(stato300);
        pianoMedia = utils.cambiaStato(pianoMedia, statoPromo300, "junit", false);
        verify(pianoMedia, never()).addPianoMediaStato(any(PianoMediaStatoEntity.class));
    }

    @Test
    public void isApproveEnabled_givenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        utils.isApproveEnabled(null);
    }

    @Test
    public void isApproveEnabled_whenNoStato_shouldReturnFalse() {
        when(pianoMedia.getCurrentStatus()).thenReturn(statoFake);
        assertFalse(utils.isApproveEnabled(pianoMedia));
    }

    @Test
    public void isApproveEnabled_whenStato300or310or320_shouldReturnTrue() {
        when(pianoMedia.getDettagliPianificazione()).thenReturn(Collections.singleton(dettaglio1));
        when(pianoMedia.getCurrentStatus()).thenReturn(stato300);
        assertTrue(utils.isApproveEnabled(pianoMedia));
        when(pianoMedia.getCurrentStatus()).thenReturn(stato310);
        assertTrue(utils.isApproveEnabled(pianoMedia));
        when(pianoMedia.getCurrentStatus()).thenReturn(stato320);
        assertTrue(utils.isApproveEnabled(pianoMedia));
    }

    @Test
    public void isApproveEnabled_whenStatoNot300or310or320_shouldReturnFalse() {
        when(pianoMedia.getCurrentStatus()).thenReturn(stato500);
        assertFalse(utils.isApproveEnabled(pianoMedia));
    }

    private StatoPromozioneEntity mockStatoPromo(String codice) {
        final StatoPromozioneEntity statoPromo = mock(StatoPromozioneEntity.class);
        when(statoPromo.getCodiceStato()).thenReturn(codice);
        return statoPromo;
    }

    private PianoMediaStatoEntity mockPianoMediaStato(StatoPromozioneEntity statoPromo) {
        PianoMediaStatoEntity pianoMediaStato = mock(PianoMediaStatoEntity.class);
        when(pianoMediaStato.getStato()).thenReturn(statoPromo);
        return pianoMediaStato;
    }

    private PianoMediaPianificazioneDettaglioEntity mockPianoMediaDettaglio(StatoPromozioneEntity statoPromo) {
        PianoMediaPianificazioneDettaglioEntity dettaglio = mock(PianoMediaPianificazioneDettaglioEntity.class);
        when(dettaglio.getStato()).thenReturn(statoPromo);
        return dettaglio;
    }
}