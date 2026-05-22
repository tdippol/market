package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ContributiPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class FatturazioneServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private ContributiPromoDAO dao;

    @InjectMocks
    private FatturazioneServiceImpl service;

    @Test
    public void countByPromozioneAndCompratoreAndFornitore_whenNullCompratore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.countByPromozioneAndCompratoreAndFornitore(
                mock(PromozioneTestataEntity.class), null, mock(FornitoreEntity.class));
        verifyZeroInteractions(dao);
    }

    @Test
    public void countByPromozioneAndCompratoreAndFornitore_whenNullFornitore_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.countByPromozioneAndCompratoreAndFornitore(
                mock(PromozioneTestataEntity.class), mock(CompratoreEntity.class), null);
        verifyZeroInteractions(dao);
    }
}