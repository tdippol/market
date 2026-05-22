package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiPromoDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoDbPromoEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.Collections;
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
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MuiPromoDbPromoServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private MuiPromoDbPromoDAO dao;

    @Mock
    private MuiPromoDbPromoEntity entity1;

    @Mock
    private MuiPromoDbPromoEntity entity2;

    @InjectMocks
    private MuiPromoDbPromoServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findAll()).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findByDataFineGreaterThan(any(Date.class))).thenReturn(Collections.singletonList(entity2));
        when(dao.findByCodicePromozione("PR01")).thenReturn(entity1);
    }

    @Test
    public void findAll() {
        final List<MuiPromoDbPromoEntity> all = service.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(entity1));
        assertTrue(all.contains(entity2));
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findByGiorniSelezione_whenGiorniSelezioneNull() {
        final List<MuiPromoDbPromoEntity> result = service.findByGiorniSelezione(null);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findAll();
        verify(dao, never()).findByDataFineGreaterThan(any(Date.class));
    }

    @Test
    public void findByGiorniSelezione_whenGiorniSelezioneZero() {
        final List<MuiPromoDbPromoEntity> result = service.findByGiorniSelezione(0);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findAll();
        verify(dao, never()).findByDataFineGreaterThan(any(Date.class));
    }

    @Test
    public void findByGiorniSelezione() {
        final List<MuiPromoDbPromoEntity> result = service.findByGiorniSelezione(3);
        assertEquals(1, result.size());
        assertFalse(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, never()).findAll();
        verify(dao, times(1)).findByDataFineGreaterThan(any(Date.class));
    }

    @Test
    public void findByCodicePromozione_whenNullCodicePromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByCodicePromozione(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByCodicePromozione() {
        assertEquals(entity1, service.findByCodicePromozione("PR01"));
        verify(dao, times(1)).findByCodicePromozione("PR01");
    }
}