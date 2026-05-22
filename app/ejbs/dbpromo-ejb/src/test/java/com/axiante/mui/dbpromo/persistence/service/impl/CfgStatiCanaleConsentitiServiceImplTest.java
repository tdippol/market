package com.axiante.mui.dbpromo.persistence.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.dao.CfgStatiCanaleConsentDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiCanaleConsentEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgStatiCanaleConsentitiServiceImplTest {
    @Mock
    private CfgStatiCanaleConsentDAO dao;

    @Mock
    private CanalePromozioneEntity canale;

    @Mock
    private StatoPromozioneEntity stato;

    @Mock
    private CfgStatiCanaleConsentEntity cfgStatoCanale;

    @Mock
    private CfgAzioniEntity cfgAzione1;

    @Mock
    private CfgAzioniEntity cfgAzione2;

    @InjectMocks
    private CfgStatiCanaleConsentitiServiceImpl service;

    private List<CfgAzioniEntity> cfgAzioni = Arrays.asList(cfgAzione1, cfgAzione2);
    private List<String> codiciAzioni = Arrays.asList("FOO", "BAR", "BAZ");

    @Before
    public void setUp() throws Exception {
        when(dao.findByCanaleAndStato(canale, stato)).thenReturn(cfgStatoCanale);
        when(dao.findAzioniConsentByCanale(canale)).thenReturn(cfgAzioni);
        when(dao.findAzioniConsentByCanaleAndStato(canale, stato)).thenReturn(cfgAzioni);
        when(dao.findCodiciAzioniConsentiteByCanaleAndStato(canale, stato)).thenReturn(codiciAzioni);
    }

    @Test
    public void findByCanaleAndStato() {
        assertEquals(cfgStatoCanale, service.findByCanaleAndStato(canale, stato));
        verify(dao, times(1)).findByCanaleAndStato(canale, stato);
    }

    @Test
    public void findAzioniConsentByCanale() {
        assertEquals(cfgAzioni, service.findAzioniConsentByCanale(canale));
        verify(dao, times(1)).findAzioniConsentByCanale(canale);
    }

    @Test
    public void findAzioniConsentByCanaleAndStato() {
        assertEquals(cfgAzioni, service.findAzioniConsentByCanaleAndStato(canale, stato));
        verify(dao, times(1)).findAzioniConsentByCanaleAndStato(canale, stato);
    }

    @Test
    public void findCodiciAzioniConsentiteByCanaleAndStato() {
        assertEquals(codiciAzioni, service.findCodiciAzioniConsentiteByCanaleAndStato(canale, stato));
        verify(dao, times(1)).findCodiciAzioniConsentiteByCanaleAndStato(canale, stato);
    }
}