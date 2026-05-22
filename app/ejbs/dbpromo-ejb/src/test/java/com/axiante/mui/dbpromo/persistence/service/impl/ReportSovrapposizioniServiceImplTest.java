package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.ReportSovrapposizioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniEntity;
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
public class ReportSovrapposizioniServiceImplTest {
    @Mock
    private ReportSovrapposizioniDAO dao;

    @Mock
    private PromozioneTestataEntity testata;

    @Mock
    private ReportSovrapposizioniEntity entity1;

    @Mock
    private ReportSovrapposizioniEntity entity2;

    @InjectMocks
    private ReportSovrapposizioniServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private List<ReportSovrapposizioniEntity> entities = Arrays.asList(entity1, entity2);
    private List<String> codiciCompratore = Arrays.asList("S1", "S2", "S3");

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
    public void findAllByPromozioneAndCodiciCompratore_givenNullCodiciCompratore_shouldReturnEmptyList() {
        assertTrue(service.findAllByPromozioneAndCodiciCompratore(testata, null).isEmpty());
        verify(dao, never()).findAllByPromozioneAndCodiciCompratore(any(), any());
    }

    @Test
    public void findAllByPromozioneAndCodiciCompratore_givenEmptyCodiciCompratore_shouldReturnEmptyList() {
        assertTrue(service.findAllByPromozioneAndCodiciCompratore(testata, Collections.emptyList()).isEmpty());
        verify(dao, never()).findAllByPromozioneAndCodiciCompratore(any(), any());
    }

    @Test
    public void findAllByPromozioneAndCodiciCompratore() {
        assertEquals(entities, service.findAllByPromozioneAndCodiciCompratore(testata, codiciCompratore));
        verify(dao, times(1)).findAllByPromozioneAndCodiciCompratore(testata, codiciCompratore);
    }
}