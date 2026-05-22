package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ItemDAO;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private ItemDAO dao;

    @Mock
    private ItemEntity entity1;

    @Mock
    private ItemEntity entity2;

    @InjectMocks
    private ItemServiceImpl service;

    private List<Long> ids = Stream.of(1L, 2L).collect(Collectors.toList());
    private List<String> codiciCompratori = Stream.of("C01", "C02").collect(Collectors.toList());

    @Before
    public void setUp() throws Exception {
        when(dao.findByIds(ids)).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findCodiceMarchioPrivatoByCompratori(codiciCompratori)).thenReturn(Collections.singletonList("I001"));
    }

    @Test
    public void findByIds_whenNullIds_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByIds(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByIds() {
        final List<ItemEntity> result = service.findByIds(ids);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findByIds(ids);
    }

    @Test
    public void findByIdsAndCompratoriAndFornitore_whenNullIds_shouldReturnEmptyList() {
        final List<ItemEntity> result = service.findByIdsAndCompratoriAndFornitore(null, codiciCompratori, "F01");
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByIdsAndCompratoriAndFornitore_whenEmptyIds_shouldReturnEmptyList() {
        final List<ItemEntity> result = service.findByIdsAndCompratoriAndFornitore(Collections.emptyList(), codiciCompratori, "F01");
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByIdsAndCompratoriAndFornitore_whenNullCodiciCompratore_shouldReturnEmptyList() {
        final List<ItemEntity> result = service.findByIdsAndCompratoriAndFornitore(ids, null, "F01");
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByIdsAndCompratoriAndFornitore_whenEmptyCodiciCompratore_shouldReturnEmptyList() {
        final List<ItemEntity> result = service.findByIdsAndCompratoriAndFornitore(ids, Collections.emptyList(), "F01");
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findCodiceMarchioPrivatoByCompratori_whenNullCodiciCompratore_shouldReturnEmptyList() {
        final List<String> result = service.findCodiceMarchioPrivatoByCompratori(null);
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findCodiceMarchioPrivatoByCompratori_whenEmptyCodiciCompratore_shouldReturnEmptyList() {
        final List<String> result = service.findCodiceMarchioPrivatoByCompratori(Collections.emptyList());
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findCodiceMarchioPrivatoByCompratori() {
        final List<String> result = service.findCodiceMarchioPrivatoByCompratori(codiciCompratori);
        assertEquals(1, result.size());
        assertTrue(result.contains("I001"));
        verify(dao, times(1)).findCodiceMarchioPrivatoByCompratori(codiciCompratori);
    }
}