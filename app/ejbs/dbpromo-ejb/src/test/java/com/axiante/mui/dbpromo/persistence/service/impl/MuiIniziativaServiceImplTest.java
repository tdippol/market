package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.MuiIniziativaDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiIniziativaEntity;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class MuiIniziativaServiceImplTest {
    @Mock
    private MuiIniziativaDAO dao;

    @Mock
    private MuiIniziativaEntity iniziativa;

    @InjectMocks
    private MuiIniziativaServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private List<MuiIniziativaEntity> iniziative = Collections.singletonList(iniziativa);
    private Date dataInizio = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 5).getTime();
    private Date dataFine = new GregorianCalendar(2024, GregorianCalendar.NOVEMBER, 10).getTime();

    @Before
    public void setUp() throws Exception {
        when(dao.findAllByDataInizioAndDataFine(dataInizio, dataFine)).thenReturn(iniziative);
    }

    @Test
    public void findAllByDataInizioAndDataFine_givenNullDataInizio_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllByDataInizioAndDataFine(null, new Date());
        verify(dao, never()).findAllByDataInizioAndDataFine(any(), any());
    }

    @Test
    public void findAllByDataInizioAndDataFine_givenNullDataFine_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findAllByDataInizioAndDataFine(new Date(), null);
        verify(dao, never()).findAllByDataInizioAndDataFine(any(), any());
    }

    @Test
    public void findAllByDataInizioAndDataFine() {
        List<MuiIniziativaEntity> result = service.findAllByDataInizioAndDataFine(dataInizio, dataFine);
        assertEquals(1, result.size());
        verify(dao, times(1)).findAllByDataInizioAndDataFine(dataInizio, dataFine);
    }
}