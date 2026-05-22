package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.SottoclasseDAO;
import com.axiante.mui.dbpromo.persistence.entities.SottoclasseEntity;
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
public class SottoclasseServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private SottoclasseDAO dao;

    @Mock
    private SottoclasseEntity entity1;

    @Mock
    private SottoclasseEntity entity2;

    @InjectMocks
    private SottoclasseServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findAll()).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findById(1L)).thenReturn(entity1);
        when(dao.findById(99L)).thenReturn(null);
    }

    @Test
    public void findAll() {
        final List<SottoclasseEntity> all = service.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(entity1));
        assertTrue(all.contains(entity2));
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findById_whenNullId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findById(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findById_existing() {
        assertEquals(entity1, service.findById(1L));
        verify(dao, times(1)).findById(1L);
    }

    @Test
    public void findById_notExisting() {
        assertNull(service.findById(99L));
        verify(dao, times(1)).findById(99L);
    }

    @Test
    public void existsByCodeOrDescription_givenNullCode_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.existsByCodeOrDescription(null, "FOO");
        verifyZeroInteractions(dao);
    }

    @Test
    public void existsByCodeOrDescription_givenNullDescription_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.existsByCodeOrDescription("FOO", null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void existsByCodeOrDescription() {
        when(dao.existsByCodeOrDescription("FOO", "SOTTOCLASSE FOO")).thenReturn(true);
        assertTrue(service.existsByCodeOrDescription("FOO", "SOTTOCLASSE FOO"));
        verify(dao, times(1)).existsByCodeOrDescription("FOO", "SOTTOCLASSE FOO");
    }

    @Test
    public void runFunctionPubblicaSottoclassi_givenNullUsername_shouldThrowException() throws Exception {
        ex.expect(NullPointerException.class);
        service.runFunctionPubblicaSottoclassi(null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void runFunctionPubblicaSottoclassi_whenDaoThrowsException_shouldPropagateIt() throws Exception {
        ex.expect(RuntimeException.class);
        when(dao.runFunctionPubblicaSottoclassi("USER")).thenThrow(new RuntimeException("DB Failure"));
        service.runFunctionPubblicaSottoclassi("USER");
        verify(dao, times(1)).runFunctionPubblicaSottoclassi("USER");
    }

    @Test
    public void runFunctionPubblicaSottoclassi() throws Exception {
        when(dao.runFunctionPubblicaSottoclassi("USER")).thenReturn(true);
        assertTrue(service.runFunctionPubblicaSottoclassi("USER"));
        verify(dao, times(1)).runFunctionPubblicaSottoclassi("USER");
    }

    @Test
    public void countSottoclassiNonScaricate() {
        when(dao.countSottoclassiNonScaricate()).thenReturn(42L);
        assertEquals(42, service.countSottoclassiNonScaricate().longValue());
        verify(dao, times(1)).countSottoclassiNonScaricate();
    }
}