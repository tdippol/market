package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgLivelloPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgLivelloPianificazioneServiceImplTest {
    @Mock
    private CfgLivelloPianificazioneDAO dao;

    @Mock
    private CfgLivelloPianificazioneEntity daoResult;

    @InjectMocks
    private CfgLivelloPianificazioneServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        when(dao.findByDescription("FOO")).thenReturn(daoResult);
    }

    @Test
    public void findByDescription_whenDescriptionNull_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByDescription(null);
        verify(dao, never()).findByDescription("FOO");
    }

    @Test
    public void findByDescription() {
        assertEquals(daoResult, service.findByDescription("FOO"));
        verify(dao, times(1)).findByDescription("FOO");
    }
}