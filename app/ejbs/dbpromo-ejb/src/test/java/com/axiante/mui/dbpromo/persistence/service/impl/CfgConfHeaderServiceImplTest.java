package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.dbpromo.persistence.dao.CfgConfHeaderDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CfgConfHeaderServiceImplTest {
    @Mock
    private CfgConfHeaderDAO dao;

    @Mock
    private CfgPianificazioneDAO cfgPianificazioneDAO;

    @InjectMocks
    private CfgConfHeaderServiceImpl service;

    public static final String FIELD = PianificazioneConstants.CAMPO_PIANIFICAZIONE_MECCANICA;

    @Before
    public void setUp() throws Exception {
        when(dao.findLivelloByIdHeader(99L)).thenReturn("FOO");
        when(dao.findLivelloByIdHeader(1L)).thenReturn("SET");
        when(dao.findLivelloByIdHeader(11L)).thenReturn("SET");
        when(dao.findLivelloByIdHeader(12L)).thenReturn("SET");
        when(dao.findLivelloByIdHeader(13L)).thenReturn("SET");
        when(dao.findLivelloByIdHeader(2L)).thenReturn("RAGGRUPPAMENTO");
        when(dao.findLivelloByIdHeader(21L)).thenReturn("RAGGRUPPAMENTO");
        when(dao.findLivelloByIdHeader(3L)).thenReturn("ELEMENTO");
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(1L, FIELD))
                .thenReturn(Arrays.asList("SET", "RAGGRUPPAMENTO", "ELEMENTO"));
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(11L, FIELD))
                .thenReturn(Arrays.asList("SET", "RAGGRUPPAMENTO"));
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(12L, FIELD))
                .thenReturn(Arrays.asList("SET", "ELEMENTO"));
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(13L, FIELD))
                .thenReturn(Arrays.asList("RAGGRUPPAMENTO", "ELEMENTO"));
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(2L, FIELD))
                .thenReturn(Arrays.asList("RAGGRUPPAMENTO", "ELEMENTO"));
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(21L, FIELD))
                .thenReturn(Collections.singletonList("RAGGRUPPAMENTO"));
        when(cfgPianificazioneDAO.findTipiRigaByHeaderAndCampo(3L, FIELD))
                .thenReturn(Collections.singletonList("ELEMENTO"));
    }

    @Test
    public void isFieldMeccanicaFullyConfigured_whenLivelloNonGestito_shouldReturnNull() {
        assertNull(service.isFieldMeccanicaFullyConfigured(99L));
        verify(dao, times(1)).findLivelloByIdHeader(99L);
        verifyZeroInteractions(cfgPianificazioneDAO);
    }

    @Test
    public void isFieldMeccanicaFullyConfigured_whenTipiRigaMatch_shouldReturnTrue() {
        assertTrue(service.isFieldMeccanicaFullyConfigured(1L));
        verify(dao, times(1)).findLivelloByIdHeader(1L);
        verify(cfgPianificazioneDAO, times(1)).findTipiRigaByHeaderAndCampo(1L, FIELD);
        assertTrue(service.isFieldMeccanicaFullyConfigured(2L));
        verify(dao, times(1)).findLivelloByIdHeader(2L);
        verify(cfgPianificazioneDAO, times(1)).findTipiRigaByHeaderAndCampo(2L, FIELD);
        assertTrue(service.isFieldMeccanicaFullyConfigured(3L));
        verify(dao, times(1)).findLivelloByIdHeader(3L);
        verify(cfgPianificazioneDAO, times(1)).findTipiRigaByHeaderAndCampo(3L, FIELD);
    }

    @Test
    public void isFieldMeccanicaFullyConfigured_whenTipiRigaNotMatch_shouldReturnFalse() {
        assertFalse(service.isFieldMeccanicaFullyConfigured(11L));
        verify(dao, times(1)).findLivelloByIdHeader(11L);
        verify(cfgPianificazioneDAO, times(1)).findTipiRigaByHeaderAndCampo(11L, FIELD);
        assertFalse(service.isFieldMeccanicaFullyConfigured(12L));
        verify(dao, times(1)).findLivelloByIdHeader(12L);
        verify(cfgPianificazioneDAO, times(1)).findTipiRigaByHeaderAndCampo(12L, FIELD);
        assertFalse(service.isFieldMeccanicaFullyConfigured(13L));
        verify(dao, times(1)).findLivelloByIdHeader(13L);
        verify(cfgPianificazioneDAO, times(1)).findTipiRigaByHeaderAndCampo(13L, FIELD);
        assertFalse(service.isFieldMeccanicaFullyConfigured(21L));
        verify(dao, times(1)).findLivelloByIdHeader(21L);
        verify(cfgPianificazioneDAO, times(1)).findTipiRigaByHeaderAndCampo(21L, FIELD);
    }
}