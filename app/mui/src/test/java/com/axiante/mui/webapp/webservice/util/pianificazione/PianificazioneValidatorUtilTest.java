package com.axiante.mui.webapp.webservice.util.pianificazione;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.ConfigurationService;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.LinkHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PianificazioneValidatorUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PlanningCommons;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianificazioneValidatorUtilTest {

    @Spy
    @InjectMocks
    private PianificazioneValidatorUtil util;

    @Mock
    private CfgSetPianificazioneEntity cfgSetPianificazione;

    @Mock
    private MeccanicheEntity meccanicaSet;

    @Mock
    private MeccanicheEntity meccanicaRaggr;

    @Mock
    private PromozioneTestataEntity testata;

    @Mock
    private PromozionePianificazioneEntity promoPianificazioneSet;

    @Mock
    private PromozionePianificazioneEntity promoPianificazioneRaggr;

    @Mock
    private CfgConfHeaderEntity cfgHeaderSet;

    @Mock
    private CfgConfHeaderEntity cfgHeaderRaggr;

    @Mock
    private CfgPianificazTipoRigaEntity tipoRigaSet;

    @Mock
    private CfgPianificazTipoRigaEntity tipoRigaRaggr;

    @Mock
    private ConfigurationService configurationService;
    
    @Mock
    private PianificazionePromoUtil pianificazionePromoUtil;

    @Mock
    private Instance<LinkHelper> linkHelperInstance;

    @Mock
    private LinkHelper linkHelper;
    
    @Mock
    private PlanningCommons planningCommons;
    
    @Before
    public void setUp() {
        when(tipoRigaSet.getCodiceTipo()).thenReturn(PlanningLevelEnum.SET.getCode());
        when(tipoRigaRaggr.getCodiceTipo()).thenReturn(PlanningLevelEnum.RAGGRUPPAMENTO.getCode());
        when(meccanicaSet.getId()).thenReturn(1L);
        when(meccanicaRaggr.getId()).thenReturn(2L);
        when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        when(promoPianificazioneSet.getTipoRiga()).thenReturn(tipoRigaSet);
        when(promoPianificazioneSet.getMeccanicaEntity()).thenReturn(meccanicaSet);
        when(promoPianificazioneRaggr.getTipoRiga()).thenReturn(tipoRigaRaggr);
        when(promoPianificazioneRaggr.getMeccanicaEntity()).thenReturn(meccanicaRaggr);
        when(cfgHeaderSet.getMeccanicaEntity()).thenReturn(meccanicaSet);
        when(cfgHeaderRaggr.getMeccanicaEntity()).thenReturn(meccanicaRaggr);
        when(testata.getPromozionePianificazioneEntities())
                .thenReturn(new HashSet<>(Arrays.asList(promoPianificazioneSet, promoPianificazioneRaggr)));
        when(cfgSetPianificazione.getMuiCfgConfHeaders())
                .thenReturn(new HashSet<>(Arrays.asList(cfgHeaderSet, cfgHeaderRaggr)));
        when(linkHelperInstance.get()).thenReturn(linkHelper);
    }

    @Test
    public void validateSet_shouldReturnTrue_whenMaxSetNull() {
        when(cfgHeaderSet.getMaxSet()).thenReturn(null);
        assertTrue(util.validateSet(testata, meccanicaSet, cfgHeaderSet));
    }

    @Test
    public void validateSet_shouldReturnTrue_whenSetRowLessMaxSet() {
        when(cfgHeaderSet.getMaxSet()).thenReturn(2);
        assertTrue(util.validateSet(testata, meccanicaSet, cfgHeaderSet));
    }

    @Test
    public void validateSet_shouldReturnFalse_whenSetRowEqualsMoreMaxSet() {
        cfgHeaderSet.setMaxSet(1);
        when(cfgHeaderSet.getMaxSet()).thenReturn(1);
        assertFalse(util.validateSet(testata, meccanicaSet, cfgHeaderSet));
    }

    @Test
    public void validateRaggruppamento_shouldReturnTrue_whenThereIsRoomForOtherRaggruppamenti() {
        when(cfgHeaderRaggr.getMaxRaggruppamento()).thenReturn(3);
        assertTrue(util.validateRaggruppamento(testata, meccanicaRaggr));
    }

    @Test
    public void validateRaggruppamento_shouldReturnTrue_whenThereIsNoMaxRaggruppamento() {
        when(cfgHeaderRaggr.getMaxRaggruppamento()).thenReturn(null);
        assertTrue(util.validateRaggruppamento(testata, meccanicaRaggr));
    }

    @Test
    public void validateRaggruppamento_shouldReturnFalse_whenMaxRaggruppamentiReached() {
        assertFalse(util.validateRaggruppamento(testata, meccanicaRaggr));
    }

    @Test
    public void validateRaggruppamento_shouldReturnFalse_whenThereIsNoHeader() {
        when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(Collections.emptySet());
        assertFalse(util.validateRaggruppamento(testata, meccanicaRaggr));
    }

    @Test
    public void validateRaggruppamento_shouldReturnFalse_whenSomethingWentWrong() {
        when(promoPianificazioneRaggr.getTipoRiga()).thenReturn(null);
        assertFalse(util.validateRaggruppamento(testata, meccanicaRaggr));
    }

    @Test
    public void validateOmogeneo_shouldReturnTrue_whenHeaderHasOmogeneo() {
        final CfgTipoElementoEntity tipoElemento = mock(CfgTipoElementoEntity.class);
        when(tipoElemento.getOmogeneo()).thenReturn(1);
        when(cfgHeaderSet.getTipiElemento()).thenReturn(Collections.singleton(tipoElemento));
        assertTrue(util.validateOmogeneo(testata, meccanicaSet, null));
    }

    @Test
    public void validateOmogeneo_shouldReturnFalse_whenHeaderHasNotOmogeneo() {
        final CfgTipoElementoEntity tipoElemento = mock(CfgTipoElementoEntity.class);
        when(tipoElemento.getOmogeneo()).thenReturn(0);
        when(cfgHeaderSet.getTipiElemento()).thenReturn(Collections.singleton(tipoElemento));
        assertFalse(util.validateOmogeneo(testata, meccanicaSet, null));
    }

    @Test
    public void validateOmogeneo_shouldReturnFalse_whenThereIsNoHeader() {
        when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(Collections.emptySet());
        assertFalse(util.validateOmogeneo(testata, meccanicaSet, null));
    }

    @Test
    public void validateOmogeneo_shouldReturnFalse_whenTipoElementoIsNull() {
        when(cfgHeaderSet.getTipiElemento()).thenReturn(Collections.emptySet());
        assertFalse(util.validateOmogeneo(testata, meccanicaSet, null));
    }

    @Test
    public void validateOmogeneo_shouldReturnFalse_whenTipoElementoRaggruppamentoNotEqualNumRaggr() {
        final CfgTipoElementoEntity tipoElemento1 = mock(CfgTipoElementoEntity.class);
        final CfgTipoElementoEntity tipoElemento2 = mock(CfgTipoElementoEntity.class);
        when(tipoElemento1.getRaggruppamento()).thenReturn(1);
        when(tipoElemento2.getRaggruppamento()).thenReturn(2);
        when(cfgHeaderSet.getTipiElemento())
                .thenReturn(new HashSet<>(Arrays.asList(tipoElemento1, tipoElemento2)));
        assertFalse(util.validateOmogeneo(testata, meccanicaSet, "3"));
    }

    @Test
    public void validateOmogeneo_shouldReturnTrue_whenTipoElementoRaggruppamentoEqualNumRaggrHasOmogeneo() {
        final CfgTipoElementoEntity tipoElemento1 = mock(CfgTipoElementoEntity.class);
        final CfgTipoElementoEntity tipoElemento2 = mock(CfgTipoElementoEntity.class);
        when(tipoElemento1.getRaggruppamento()).thenReturn(1);
        when(tipoElemento1.getOmogeneo()).thenReturn(1);
        when(cfgHeaderSet.getTipiElemento())
                .thenReturn(new HashSet<>(Arrays.asList(tipoElemento1, tipoElemento2)));
        assertTrue(util.validateOmogeneo(testata, meccanicaSet, "1"));
    }

    @Test
    public void validateOmogeneo_shouldReturnFalse_whenTipoElementoRaggruppamentoEqualNumRaggrHasNotOmogeneo() {
        final CfgTipoElementoEntity tipoElemento1 = mock(CfgTipoElementoEntity.class);
        final CfgTipoElementoEntity tipoElemento2 = mock(CfgTipoElementoEntity.class);
        when(tipoElemento1.getRaggruppamento()).thenReturn(1);
        when(tipoElemento1.getOmogeneo()).thenReturn(0);
        when(cfgHeaderSet.getTipiElemento())
                .thenReturn(new HashSet<>(Arrays.asList(tipoElemento1, tipoElemento2)));
        assertFalse(util.validateOmogeneo(testata, meccanicaSet, "1"));
    }

    @Test
    public void validateOmogeneo_shouldReturnFalse_whenSomethingWentWrong() {
        when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(null);
        assertFalse(util.validateOmogeneo(testata, meccanicaSet, null));
    }

    @Test
    public void validateBuonoScontoProgressivo_conListaPianificazioniEmpty_ReturnsDefaultValue() {
        when(promoPianificazioneSet.getPromozioneTestataEntity()).thenReturn(testata);
        when(configurationService.findBySetAndMeccanicaAndCampo(cfgSetPianificazione, meccanicaSet,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN))
                .thenReturn(Collections.emptyList());
    	assertEquals("0",
                util.validateBuonoScontoProgressivo(promoPianificazioneSet, "0", "666"));
    }

    @Test
    public void validateBuonoScontoProgressivo_returnsDefaultValue_whenInList() {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        final List<String> pickListValues = Arrays.asList("1", "2", "3");
        final List<Integer> range = Arrays.asList(1, 2, 3);
        when(cfgPianificazione.getLista()).thenReturn("[1..3]");
        when(promoPianificazioneSet.getPromozioneTestataEntity()).thenReturn(testata);
        when(configurationService.findBySetAndMeccanicaAndCampo(cfgSetPianificazione, meccanicaSet,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN))
                .thenReturn(Collections.singletonList(cfgPianificazione));
        when(pianificazionePromoUtil.getCorrectFormatPickListValues("[1..3]")).thenReturn(pickListValues);
        when(pianificazionePromoUtil.getAvailableProgressiveDiscountCodesBuoniPotenziamento(
                    any(PromozioneTestataEntity.class), any(), any(), any()))
                .thenReturn(range);
        assertEquals("1",
                util.validateBuonoScontoProgressivo(promoPianificazioneSet, "1", "666"));
    }

    @Test
    public void validateBuonoScontoProgressivo_returnsFirstValue_whenDefaultValueNotInList() {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        final List<String> pickListValues = Arrays.asList("11", "12", "13");
        final List<Integer> range = Arrays.asList(11, 12, 13);
        when(cfgPianificazione.getLista()).thenReturn("[11..13]");
        when(promoPianificazioneSet.getPromozioneTestataEntity()).thenReturn(testata);
        when(configurationService.findBySetAndMeccanicaAndCampo(cfgSetPianificazione, meccanicaSet,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN))
                .thenReturn(Collections.singletonList(cfgPianificazione));
        when(pianificazionePromoUtil.getCorrectFormatPickListValues("[11..13]")).thenReturn(pickListValues);
        when(pianificazionePromoUtil.getAvailableProgressiveDiscountCodesBuoniPotenziamento(
                    any(PromozioneTestataEntity.class), any(), any(), any()))
                .thenReturn(range);
        assertEquals("11",
                util.validateBuonoScontoProgressivo(promoPianificazioneSet, "1", "666"));
    }

    @Test
    public void validateBuonoScontoProgressivo_returnsNull_whenCannotParseDefaultValue() {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        final List<String> pickListValues = Arrays.asList("11", "12", "13");
        final List<Integer> range = Arrays.asList(11, 12, 13);
        when(cfgPianificazione.getLista()).thenReturn("[11..13]");
        when(promoPianificazioneSet.getPromozioneTestataEntity()).thenReturn(testata);
        when(configurationService.findBySetAndMeccanicaAndCampo(cfgSetPianificazione, meccanicaSet,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN))
                .thenReturn(Collections.singletonList(cfgPianificazione));
        when(pianificazionePromoUtil.getCorrectFormatPickListValues("[11..13]")).thenReturn(pickListValues);
        when(pianificazionePromoUtil.getAvailableProgressiveDiscountCodesBuoniPotenziamento(
                    any(PromozioneTestataEntity.class), any(), any(), any()))
                .thenReturn(range);
        assertNull(util.validateBuonoScontoProgressivo(promoPianificazioneSet, "foo", "666"));
    }

    @Test
    public void validateValidoSeRaggruppamento_whenCfgPianificazioneIsNull_shouldReturnTrue() {
        when(promoPianificazioneSet.getPromozioneTestataEntity()).thenReturn(testata);
        when(configurationService.findBySetAndMeccanicaAndCampo(cfgSetPianificazione, meccanicaSet,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN))
                .thenReturn(Collections.emptyList());
    	assertTrue(util.validateValidoSeRaggruppamento(promoPianificazioneSet,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN));
    }

    @Test
    public void validateValidoSeRaggruppamento_whenPianificazioneFollowItsCfg_shouldReturnTrue() {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        when(configurationService.findBySetAndMeccanicaAndCampo(cfgSetPianificazione, meccanicaSet,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN))
                .thenReturn(Collections.singletonList(cfgPianificazione));
        when(promoPianificazioneSet.getPromozioneTestataEntity()).thenReturn(testata);
        when(planningCommons.overrideConfiguration(cfgPianificazione, promoPianificazioneSet))
                .thenReturn(false);
    	assertTrue(util.validateValidoSeRaggruppamento(promoPianificazioneSet,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN));
    }

    @Test
    public void validateValidoSeRaggruppamento_whenPianificazioneDoesNotFollowItsCfg_shouldReturnFalse() {
        final CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        when(configurationService.findBySetAndMeccanicaAndCampo(cfgSetPianificazione, meccanicaSet,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN))
                .thenReturn(Collections.singletonList(cfgPianificazione));
        when(promoPianificazioneSet.getPromozioneTestataEntity()).thenReturn(testata);
        when(planningCommons.overrideConfiguration(cfgPianificazione, promoPianificazioneSet))
                .thenReturn(true);
    	assertFalse(util.validateValidoSeRaggruppamento(promoPianificazioneSet,
                PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN));
    }

    @Test(expected = NullPointerException.class)
    public void validateLink_whenPianificazioneIsNull_shouldThrowException() {
        util.validateLink(null, "0005");
    }

    @Test
    public void validateLink_shouldReturnsTrue_whenValueToBeUpdatedIsNotNullAndNotAlreadyUsed() {
        CfgPianificazioneEntity configurazione = mock(CfgPianificazioneEntity.class);
        when(configurazione.getLista()).thenReturn("[1..10]");
        List<CfgPianificazioneEntity> configurazioni = Collections.singletonList(configurazione);
        List<String> availableValues = IntStream.rangeClosed(1, 10).boxed()
                .map(v -> String.format("%04d", v)).collect(Collectors.toList());
        when(promoPianificazioneSet.getPromozioneTestataEntity()).thenReturn(testata);
        when(configurationService.findBySetAndMeccanicaAndCampo(cfgSetPianificazione, meccanicaSet, PianificazioneConstants.REFERENCE_LINK))
                .thenReturn(configurazioni);
        when(linkHelper.getAvailableValues("[1..10]", promoPianificazioneSet)).thenReturn(availableValues);
        assertTrue(util.validateLink(promoPianificazioneSet, "0005"));
    }

    @Test
    public void validateLink_shouldReturnsFalse_whenValueToBeUpdatedIsNullAndThereIsLinksUsed() {
        CfgPianificazioneEntity configurazione = mock(CfgPianificazioneEntity.class);
        when(configurazione.getLista()).thenReturn("[1..10]");
        List<CfgPianificazioneEntity> configurazioni = Collections.singletonList(configurazione);
        List<String> availableValues = IntStream.rangeClosed(1, 10).boxed()
                .map(v -> String.format("%04d", v)).collect(Collectors.toList());
        when(promoPianificazioneSet.getPromozioneTestataEntity()).thenReturn(testata);
        when(configurationService.findBySetAndMeccanicaAndCampo(cfgSetPianificazione, meccanicaSet, PianificazioneConstants.REFERENCE_LINK))
                .thenReturn(configurazioni);
        when(linkHelper.getAvailableValues("[1..10]", promoPianificazioneSet)).thenReturn(availableValues);
        assertFalse(util.validateLink(promoPianificazioneSet, null));
    }

    @Test
    public void validateLink_shouldReturnsFalse_whenValueToBeUpdatedIsNotNullAndAlreadyUsed() {
        CfgPianificazioneEntity configurazione = mock(CfgPianificazioneEntity.class);
        when(configurazione.getLista()).thenReturn("[1..10]");
        List<CfgPianificazioneEntity> configurazioni = Collections.singletonList(configurazione);
        List<String> availableValues = IntStream.rangeClosed(1, 10).boxed()
                .map(v -> String.format("%04d", v)).collect(Collectors.toList());
        availableValues.remove("0005");
        when(promoPianificazioneSet.getPromozioneTestataEntity()).thenReturn(testata);
        when(configurationService.findBySetAndMeccanicaAndCampo(cfgSetPianificazione, meccanicaSet, PianificazioneConstants.REFERENCE_LINK))
                .thenReturn(configurazioni);
        when(linkHelper.getAvailableValues("[1..10]", promoPianificazioneSet)).thenReturn(availableValues);
        assertFalse(util.validateLink(promoPianificazioneSet, "0005"));
    }
}
