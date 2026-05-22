package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ClusterEntityDAO;
import com.axiante.mui.dbpromo.persistence.entities.ClusterClienteEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClusterClienteServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private ClusterEntityDAO dao;

    @Mock
    private ClusterClienteEntity entity1;

    @Mock
    private ClusterClienteEntity entity2;

    @InjectMocks
    private ClusterClienteServiceImpl service;

    private Date dataInizio = new GregorianCalendar(2025, Calendar.JULY, 30).getTime();
    private Date dataFine = new GregorianCalendar(2025, Calendar.AUGUST, 30).getTime();

    @Before
    public void setUp() throws Exception {
        when(dao.findAll()).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findAllByIdCluster("A")).thenReturn(Stream.of(entity1).collect(Collectors.toList()));
        when(dao.findByDataInizioAndDataFine(dataInizio, dataFine)).thenReturn(Stream.of(entity2).collect(Collectors.toList()));
    }

    @Test
    public void findAll() {
        final List<ClusterClienteEntity> all = service.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(entity1));
        assertTrue(all.contains(entity2));
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findAllByIdCluster() {
        final List<ClusterClienteEntity> byIdCluster = service.findAllByIdCluster("A");
        assertEquals(1, byIdCluster.size());
        assertTrue(byIdCluster.contains(entity1));
        assertFalse(byIdCluster.contains(entity2));
        verify(dao, times(1)).findAllByIdCluster("A");
    }

    @Test
    public void findByDataInizioAndDataFine_whenNullDataInizio_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByDataInizioAndDataFine(null, dataFine);
        verify(dao, never()).findByDataInizioAndDataFine(any(Date.class), any(Date.class));
    }

    @Test
    public void findByDataInizioAndDataFine_whenNullDataFine_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByDataInizioAndDataFine(dataInizio, null);
        verify(dao, never()).findByDataInizioAndDataFine(any(Date.class), any(Date.class));
    }

    @Test
    public void findByDataInizioAndDataFine() {
        final List<ClusterClienteEntity> byDataInizioAndDataFine = service.findByDataInizioAndDataFine(dataInizio, dataFine);
        assertEquals(1, byDataInizioAndDataFine.size());
        assertFalse(byDataInizioAndDataFine.contains(entity1));
        assertTrue(byDataInizioAndDataFine.contains(entity2));
        verify(dao, times(1)).findByDataInizioAndDataFine(dataInizio, dataFine);
    }
}