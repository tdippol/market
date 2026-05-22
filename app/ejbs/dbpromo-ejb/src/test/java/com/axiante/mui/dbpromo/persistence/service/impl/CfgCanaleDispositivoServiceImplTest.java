package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleDispositivoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CfgCanaleDispositivoServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private CfgCanaleDispositivoDAO dao;

    @Mock
    private CfgCanaleDispositivoEntity entity1;

    @InjectMocks
    private CfgCanaleDispositivoServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findByCodice("FOO")).thenReturn(entity1);
    }

    @Test
    public void findByCode_whenNullCode_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByCode(null);
        verify(dao, never()).findByCodice(anyString());
    }

    @Test
    public void findByCode() {
        final CfgCanaleDispositivoEntity byCode = service.findByCode("FOO");
        assertEquals(entity1, byCode);
        verify(dao, times(1)).findByCodice("FOO");
    }
}