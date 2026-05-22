package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.StatoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatoPromoServiceImplTest {
    @Mock
    private StatoPromozioneDAO dao;

    @Mock
    private StatoPromozioneEntity entity1;

    @Mock
    private StatoPromozioneEntity entity2;

    @InjectMocks
    private StatoPromoServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findAllSorted()).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
        when(dao.findByCode("C01")).thenReturn(entity1);
    }

    @Test
    public void findAllSorted() throws Exception {
        final List<StatoPromozioneEntity> all = service.findAllSorted();
        assertEquals(2, all.size());
        assertTrue(all.contains(entity1));
        assertTrue(all.contains(entity2));
        verify(dao, times(1)).findAllSorted();
    }

    @Test
    public void findByCode() throws Exception {
        assertEquals(entity1, service.findByCode("C01"));
        verify(dao, times(1)).findByCode("C01");
    }
}