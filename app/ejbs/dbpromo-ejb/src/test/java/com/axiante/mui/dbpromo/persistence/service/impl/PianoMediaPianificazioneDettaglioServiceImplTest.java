package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPianificazioneDettaglioDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianoMediaPianificazioneDettaglioServiceImplTest {
    @Mock
    private PianoMediaPianificazioneDettaglioDAO dao;

    @Mock
    private PianoMediaPianificazioneDettaglioEntity entity1;

    @Mock
    private PianoMediaPianificazioneDettaglioEntity entity2;

    @Mock
    private PianoMediaEntity pianoMedia;

    @InjectMocks
    private PianoMediaPianificazioneDettaglioServiceImpl service;

    public List<PianoMediaPianificazioneDettaglioEntity> daoResult = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findByPianoMedia(pianoMedia)).thenReturn(daoResult);
    }

    @Test
    public void findByPianoMedia() {
        assertEquals(daoResult, service.findByPianoMedia(pianoMedia));
        verify(dao, times(1)).findByPianoMedia(pianoMedia);
    }
}