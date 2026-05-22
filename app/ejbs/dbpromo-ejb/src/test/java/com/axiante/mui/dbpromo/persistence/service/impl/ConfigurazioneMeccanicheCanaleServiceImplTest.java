package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgAbilitaMeccCanaleDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
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
public class ConfigurazioneMeccanicheCanaleServiceImplTest {
    @Mock
    private CfgAbilitaMeccCanaleDAO dao;

    @Mock
    private CfgAbilitaMeccCanaleEntity entity;

    @InjectMocks
    private ConfigurazioneMeccanicheCanaleServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findByMeccanicaAndCanale(1L, 1L)).thenReturn(entity);
    }

    @Test
    public void findByMeccanicaAndCanale() {
        assertEquals(entity, service.findByMeccanicaAndCanale(1L, 1L));
        verify(dao, times(1)).findByMeccanicaAndCanale(1L, 1L);
    }
}