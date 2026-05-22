package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgSetPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CfgSetPianificazioneServiceImplTest {
    @Mock
    private CfgSetPianificazioneDAO dao;

    @Mock
    private CfgSetPianificazioneEntity entity;

    @InjectMocks
    private CfgSetPianificazioneServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findById(1L)).thenReturn(entity);
    }

    @Test
    public void findById() {
        assertEquals(entity, service.findById(1L));
        verify(dao, times(1)).findById(1L);
    }
}