package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JpaPianoMediaServiceImplTest {
    @Mock
    private PianoMediaDAO dao;

    @InjectMocks
    @Spy
    private PianoMediaServiceImpl service;

    @Mock
    private PianoMediaEntity pianoMedia1;

    @Mock
    private PianoMediaEntity pianoMedia2;

    private List<PianoMediaEntity> pianiMedia = Stream.of(pianoMedia1, pianoMedia2).collect(Collectors.toList());

    @Before
    public void setUp() throws Exception {
        when(dao.findPubblicatiByDataInizio(any(Date.class))).thenReturn(pianiMedia);
        when(dao.findOpenByDataFine(any(Date.class))).thenReturn(pianiMedia);
    }

    @Test
    public void findPubblicatiByDataInizio() {
        assertEquals(pianiMedia, service.findPubblicatiByDataInizio());
        verify(service, times(1)).findPubblicatiByDataInizio(any(Date.class));
    }

    @Test
    public void findOpenByDataFine() {
        assertEquals(pianiMedia, service.findOpenByDataFine());
        verify(service, times(1)).findOpenByDataFine(any(Date.class));
    }
}