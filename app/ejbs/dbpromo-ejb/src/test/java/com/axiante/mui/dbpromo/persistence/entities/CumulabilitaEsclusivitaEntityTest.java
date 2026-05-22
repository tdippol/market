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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CumulabilitaEsclusivitaEntityTest {
    @Mock
    private CumulabilitaBuoniEntity buono;

    private CumulabilitaEsclusivitaEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new CumulabilitaEsclusivitaEntity();
    }

    @Test
    public void addCumulabilitaBuoniEntity_whenBuoniNull() {
        doNothing().when(buono).setCumulabilitaEsclusivitaEntity(entity);
        assertTrue(entity.addCumulabilitaBuoniEntity(buono));
        verify(buono, times(1)).setCumulabilitaEsclusivitaEntity(entity);
    }

    @Test
    public void addCumulabilitaBuoniEntity() {
        entity.setCumulabilitaBuoniEntities(new HashSet<>());
        doNothing().when(buono).setCumulabilitaEsclusivitaEntity(entity);
        assertTrue(entity.addCumulabilitaBuoniEntity(buono));
        verify(buono, times(1)).setCumulabilitaEsclusivitaEntity(entity);
    }

    @Test
    public void removeCumulabilitaBuoniEntity_whenBuoniNull() {
        assertFalse(entity.removeCumulabilitaBuoniEntity(buono));
        verify(buono, never()).setCumulabilitaEsclusivitaEntity(any(CumulabilitaEsclusivitaEntity.class));
    }

    @Test
    public void removeCumulabilitaBuoniEntity_whenBuonoNotPresent() {
        entity.setCumulabilitaBuoniEntities(new HashSet<>());
        doNothing().when(buono).setCumulabilitaEsclusivitaEntity(null);
        assertFalse(entity.removeCumulabilitaBuoniEntity(buono));
        assertTrue(entity.getCumulabilitaBuoniEntities().isEmpty());
        verify(buono, times(1)).setCumulabilitaEsclusivitaEntity(null);
    }

    @Test
    public void removeCumulabilitaBuoniEntity() {
        Set<CumulabilitaBuoniEntity> buoni = Stream.of(buono).collect(Collectors.toSet());
        entity.setCumulabilitaBuoniEntities(buoni);
        doNothing().when(buono).setCumulabilitaEsclusivitaEntity(null);
        assertTrue(entity.removeCumulabilitaBuoniEntity(buono));
        assertTrue(entity.getCumulabilitaBuoniEntities().isEmpty());
        verify(buono, times(1)).setCumulabilitaEsclusivitaEntity(null);
    }
}