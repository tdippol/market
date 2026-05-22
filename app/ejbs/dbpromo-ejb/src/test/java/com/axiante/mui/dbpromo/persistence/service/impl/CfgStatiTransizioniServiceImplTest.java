package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgStatiTransizioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiTransizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
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
public class CfgStatiTransizioniServiceImplTest {
    @Mock
    private CfgStatiTransizioniDAO dao;

    @Mock
    private CanalePromozioneEntity canale;

    @Mock
    private StatoPromozioneEntity fromStato;

    @Mock
    private StatoPromozioneEntity toStato;

    @Mock
    private CfgStatiTransizioniEntity entity1;

    @Mock
    private CfgStatiTransizioniEntity entity2;

    @InjectMocks
    private CfgStatiTransizioniServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private List<CfgStatiTransizioniEntity> entities = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findAllByCanaleAndStatusFromAndStatusTo(canale, fromStato, toStato)).thenReturn(entities);
        when(dao.existAutomaticByCanaleAndFromStatus(canale, fromStato)).thenReturn(Boolean.TRUE);
    }

    @Test
    public void findAllByCanaleAndStatusFromAndStatusTo() {
        assertEquals(entities, service.findAllByCanaleAndStatusFromAndStatusTo(canale, fromStato, toStato));
        verify(dao, times(1)).findAllByCanaleAndStatusFromAndStatusTo(canale, fromStato, toStato);
    }

    @Test
    public void existAutomaticByCanaleAndFromStatus_givenNullChannel_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.existAutomaticByCanaleAndFromStatus(null, fromStato);
        verify(dao, never()).existAutomaticByCanaleAndFromStatus(any(), any());
    }

    @Test
    public void existAutomaticByCanaleAndFromStatus_givenNullFromStatus_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.existAutomaticByCanaleAndFromStatus(canale, null);
        verify(dao, never()).existAutomaticByCanaleAndFromStatus(any(), any());
    }

    @Test
    public void existAutomaticByCanaleAndFromStatus() {
        assertTrue(service.existAutomaticByCanaleAndFromStatus(canale, fromStato));
        verify(dao, times(1)).existAutomaticByCanaleAndFromStatus(canale, fromStato);
    }
}