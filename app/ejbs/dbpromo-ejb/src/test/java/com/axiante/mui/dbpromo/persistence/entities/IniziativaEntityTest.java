package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class IniziativaEntityTest {
    @Mock
    private IniziativaStatoEntity stato;

    @Mock
    private PromozioneTestataEntity testata;

    @Mock
    private TotalizzatoriEntity totalizzatore;

    @Rule
    public final ExpectedException ex = ExpectedException.none();

    private IniziativaEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new IniziativaEntity();
    }

    @Test
    public void getUuid_whenAlreadySetted_shouldReturnSameValue() {
        entity.setUuid("uuid");
        assertEquals("uuid", entity.getUuid());
    }

    @Test
    public void getUuid() {
        assertNotNull(entity.getUuid());
    }

    @Test
    public void addStato_whenNullStato_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addStato(null);
        verify(stato, never()).setIniziativa(any(IniziativaEntity.class));
    }

    @Test
    public void addStato_whenStatiNull() {
        entity.setStati(null);
        doNothing().when(stato).setIniziativa(entity);
        entity.addStato(stato);
        verify(stato, times(1)).setIniziativa(entity);
    }

    @Test
    public void addStato() {
        entity.setStati(new HashSet<>());
        doNothing().when(stato).setIniziativa(entity);
        entity.addStato(stato);
        verify(stato, times(1)).setIniziativa(entity);
    }

    @Test
    public void removeStato_whenNullStato_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeStato(null);
    }

    @Test
    public void removeStato_whenStatiNull() {
        entity.setStati(null);
        doNothing().when(stato).setIniziativa(null);
        entity.removeStato(stato);
        verify(stato, times(1)).setIniziativa(null);
    }

    @Test
    public void removeStato() {
        entity.setStati(new HashSet<>());
        doNothing().when(stato).setIniziativa(null);
        entity.removeStato(stato);
        verify(stato, times(1)).setIniziativa(null);
    }

    @Test
    public void addTestata_whenNullTestata_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addTestata(null);
        verify(testata, never()).setIniziativa(any(IniziativaEntity.class));
    }

    @Test
    public void addTestata_whenPromozioniNull() {
        entity.setPromozioni(null);
        doNothing().when(testata).setIniziativa(entity);
        entity.addTestata(testata);
        verify(testata, times(1)).setIniziativa(entity);
    }

    @Test
    public void addTestata() {
        entity.setPromozioni(new HashSet<>());
        doNothing().when(testata).setIniziativa(entity);
        entity.addTestata(testata);
        verify(testata, times(1)).setIniziativa(entity);
    }

    @Test
    public void removeTestata_whenNullTestata_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeTestata(null);
    }

    @Test
    public void removeTestata_whenPromozioniNull() {
        entity.setPromozioni(null);
        doNothing().when(testata).setIniziativa(null);
        entity.removeTestata(testata);
        verify(testata, times(1)).setIniziativa(null);
    }

    @Test
    public void removeTestata() {
        entity.setPromozioni(new HashSet<>());
        doNothing().when(testata).setIniziativa(null);
        entity.removeTestata(testata);
        verify(testata, times(1)).setIniziativa(null);
    }

    @Test
    public void addTotalizzatore_whenTotalizzatoriNull() {
        entity.setTotalizzatori(null);
        doNothing().when(totalizzatore).setIniziativa(entity);
        entity.addTotalizzatore(totalizzatore);
        verify(totalizzatore, times(1)).setIniziativa(entity);
    }

    @Test
    public void addTotalizzatore() {
        entity.setTotalizzatori(new HashSet<>());
        doNothing().when(totalizzatore).setIniziativa(entity);
        entity.addTotalizzatore(totalizzatore);
        verify(totalizzatore, times(1)).setIniziativa(entity);
    }

    @Test
    public void removeTotalizzatore_whenTotalizzatoriNull() {
        entity.setTotalizzatori(null);
        doNothing().when(totalizzatore).setIniziativa(null);
        entity.removeTotalizzatore(totalizzatore);
        verify(totalizzatore, times(1)).setIniziativa(null);
    }

    @Test
    public void removeTotalizzatore() {
        entity.setTotalizzatori(new HashSet<>());
        doNothing().when(totalizzatore).setIniziativa(null);
        entity.removeTotalizzatore(totalizzatore);
        verify(totalizzatore, times(1)).setIniziativa(null);
    }
}