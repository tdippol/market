package com.axiante.mui.dbpromo.business.service.impl;

import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.StatoContributiDAO;
import com.axiante.mui.dbpromo.persistence.service.impl.StatoContributiServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatoContributiServiceTest {
    @Mock
    private StatoContributiDAO dao;
    @Spy
    @InjectMocks
    private StatoContributiServiceImpl service;

    @Test
    public void testFindDescrizioneByCodice(){
        String codice = "some";
        service.findDescrizioneByCodice(codice);
        verify(dao).findDescrizioneByCodice(codice);
    }
}
