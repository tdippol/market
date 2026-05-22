package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MarchioPrivatoEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarchioPrivatoServiceImplTest {
    @Mock
    private MarchioPrivatoDAO dao;

    @Mock
    private MarchioPrivatoEntity entity1;

    @Mock
    private MarchioPrivatoEntity entity2;

    @InjectMocks
    private MarchioPrivatoServiceImpl service;

    final List<String> codici = Stream.of("BAR", "BAZ").collect(Collectors.toList());

    @Before
    public void setUp() throws Exception {
        when(dao.findAll()).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findByCodice("FOO")).thenReturn(entity1);
        when(dao.findByCodici(codici)).thenReturn(Stream.of(entity2).collect(Collectors.toList()));
    }

    @Test
    public void findAll() {
        final List<MarchioPrivatoEntity> all = service.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(entity1));
        assertTrue(all.contains(entity2));
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findByCodice() {
        final MarchioPrivatoEntity byCodice = service.findByCodice("FOO");
        assertEquals(entity1, byCodice);
        verify(dao, times(1)).findByCodice("FOO");
    }

    @Test
    public void findByCodici_whenNullCodici_shouldReturnEmptyList() {
        final List<MarchioPrivatoEntity> byCodici = service.findByCodici(null);
        assertTrue(byCodici.isEmpty());
        verify(dao, never()).findByCodici(anyList());
    }

    @Test
    public void findByCodici_whenEmptyCodici_shouldReturnEmptyList() {
        final List<MarchioPrivatoEntity> byCodici = service.findByCodici(new ArrayList<>());
        assertTrue(byCodici.isEmpty());
        verify(dao, never()).findByCodici(anyList());
    }

    @Test
    public void findByCodici() {
        final List<MarchioPrivatoEntity> byCodici = service.findByCodici(codici);
        assertEquals(1, byCodici.size());
        assertFalse(byCodici.contains(entity1));
        assertTrue(byCodici.contains(entity2));
        verify(dao, times(1)).findByCodici(codici);
    }
}