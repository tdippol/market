package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiFontStileDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MuiFontStileServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private MuiFontStileDAO dao;

    @Mock
    private MuiFontStileEntity entity1;

    @Mock
    private MuiFontStileEntity entity2;

    @InjectMocks
    private MuiFontStileServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findAll()).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findById("FOO")).thenReturn(entity1);
    }

    @Test
    public void findAll() {
        final List<MuiFontStileEntity> entities = service.findAll();
        assertEquals(2, entities.size());
        assertTrue(entities.contains(entity1));
        assertTrue(entities.contains(entity2));
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findById_whenNullId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findById(null);
        verify(dao, never()).findById(anyString());
    }

    @Test
    public void findById() {
        final MuiFontStileEntity byId = service.findById("FOO");
        assertEquals(entity1, byId);
        verify(dao, times(1)).findById("FOO");
    }
}