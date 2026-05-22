package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.GruppoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import org.junit.Before;
import org.junit.Test;
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
public class GruppoPromozioniServiceImplTest {
    @Mock
    private GruppoPromozioneDAO dao;

    @Mock
    private GruppoPromozioneEntity entity1;

    @Mock
    private GruppoPromozioneEntity entity2;

    @InjectMocks
    private GruppoPromozioniServiceImpl service;

    private List<Long> codiciCanale = Stream.of(1L, 2L).collect(Collectors.toList());

    @Before
    public void setUp() throws Exception {
        when(dao.findAllByCodiciCanale(codiciCanale)).thenReturn(Stream.of(entity1, entity2).collect(Collectors.toList()));
    }

    @Test
    public void findAllByCodiciCanale_whenCodiciCanaleNull_shouldReturnEmptyList() {
        assertTrue(service.findAllByCodiciCanale(null).isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByCodiciCanale_whenCodiciCanaleIsEmpty_shouldReturnEmptyList() {
        assertTrue(service.findAllByCodiciCanale(Collections.emptyList()).isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByCodiciCanale() {
        final List<GruppoPromozioneEntity> result = service.findAllByCodiciCanale(codiciCanale);
        assertEquals(2, result.size());
        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findAllByCodiciCanale(codiciCanale);
    }
}