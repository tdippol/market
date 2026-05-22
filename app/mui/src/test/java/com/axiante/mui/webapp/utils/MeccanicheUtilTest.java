package com.axiante.mui.webapp.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MeccanicheUtilTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private Instance<PianificazionePromoUtil> pianificazionePromoUtilInstance;

    @Mock
    private PianificazionePromoUtil pianificazionePromoUtil;

    @Mock
    private Instance<CfgPianificazioneService> cfgPianificazioneServiceInstance;

    @Mock
    private CfgPianificazioneService cfgPianificazioneService;

    @InjectMocks
    private MeccanicheUtil util;

    private PromozioneTestataEntity testata;
    private List<CanalePromozioneEntity> canali;

    @Before
    public void setUp() {
        when(pianificazionePromoUtilInstance.get()).thenReturn(pianificazionePromoUtil);
        when(cfgPianificazioneServiceInstance.get()).thenReturn(cfgPianificazioneService);
        when(pianificazionePromoUtil.isMeccanicaAvailable(any(PromozioneTestataEntity.class), anyString()))
                .thenReturn(true);
        canali = new ArrayList<>();
        MeccanicheEntity meccanicaSet = mockMeccanica("M-001");
        MeccanicheEntity meccanicaRaggr = mockMeccanica("M-002");
        MeccanicheEntity meccanicaElem = mockMeccanica("M-003");
        PromozioneMeccanicheEntity meccanicaPromoSet = mockMeccanicaPromo(meccanicaSet);
        PromozioneMeccanicheEntity meccanicaPromoRaggr = mockMeccanicaPromo(meccanicaRaggr);
        PromozioneMeccanicheEntity meccanicaPromoElem = mockMeccanicaPromo(meccanicaElem);
        CfgConfHeaderEntity headerSet = mockHeader(meccanicaSet, PlanningLevelEnum.SET);
        CfgConfHeaderEntity headerRaggr = mockHeader(meccanicaRaggr, PlanningLevelEnum.RAGGRUPPAMENTO);
        CfgConfHeaderEntity headerElem = mockHeader(meccanicaElem, PlanningLevelEnum.ELEMENTO);
        CfgSetPianificazioneEntity cfgSetPianificazione = mockCfgSetPianificazione(headerSet, headerRaggr, headerElem);
        testata = mockTestata(cfgSetPianificazione,
                new HashSet<>(Arrays.asList(meccanicaPromoSet, meccanicaPromoRaggr, meccanicaPromoElem)));
    }

    @Test
    public void getMeccanicheDisponibili_givenNullTestata_shouldThrowException() {
        ex.expect(NullPointerException.class);
        util.getMeccanicheDisponibili(null, canali, null);
    }

    @Test
    public void getMeccanicheDisponibili_givenPlanningLevel_shouldReturnOnlyMeccanichePlannableAtGivenLevel() {
        List<MeccanicheEntity> meccaniche = util.getMeccanicheDisponibili(testata, canali, PlanningLevelEnum.ELEMENTO);
        assertEquals(1, meccaniche.size());
        assertEquals("M-003", meccaniche.get(0).getCodiceMeccanica());
        meccaniche = util.getMeccanicheDisponibili(testata, canali, PlanningLevelEnum.RAGGRUPPAMENTO);
        assertEquals(1, meccaniche.size());
        assertEquals("M-002", meccaniche.get(0).getCodiceMeccanica());
        meccaniche = util.getMeccanicheDisponibili(testata, canali, PlanningLevelEnum.SET);
        assertEquals(1, meccaniche.size());
        assertEquals("M-001", meccaniche.get(0).getCodiceMeccanica());
    }

    @Test
    public void getMeccanicheDisponibili_whenFlagDuplicaTotaleIsFalse_shouldNotDiscardAny() {
        MeccanicheEntity meccanica1 = mockMeccanica("M-001");
        MeccanicheEntity meccanica2 = mockMeccanica("M-002");
        PromozioneMeccanicheEntity meccanicaPromo1 = mockMeccanicaPromo(meccanica1);
        PromozioneMeccanicheEntity meccanicaPromo2 = mockMeccanicaPromo(meccanica2);
        CfgConfHeaderEntity header1 = mockHeader(meccanica1, PlanningLevelEnum.ELEMENTO);
        CfgConfHeaderEntity header2 = mockHeader(meccanica2, PlanningLevelEnum.ELEMENTO);
        CfgSetPianificazioneEntity cfgSetPianificazione = mockCfgSetPianificazione(header1, header2);
        PromozionePianificazioneEntity pianificazione1 = mockPianificazione("TOTALE", meccanica1);
        PromozionePianificazioneEntity pianificazione2 = mockPianificazione("FOO", meccanica2);
        final CanalePromozioneEntity canale = mockCanale(Boolean.FALSE);
        testata = mockTestata(cfgSetPianificazione, canale,
                new HashSet<>(Arrays.asList(meccanicaPromo1, meccanicaPromo2)),
                new HashSet<>(Arrays.asList(pianificazione1, pianificazione2)));
        List<MeccanicheEntity> meccaniche = util.getMeccanicheDisponibili(testata, canali, PlanningLevelEnum.ELEMENTO, true);
        assertEquals(2, meccaniche.size());
        assertTrue(meccaniche.contains(meccanica1));
        assertTrue(meccaniche.contains(meccanica2));
    }

    @Test
    public void getMeccanicheDisponibili_whenIsElementoTotaleAndDuplicaCanaleIsTrue_shouldDiscardMeccanicheAlreadyUsedForElementoTotale() {
        MeccanicheEntity meccanica1 = mockMeccanica("M-001");
        MeccanicheEntity meccanica2 = mockMeccanica("M-002");
        PromozioneMeccanicheEntity meccanicaPromo1 = mockMeccanicaPromo(meccanica1);
        PromozioneMeccanicheEntity meccanicaPromo2 = mockMeccanicaPromo(meccanica2);
        CfgConfHeaderEntity header1 = mockHeader(meccanica1, PlanningLevelEnum.ELEMENTO);
        CfgConfHeaderEntity header2 = mockHeader(meccanica2, PlanningLevelEnum.ELEMENTO);
        CfgSetPianificazioneEntity cfgSetPianificazione = mockCfgSetPianificazione(header1, header2);
        PromozionePianificazioneEntity pianificazione1 = mockPianificazione("TOTALE", meccanica1);
        PromozionePianificazioneEntity pianificazione2 = mockPianificazione("FOO", meccanica2);
        final CanalePromozioneEntity canale = mockCanale(Boolean.TRUE);
        testata = mockTestata(cfgSetPianificazione, canale,
                new HashSet<>(Arrays.asList(meccanicaPromo1, meccanicaPromo2)),
                new HashSet<>(Arrays.asList(pianificazione1, pianificazione2)));
        List<MeccanicheEntity> meccaniche = util.getMeccanicheDisponibili(testata, canali, PlanningLevelEnum.ELEMENTO, true);
        assertEquals(1, meccaniche.size());
        assertEquals("M-002", meccaniche.get(0).getCodiceMeccanica());
        assertFalse(meccaniche.contains(meccanica1));
        assertTrue(meccaniche.contains(meccanica2));
    }

    private CanalePromozioneEntity mockCanale(Boolean duplicaTotale) {
        final CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
        when(mock.getDuplicaTotale()).thenReturn(duplicaTotale);
        return mock;
    }

    private PromozionePianificazioneEntity mockPianificazione(String tipoElemento, MeccanicheEntity meccanica) {
        final PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
        when(mock.getTipoElemento()).thenReturn(tipoElemento);
        when(mock.getMeccanicaEntity()).thenReturn(meccanica);
        return mock;
    }

    private CfgConfHeaderEntity mockHeader(MeccanicheEntity meccanica, PlanningLevelEnum planningLevel) {
        final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
        final CfgConfHeaderEntity header = mock(CfgConfHeaderEntity.class);
        when(cfgLivello.getCodice()).thenReturn(planningLevel.getDescription());
        when(header.getMeccanicaEntity()).thenReturn(meccanica);
        when(header.getLivelloPianificazione()).thenReturn(cfgLivello);
        return header;
    }

    private CfgSetPianificazioneEntity mockCfgSetPianificazione(CfgConfHeaderEntity... headers) {
        final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(new HashSet<>(Arrays.asList(headers)));
        return cfgSetPianificazione;
    }

    private PromozioneTestataEntity mockTestata(CfgSetPianificazioneEntity cfgSetPianificazione,
                                                Set<PromozioneMeccanicheEntity> meccaniche) {
        final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(testata.getPromozioneMeccanicheEntities()).thenReturn(meccaniche);
        when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        return testata;
    }

    private PromozioneTestataEntity mockTestata(CfgSetPianificazioneEntity cfgSetPianificazione,
                                                CanalePromozioneEntity canale,
                                                Set<PromozioneMeccanicheEntity> meccaniche,
                                                Set<PromozionePianificazioneEntity> pianificazioni) {
        final PromozioneTestataEntity testata = mockTestata(cfgSetPianificazione, meccaniche);
        when(testata.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        return testata;
    }

    private MeccanicheEntity mockMeccanica(String codice) {
        final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        when(meccanica.getCodiceMeccanica()).thenReturn(codice);
        return meccanica;
    }

    private PromozioneMeccanicheEntity mockMeccanicaPromo(MeccanicheEntity meccanica) {
        final PromozioneMeccanicheEntity promoMeccanica = mock(PromozioneMeccanicheEntity.class);
        when(promoMeccanica.getMeccanicheEntity()).thenReturn(meccanica);
        return promoMeccanica;
    }
}