package com.axiante.mui.dbpromo.business.service.impl;

import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.ReportSovrapposizioniNegoziDAO;
import com.axiante.mui.dbpromo.persistence.service.impl.ReportSovrapposizioniNegoziServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportSovrapposizioniNegoziServiceTest {
    @Mock
    private ReportSovrapposizioniNegoziDAO dao;
    @Spy
    @InjectMocks
    private ReportSovrapposizioniNegoziServiceImpl service;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void testFindByIdSovrapposizioneThrowsNullPointerExceptionWhenIdReportIsNull(){
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("idReport is marked non-null but is null");
        service.findByIdSovrapposizione(null);
    }
    @Test
    public void testFindByIdSovrapposizione(){
        Long idReport = 1L;
        service.findByIdSovrapposizione(idReport);
        verify(dao).findByIdSovrapposizione(idReport);
    }
}
