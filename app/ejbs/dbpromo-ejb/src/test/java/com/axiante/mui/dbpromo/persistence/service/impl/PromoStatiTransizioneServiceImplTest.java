package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromoStatiTransizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromoStatiTransizioneServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PromoStatiTransizioneEntity entity1;

    @Mock
    private PromoStatiTransizioneEntity entity2;

    @Mock
    private PromozioneTestataEntity testata;

    @Mock
    private StatoPromozioneEntity fromStatus;

    @Mock
    private StatoPromozioneEntity toStatus;

    @Mock
    private PromoStatiTransizioneDAO dao;

    @InjectMocks
    private PromoStatiTransizioneServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.findAllAutomaticByIdAndStatus(1L, 1L)).thenReturn(Collections.singletonList(entity1));
        when(dao.findAllNotAutomaticByIdAndStatus(1L, 1L)).thenReturn(Collections.singletonList(entity2));
        when(dao.findByPromoAndStatuses(testata, fromStatus, toStatus))
                .thenReturn(entity1);
        when(dao.findByPromoAndStatusesAndFlagAutomatico(testata, fromStatus, toStatus, true))
                .thenReturn(entity2);
    }

    @Test
    public void findAllNotAutomaticByIdAndStatus() {
        final List<PromoStatiTransizioneEntity> result = service.findAllNotAutomaticByIdAndStatus(1L, 1L);
        assertEquals(1, result.size());
        assertTrue(result.contains(entity2));
        verify(dao, times(1)).findAllNotAutomaticByIdAndStatus(1L, 1L);
    }

    @Test
    public void findAllAutomaticByIdAndStatus() {
        final List<PromoStatiTransizioneEntity> result = service.findAllAutomaticByIdAndStatus(1L, 1L);
        assertEquals(1, result.size());
        assertTrue(result.contains(entity1));
        verify(dao, times(1)).findAllAutomaticByIdAndStatus(1L, 1L);
    }

    @Test
    public void findByPromoAndStatuses_whenNullTestata_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByPromoAndStatuses(null, fromStatus, toStatus);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByPromoAndStatuses_whenNullFromStatus_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByPromoAndStatuses(testata, null, toStatus);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByPromoAndStatuses_whenNullToStatus_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByPromoAndStatuses(testata, fromStatus, null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByPromoAndStatuses() {
        assertEquals(entity1, service.findByPromoAndStatuses(testata, fromStatus, toStatus));
        verify(dao, times(1)).findByPromoAndStatuses(testata, fromStatus, toStatus);
    }

    @Test
    public void findManualiByPromoAndStatuses() {
        assertEquals(entity2, service.findManualiByPromoAndStatuses(testata, fromStatus, toStatus, true));
        verify(dao, times(1)).findByPromoAndStatusesAndFlagAutomatico(testata, fromStatus, toStatus, true);
    }
}