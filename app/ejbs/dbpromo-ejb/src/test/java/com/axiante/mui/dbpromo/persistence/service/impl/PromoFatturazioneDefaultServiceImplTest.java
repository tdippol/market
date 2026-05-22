package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromoFatturazioneDefaultDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneDefaultEntity;
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
public class PromoFatturazioneDefaultServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PromoFatturazioneDefaultDAO dao;

    @Mock
    private PromoFatturazioneDefaultEntity entity1;

    @Mock
    private PromoFatturazioneDefaultEntity entity2;

    @InjectMocks
    private PromoFatturazioneDefaultServiceImpl service;

    private List<Long> idsCompratori;

    @Before
    public void setUp() throws Exception {
        idsCompratori = Stream.of(1L, 2L).collect(Collectors.toList());
        List<PromoFatturazioneDefaultEntity> entities = Stream.of(entity1, entity2).collect(Collectors.toList());
        when(dao.findAllByIdsCompratori(idsCompratori)).thenReturn(entities);
    }

    @Test
    public void findAllByIdsCompratori_givenNullIds_shouldReturnEmptyList() {
        assertTrue(service.findAllByIdsCompratori(null).isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByIdsCompratori_givenEmptyIds_shouldReturnEmptyList() {
        assertTrue(service.findAllByIdsCompratori(Collections.emptyList()).isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByIdsCompratori() {
        List<PromoFatturazioneDefaultEntity> result = service.findAllByIdsCompratori(idsCompratori);
        assertEquals(2, result.size());
        verify(dao, times(1)).findAllByIdsCompratori(idsCompratori);
    }
}