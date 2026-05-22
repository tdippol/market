package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneNegozioDAO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromozioneNegozioServiceImplTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PromozioneNegozioDAO dao;

    @InjectMocks
    private PromozioneNegozioServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(dao.impostaNegozi(1L, "PS", "CP", "TP", "DP", "USER"))
                .thenReturn(true);
        when(dao.impostaNegozi(2L, "PS", "CP", "TP", "DP", "USER"))
                .thenThrow(new RuntimeException("FOO EXCEPTION"));
        when(dao.eliminaNegozi(1L, "CP", "TP", "DP"))
                .thenReturn(true);
        when(dao.eliminaNegozi(2L, "CP", "TP", "DP"))
                .thenThrow(new RuntimeException("FOO EXCEPTION"));
    }

    @Test
    public void importaDaPromo_whenNullIdPromozione_shouldThrowException() throws Exception {
        ex.expect(NullPointerException.class);
        service.importaDaPromo(null, "PS", "CP", "TP", "DP", "USER");
        verifyZeroInteractions(dao);
    }

    @Test
    public void importaDaPromo_whenNullUser_shouldThrowException() throws Exception {
        ex.expect(NullPointerException.class);
        service.importaDaPromo(1L, "PS", "CP", "TP", "DP", null);
        verifyZeroInteractions(dao);
    }

    @Test
    public void importaDaPromo_whenException_shouldReturnFalse() throws Exception {
        assertFalse(service.importaDaPromo(2L, "PS", "CP", "TP", "DP", "USER"));
        verify(dao, times(1)).impostaNegozi(2L, "PS", "CP", "TP", "DP", "USER");
    }

    @Test
    public void importaDaPromo() throws Exception {
        assertTrue(service.importaDaPromo(1L, "PS", "CP", "TP", "DP", "USER"));
        verify(dao, times(1)).impostaNegozi(1L, "PS", "CP", "TP", "DP", "USER");
    }

    @Test
    public void eliminaNegozi_whenNullIdPromozione_shouldThrowException() {
        ex.expect(NullPointerException.class);
        service.eliminaNegozi(null, "CP", "TP", "DP");
        verifyZeroInteractions(dao);
    }

    @Test
    public void eliminaNegozi_whenException_shouldReturnFalse() throws Exception {
        assertFalse(service.eliminaNegozi(2L, "CP", "TP", "DP"));
        verify(dao, times(1)).eliminaNegozi(2L, "CP", "TP", "DP");
    }

    @Test
    public void eliminaNegozi() throws Exception {
        assertTrue(service.eliminaNegozi(1L, "CP", "TP", "DP"));
        verify(dao, times(1)).eliminaNegozi(1L, "CP", "TP", "DP");
    }
}