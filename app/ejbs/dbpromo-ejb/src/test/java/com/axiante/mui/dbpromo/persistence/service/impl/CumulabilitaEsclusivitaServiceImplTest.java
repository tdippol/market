package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import com.axiante.mui.dbpromo.persistence.dao.CumulabilitaEsclusivitaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CumulabilitaEsclusivitaServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private CumulabilitaEsclusivitaDAO dao;

    @Mock
    private CumulabilitaEsclusivitaEntity entity1;

    @Mock
    private CumulabilitaEsclusivitaEntity entity2;

    @InjectMocks
    private CumulabilitaEsclusivitaServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findByType(CumulabilitaType.CUMULABILE)).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findOpenByType(CumulabilitaType.CUMULABILE)).thenReturn(Stream.of(entity1).collect(Collectors.toList()));
        when(dao.findOverlapping(entity1)).thenReturn(Stream.of(entity2).collect(Collectors.toList()));
        when(dao.exportCumulabilita(1L, "FOO")).thenReturn(true);
    }

    @Test
    public void findByType() {
        final List<CumulabilitaEsclusivitaEntity> byType = service.findByType(CumulabilitaType.CUMULABILE);
        assertEquals(2, byType.size());
        assertTrue(byType.contains(entity1));
        assertTrue(byType.contains(entity2));
        verify(dao, times(1)).findByType(CumulabilitaType.CUMULABILE);
    }

    @Test
    public void findOpenByType() {
        final List<CumulabilitaEsclusivitaEntity> openByType = service.findOpenByType(CumulabilitaType.CUMULABILE);
        assertEquals(1, openByType.size());
        assertTrue(openByType.contains(entity1));
        assertFalse(openByType.contains(entity2));
        verify(dao, times(1)).findOpenByType(CumulabilitaType.CUMULABILE);
    }

    @Test
    public void findOverlapping() {
        final List<CumulabilitaEsclusivitaEntity> overlapping = service.findOverlapping(entity1);
        assertEquals(1, overlapping.size());
        assertFalse(overlapping.contains(entity1));
        assertTrue(overlapping.contains(entity2));
        verify(dao, times(1)).findOverlapping(entity1);
    }

    @Test
    public void exportCumulabilita_whenNullId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.exportCumulabilita(null, "FOO");
        verify(dao, never()).exportCumulabilita(1L, "FOO");
    }

    @Test
    public void exportCumulabilita_whenNullCodice_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.exportCumulabilita(1L, null);
        verify(dao, never()).exportCumulabilita(1L, "FOO");
    }

    @Test
    public void exportCumulabilita() {
        assertTrue(service.exportCumulabilita(1L, "FOO"));
        verify(dao, times(1)).exportCumulabilita(1L, "FOO");
    }
}