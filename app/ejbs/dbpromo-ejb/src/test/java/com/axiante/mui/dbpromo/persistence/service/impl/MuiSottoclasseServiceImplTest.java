package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiSottoclasseDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSottoclasseEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MuiSottoclasseServiceImplTest {
    @Mock
    private MuiSottoclasseDAO dao;

    @Mock
    private MuiSottoclasseEntity entity1;

    @Mock
    private MuiSottoclasseEntity entity2;

    @InjectMocks
    private MuiSottoclasseServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private List<MuiSottoclasseEntity> entities = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findByCode("FOO")).thenReturn(entity1);
        when(dao.findById("BAR")).thenReturn(entity2);
        when(dao.findAll()).thenReturn(entities);
        when(dao.findAllAttive()).thenReturn(entities);
        when(dao.findActiveByCode("FOO")).thenReturn(entity1);
    }

    @Test
    public void findByCode() {
        assertEquals(entity1, service.findByCode("FOO"));
        verify(dao, times(1)).findByCode("FOO");
    }

    @Test
    public void findById_givenNullId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findById(null);
        verify(dao, never()).findById(any());
    }

    @Test
    public void findById() {
        assertEquals(entity2, service.findById("BAR"));
        verify(dao, times(1)).findById("BAR");
    }

    @Test
    public void findAll() {
        assertEquals(entities, service.findAll());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findAllAttive() {
        assertEquals(entities, service.findAllAttive());
        verify(dao, times(1)).findAllAttive();
    }

    @Test
    public void findActiveByCode() {
        assertEquals(entity1, service.findActiveByCode("FOO"));
        verify(dao, times(1)).findActiveByCode("FOO");
    }
}