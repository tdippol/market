package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.IniziativaDAO;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IniziativeServiceImplTest {
    @Mock
    private IniziativaDAO dao;

    @Mock
    private IniziativaEntity entity1;

    @Mock
    private IniziativaEntity entity2;

    @Mock
    private StatoPromozioneEntity statoPromozione1;

    @Mock
    private StatoPromozioneEntity statoPromozione2;

    @InjectMocks
    private IniziativeServiceImpl service;

    private Date dataInizio = new GregorianCalendar(2024, Calendar.OCTOBER, 20).getTime();
    private Date dataFine = new GregorianCalendar(2024, Calendar.OCTOBER, 30).getTime();
    private List<IniziativaEntity> entities = Arrays.asList(entity1, entity2);
    private List<StatoPromozioneEntity> statiTransizione = Arrays.asList(statoPromozione1, statoPromozione2);

    @Before
    public void setUp() throws Exception {
        when(dao.findAllNotCancelled()).thenReturn(entities);
        when(dao.findAllPublishedAndInProgressAndValidDates(dataInizio, dataFine)).thenReturn(entities);
        when(dao.findStatiTransizioneConsentiti()).thenReturn(statiTransizione);
    }

    @Test
    public void findAllNotCancelled() {
        assertEquals(entities, service.findAllNotCancelled());
        verify(dao, times(1)).findAllNotCancelled();
    }

    @Test
    public void findAllPublishedAndInProgressAndValidDates() {
        assertEquals(entities, service.findAllPublishedAndInProgressAndValidDates(dataInizio, dataFine));
        verify(dao, times(1)).findAllPublishedAndInProgressAndValidDates(dataInizio, dataFine);
    }

    @Test
    public void findAllStatiTransizione() {
        assertEquals(statiTransizione, service.findStatiTransizioneConsentiti());
        verify(dao, times(1)).findStatiTransizioneConsentiti();
    }
}