package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneCampiDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianificazioneCampiServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private CfgPianificazioneCampiDAO dao;

    @Mock
    private CfgPianificazioneCampiEntity entity;

    @InjectMocks
    private PianificazioneCampiServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.persistWithAuditLog(entity, "USER")).thenReturn(entity);
    }

    @Test
    public void saveCfgPianificazioneCampiEntity_whenNullEntity_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.saveCfgPianificazioneCampiEntity(null, "USER");
        verifyZeroInteractions(dao);
    }

    @Test
    public void saveCfgPianificazioneCampiEntity_whenNullUser_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.saveCfgPianificazioneCampiEntity(entity, null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void saveCfgPianificazioneCampiEntity() {
        assertEquals(entity, service.saveCfgPianificazioneCampiEntity(entity, "USER"));
        verify(dao, times(1)).persistWithAuditLog(entity, "USER");
    }
}