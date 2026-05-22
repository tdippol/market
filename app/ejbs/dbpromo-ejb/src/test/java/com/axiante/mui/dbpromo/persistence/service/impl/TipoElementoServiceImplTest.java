package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.TipoElementoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TipoElementoServiceImplTest {
    @Mock
    private TipoElementoDAO dao;

    @Mock
    private CfgTipoElementoEntity entity1;

    @Mock
    private CfgTipoElementoEntity entity2;

    @InjectMocks
    private TipoElementoServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findByMeccanicaIdAndSetPianificazioneId(99L, 1L)).thenReturn(null);
        when(dao.findByMeccanicaIdAndSetPianificazioneId(1L, 1L)).thenReturn(entity1);
        when(dao.findByMeccanicaIdAndSetPianificazioneId(2L, 1L)).thenReturn(entity2);
        when(entity1.getArticolo()).thenReturn(0);
        when(entity1.getAttributo()).thenReturn(0);
        when(entity1.getGrm()).thenReturn(0);
        when(entity1.getReparto()).thenReturn(0);
        when(entity1.getTotale()).thenReturn(0);
        when(entity2.getArticolo()).thenReturn(1);
        when(entity2.getAttributo()).thenReturn(1);
        when(entity2.getGrm()).thenReturn(1);
        when(entity2.getReparto()).thenReturn(1);
        when(entity2.getTotale()).thenReturn(1);
    }

    @Test
    public void findTipoElemento_whenEntityNull_shouldReturnEmptyList() {
        final List<String> result = service.findTipoElemento(99L, 1L);
        assertTrue(result.isEmpty());
        verify(dao, times(1)).findByMeccanicaIdAndSetPianificazioneId(99L, 1L);
    }

    @Test
    public void findTipoElemento() {
        List<String> result = service.findTipoElemento(1L, 1L);
        assertTrue(result.isEmpty());
        verify(dao, times(1)).findByMeccanicaIdAndSetPianificazioneId(1L, 1L);
        result = service.findTipoElemento(2L, 1L);
        assertEquals(5, result.size());
        assertTrue(result.contains("ARTICOLO"));
        assertTrue(result.contains("ATTRIBUTO"));
        assertTrue(result.contains("GRM"));
        assertTrue(result.contains("REPARTO"));
        assertTrue(result.contains("TOTALE"));
        verify(dao, times(1)).findByMeccanicaIdAndSetPianificazioneId(2L, 1L);
    }
}