package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import com.axiante.mui.dbpromo.persistence.dao.MuiCfgCastellettoMessaggiComponentDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgCastellettoMessaggiComponentEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MuiCfgCastellettoMessaggiComponentServiceImplTest {

    @Mock
    private MuiCfgCastellettoMessaggiComponentDAO dao;

    @Spy
    @InjectMocks
    private MuiCfgCastellettoMessaggiComponentServiceImpl service;

    private List<MuiCfgCastellettoMessaggiComponentEntity> mockEntityList;
    private MuiCfgCastellettoMessaggiComponentEntity mockEntity;
    private Long codiceCanale;
    private String codiceMeccanica;
    private String codiceDispositivo;
    private MessaggiComponentsEnum componentName;

    @Before
    public void setUp() {
        mockEntityList = new ArrayList<>();
        mockEntity = new MuiCfgCastellettoMessaggiComponentEntity();
        mockEntity.setId(1L);
        mockEntityList.add(mockEntity);

        codiceCanale = 1L;
        codiceMeccanica = "MECH1";
        codiceDispositivo = "DISP1";
        componentName = MessaggiComponentsEnum.TESTO;
    }

    @Test
    public void testFindByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo() {
        // Arrange
        when(dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                codiceCanale, codiceMeccanica, codiceDispositivo))
                .thenReturn(mockEntityList);

        // Act
        List<MuiCfgCastellettoMessaggiComponentEntity> result =
                service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        codiceCanale, codiceMeccanica, codiceDispositivo);

        // Assert
        assertEquals(mockEntityList, result);
        verify(dao).findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                codiceCanale, codiceMeccanica, codiceDispositivo);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_NullCodiceCanale() {
        service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(null, codiceMeccanica, codiceDispositivo);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_NullCodiceMeccanica() {
        service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(codiceCanale, null, codiceDispositivo);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_NullCodiceDispositivo() {
        service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(codiceCanale, codiceMeccanica, null);
    }

    @Test
    public void testRemoveByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent_Success() {
        // Arrange
        when(dao.countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName))
                .thenReturn(1L);
        when(dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName))
                .thenReturn(mockEntity);
        doNothing().when(dao).remove(mockEntity);

        // Act
        boolean result = service.removeByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName);

        // Assert
        assertTrue(result);
        verify(dao).countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName);
        verify(dao).findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName);
        verify(dao).remove(mockEntity);
    }

    @Test
    public void testRemoveByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent_NoComponentFound() {
        // Arrange
        when(dao.countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName))
                .thenReturn(0L);

        // Act
        boolean result = service.removeByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName);

        // Assert
        assertFalse(result);
        verify(dao).countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName);
        verify(dao, never()).findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                any(), any(), any(), any());
        verify(dao, never()).remove(any());
    }

    @Test
    public void testRemoveByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent_MultipleComponentsFound() {
        // Arrange
        when(dao.countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName))
                .thenReturn(2L);

        // Act
        boolean result = service.removeByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName);

        // Assert
        assertFalse(result);
        verify(dao).countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName);
        verify(dao, never()).findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                any(), any(), any(), any());
        verify(dao, never()).remove(any());
    }

    @Test
    public void testRemoveByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent_ExceptionThrown() {
        // Arrange
        when(dao.countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName))
                .thenReturn(1L);
        when(dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName))
                .thenReturn(mockEntity);
        doThrow(new RuntimeException("Test exception")).when(dao).remove(mockEntity);

        // Act
        boolean result = service.removeByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName);

        // Assert
        assertFalse(result);
        verify(dao).countCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName);
        verify(dao).findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName);
        verify(dao).remove(mockEntity);
    }

    @Test
    public void testFindByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent() {
        // Arrange
        when(dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName))
                .thenReturn(mockEntity);

        // Act
        MuiCfgCastellettoMessaggiComponentEntity result =
                service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                        codiceCanale, codiceMeccanica, codiceDispositivo, componentName);

        // Assert
        assertEquals(mockEntity, result);
        verify(dao).findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndComponent(
                codiceCanale, codiceMeccanica, codiceDispositivo, componentName);
    }
}