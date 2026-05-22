package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleFlagDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleFlagEntity;
import java.util.Arrays;
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
public class CfgCanaleFlagServiceImplTest {
    @Mock
    private CfgCanaleFlagDAO dao;

    @Mock
    private CfgCanaleFlagEntity entity1;

    @Mock
    private CfgCanaleFlagEntity entity2;

    @InjectMocks
    private CfgCanaleFlagServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private List<CfgCanaleFlagEntity> daoResult = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findActiveByChannel(42L)).thenReturn(daoResult);
    }

    @Test
    public void findActiveByChannel_whenNullIdCanale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findActiveByChannel(null);
        verify(dao, never()).findActiveByChannel(any());
    }

    @Test
    public void findActiveByChannel() {
        assertEquals(daoResult, service.findActiveByChannel(42L));
        verify(dao, times(1)).findActiveByChannel(42L);
    }
}