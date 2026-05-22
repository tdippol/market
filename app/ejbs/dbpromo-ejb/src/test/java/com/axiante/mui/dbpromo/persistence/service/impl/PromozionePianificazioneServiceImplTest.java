package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozionePianificazioneDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PromozionePianificazioneServiceImplTest {
    @Mock
    PromozionePianificazioneDAO dao;

    @InjectMocks
    PromozionePianificazioneServiceImpl service;

    @Test
    public void getDao() {
        assertNotNull(service.getDao());
    }
}