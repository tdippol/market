package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CumulabilitaBuoniDAO;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaBuoniEntity;
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
public class CumulabilitaBuoniServiceImplTest {
    @Mock
    private CumulabilitaBuoniDAO dao;

    @Mock
    private CumulabilitaBuoniEntity entity;

    @InjectMocks
    private CumulabilitaBuoniServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findByCodicePromozioneAndCodiceCanaleAndPrefissoBs("PR", "CC", "BS"))
                .thenReturn(entity);
    }

    @Test
    public void findByCodicePromozioneAndCodiceCanaleAndPrefissoBs() {
        final CumulabilitaBuoniEntity e = service.findByCodicePromozioneAndCodiceCanaleAndPrefissoBs("PR", "CC", "BS");
        assertEquals(entity, e);
        verify(dao, times(1))
                .findByCodicePromozioneAndCodiceCanaleAndPrefissoBs("PR", "CC", "BS");
    }
}