package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CfgStatiCanaleConsentEntityTest {
    @Mock
    private CfgAzioniEntity azione;

    private CfgStatiCanaleConsentEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new CfgStatiCanaleConsentEntity();
    }

    @Test
    public void addAzione_whenAzioniNull() {
        when(azione.getCanali()).thenReturn(Stream.of(entity).collect(Collectors.toSet()));
        entity.setAzioni(null);
        entity.addAzione(azione);
        verify(azione, times(1)).getCanali();
    }

    @Test
    public void addAzione() {
        when(azione.getCanali()).thenReturn(Stream.of(entity).collect(Collectors.toSet()));
        entity.setAzioni(new HashSet<>());
        entity.addAzione(azione);
        verify(azione, times(1)).getCanali();
    }

    @Test
    public void removeAzione_whenAzioniNull() {
        entity.setAzioni(null);
        assertNull(entity.removeAzione(azione));
        verify(azione, never()).getCanali();
    }

    @Test
    public void removeAzione_whenAzioneNotPresent() {
        entity.setAzioni(new HashSet<>());
        assertNull(entity.removeAzione(azione));
        verify(azione, never()).getCanali();
    }

    @Test
    public void removeAzione() {
        entity.setAzioni(Stream.of(azione).collect(Collectors.toSet()));
        when(azione.getCanali()).thenReturn(Stream.of(entity).collect(Collectors.toSet()));
        assertEquals(azione, entity.removeAzione(azione));
        verify(azione, times(1)).getCanali();
    }
}