package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleSecDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleSecEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.NonUniqueResultException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CfgCanaleSecServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private CfgCanaleSecDAO dao;

    @Mock
    private CanalePromozioneEntity canale0;

    @Mock
    private CanalePromozioneEntity canale1;

    @Mock
    private CanalePromozioneEntity canale2;

    @Mock
    private CfgCanaleSecEntity entity;

    @InjectMocks
    private CfgCanaleSecServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.countByCanale(canale0)).thenReturn(0L);
        when(dao.countByCanale(canale1)).thenReturn(1L);
        when(dao.countByCanale(canale2)).thenReturn(2L);
        when(dao.findByCanale(canale1)).thenReturn(entity);
        when(dao.persist(any(CfgCanaleSecEntity.class))).thenReturn(entity);
    }

    @Test
    public void findByCanale_whenZeroEntities_shouldCreateDefault() {
        final CfgCanaleSecEntity result = service.findByCanale(canale0);
        assertEquals(entity, result);
        verify(dao, times(1)).countByCanale(canale0);
        verify(dao, times(1)).persist(any(CfgCanaleSecEntity.class));
        verify(dao, never()).findByCanale(any(CanalePromozioneEntity.class));
    }

    @Test
    public void findByCanale_whenZeroEntitiesAndErrorCreatingDefault_shouldReturnNull() {
        when(dao.persist(any(CfgCanaleSecEntity.class))).thenThrow(new RuntimeException("FOO"));
        final CfgCanaleSecEntity result = service.findByCanale(canale0);
        assertNull(result);
        verify(dao, times(1)).countByCanale(canale0);
        verify(dao, times(1)).persist(any(CfgCanaleSecEntity.class));
        verify(dao, never()).findByCanale(any(CanalePromozioneEntity.class));
    }

    @Test
    public void findByCanale_whenOneEntity_shouldReturnIt() {
        final CfgCanaleSecEntity result = service.findByCanale(canale1);
        assertEquals(entity, result);
        verify(dao, times(1)).countByCanale(canale1);
        verify(dao, never()).persist(any(CfgCanaleSecEntity.class));
        verify(dao, times(1)).findByCanale(canale1);
    }

    @Test
    public void findByCanale_whenMoreThanOneEntity_shouldThrowException() {
        ex.expect(NonUniqueResultException.class);
        service.findByCanale(canale2);
        verify(dao, times(1)).countByCanale(canale2);
        verify(dao, never()).findByCanale(any(CanalePromozioneEntity.class));
    }
}