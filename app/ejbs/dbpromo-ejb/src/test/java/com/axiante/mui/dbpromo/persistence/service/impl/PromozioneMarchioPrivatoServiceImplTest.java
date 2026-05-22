package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneMarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromozioneMarchioPrivatoServiceImplTest {
    @Mock
    private PromozioneMarchioPrivatoDAO dao;

    @Mock
    private PromozioneMarchioPrivatoEntity entity1;

    @Mock
    private PromozioneMarchioPrivatoEntity entity2;

    @InjectMocks
    private PromozioneMarchioPrivatoServiceImpl service;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private List<PromozioneMarchioPrivatoEntity> daoResult = Arrays.asList(entity1, entity2);

    @Before
    public void setUp() throws Exception {
        when(dao.findByIdPromozione(42L)).thenReturn(daoResult);
    }

    @Test
    public void findByIdPromozione_givenNullIdPromozione_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        service.findByIdPromozione((Long) null);
        verify(dao, never()).findByIdPromozione(any());
    }

    @Test
    public void findByIdPromozione() {
        assertEquals(daoResult, service.findByIdPromozione(42L));
        verify(dao, times(1)).findByIdPromozione(42L);
    }

    @Test
    public void findByIdPromozione_givenNullPromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByIdPromozione((PromozioneTestataEntity) null);
        verify(dao, never()).findByIdPromozione(any());
    }

    @Test
    public void findByIdPromozione_withPromozioneTestata() {
        PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
        when(promozione.getId()).thenReturn(42L);
        assertEquals(daoResult, service.findByIdPromozione(promozione));
        verify(dao, times(1)).findByIdPromozione(42L);
    }
}