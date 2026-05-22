package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.CanaleLastProgService;
import com.axiante.mui.dbpromo.persistence.service.CfgParametriService;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import com.axiante.mui.dbpromo.persistence.service.PromoPubblicazioneTestataService;
import com.axiante.mui.dbpromo.persistence.service.PromoStatiConsentitiService;
import com.axiante.mui.dbpromo.persistence.service.PromoStatiTransizioneService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneMeccanicheService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneNegozioService;
import com.axiante.mui.dbpromo.persistence.service.PromozionePianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneStatoService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTestataService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTipoTerminaleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UuiUtilityServiceImplTest {
    @Mock
    CanaleLastProgService canaleLastProgService;

    @Mock
    CfgParametriService cfgParametriService;

    @Mock
    CfgPianificazioneService cfgPianificazioneService;

    @Mock
    CreatePromotionService createPromotionService;

    @Mock
    PromoPubblicazioneTestataService promoPubblicazioneTestataService;

    @Mock
    PromoStatiConsentitiService promoStatiConsentitiService;

    @Mock
    PromoStatiTransizioneService promoStatiTransizioneService;

    @Mock
    PromozioneMeccanicheService promozioneMeccanicheService;

    @Mock
    PromozioneNegozioService promozioneNegozioService;

    @Mock
    PromozionePianificazioneService promozionePianificazioneService;

    @Mock
    PromozioneStatoService promozioneStatoService;

    @Mock
    PromozioneTestataService promozioneTestataService;

    @Mock
    PromozioneTipoTerminaleService proTerminaleService;

    @InjectMocks
    private UuiUtilityServiceImpl service;

    @Before
    public void setUp() throws Exception {
        doNothing().when(canaleLastProgService).ensureAllUuidAreFilled();
        doNothing().when(cfgParametriService).ensureAllUuidAreFilled();
        doNothing().when(cfgPianificazioneService).ensureAllUuidAreFilled();
        doNothing().when(createPromotionService).ensureAllUuidAreFilled();
        doNothing().when(promoPubblicazioneTestataService).ensureAllUuidAreFilled();
        doNothing().when(promoStatiConsentitiService).ensureAllUuidAreFilled();
        doNothing().when(promoStatiTransizioneService).ensureAllUuidAreFilled();
        doNothing().when(promozioneMeccanicheService).ensureAllUuidAreFilled();
        doNothing().when(promozioneNegozioService).ensureAllUuidAreFilled();
        doNothing().when(promozionePianificazioneService).ensureAllUuidAreFilled();
        doNothing().when(promozioneStatoService).ensureAllUuidAreFilled();
        doNothing().when(promozioneTestataService).ensureAllUuidAreFilled();
        doNothing().when(proTerminaleService).ensureAllUuidAreFilled();
    }

    @Test
    public void ensureAllNonEmptyUuid() {
        service.ensureAllNonEmptyUuid();
        verify(canaleLastProgService, times(1)).ensureAllUuidAreFilled();
        verify(cfgParametriService, times(1)).ensureAllUuidAreFilled();
        verify(cfgPianificazioneService, times(1)).ensureAllUuidAreFilled();
        verify(createPromotionService, times(1)).ensureAllUuidAreFilled();
        verify(promoPubblicazioneTestataService, times(1)).ensureAllUuidAreFilled();
        verify(promoStatiConsentitiService, times(1)).ensureAllUuidAreFilled();
        verify(promoStatiTransizioneService, times(1)).ensureAllUuidAreFilled();
        verify(promozioneMeccanicheService, times(1)).ensureAllUuidAreFilled();
        verify(promozioneNegozioService, times(1)).ensureAllUuidAreFilled();
        verify(promozionePianificazioneService, times(1)).ensureAllUuidAreFilled();
        verify(promozioneStatoService, times(1)).ensureAllUuidAreFilled();
        verify(promozioneTestataService, times(1)).ensureAllUuidAreFilled();
        verify(proTerminaleService, times(1)).ensureAllUuidAreFilled();
    }
}