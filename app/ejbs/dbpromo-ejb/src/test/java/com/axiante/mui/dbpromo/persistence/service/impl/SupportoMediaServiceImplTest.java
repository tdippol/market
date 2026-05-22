package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.SupportoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SupportoMediaServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private SupportoMediaDAO dao;

    @Mock
    private SupportoMediaEntity entity1;

    @Mock
    private SupportoMediaEntity entity2;

    @InjectMocks
    private SupportoMediaServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findAllAttivi()).thenReturn(Stream.of(entity1, entity2).collect(java.util.stream.Collectors.toList()));
    }

    @Test
    public void isCodeUsed_whenCodiceIsNull_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.isCodeUsed(null);
        verify(dao, never()).findByCode(any());
    }

    @Test
    public void isCodeUsed_whenEntityNotFound_shouldReturnFalse() {
        when(dao.findByCode("ABC")).thenReturn(null);
        assertFalse(service.isCodeUsed("ABC"));
        verify(dao, times(1)).findByCode("ABC");
    }

    @Test
    public void isCodeUsed_whenEntityIsFound_shouldReturnTrue() {
        final SupportoMediaEntity entity = mock(SupportoMediaEntity.class);
        when(dao.findByCode("ABC")).thenReturn(entity);
        assertTrue(service.isCodeUsed("ABC"));
        verify(dao, times(1)).findByCode("ABC");
    }

    @Test
    public void isCodeUsed_whenDaoThrowException_shouldReturnTrue() {
        when(dao.findByCode("ABC")).thenThrow(IllegalArgumentException.class);
        assertTrue(service.isCodeUsed("ABC"));
        verify(dao, times(1)).findByCode("ABC");
    }

    @Test
    public void findAllAttivi() {
        final List<SupportoMediaEntity> result = service.findAllAttivi();
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findAllAttivi();
    }
}