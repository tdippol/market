package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CheckPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CheckPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CheckPianificazioneServiceImplTest {
    @Mock
    private CheckPianificazioneDAO dao;

    @Mock
    private PromozioneTestataEntity testata;

    @Mock
    private CheckPianificazioneEntity entity1;

    @Mock
    private CheckPianificazioneEntity entity2;

    @InjectMocks
    private CheckPianificazioneServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private List<CheckPianificazioneEntity> entities = Arrays.asList(entity1, entity2);
    private List<String> codiciCompratore = Arrays.asList("1", "2", "3");

    @Before
    public void setUp() throws Exception {
        when(dao.findAllByPromozioneAndCodiciCompratore(testata, codiciCompratore)).thenReturn(entities);
    }

    @Test
    public void findAllByPromozioneAndCodiciCompratore_givenNullTestata_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllByPromozioneAndCodiciCompratore(null, codiciCompratore);
        verify(dao, never()).findAllByPromozioneAndCodiciCompratore(any(), any());
    }

    @Test
    public void findAllByPromozioneAndCodiciCompratore_givenNullCodiciCompratori_shouldReturnEmptyList() {
        assertTrue(service.findAllByPromozioneAndCodiciCompratore(testata, null).isEmpty());
        verify(dao, never()).findAllByPromozioneAndCodiciCompratore(any(), any());
    }

    @Test
    public void findAllByPromozioneAndCodiciCompratore_givenEmptyCodiciCompratori_shouldReturnEmptyList() {
        assertTrue(service.findAllByPromozioneAndCodiciCompratore(testata, Collections.emptyList()).isEmpty());
        verify(dao, never()).findAllByPromozioneAndCodiciCompratore(any(), any());
    }

    @Test
    public void findAllByPromozioneAndCodiciCompratore() {
        assertEquals(entities, service.findAllByPromozioneAndCodiciCompratore(testata, codiciCompratore));
        verify(dao, times(1)).findAllByPromozioneAndCodiciCompratore(testata, codiciCompratore);
    }
}