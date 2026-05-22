package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NumSetUtilsPrecaricateTest extends AbstractNumSetUtilsTest {
    @Mock
    Instance<PromoService> promoServiceInstance;

    @Mock
    PromoService promoService;

    @Mock
    private MeccanicheEntity meccanica;

    @InjectMocks
    @Spy
    private NumSetUtils numSetUtils;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CfgPianificazioneCampiEntity cfgPianificazioneCampo;
    private PromozioneTestataEntity testata;
    private List<String> codiciMeccanica = Stream.of("M141", "M142", "M143").collect(Collectors.toList());

    @Before
    public void setUp() throws Exception {
        cfgPianificazioneCampo = mockCfgPianificazioneCampo(PianificazioneConstants.REFERENCE_NUM_SET_COLUMN);
        when(promoServiceInstance.get()).thenReturn(promoService);
    }

    @Test
    public void getNumSetPrecaricate_whenFlagDisabledOnChannel_shouldUseCommonAlgorithm() throws Exception {
        CfgPianificazioneEntity cfgPianificazione1 = mockCfgPianificazione("[800..899]", cfgPianificazioneCampo, "1");
        CfgPianificazioneEntity cfgPianificazione2 = mockCfgPianificazione(null, null, "1");
        CfgConfHeaderEntity cfgConfHeader = mockCfgConfHeader(Stream.of(cfgPianificazione2, cfgPianificazione1)
                .collect(Collectors.toSet()));
        CanalePromozioneEntity canale = mockCanalePromozione(false);
        testata = mockPromozioneTestata(canale);
        final String numSet = numSetUtils.getNumSetPrecaricate(testata, meccanica, cfgConfHeader);
        assertEquals("800", numSet);
        verify(numSetUtils, times(1)).getNumSet(testata, meccanica, cfgConfHeader);
    }

    @Test
    public void getNumSetPrecaricate_whenNoFieldConfigured_shouldReturnNull() throws Exception {
        CfgPianificazioneCampiEntity cfgPianificazioneCampo = mockCfgPianificazioneCampo("FOOBAR");
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione(null, cfgPianificazioneCampo, "1");
        CfgConfHeaderEntity cfgConfHeader = mockCfgConfHeader(Collections.singleton(cfgPianificazione));
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        assertNull(numSetUtils.getNumSetPrecaricate(testata, meccanica, cfgConfHeader));
        verify(numSetUtils, never()).getNumSet(testata, meccanica, cfgConfHeader);
        verify(promoService, never()).findOverlappingByAnnoAndCodiceMeccanicaWithOffset(
                any(PromozioneTestataEntity.class), anyList());
    }

    @Test
    public void getNumSetPrecaricate_whenListaNull_shouldReturnNull() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione(null, cfgPianificazioneCampo, "1");
        CfgConfHeaderEntity cfgConfHeader = mockCfgConfHeader(Collections.singleton(cfgPianificazione));
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        assertNull(numSetUtils.getNumSetPrecaricate(testata, meccanica, cfgConfHeader));
        verify(numSetUtils, never()).getNumSet(testata, meccanica, cfgConfHeader);
        verify(promoService, never()).findOverlappingByAnnoAndCodiceMeccanicaWithOffset(
                any(PromozioneTestataEntity.class), anyList());
    }

    @Test
    public void getNumSetPrecaricate_whenListaEmpty_shouldReturnNull() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("", cfgPianificazioneCampo, "1");
        CfgConfHeaderEntity cfgConfHeader = mockCfgConfHeader(Collections.singleton(cfgPianificazione));
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        assertNull(numSetUtils.getNumSetPrecaricate(testata, meccanica, cfgConfHeader));
        verify(numSetUtils, never()).getNumSet(testata, meccanica, cfgConfHeader);
        verify(promoService, never()).findOverlappingByAnnoAndCodiceMeccanicaWithOffset(
                any(PromozioneTestataEntity.class), anyList());
    }

    @Test
    public void getNumSetPrecaricate_whenConfiguredListMalformed_shouldReturnNull() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("[800-810]", cfgPianificazioneCampo, "1");
        CfgConfHeaderEntity cfgConfHeader = mockCfgConfHeader(Collections.singleton(cfgPianificazione));
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        assertNull(numSetUtils.getNumSetPrecaricate(testata, meccanica, cfgConfHeader));
        verify(numSetUtils, never()).getNumSet(testata, meccanica, cfgConfHeader);
        verify(promoService, never()).findOverlappingByAnnoAndCodiceMeccanicaWithOffset(
                any(PromozioneTestataEntity.class), anyList());
    }

    @Test
    public void getNumSetPrecaricate_whenNumSetNotExistsInOverlapper_shouldReturnFirstValueInRange() throws Exception {
        CfgPianificazioneEntity cfgPianificazione1 = mockCfgPianificazione("[800..810]", cfgPianificazioneCampo, "1");
        CfgPianificazioneEntity cfgPianificazione2 = mockCfgPianificazione(null, null, "1");
        CfgConfHeaderEntity cfgConfHeader = mockCfgConfHeader(Stream.of(cfgPianificazione1, cfgPianificazione2, null)
                .collect(Collectors.toSet()));
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        when(promoService.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(testata, codiciMeccanica))
                .thenReturn(Collections.emptyList());
        String numSet = numSetUtils.getNumSetPrecaricate(testata, meccanica, cfgConfHeader);
        assertEquals("800", numSet);
        verify(numSetUtils, never()).getNumSet(testata, meccanica, cfgConfHeader);
        verify(promoService, times(1))
                .findOverlappingByAnnoAndCodiceMeccanicaWithOffset(testata, codiciMeccanica);
    }

    @Test
    public void getNumSetPrecaricate_whenNumSetExistsInOverlapperAndMaxPlusOneAvailable_shouldReturnMaxPlusOne() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("[800..810]", cfgPianificazioneCampo, "1");
        CfgConfHeaderEntity cfgConfHeader = mockCfgConfHeader(Collections.singleton(cfgPianificazione));
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        PromozionePianificazioneEntity pianificazione1 = mockPianificazione("800");
        PromozionePianificazioneEntity pianificazione2 = mockPianificazione("801");
        PromozionePianificazioneEntity pianificazione3 = mockPianificazione("802");
        PromozioneTestataEntity promoOverlapped1 = mockPromozioneTestata(canale,
                Stream.of(pianificazione1, pianificazione3).collect(Collectors.toSet()));
        PromozioneTestataEntity promoOverlapped2 = mockPromozioneTestata(canale,
                Stream.of(pianificazione2, null).collect(Collectors.toSet()));
        List<PromozioneTestataEntity> overlappingPromo = Stream.of(promoOverlapped1, promoOverlapped2)
                .collect(Collectors.toList());
        when(promoService.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(testata, codiciMeccanica))
                .thenReturn(overlappingPromo);
        String numSet = numSetUtils.getNumSetPrecaricate(testata, meccanica, cfgConfHeader);
        assertEquals("803", numSet);
        verify(numSetUtils, never()).getNumSet(testata, meccanica, cfgConfHeader);
        verify(promoService, times(1))
                .findOverlappingByAnnoAndCodiceMeccanicaWithOffset(testata, codiciMeccanica);
    }

    @Test
    public void getNumSetPrecaricate_whenNumSetExistsInOverlapperAndMaxPlusOneNotAvailable_shouldReturnMinFromRemaining() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("[800..810]", cfgPianificazioneCampo, "1");
        CfgConfHeaderEntity cfgConfHeader = mockCfgConfHeader(Collections.singleton(cfgPianificazione));
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        PromozionePianificazioneEntity pianificazione1 = mockPianificazione("800");
        PromozionePianificazioneEntity pianificazione2 = mockPianificazione("806");
        PromozionePianificazioneEntity pianificazione3 = mockPianificazione("810");
        PromozioneTestataEntity promoOverlapped1 = mockPromozioneTestata(canale,
                Stream.of(pianificazione1, pianificazione3).collect(Collectors.toSet()));
        PromozioneTestataEntity promoOverlapped2 = mockPromozioneTestata(canale,
                Stream.of(pianificazione2).collect(Collectors.toSet()));
        List<PromozioneTestataEntity> overlappingPromo = Stream.of(promoOverlapped1, promoOverlapped2)
                .collect(Collectors.toList());
        when(promoService.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(testata, codiciMeccanica))
                .thenReturn(overlappingPromo);
        String numSet = numSetUtils.getNumSetPrecaricate(testata, meccanica, cfgConfHeader);
        assertEquals("801", numSet);
        verify(numSetUtils, never()).getNumSet(testata, meccanica, cfgConfHeader);
        verify(promoService, times(1))
                .findOverlappingByAnnoAndCodiceMeccanicaWithOffset(testata, codiciMeccanica);
    }

    @Test
    public void getNumSetPrecaricate_whenNoNumSetAvailableRamaining_shouldReturnNull() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("[800..802]", cfgPianificazioneCampo, "1");
        CfgConfHeaderEntity cfgConfHeader = mockCfgConfHeader(Collections.singleton(cfgPianificazione));
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        PromozionePianificazioneEntity pianificazione1 = mockPianificazione("800");
        PromozionePianificazioneEntity pianificazione2 = mockPianificazione("801");
        PromozionePianificazioneEntity pianificazione3 = mockPianificazione("802");
        PromozioneTestataEntity promoOverlapped1 = mockPromozioneTestata(canale,
                Stream.of(pianificazione1, pianificazione3).collect(Collectors.toSet()));
        PromozioneTestataEntity promoOverlapped2 = mockPromozioneTestata(canale,
                Stream.of(pianificazione2).collect(Collectors.toSet()));
        List<PromozioneTestataEntity> overlappingPromo = Stream.of(promoOverlapped1, promoOverlapped2)
                .collect(Collectors.toList());
        when(promoService.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(testata, codiciMeccanica))
                .thenReturn(overlappingPromo);
        assertNull(numSetUtils.getNumSetPrecaricate(testata, meccanica, cfgConfHeader));
        verify(numSetUtils, never()).getNumSet(testata, meccanica, cfgConfHeader);
        verify(promoService, times(1))
                .findOverlappingByAnnoAndCodiceMeccanicaWithOffset(testata, codiciMeccanica);
    }

    @Test
    public void getAvailablePrecaricate_givenNullCfgPianificazione_shouldThrowException() throws Exception {
        ex.expect(NullPointerException.class);
        numSetUtils.getAvailablePrecaricate(mock(PromozioneTestataEntity.class), (String)null);
    }

    @Test
    public void getAvailablePrecaricate_whenFlagDisabledOnChannel_shouldUseCommonAlgorithm() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("[800..810]", cfgPianificazioneCampo, "1");
        CanalePromozioneEntity canale = mockCanalePromozione(false);
        testata = mockPromozioneTestata(canale);
        PromozionePianificazioneEntity pianificazione1 = mockPianificazione("800");
        PromozionePianificazioneEntity pianificazione2 = mockPianificazione("801");
        PromozionePianificazioneEntity pianificazione3 = mockPianificazione("807");
        PromozioneTestataEntity promoOverlapped1 = mockPromozioneTestata(canale,
                Stream.of(pianificazione1, pianificazione3).collect(Collectors.toSet()));
        PromozioneTestataEntity promoOverlapped2 = mockPromozioneTestata(canale,
                Stream.of(pianificazione2).collect(Collectors.toSet()));
        List<PromozioneTestataEntity> overlappingPromo = Stream.of(promoOverlapped1, promoOverlapped2)
                .collect(Collectors.toList());
        when(promoService.findOverlappingPromo(testata, null)).thenReturn(overlappingPromo);
        final List<Integer> availableValues = numSetUtils.getAvailablePrecaricate(testata, cfgPianificazione);
        assertEquals(8, availableValues.size());
        assertFalse(availableValues.contains(800));
        assertFalse(availableValues.contains(801));
        assertTrue(availableValues.contains(802));
        assertTrue(availableValues.contains(803));
        assertTrue(availableValues.contains(804));
        assertTrue(availableValues.contains(805));
        assertTrue(availableValues.contains(806));
        assertFalse(availableValues.contains(807));
        assertTrue(availableValues.contains(808));
        assertTrue(availableValues.contains(809));
        assertTrue(availableValues.contains(810));
        Integer minValue = Collections.min(availableValues);
        Integer maxValue = Collections.max(availableValues);
        assertEquals(802, (long) minValue);
        assertEquals(810, (long) maxValue);
        verify(numSetUtils, times(1)).getAvailable(testata, "[800..810]", null);
        verify(promoService, times(1)).findOverlappingPromo(testata, null);
        verify(promoService, never()).findOverlappingByAnnoAndCodiceMeccanicaWithOffset(any(), any());
    }

    @Test
    public void getAvailablePrecaricate_givenNullPromozione_shouldThrowException() throws Exception {
        ex.expect(NullPointerException.class);
        numSetUtils.getAvailablePrecaricate(null, mock(CfgPianificazioneEntity.class));
    }

    @Test
    public void getAvailablePrecaricate_whenConfiguredListaNull_shouldReturnEmptyList() throws Exception {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        when(cfgPianificazione.getLista()).thenReturn(null);
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        assertTrue(numSetUtils.getAvailablePrecaricate(testata, cfgPianificazione).isEmpty());
    }

    @Test
    public void getAvailablePrecaricate_whenConfiguredListaEmpty_shouldReturnEmptyList() throws Exception {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        when(cfgPianificazione.getLista()).thenReturn("");
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        assertTrue(numSetUtils.getAvailablePrecaricate(testata, cfgPianificazione).isEmpty());
    }

    @Test
    public void getAvailablePrecaricate_whenConfiguredListMalformed_shouldReturnEmptyList() throws Exception {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        when(cfgPianificazione.getLista()).thenReturn("[42-69]");
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        assertTrue(numSetUtils.getAvailablePrecaricate(testata, cfgPianificazione).isEmpty());
    }

    @Test
    public void getAvailablePrecaricate() {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("[800..810]", cfgPianificazioneCampo, "1");
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        PromozionePianificazioneEntity pianificazione1 = mockPianificazione("800");
        PromozionePianificazioneEntity pianificazione2 = mockPianificazione("801");
        PromozionePianificazioneEntity pianificazione3 = mockPianificazione("802");
        PromozioneTestataEntity promoOverlapped1 = mockPromozioneTestata(canale,
                Stream.of(pianificazione1, pianificazione3).collect(Collectors.toSet()));
        PromozioneTestataEntity promoOverlapped2 = mockPromozioneTestata(canale,
                Stream.of(pianificazione2).collect(Collectors.toSet()));
        List<PromozioneTestataEntity> overlappingPromo = Stream.of(promoOverlapped1, promoOverlapped2)
                .collect(Collectors.toList());
        when(promoService.findOverlappingByAnnoAndCodiceMeccanicaWithOffset(testata, codiciMeccanica))
                .thenReturn(overlappingPromo);
        List<Integer> availableValues = numSetUtils.getAvailablePrecaricate(testata, cfgPianificazione);
        assertEquals(8, availableValues.size());
        assertFalse(availableValues.contains(800));
        assertFalse(availableValues.contains(801));
        assertFalse(availableValues.contains(802));
        assertTrue(availableValues.contains(803));
        assertTrue(availableValues.contains(804));
        assertTrue(availableValues.contains(805));
        assertTrue(availableValues.contains(806));
        assertTrue(availableValues.contains(807));
        assertTrue(availableValues.contains(808));
        assertTrue(availableValues.contains(809));
        assertTrue(availableValues.contains(810));
        Integer minValue = Collections.min(availableValues);
        Integer maxValue = Collections.max(availableValues);
        assertEquals(803, (long) minValue);
        assertEquals(810, (long) maxValue);
    }
}
