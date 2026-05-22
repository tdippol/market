package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromozioneTestataServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    PromozioneTestataDAO dao;

    @InjectMocks
    PromozioneTestataServiceImpl service;

    @Mock
    PromozioneTestataEntity promozione1;

    @Mock
    PromozioneTestataEntity promozione2;

    @Test
    public void getDao() {
        assertNotNull(service.getDao());
    }

    @Test
    public void findByCanaleMeccanicheDate_givenNullCanale_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByCanaleMeccanicheDate(null, Collections.emptyList(), new Date(), new Date());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByCanaleMeccanicheDate_givenNullCodiciMeccaniche_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByCanaleMeccanicheDate(1L, null, new Date(), new Date());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByCanaleMeccanicheDate_givenEmptyCodiciMeccaniche_shouldReturnEmptyList() {
        final List<PromozioneTestataEntity> result = service.findByCanaleMeccanicheDate(1L, Collections.emptyList(),
                new Date(), new Date());
        assertTrue(result.isEmpty());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByCanaleMeccanicheDate_givenNullDataInizio_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByCanaleMeccanicheDate(1L, Collections.emptyList(), null, new Date());
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByCanaleMeccanicheDate_givenNullDataFine_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.findByCanaleMeccanicheDate(1L, Collections.emptyList(), new Date(), null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void findByCanaleMeccanicheDate() {
        List<String> codiciMeccaniche = Arrays.asList("M001", "M002");
        List<PromozioneTestataEntity> promozioni = Arrays.asList(promozione1, promozione2);
        Date dataInizio = new GregorianCalendar(2025, Calendar.NOVEMBER, 1).getTime();
        Date dataFine = new GregorianCalendar(2025, Calendar.NOVEMBER, 30).getTime();
        when(dao.findByCanaleMeccanicheDate(1L, codiciMeccaniche, dataInizio, dataFine))
                .thenReturn(promozioni);
        final List<PromozioneTestataEntity> result = service.findByCanaleMeccanicheDate(1L, codiciMeccaniche,
                dataInizio, dataFine);
        assertEquals(2, result.size());
        assertTrue(result.contains(promozione1));
        assertTrue(result.contains(promozione2));
        verify(dao, times(1))
                .findByCanaleMeccanicheDate(1L, codiciMeccaniche, dataInizio, dataFine);
    }
}