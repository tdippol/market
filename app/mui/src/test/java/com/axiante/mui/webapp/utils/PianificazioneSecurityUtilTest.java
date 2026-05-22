package com.axiante.mui.webapp.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.CanaleSecurityEnum;
import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleSecEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleSecService;
import com.axiante.mui.persistence.service.ArticoloService;
import com.axiante.mui.persistence.service.GrmService;
import com.axiante.mui.persistence.service.GruppoService;
import com.axiante.mui.persistence.service.RepartoService;
import com.axiante.mui.webapp.business.OwnershipService;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.enterprise.inject.Instance;
import javax.persistence.NonUniqueResultException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianificazioneSecurityUtilTest {

    @Mock
    private Instance<OwnershipService> ownershipServiceInstance;

    @Mock
    private OwnershipService ownershipService;

    @Mock
    private Instance<CfgCanaleSecService> canaleSecServiceInstance;

    @Mock
    private CfgCanaleSecService canaleSecService;

    @Mock
    private Instance<RepartoService> repartoServiceInstance;

    @Mock
    private RepartoService repartoService;

    @Mock
    private Instance<GrmService> grmServiceInstance;

    @Mock
    private GrmService grmService;

    @Mock
    private Instance<GruppoService> gruppoServiceInstance;

    @Mock
    private GruppoService gruppoService;

    @Mock
    private Instance<ArticoloService> articoloServiceInstance;

    @Mock
    private ArticoloService articoloService;

    @Mock
    private PromozioneTestataEntity testata;

    @Mock
    private CanalePromozioneEntity canale;

    @Mock
    private PromozionePianificazioneEntity pianificazione;

    @InjectMocks
    private PianificazioneSecurityUtil util;

    private CfgCanaleSecEntity cfgCanaleSec;
    private List<String> codiciGruppo = Arrays.asList("GR1", "GR2");

    @Before
    public void setUp() {
        when(ownershipServiceInstance.get()).thenReturn(ownershipService);
        when(canaleSecServiceInstance.get()).thenReturn(canaleSecService);
        when(repartoServiceInstance.get()).thenReturn(repartoService);
        when(grmServiceInstance.get()).thenReturn(grmService);
        when(gruppoServiceInstance.get()).thenReturn(gruppoService);
        when(articoloServiceInstance.get()).thenReturn(articoloService);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
    }

    @Test(expected = NullPointerException.class)
    public void isReadable_givenNullPianificazione_shouldThrowException() {
        util.isReadable(null, codiciGruppo);
    }

    @Test
    public void isReadable_whenSomethingWentWrong_shouldReturnFalse() {
        when(canaleSecService.findByCanale(canale)).thenThrow(NonUniqueResultException.class);
        assertFalse(util.isReadable(pianificazione, codiciGruppo));
    }

    @Test
    public void isReadable_whenOwner_givenChannelWithSecurityAll_shouldReturnTrue() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(CanaleSecurityEnum.ALL, null, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        mockPianificazione();
        assertTrue(util.isReadable(pianificazione, codiciGruppo));
    }

    @Test
    public void isReadable_whenOwner_givenChannelWithSecurityNone_shouldReturnFalse() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(CanaleSecurityEnum.NONE, null, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        mockPianificazione();
        assertFalse(util.isReadable(pianificazione, codiciGruppo));
    }

    @Test
    public void isReadable_whenOwner_givenChannelWithSecuritySecuredAndArticoloWithCompratoreInAssociatedGroups_shouldReturnTrue() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(CanaleSecurityEnum.SECURED, null, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.ARTICOLO, "1");
        assertTrue(util.isReadable(pianificazione, codiciGruppo));
        verify(articoloService, times(1))
                .hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenOwner_givenChannelWithSecuritySecuredAndArticoloWithCompratoreNotInAssociatedGroups_shouldReturnFalse() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(CanaleSecurityEnum.SECURED, null, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.ARTICOLO, "1");
        assertFalse(util.isReadable(pianificazione, codiciGruppo));
        verify(articoloService, times(1))
                .hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenOwner_givenChannelWithSecuritySecuredAndRepartoInAssociatedGroups_shouldReturnTrue() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(CanaleSecurityEnum.SECURED, null, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(repartoService.hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.REPARTO, "1");
        assertTrue(util.isReadable(pianificazione, codiciGruppo));
        verify(repartoService, times(1))
                .hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenOwner_givenChannelWithSecuritySecuredAndRepartoNotInAssociatedGroups_shouldReturnFalse() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(CanaleSecurityEnum.SECURED, null, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(repartoService.hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.REPARTO, "1");
        assertFalse(util.isReadable(pianificazione, codiciGruppo));
        verify(repartoService, times(1))
                .hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenOwner_givenChannelWithSecuritySecuredAndGrmInAssociatedGroups_shouldReturnTrue() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(CanaleSecurityEnum.SECURED, null, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(grmService.hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.GRM, "1");
        assertTrue(util.isReadable(pianificazione, codiciGruppo));
        verify(grmService, times(1))
                .hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenOwner_givenChannelWithSecuritySecuredAndGrmNotInAssociatedGroups_shouldReturnFalse() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(CanaleSecurityEnum.SECURED, null, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(grmService.hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.GRM, "1");
        assertFalse(util.isReadable(pianificazione, codiciGruppo));
        verify(grmService, times(1))
                .hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenOwner_givenChannelWithSecuritySecuredAndAccessoTotaleInGroups_shouldReturnTrue() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(CanaleSecurityEnum.SECURED, null, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(gruppoService.hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.TOTALE, null);
        assertTrue(util.isReadable(pianificazione, codiciGruppo));
        verify(gruppoService, times(1))
                .hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenOwner_givenChannelWithSecuritySecuredAndAccessoTotaleNullInGroups_shouldReturnFalse() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(CanaleSecurityEnum.SECURED, null, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(gruppoService.hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.TOTALE, null);
        assertFalse(util.isReadable(pianificazione, codiciGruppo));
        verify(gruppoService, times(1))
                .hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenNotOwner_givenChannelWithSecurityAll_shouldReturnTrue() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(false);
        cfgCanaleSec = mockCanale(null, null, CanaleSecurityEnum.ALL, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        mockPianificazione();
        assertTrue(util.isReadable(pianificazione, codiciGruppo));
    }

    @Test
    public void isReadable_whenNotOwner_givenChannelWithSecurityNone_shouldReturnFalse() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(false);
        cfgCanaleSec = mockCanale(null, null, CanaleSecurityEnum.NONE, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        mockPianificazione();
        assertFalse(util.isReadable(pianificazione, codiciGruppo));
    }

    @Test
    public void isReadable_whenNotOwner_givenChannelWithSecuritySecuredAndArticoloWithCompratoreInAssociatedGroups_shouldReturnTrue() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(false);
        cfgCanaleSec = mockCanale(null, null, CanaleSecurityEnum.SECURED, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.ARTICOLO, "1");
        assertTrue(util.isReadable(pianificazione, codiciGruppo));
        verify(articoloService, times(1))
                .hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenNotOwner_givenChannelWithSecuritySecuredAndArticoloWithCompratoreNotInAssociatedGroups_shouldReturnFalse() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(false);
        cfgCanaleSec = mockCanale(null, null, CanaleSecurityEnum.SECURED, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.ARTICOLO, "1");
        assertFalse(util.isReadable(pianificazione, codiciGruppo));
        verify(articoloService, times(1))
                .hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenNotOwner_givenChannelWithSecuritySecuredAndRepartoInAssociatedGroups_shouldReturnTrue() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(false);
        cfgCanaleSec = mockCanale(null, null, CanaleSecurityEnum.SECURED, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(repartoService.hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.REPARTO, "1");
        assertTrue(util.isReadable(pianificazione, codiciGruppo));
        verify(repartoService, times(1))
                .hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenNotOwner_givenChannelWithSecuritySecuredAndRepartoNotInAssociatedGroups_shouldReturnFalse() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(false);
        cfgCanaleSec = mockCanale(null, null, CanaleSecurityEnum.SECURED, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(repartoService.hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.REPARTO, "1");
        assertFalse(util.isReadable(pianificazione, codiciGruppo));
        verify(repartoService, times(1))
                .hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenNotOwner_givenChannelWithSecuritySecuredAndGrmInAssociatedGroups_shouldReturnTrue() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(false);
        cfgCanaleSec = mockCanale(null, null, CanaleSecurityEnum.SECURED, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(grmService.hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.GRM, "1");
        assertTrue(util.isReadable(pianificazione, codiciGruppo));
        verify(grmService, times(1))
                .hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenNotOwner_givenChannelWithSecuritySecuredAndGrmNotInAssociatedGroups_shouldReturnFalse() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(false);
        cfgCanaleSec = mockCanale(null, null, CanaleSecurityEnum.SECURED, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(grmService.hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.GRM, "1");
        assertFalse(util.isReadable(pianificazione, codiciGruppo));
        verify(grmService, times(1))
                .hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenNotOwner_givenChannelWithSecuritySecuredAndAccessoTotaleInGroups_shouldReturnTrue() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(false);
        cfgCanaleSec = mockCanale(null, null, CanaleSecurityEnum.SECURED, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(gruppoService.hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.TOTALE, null);
        assertTrue(util.isReadable(pianificazione, codiciGruppo));
        verify(gruppoService, times(1))
                .hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void isReadable_whenNotOwner_givenChannelWithSecuritySecuredAndAccessoTotaleNullInGroups_shouldReturnFalse() {
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(false);
        cfgCanaleSec = mockCanale(null, null, CanaleSecurityEnum.SECURED, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(gruppoService.hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.READ))
                .thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.TOTALE, null);
        assertFalse(util.isReadable(pianificazione, codiciGruppo));
        verify(gruppoService, times(1))
                .hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.READ);
    }

    @Test(expected = NullPointerException.class)
    public void isWriteable_givenNullPianificazion_shouldThrowException() {
        util.isWriteable(null, codiciGruppo);
    }

    @Test
    public void isWriteable_whenSomethingWentWrong_shouldReturnFalse() {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(canaleSecService.findByCanale(canale)).thenThrow(NonUniqueResultException.class);
        assertFalse(util.isWriteable(pianificazione, codiciGruppo));
    }

    @Test
    public void isWriteable_whenOwner_givenChannelWithSecurityAll_shouldReturnTrue() {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.ALL, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        mockPianificazione();
        assertTrue(util.isWriteable(pianificazione, codiciGruppo));
    }

    @Test
    public void isWriteable_whenOwner_givenChannelWithSecurityNone_shouldReturnFalse() {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.NONE, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        mockPianificazione();
        assertFalse(util.isWriteable(pianificazione, codiciGruppo));
    }

    @Test
    public void isWriteable_whenOwner_givenChannelWithSecuritySecuredAndArticoloWithCompratoreInAssociatedGroups_shouldReturnTrue() {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.ARTICOLO, "1");
        assertTrue(util.isWriteable(pianificazione, codiciGruppo));
        verify(articoloService, times(1))
                .hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }

    @Test
    public void isWriteable_whenOwner_givenChannelWithSecuritySecuredAndArticoloWithCompratoreNotInAssociatedGroups_shouldReturnFalse() {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.ARTICOLO, "1");
        assertFalse(util.isWriteable(pianificazione, codiciGruppo));
        verify(articoloService, times(1))
                .hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }

    @Test
    public void isWriteable_whenOwner_givenChannelWithSecuritySecuredAndRepartoInAssociatedGroups_shouldReturnTrue() {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(repartoService.hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.REPARTO, "1");
        assertTrue(util.isWriteable(pianificazione, codiciGruppo));
        verify(repartoService, times(1))
                .hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }

    @Test
    public void isWriteable_whenOwner_givenChannelWithSecuritySecuredAndRepartoNotInAssociatedGroups_shouldReturnFalse() {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(repartoService.hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.REPARTO, "1");
        assertFalse(util.isWriteable(pianificazione, codiciGruppo));
        verify(repartoService, times(1))
                .hasAssociationWithIdRepartoAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }

    @Test
    public void isWriteable_whenOwner_givenChannelWithSecuritySecuredAndGrmInAssociatedGroups_shouldReturnTrue() {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(grmService.hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.GRM, "1");
        assertTrue(util.isWriteable(pianificazione, codiciGruppo));
        verify(grmService, times(1))
                .hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }

    @Test
    public void isWriteable_whenOwner_givenChannelWithSecuritySecuredAndGrmNotInAssociatedGroups_shouldReturnFalse() {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(grmService.hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.GRM, "1");
        assertFalse(util.isWriteable(pianificazione, codiciGruppo));
        verify(grmService, times(1))
                .hasAssociationWithIdGrmAndCodiciGruppo(1, codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }

    @Test
    public void isWriteable_whenOwner_givenChannelWithSecuritySecuredAndAccessoTotaleInGroups_shouldReturnTrue() {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(gruppoService.hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(true);
        mockPianificazione(PromoPianificazioneEnum.TOTALE, null);
        assertTrue(util.isWriteable(pianificazione, codiciGruppo));
        verify(gruppoService, times(1))
                .hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }

    @Test
    public void isWriteable_whenOwner_givenChannelWithSecuritySecuredAndAccessoTotaleNullInGroups_shouldReturnFalse() {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(gruppoService.hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.WRITE)).thenReturn(false);
        mockPianificazione(PromoPianificazioneEnum.TOTALE, null);
        assertFalse(util.isWriteable(pianificazione, codiciGruppo));
        verify(gruppoService, times(1))
                .hasAccessTotaleByCodiciGruppo(codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }

    @Test(expected = NullPointerException.class)
    public void isDeletable_givenNullPianificazione_shouldThrowException() {
        util.isDeletable(null, codiciGruppo);
    }

    @Test
    public void isDeletable_givenPianificazioneSetWithAllWritableChildren_shouldReturnTrue() {
        final PromozionePianificazioneEntity elemento1 = mockPianificazioneElemento("1");
        final PromozionePianificazioneEntity elemento2 = mockPianificazioneElemento("2");
        final PromozionePianificazioneEntity raggruppamento =
                mockPianificazioneWithChildren(PianificazioneRowTypeEnum.RAGGRUPPAMENTO, elemento1, elemento2);
        final PromozionePianificazioneEntity set =
                mockPianificazioneWithChildren(PianificazioneRowTypeEnum.SET, raggruppamento);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(true);
        when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(2L, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(true);
        assertTrue(util.isDeletable(set, codiciGruppo));
        verify(articoloService, times(1))
                .hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.WRITE);
        verify(articoloService, times(1))
                .hasAssociationWithIdArticoloAndCodiciGruppo(2L, codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }

    @Test
    public void isDeletable_givenPianificazioneSetWithNotAllWritableChildren_shouldReturnFalse() {
        final PromozionePianificazioneEntity elemento1 = mockPianificazioneElemento("1");
        final PromozionePianificazioneEntity elemento2 = mockPianificazioneElemento("2");
        final PromozionePianificazioneEntity raggruppamento =
                mockPianificazioneWithChildren(PianificazioneRowTypeEnum.RAGGRUPPAMENTO, elemento1, elemento2);
        final PromozionePianificazioneEntity set =
                mockPianificazioneWithChildren(PianificazioneRowTypeEnum.SET, raggruppamento);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        lenient().when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(false);
        lenient().when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(2L, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(true);
        assertFalse(util.isDeletable(set, codiciGruppo));
        verify(articoloService, atLeastOnce())
                .hasAssociationWithIdArticoloAndCodiciGruppo(anyLong(), eq(codiciGruppo), eq(PianificazioneSecurityEnum.WRITE));
        verify(articoloService, atMost(2))
                .hasAssociationWithIdArticoloAndCodiciGruppo(anyLong(), eq(codiciGruppo), eq(PianificazioneSecurityEnum.WRITE));
    }

    @Test
    public void isDeletable_givenPianificazioneSetWithoutChildrenTipoElemento_shouldReturnTrue() {
        final PromozionePianificazioneEntity raggruppamento =
                mockPianificazioneWithChildren(PianificazioneRowTypeEnum.RAGGRUPPAMENTO, (PromozionePianificazioneEntity[]) null);
        final PromozionePianificazioneEntity set =
                mockPianificazioneWithChildren(PianificazioneRowTypeEnum.SET, raggruppamento);
        assertTrue(util.isDeletable(set, codiciGruppo));
        verify(articoloService, never())
                .hasAssociationWithIdArticoloAndCodiciGruppo(anyLong(), eq(codiciGruppo), eq(PianificazioneSecurityEnum.WRITE));
    }

    @Test
    public void isDeletable_givenPianificazioneSetWithoutChildrenTipoRaggruppamento_shouldReturnTrue() {
        final PromozionePianificazioneEntity set =
                mockPianificazioneWithChildren(PianificazioneRowTypeEnum.SET, (PromozionePianificazioneEntity[]) null);
        assertTrue(util.isDeletable(set, codiciGruppo));
        verify(articoloService, never())
                .hasAssociationWithIdArticoloAndCodiciGruppo(anyLong(), eq(codiciGruppo), eq(PianificazioneSecurityEnum.WRITE));
    }

    @Test
    public void isDeletable_givenPianificazioneRaggruppamentoWithAllWritableChildren_shouldReturnTrue() {
        final PromozionePianificazioneEntity elemento1 = mockPianificazioneElemento("1");
        final PromozionePianificazioneEntity elemento2 = mockPianificazioneElemento("2");
        final PromozionePianificazioneEntity raggruppamento =
                mockPianificazioneWithChildren(PianificazioneRowTypeEnum.RAGGRUPPAMENTO, elemento1, elemento2);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(true);
        when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(2L, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(true);
        assertTrue(util.isDeletable(raggruppamento, codiciGruppo));
        verify(articoloService, times(1))
                .hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.WRITE);
        verify(articoloService, times(1))
                .hasAssociationWithIdArticoloAndCodiciGruppo(2L, codiciGruppo, PianificazioneSecurityEnum.WRITE);
    }

    @Test
    public void isDeletable_givenPianificazioneRaggruppamentoWithNotAllWritableChildren_shouldReturnFalse() {
        final PromozionePianificazioneEntity elemento1 = mockPianificazioneElemento("1");
        final PromozionePianificazioneEntity elemento2 = mockPianificazioneElemento("2");
        final PromozionePianificazioneEntity raggruppamento =
                mockPianificazioneWithChildren(PianificazioneRowTypeEnum.RAGGRUPPAMENTO, elemento1, elemento2);
        when(ownershipService.hasOwnership(testata, codiciGruppo)).thenReturn(true);
        cfgCanaleSec = mockCanale(null, CanaleSecurityEnum.SECURED, null, null);
        when(canaleSecService.findByCanale(canale)).thenReturn(cfgCanaleSec);
        lenient().when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(1L, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(false);
        lenient().when(articoloService.hasAssociationWithIdArticoloAndCodiciGruppo(2L, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                .thenReturn(true);
        assertFalse(util.isDeletable(raggruppamento, codiciGruppo));
        verify(articoloService, atLeastOnce())
                .hasAssociationWithIdArticoloAndCodiciGruppo(anyLong(), eq(codiciGruppo), eq(PianificazioneSecurityEnum.WRITE));
        verify(articoloService, atMost(2))
                .hasAssociationWithIdArticoloAndCodiciGruppo(anyLong(), eq(codiciGruppo), eq(PianificazioneSecurityEnum.WRITE));
    }

    @Test
    public void isDeletable_givenPianificazioneRaggruppamentoWithoutChildren_shouldReturnTrue() {
        final PromozionePianificazioneEntity raggruppamento =
                mockPianificazioneWithChildren(PianificazioneRowTypeEnum.RAGGRUPPAMENTO, (PromozionePianificazioneEntity[]) null);
        assertTrue(util.isDeletable(raggruppamento, codiciGruppo));
        verify(articoloService, never())
                .hasAssociationWithIdArticoloAndCodiciGruppo(anyLong(), eq(codiciGruppo), eq(PianificazioneSecurityEnum.WRITE));
    }

    private void mockPianificazione() {
        mockPianificazione(PromoPianificazioneEnum.ARTICOLO, "1");
    }

    private void mockPianificazione(PromoPianificazioneEnum tipoElemento, String codiceElemento) {
        when(pianificazione.getTipoElemento()).thenReturn(tipoElemento.getTipoElemento());
        when(pianificazione.getCodiceElemento()).thenReturn(codiceElemento);
    }

    private PromozionePianificazioneEntity mockPianificazioneElemento(String codiceElemento) {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn(PianificazioneRowTypeEnum.ELEMENTO.getTypeCode());
        final PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
        when(mock.getTipoElemento()).thenReturn(PromoPianificazioneEnum.ARTICOLO.getTipoElemento());
        when(mock.getCodiceElemento()).thenReturn(codiceElemento);
        when(mock.getPromozioneTestataEntity()).thenReturn(testata);
        when(mock.getTipoRiga()).thenReturn(tipoRiga);
        return mock;
    }

    private PromozionePianificazioneEntity mockPianificazioneWithChildren(PianificazioneRowTypeEnum rowType,
                                                                          PromozionePianificazioneEntity... children) {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        final PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
        when(mock.getTipoRiga()).thenReturn(tipoRiga);
        when(tipoRiga.getCodiceTipo()).thenReturn(rowType.getTypeCode());
        if (children != null) {
            when(mock.getChildren()).thenReturn(new HashSet<>(Arrays.asList(children)));
        } else {
            when(mock.getChildren()).thenReturn(Collections.emptySet());
        }
        return mock;
    }

    private CfgCanaleSecEntity mockCanale(CanaleSecurityEnum ownerRead, CanaleSecurityEnum ownerWrite,
                                          CanaleSecurityEnum otherRead, CanaleSecurityEnum otherWrite) {
        final CfgCanaleSecEntity mock = mock(CfgCanaleSecEntity.class);
        lenient().when(mock.getCanale()).thenReturn(canale);
        if (ownerRead != null) {
            when(mock.getOwnerRead()).thenReturn(ownerRead);
        }
        if (ownerWrite != null) {
            when(mock.getOwnerWrite()).thenReturn(ownerWrite);
        }
        if (otherRead != null) {
            when(mock.getOtherRead()).thenReturn(otherRead);
        }
        if (otherWrite != null) {
            when(mock.getOtherWrite()).thenReturn(otherWrite);
        }
        return mock;
    }
}