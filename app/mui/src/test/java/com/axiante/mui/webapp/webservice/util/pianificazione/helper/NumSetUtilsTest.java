package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
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
public class NumSetUtilsTest extends AbstractNumSetUtilsTest {
    @Mock
    Instance<PromoService> promoServiceInstance;

    @Mock
    PromoService promoService;

    @Mock
    private MeccanicheEntity meccanica;

    @Mock
    private CfgConfHeaderEntity cfgConfHeader;

    @InjectMocks
    @Spy
    private NumSetUtils numSetUtils;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private CfgPianificazioneCampiEntity cfgPianificazioneCampo;
    private PromozioneTestataEntity testata;

    @Before
    public void setUp() throws Exception {
        cfgPianificazioneCampo = mockCfgPianificazioneCampo(PianificazioneConstants.REFERENCE_NUM_SET_COLUMN);
        testata = mockPromozioneTestata(mock(CanalePromozioneEntity.class));
        when(promoServiceInstance.get()).thenReturn(promoService);
    }

    @Test
    public void getNumSet_whenNoFieldConfigured_shouldReturnNull() throws Exception {
        assertNull(numSetUtils.getNumSet(testata, meccanica, cfgConfHeader));
    }

    @Test
    public void getNumSet_whenListaNull_shouldReturnNull() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione(null, cfgPianificazioneCampo, "1");
        when(cfgConfHeader.getMuiCfgPianificaziones()).thenReturn(Collections.singleton(cfgPianificazione));
        assertNull(numSetUtils.getNumSet(testata, meccanica, cfgConfHeader));
    }

    @Test
    public void getNumSet_whenListaEmpty_shouldReturnNull() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("", cfgPianificazioneCampo, "1");
        when(cfgConfHeader.getMuiCfgPianificaziones()).thenReturn(Collections.singleton(cfgPianificazione));
        assertNull(numSetUtils.getNumSet(testata, meccanica, cfgConfHeader));
    }

    @Test
    public void getNumSet_whenConfiguredListMalformed_shouldReturnNull() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("[42-69]", cfgPianificazioneCampo, "1");
        when(cfgConfHeader.getMuiCfgPianificaziones()).thenReturn(Collections.singleton(cfgPianificazione));
        assertNull(numSetUtils.getNumSet(testata, meccanica, cfgConfHeader));
    }

    @Test
    public void getNum_whenNumSetNotExistsInOverlapped_shouldReturnFirstValueInRange() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("[42..69]", cfgPianificazioneCampo, "1");
        when(cfgConfHeader.getMuiCfgPianificaziones()).thenReturn(Collections.singleton(cfgPianificazione));
        assertEquals("42", numSetUtils.getNumSet(testata, meccanica, cfgConfHeader));
    }

    @Test
    public void getNum_whenNumSetExistsInOverlapperd_shouldReturnFirstValueAvailable() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("[42..69]", cfgPianificazioneCampo, "1");
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        PromozionePianificazioneEntity pianificazione1 = mockPianificazione("42");
        PromozionePianificazioneEntity pianificazione2 = mockPianificazione("43");
        PromozionePianificazioneEntity pianificazione3 = mockPianificazione("60");
        PromozioneTestataEntity promoOverlapped1 = mockPromozioneTestata(canale,
                Stream.of(pianificazione1, pianificazione3).collect(Collectors.toSet()));
        PromozioneTestataEntity promoOverlapped2 = mockPromozioneTestata(canale,
                Stream.of(pianificazione2).collect(Collectors.toSet()));
        List<PromozioneTestataEntity> overlappingPromo = Stream.of(promoOverlapped1, promoOverlapped2)
                .collect(Collectors.toList());
        when(cfgConfHeader.getMuiCfgPianificaziones()).thenReturn(Collections.singleton(cfgPianificazione));
        when(promoService.findOverlappingPromo(testata, null)).thenReturn(overlappingPromo);
        assertEquals("44", numSetUtils.getNumSet(testata, meccanica, cfgConfHeader));
        verify(promoService, times(1)).findOverlappingPromo(testata, null);
    }

    @Test
    public void getNum_whenNoNumSetAvailableRamaining_shouldReturnNull() throws Exception {
        CfgPianificazioneEntity cfgPianificazione = mockCfgPianificazione("[42..44]", cfgPianificazioneCampo, "1");
        CanalePromozioneEntity canale = mockCanalePromozione(true);
        testata = mockPromozioneTestata(canale);
        PromozionePianificazioneEntity pianificazione1 = mockPianificazione("42");
        PromozionePianificazioneEntity pianificazione2 = mockPianificazione("43");
        PromozionePianificazioneEntity pianificazione3 = mockPianificazione("44");
        PromozioneTestataEntity promoOverlapped1 = mockPromozioneTestata(canale,
                Stream.of(pianificazione1, pianificazione3).collect(Collectors.toSet()));
        PromozioneTestataEntity promoOverlapped2 = mockPromozioneTestata(canale,
                Stream.of(pianificazione2).collect(Collectors.toSet()));
        List<PromozioneTestataEntity> overlappingPromo = Stream.of(promoOverlapped1, promoOverlapped2)
                .collect(Collectors.toList());
        when(cfgConfHeader.getMuiCfgPianificaziones()).thenReturn(Collections.singleton(cfgPianificazione));
        when(promoService.findOverlappingPromo(testata, null)).thenReturn(overlappingPromo);
        assertNull(numSetUtils.getNumSet(testata, meccanica, cfgConfHeader));
        verify(promoService, times(1)).findOverlappingPromo(testata, null);
    }

    @Test
    public void getAvailable_givenNullTestata_shouldThrowException() {
        ex.expect(NullPointerException.class);
        numSetUtils.getAvailable(null, "foo", null);
    }

    @Test
    public void getAvailable_whenConfiguredListaNull_shouldReturnEmptyList() {
        assertTrue(numSetUtils.getAvailable(mock(PromozioneTestataEntity.class), null, null).isEmpty());
    }

    @Test
    public void getAvailable_whenConfiguredListaEmpty_shouldReturnEmptyList() {
        assertTrue(numSetUtils.getAvailable(mock(PromozioneTestataEntity.class), "", null).isEmpty());
    }

    @Test
    public void getAvailable_whenConfiguredListMalformed_shouldReturnEmptyList() {
        assertTrue(numSetUtils.getAvailable(mock(PromozioneTestataEntity.class), "[42-69]", null).isEmpty());
    }

    @Test
    public void getAvailable_whenAllValuesUsed_shouldReturnEmptyList() {
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
        when(promoService.findOverlappingPromo(eq(testata), any())).thenReturn(overlappingPromo);
        assertTrue(numSetUtils.getAvailable(testata, "[800..802]", null).isEmpty());
    }

    @Test
    public void getAvailable() {
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
        when(promoService.findOverlappingPromo(eq(testata), any())).thenReturn(overlappingPromo);
        List<Integer> availableValues = numSetUtils.getAvailable(testata, "[800..810]", null);
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
