package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneNegozioDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class NegoziPromoServiceImplTest {
    @Mock
    private PromozioneNegozioDAO dao;

    @InjectMocks
    private NegoziPromoServiceImpl service;

    @Test
    public void findById_whenNullIds_shouldReturnEmptyList() {
        final List<PromozioneNegozioEntity> result = service.findById((List<Long>) null);
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findById_whenEmptyIds_shouldReturnEmptyList() {
        final List<PromozioneNegozioEntity> result = service.findById(Collections.emptyList());
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }
}