package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgIdMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgIdMessaggiEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CfgIdMessaggiServiceImplTest {
    @Mock
    private CfgIdMessaggiDAO dao;

    @Mock
    private CfgIdMessaggiEntity entity1;

    @Mock
    private CfgIdMessaggiEntity entity2;

    @InjectMocks
    private CfgIdMessaggiServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio(1L, "M1", "D1", 1))
                .thenReturn(entity1);
        when(dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M1", "D1"))
                .thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio() {
        final CfgIdMessaggiEntity result = service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio(1L, "M1", "D1", 1);
        assertEquals(entity1, result);
        verify(dao, times(1))
                .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio(1L, "M1", "D1", 1);
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_whenCodiceCanaleNull() {
        final List<CfgIdMessaggiEntity> result = service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(null, "M1", "D1");
        assertTrue(result.isEmpty());
        verify(dao, never()).findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(anyLong(), anyString(), anyString());
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_whenCodiceMeccanicaNull() {
        final List<CfgIdMessaggiEntity> result = service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, null, "D1");
        assertTrue(result.isEmpty());
        verify(dao, never()).findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(anyLong(), anyString(), anyString());
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_whenCodiceDispositivoNull() {
        final List<CfgIdMessaggiEntity> result = service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M1", null);
        assertTrue(result.isEmpty());
        verify(dao, never()).findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(anyLong(), anyString(), anyString());
    }

    @Test
    public void findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo() {
        final List<CfgIdMessaggiEntity> result = service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M1", "D1");
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1))
                .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "M1", "D1");
    }
}