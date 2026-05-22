package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.PianificazioneAnagraficaPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneAnagraficaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaPianificazioneAnagraficaPianoMediaServiceImplTest {
    @Mock
    private PianificazioneAnagraficaPianoMediaDAO dao;

    @Mock
    private PianificazioneAnagraficaPianoMediaEntity daoResult;

    @Mock
    private PianoMediaPianificazioneDettaglioEntity pianificazioneDettaglio;

    @Mock
    private PianificazionePianoMediaEntity pianificazioneMedia;

    @InjectMocks
    private JpaPianificazioneAnagraficaPianoMediaServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findByPianificazioneDettaglioAndPianificazioneMedia(pianificazioneDettaglio, pianificazioneMedia))
                .thenReturn(daoResult);
    }

    @Test
    public void findByPianificazioneDettaglioAndPianificazioneMedia() {
        assertEquals(daoResult, service.findByPianificazioneDettaglioAndPianificazioneMedia(pianificazioneDettaglio,
                pianificazioneMedia));
        verify(dao, times(1))
                .findByPianificazioneDettaglioAndPianificazioneMedia(pianificazioneDettaglio, pianificazioneMedia);
    }
}