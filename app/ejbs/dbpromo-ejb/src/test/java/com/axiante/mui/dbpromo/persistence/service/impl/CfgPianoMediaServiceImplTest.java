package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianoMediaEntity;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgPianoMediaServiceImplTest {
    @Mock
    private CfgPianoMediaDAO dao;

    @Mock
    private CfgPianoMediaEntity cfgPianoMedia1;

    @Mock
    private CfgPianoMediaEntity cfgPianoMedia2;

    @InjectMocks
    private CfgPianoMediaServiceImpl service;

    private List<CfgPianoMediaEntity> daoResult = Arrays.asList(cfgPianoMedia1, cfgPianoMedia2);

    @Before
    public void setUp() throws Exception {
        when(dao.findAllAttivi()).thenReturn(daoResult);
    }

    @Test
    public void findAllAttivi() {
        final List<CfgPianoMediaEntity> result = service.findAllAttivi();
        assertEquals(daoResult, result);
        verify(dao, times(1)).findAllAttivi();
    }
}