package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaliAttributiDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaliAttributiEntity;
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CfgCanaliAttributiServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private CfgCanaliAttributiDAO dao;

    @Mock
    private CfgCanaliAttributiEntity entity1;

    @Mock
    private CfgCanaliAttributiEntity entity2;

    @InjectMocks
    private CfgCanaliAttributiServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.getListaByCanaleAndAttributo(1L, 1L)).thenReturn("FOO");
        when(dao.getListaByCanaleAndAttributo(1L, 2L)).thenThrow(new RuntimeException("FOO EXCEPTION"));
        when(dao.getAllByCanale(1L)).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
    }

    @Test
    public void getListaByCanaleAndAttributo_whenNullCanaleId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.getListaByCanaleAndAttributo(null, 1L);
        verifyZeroInteractions(dao);
    }

    @Test
    public void getListaByCanaleAndAttributo_whenNullAttributoId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.getListaByCanaleAndAttributo(1L, null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void getListaByCanaleAndAttributo_whenException_shouldReturnNull() {
        assertNull(service.getListaByCanaleAndAttributo(1L, 2L));
        verify(dao, times(1)).getListaByCanaleAndAttributo(1L, 2L);
    }

    @Test
    public void getListaByCanaleAndAttributo() {
        assertEquals("FOO", service.getListaByCanaleAndAttributo(1L, 1L));
        verify(dao, times(1)).getListaByCanaleAndAttributo(1L, 1L);
    }

    @Test
    public void getAllByCanale_whenNullIdCanale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.getAllByCanale(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void getAllByCanale() {
        final List<CfgCanaliAttributiEntity> result = service.getAllByCanale(1L);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).getAllByCanale(1L);
    }
}