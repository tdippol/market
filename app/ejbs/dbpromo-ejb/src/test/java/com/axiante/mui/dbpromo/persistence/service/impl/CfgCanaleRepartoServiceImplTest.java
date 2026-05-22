package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleRepartoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleReparto;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import java.util.Arrays;
import java.util.List;
import javax.persistence.NoResultException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgCanaleRepartoServiceImplTest {
    @Mock
    private CfgCanaleRepartoDAO dao;

    @Mock
    private CanalePromozioneEntity canale;

    @Mock
    private RepartoEntity reparto1;

    @Mock
    private RepartoEntity reparto2;

    @Mock
    private CfgCanaleReparto entity1;

    @Mock
    private CfgCanaleReparto entity2;

    @InjectMocks
    private CfgCanaleRepartoServiceImpl service;

    private List<CfgCanaleReparto> entities = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findByCanale(canale)).thenReturn(entities);
        when(dao.findByCanaleAndReparto(canale, reparto1)).thenReturn(entity1);
        when(dao.findByCanaleAndReparto(canale, reparto2)).thenThrow(NoResultException.class);
    }

    @Test
    public void findByCanale() {
        assertEquals(entities, service.findByCanale(canale));
        verify(dao, times(1)).findByCanale(canale);
    }

    @Test
    public void findByCanaleAndReparto_whenCannotFindEntity_shouldReturnNull() {
        assertNull(service.findByCanaleAndReparto(canale, reparto2));
        verify(dao, times(1)).findByCanaleAndReparto(canale, reparto2);
    }

    @Test
    public void findByCanaleAndReparto() {
        assertEquals(entity1, service.findByCanaleAndReparto(canale, reparto1));
        verify(dao, times(1)).findByCanaleAndReparto(canale, reparto1);
    }
}