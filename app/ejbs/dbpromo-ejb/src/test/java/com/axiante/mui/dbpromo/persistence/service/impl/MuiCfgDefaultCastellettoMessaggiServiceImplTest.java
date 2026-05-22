package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiCfgDefaultCastellettoMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MuiCfgDefaultCastellettoMessaggiServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private MuiCfgDefaultCastellettoMessaggiDAO dao;

    @Spy
    @InjectMocks
    private MuiCfgDefaultCastellettoMessaggiServiceImpl service;

    private List<MuiCfgDefaultCastellettoMessaggiEntity> mockEntityList;

    @Before
    public void setUp() {
        mockEntityList = new ArrayList<>();
        // Add some test entities to the list
        MuiCfgDefaultCastellettoMessaggiEntity entity1 = new MuiCfgDefaultCastellettoMessaggiEntity();
        MuiCfgDefaultCastellettoMessaggiEntity entity2 = new MuiCfgDefaultCastellettoMessaggiEntity();
        mockEntityList.add(entity1);
        mockEntityList.add(entity2);
    }

    @Test
    public void testFindByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo() {
        // Arrange
        Long codiceCanale = 1L;
        String codiceMeccanica = "MECH1";
        String codiceDispositivo = "DISP1";
        when(dao.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                codiceCanale, codiceMeccanica, codiceDispositivo))
                .thenReturn(mockEntityList);

        // Act
        List<MuiCfgDefaultCastellettoMessaggiEntity> result =
                service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        codiceCanale, codiceMeccanica, codiceDispositivo);

        // Assert
        assertEquals(mockEntityList, result);
        verify(dao)
                .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        codiceCanale, codiceMeccanica, codiceDispositivo);
    }

    @Test
    public void testFindByCodiceCanaleAndCodiceMeccanica() {
        // Arrange
        Long codiceCanale = 1L;
        String codiceMeccanica = "MECH1";
        when(dao.findByCodiceCanaleAndCodiceMeccanica(codiceCanale, codiceMeccanica))
                .thenReturn(mockEntityList);

        // Act
        List<MuiCfgDefaultCastellettoMessaggiEntity> result =
                service.findByCodiceCanaleAndCodiceMeccanica(codiceCanale, codiceMeccanica);

        // Assert
        assertEquals(mockEntityList, result);
        verify(dao).findByCodiceCanaleAndCodiceMeccanica(codiceCanale, codiceMeccanica);
    }

    @Test
    public void testFindMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo() {
        // Arrange
        Long codiceCanale = 1L;
        String codiceMeccanica = "MECH1";
        String codiceDispositivo = "DISP1";
        Short expectedMaxSequenza = 10;
        when(dao.findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                codiceCanale, codiceMeccanica, codiceDispositivo))
                .thenReturn(expectedMaxSequenza);

        // Act
        Short result =
                service.findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        codiceCanale, codiceMeccanica, codiceDispositivo);

        // Assert
        assertEquals(expectedMaxSequenza, result);
        verify(dao)
                .findMaxSequenzaByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        codiceCanale, codiceMeccanica, codiceDispositivo);
    }

    @Test
    public void testCountByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo() {
        // Arrange
        Long codiceCanale = 1L;
        String codiceMeccanica = "MECH1";
        String codiceDispositivo = "DISP1";
        Long expectedCount = 5L;
        when(dao.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                codiceCanale, codiceMeccanica, codiceDispositivo))
                .thenReturn(expectedCount);

        // Act
        Long result =
                service.countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        codiceCanale, codiceMeccanica, codiceDispositivo);

        // Assert
        assertEquals(expectedCount, result);
        verify(dao)
                .countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        codiceCanale, codiceMeccanica, codiceDispositivo);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_NullCodiceCanale() {
        service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(null, "MECH1", "DISP1");
    }

    @Test(expected = NullPointerException.class)
    public void testFindByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_NullCodiceMeccanica() {
        service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, null, "DISP1");
    }

    @Test(expected = NullPointerException.class)
    public void testFindByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo_NullCodiceDispositivo() {
        service.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(1L, "MECH1", null);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByCodiceCanaleAndCodiceMeccanica_NullCodiceCanale() {
        service.findByCodiceCanaleAndCodiceMeccanica(null, "MECH1");
    }

    @Test(expected = NullPointerException.class)
    public void testFindByCodiceCanaleAndCodiceMeccanica_NullCodiceMeccanica() {
        service.findByCodiceCanaleAndCodiceMeccanica(1L, null);
    }

    @Test
    public void addMessageAbove_whenNoDefaultFound() {
        when(dao.findById(1L)).thenReturn(null);
        MuiCfgDefaultCastellettoMessaggiEntity entity = mock(MuiCfgDefaultCastellettoMessaggiEntity.class);
        service.addMessageAbove(entity, 1L, "USER");
        verify(dao, never()).findAllByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndSequenzaGreaterEqualThan(
                anyLong(), anyString(), anyString(), any(Short.class));
        verify(dao, times(1)).findById(1L);
    }

    @Test
    public void addMessageAbove() {
        List<MuiCfgDefaultCastellettoMessaggiEntity> entities = new ArrayList<>();
        IntStream.range(2, 6).boxed().forEach(i -> {
            MuiCfgDefaultCastellettoMessaggiEntity e = mock(MuiCfgDefaultCastellettoMessaggiEntity.class);
            when(e.getSeqStampa()).thenReturn(i.shortValue());
            lenient().doNothing().when(e).setSeqStampa(i.shortValue());
            if (i == 1) {
                when(e.getId()).thenReturn(null);
            }
            lenient().doNothing().when(e).setCodiceUtenteInserimento(anyString());
            lenient().doNothing().when(e).setDataInserimento(any(Date.class));
            lenient().doNothing().when(e).setCodiceUtenteAggiornamento(anyString());
            lenient().doNothing().when(e).setDataAggiornamento(any(Date.class));
            entities.add(e);
        });
        MuiCfgDefaultCastellettoMessaggiEntity entity1 = mock(MuiCfgDefaultCastellettoMessaggiEntity.class);
        when(entity1.getCodiceCanale()).thenReturn(1L);
        when(entity1.getCodiceMeccanica()).thenReturn("M01");
        when(entity1.getCodiceDispositivo()).thenReturn("D01");
        when(entity1.getSeqStampa()).thenReturn((short) 1);
        when(dao.findById(1L)).thenReturn(entity1);
        when(dao.findAllByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndSequenzaGreaterEqualThan(
                1L, "M01", "D01", (short) 1))
                .thenReturn(entities);
        MuiCfgDefaultCastellettoMessaggiEntity entity = mock(MuiCfgDefaultCastellettoMessaggiEntity.class);
        service.addMessageAbove(entity, 1L, "USER");
        verify(dao, times(1)).findAllByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndSequenzaGreaterEqualThan(
                1L, "M01", "D01", (short) 1);
        verify(dao, times(1)).findById(1L);
        verify(dao, times(5))
                .persistWithAuditLog(any(MuiCfgDefaultCastellettoMessaggiEntity.class), eq("USER"));
    }
}
