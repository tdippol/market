package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromoFatturazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneEntity;
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
public class PromoFatturazioneServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PromoFatturazioneDAO dao;

    @Mock
    private PromoFatturazioneEntity entity1;

    @Mock
    private PromoFatturazioneEntity entity2;

    @InjectMocks
    private PromoFatturazioneServiceImpl service;

    private List<Long> idsCompratori;
    private List<String> codiciCompratori;

    @Before
    public void setUp() throws Exception {
        idsCompratori = Stream.of(1L, 2L).collect(Collectors.toList());
        codiciCompratori = Stream.of("S01", "S02").collect(Collectors.toList());
        List<PromoFatturazioneEntity> entities = Stream.of(entity1, entity2).collect(Collectors.toList());
        when(dao.findAllByIdsCompratori(idsCompratori)).thenReturn(entities);
        when(dao.findAllByCodiciCompratori(codiciCompratori)).thenReturn(entities);
        when(dao.findAllByIdCompratoreAndIdPromozione(1L, 1L)).thenReturn(Collections.singletonList(entity1));
    }

    @Test
    public void findAllByIdsCompratori_givneNullIds_shouldReturnEmptyList() {
        assertTrue(service.findAllByIdsCompratori(null).isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByIdsCompratori_givneEmptyIds_shouldReturnEmptyList() {
        assertTrue(service.findAllByIdsCompratori(Collections.emptyList()).isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByIdsCompratori() {
        List<PromoFatturazioneEntity> result = service.findAllByIdsCompratori(idsCompratori);
        assertEquals(2, result.size());
        verify(dao, times(1)).findAllByIdsCompratori(idsCompratori);
    }

    @Test
    public void findAllByCodiciCompratori_givneNullIds_shouldReturnEmptyList() {
        assertTrue(service.findAllByCodiciCompratori(null).isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByCodiciCompratori_givneEmptyIds_shouldReturnEmptyList() {
        assertTrue(service.findAllByCodiciCompratori(Collections.emptyList()).isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByCodiciCompratori() {
        List<PromoFatturazioneEntity> result = service.findAllByCodiciCompratori(codiciCompratori);
        assertEquals(2, result.size());
        verify(dao, times(1)).findAllByCodiciCompratori(codiciCompratori);
    }

    @Test
    public void findAllByIdCompratoreAndIdPromozione_givenNullIdCompratore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllByIdCompratoreAndIdPromozione(null, 1L);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByIdCompratoreAndIdPromozione_givenNullIdPromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllByIdCompratoreAndIdPromozione(1L, null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findAllByIdCompratoreAndIdPromozione() {
        List<PromoFatturazioneEntity> result = service.findAllByIdCompratoreAndIdPromozione(1L, 1L);
        assertEquals(1, result.size());
        verify(dao, times(1)).findAllByIdCompratoreAndIdPromozione(1L, 1L);
    }
}