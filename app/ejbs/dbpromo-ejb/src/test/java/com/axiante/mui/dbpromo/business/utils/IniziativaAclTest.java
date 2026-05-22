package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IniziativaAclTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private IniziativaEntity iniziativa;

    private StatoPromozioneEntity statoPubblicata;
    private StatoPromozioneEntity statoNonPubblicata;
    private StatoPromozioneEntity statoCancellata;
    private Date date = new GregorianCalendar(2022, Calendar.AUGUST, 10).getTime();

    @Before
    public void setUp() throws Exception {
        statoNonPubblicata = mockStato("10", "Non Pubblicata");
        statoPubblicata = mockStato("20", "Pubblicata");
        statoCancellata = mockStato("00", "Cancellata");
    }

    @Test
    public void getStatoCorrente_givenNullIniziativa_shouldThrowException() {
        ex.expect(NullPointerException.class);
        IniziativaAcl.getStatoCorrente(null);
    }

    @Test
    public void getStatoCorrente_givenIniziativaWithOnlyOneStatusWithNullDataFine_shouldReturnThatStatus() {
        final Set<IniziativaStatoEntity> stati = new HashSet<>();
        stati.add(mockIniziativaStato(statoNonPubblicata, date));
        stati.add(mockIniziativaStato(statoPubblicata, null));
        when(iniziativa.getStati()).thenReturn(stati);
        final StatoPromozioneEntity statoCorrente = IniziativaAcl.getStatoCorrente(iniziativa);
        assertEquals("20", statoCorrente.getCodiceStato());
        assertEquals("Pubblicata", statoCorrente.getDescrizione());
    }

    @Test
    public void getStatoCorrente_givenIniziativaWithTwoOrMoreStatusWithNullDataFine_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        final Set<IniziativaStatoEntity> stati = new HashSet<>();
        stati.add(mockIniziativaStato(statoNonPubblicata, date));
        stati.add(mockIniziativaStato(statoPubblicata, null));
        stati.add(mockIniziativaStato(statoPubblicata, null));
        when(iniziativa.getStati()).thenReturn(stati);
        IniziativaAcl.getStatoCorrente(iniziativa);
    }

    @Test
    public void getLastStatus_givenNullIniziativa_shouldThrowException() {
        ex.expect(NullPointerException.class);
        IniziativaAcl.getLastStatus(null);
    }

    @Test
    public void getLastStatus_givenIniziativaWithEmptyStati_shouldReturnNull() {
        when(iniziativa.getStati()).thenReturn(Collections.emptySet());
        assertNull(IniziativaAcl.getLastStatus(iniziativa));
    }

    @Test
    public void getStatoCorrente_givenIniziativaWitEmptyStati_shouldReturnNull() {
        when(iniziativa.getStati()).thenReturn(Collections.emptySet());
        assertNull(IniziativaAcl.getStatoCorrente(iniziativa));
    }

    @Test
    public void isPublished_givenNullIniziativa_shouldThrowException() {
        ex.expect(NullPointerException.class);
        IniziativaAcl.isPublished(null);
    }

    @Test
    public void isPublished_givenIniziativaWithEmptyStati_shouldReturnFalse() {
        when(iniziativa.getStati()).thenReturn(Collections.emptySet());
        assertFalse(IniziativaAcl.isPublished(iniziativa));
    }

    @Test
    public void isPublished_givenIniziativaWithStatoNot20_shouldReturnFalse() {
        final Set<IniziativaStatoEntity> stati = new HashSet<>();
        stati.add(mockIniziativaStato(statoNonPubblicata, null));
        when(iniziativa.getStati()).thenReturn(stati);
        assertFalse(IniziativaAcl.isPublished(iniziativa));
    }

    @Test
    public void isPublished_givenIniziativaWithStato20_shouldReturnTrue() {
        final Set<IniziativaStatoEntity> stati = new HashSet<>();
        stati.add(mockIniziativaStato(statoPubblicata, null));
        when(iniziativa.getStati()).thenReturn(stati);
        assertTrue(IniziativaAcl.isPublished(iniziativa));
    }

    @Test
    public void isDeleted_givenNullIniziativa_shouldThrowException() {
        ex.expect(NullPointerException.class);
        IniziativaAcl.isDeleted(null);
    }

    @Test
    public void isDeleted_givenIniziativaWithEmptyStati_shouldReturnFalse() {
        when(iniziativa.getStati()).thenReturn(Collections.emptySet());
        assertFalse(IniziativaAcl.isDeleted(iniziativa));
    }

    @Test
    public void isDeleted_givenIniziativaWithStatoNot00_shouldReturnFalse() {
        final Set<IniziativaStatoEntity> stati = new HashSet<>();
        stati.add(mockIniziativaStato(statoPubblicata, null));
        when(iniziativa.getStati()).thenReturn(stati);
        assertFalse(IniziativaAcl.isDeleted(iniziativa));
    }

    @Test
    public void isDeleted_givenIniziativaWithStato00_shouldReturnTrue() {
        final Set<IniziativaStatoEntity> stati = new HashSet<>();
        stati.add(mockIniziativaStato(statoCancellata, null));
        when(iniziativa.getStati()).thenReturn(stati);
        assertTrue(IniziativaAcl.isDeleted(iniziativa));
    }

    @Test
    public void canBePublished_givenNullIniziativa_shouldThrowException() {
        ex.expect(NullPointerException.class);
        IniziativaAcl.canBePublished(null);
    }

    @Test
    public void canBePublished_givenIniziativaPublished_shouldReturnFalse() {
        final Set<IniziativaStatoEntity> stati = new HashSet<>();
        stati.add(mockIniziativaStato(statoPubblicata, null));
        when(iniziativa.getStati()).thenReturn(stati);
        assertFalse(IniziativaAcl.canBePublished(iniziativa));
    }

    @Test
    public void canBePublished_givenIniziativaNotPublishedAndTotalizzatoreNotAligned_shouldReturnFalse() {
        final Set<IniziativaStatoEntity> stati = new HashSet<>();
        stati.add(mockIniziativaStato(statoNonPubblicata, null));
        when(iniziativa.getStati()).thenReturn(stati);
        when(iniziativa.getTotalizzatoreAllineato()).thenReturn(Boolean.FALSE);
        assertFalse(IniziativaAcl.canBePublished(iniziativa));
    }

    @Test
    public void canBePublished_givenIniziativaNotPublishedAndTotalizzatoreAligned_shouldReturnTrue() {
        final Set<IniziativaStatoEntity> stati = new HashSet<>();
        stati.add(mockIniziativaStato(statoNonPubblicata, null));
        when(iniziativa.getStati()).thenReturn(stati);
        when(iniziativa.getTotalizzatoreAllineato()).thenReturn(Boolean.TRUE);
        assertTrue(IniziativaAcl.canBePublished(iniziativa));
    }

    private IniziativaStatoEntity mockIniziativaStato(StatoPromozioneEntity stato, Date dataFine) {
        final IniziativaStatoEntity mock = mock(IniziativaStatoEntity.class);
        when(mock.getDataFineStato()).thenReturn(dataFine);
        when(mock.getStatoPromo()).thenReturn(stato);
        return mock;
    }

    private StatoPromozioneEntity mockStato(String codice, String descrizione) {
        final StatoPromozioneEntity mock = mock(StatoPromozioneEntity.class);
        when(mock.getCodiceStato()).thenReturn(codice);
        when(mock.getDescrizione()).thenReturn(descrizione);
        return mock;
    }
}