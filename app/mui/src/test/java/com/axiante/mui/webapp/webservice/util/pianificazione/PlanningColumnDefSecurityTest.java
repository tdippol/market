package com.axiante.mui.webapp.webservice.util.pianificazione;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.utils.LookupUtils;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTestataService;
import com.axiante.mui.dbpromo.persistence.service.UploadFidatyService;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.configuration.CfgPianificazioneEntityHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromoConfigurationHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.VisualizzaPianificazioneHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PlanningCommons;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlanningColumnDefSecurityTest {

    @Mock
    private PromoService promoService;

    @Mock
    private PlanningCommons planningCommons;

    @Mock
    private PianificazioneService pianificazioneService;

    @Mock
    private PromoConfigurationHelper promoConfigurationHelper;

    @Mock
    private CfgPianificazioneEntityHelper pianificazioneEntityHelper;

    @Mock
    private VisualizzaPianificazioneHelper visualizzaPianificazioneHelper;

    @Mock
    private UploadFidatyService uploadFidatyService;

    @Mock
    private Instance<PianificazioneSecurityUtil> securityUtilInstance;

    @Mock
    private PianificazioneSecurityUtil securityUtil;

    @Mock
    private PianificazionePromoUtil pianificazionePromoUtil;

    @Mock
    private PromozioneTestataService promozioneTestataService;

    @Mock
    LookupUtils lookupUtils;

    @InjectMocks
    private PlanningColumnDef columnDef;

    private PromozioneTestataEntity testata;

    // pianificazioneSet
    // |__ pianificazioneRaggr
    //     |__ pianificazioneElem1
    //     |__ pianificazioneElem3
    // pianificazioneElem2
    private PromozionePianificazioneEntity pianificazioneSet;
    private PromozionePianificazioneEntity pianificazioneRaggr;
    private PromozionePianificazioneEntity pianificazioneElem1;
    private PromozionePianificazioneEntity pianificazioneElem2;
    private PromozionePianificazioneEntity pianificazioneElem3;

    private List<String> codiciGruppo = Arrays.asList("GRA", "GRB");

    @Before
    public void setUp() {
        CanalePromozioneEntity canale = mockCanale();
        MeccanicheEntity meccanica1 = mockMeccanica(1);
        MeccanicheEntity meccanica2 = mockMeccanica(2);
        CfgLivelloPianificazioneEntity cfgLivelloPianificazione1 = mockCfgLivelloPianificazione("SET");
        CfgLivelloPianificazioneEntity cfgLivelloPianificazione2 = mockCfgLivelloPianificazione("ELEMENTO");
        CfgConfHeaderEntity cfgHeader1 = mockCfgHeader(meccanica1, cfgLivelloPianificazione1);
        CfgConfHeaderEntity cfgHeader2 = mockCfgHeader(meccanica2, cfgLivelloPianificazione2);
        Set<CfgConfHeaderEntity> headers = new HashSet<>();
        headers.add(cfgHeader1);
        headers.add(cfgHeader2);
        CfgSetPianificazioneEntity cfgSetPianificazione = mockCfgSetPianificazione(headers);
        testata = mockTestata(cfgSetPianificazione, canale);
        CfgPianificazTipoRigaEntity rigaSet = mockTipoRiga(1, PianificazioneRowTypeEnum.SET);
        CfgPianificazTipoRigaEntity rigaRaggr = mockTipoRiga(2, PianificazioneRowTypeEnum.RAGGRUPPAMENTO);
        CfgPianificazTipoRigaEntity rigaElem = mockTipoRiga(3, PianificazioneRowTypeEnum.ELEMENTO);
        pianificazioneSet = mockPianificazione(1, rigaSet, meccanica1);
        pianificazioneRaggr = mockPianificazione(2, rigaRaggr, meccanica1);
        pianificazioneElem1 = mockPianificazione(3, rigaElem, meccanica1, "ABC");
        pianificazioneElem2 = mockPianificazione(4, rigaElem, meccanica2, "XYZ");
        pianificazioneElem3 = mockPianificazione(5, rigaElem, meccanica1, "WWW");
        CfgPianificazioneCampiEntity cfgPianificazioneCampoSet = mockCfgPianificazioneCampo("CAMPO_SET");
        CfgPianificazioneCampiEntity cfgPianificazioneCampoRaggr = mockCfgPianificazioneCampo("CAMPO_RAGGR");
        CfgPianificazioneCampiEntity cfgPianificazioneCampoElem1 = mockCfgPianificazioneCampo("CAMPO_FOO");
        CfgPianificazioneCampiEntity cfgPianificazioneCampoElem2 = mockCfgPianificazioneCampo("CAMPO_BAR");
        CfgPianificazioneCampiEntity cfgPianificazioneCampoElem3 = mockCfgPianificazioneCampo("CAMPO_BAZ");
        CfgPianificazioneEntity cfgPianificazioneSet = mockCfgPianificazione(cfgHeader1, rigaSet, cfgPianificazioneCampoSet);
        CfgPianificazioneEntity cfgPianificazioneRaggr = mockCfgPianificazione(cfgHeader1, rigaRaggr, cfgPianificazioneCampoRaggr);
        CfgPianificazioneEntity cfgPianificazioneElem1 = mockCfgPianificazione(cfgHeader1, rigaElem, cfgPianificazioneCampoElem1);
        CfgPianificazioneEntity cfgPianificazioneElem2 = mockCfgPianificazione(cfgHeader2, rigaElem, cfgPianificazioneCampoElem2);
        CfgPianificazioneEntity cfgPianificazioneElem3 = mockCfgPianificazione(cfgHeader1, rigaElem, cfgPianificazioneCampoElem3);
        final PromozioneStatoEntity promoStato = mockStatoPromozione(PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE);
        when(testata.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
        when(testata.getCanalePromozioneEntity()).thenReturn(canale);
        when(promozioneTestataService.findByIdFullEagerFetch(anyLong())).thenReturn(testata);
        when(planningCommons.isPlanningEditableCellAndRow(testata)).thenReturn(true);
        when(planningCommons.getRowType(anyString())).thenReturn("rowType");
        //noinspection unchecked
        when(planningCommons.isCellEditable(anyString(), anyString(), anyString(), any(HashMap.class))).thenReturn(true);
        when(planningCommons.checkColumnEditable(eq(testata), anyString(), anyString())).thenReturn(true);
        when(promoConfigurationHelper.getConfigurazioniSorted(testata))
                .thenReturn(Arrays.asList(cfgPianificazioneSet, cfgPianificazioneRaggr, cfgPianificazioneElem1, cfgPianificazioneElem2, cfgPianificazioneElem3));
        when(promoConfigurationHelper.getHeaderFromTestataAndMeccanica(any(PromozioneTestataEntity.class), any(MeccanicheEntity.class)))
                .thenReturn(cfgHeader1);
        when(pianificazioneEntityHelper.isEntityLookup(any(CfgPianificazioneEntity.class))).thenReturn(false);
        when(visualizzaPianificazioneHelper.invokeGetterEntity(anyString(), any(PromozionePianificazioneEntity.class)))
                .thenReturn("VALUE");
        when(uploadFidatyService.findByPianificazione(anyLong())).thenReturn(Collections.emptyList());
        when(pianificazioneSet.getChildren()).thenReturn(Collections.singleton(pianificazioneRaggr));
        when(pianificazioneRaggr.getChildren())
                .thenReturn(new HashSet<>(Arrays.asList(pianificazioneElem1, pianificazioneElem3)));
        when(securityUtilInstance.get()).thenReturn(securityUtil);

        //MF: now the Testata Entity is an eager fetch
        when(testata.getPromozionePianificazioneEntities()).thenReturn(new HashSet<>(Arrays.asList(pianificazioneSet, pianificazioneElem2)));
        when(pianificazioneSet.getPromozioneTestataEntity()).thenReturn(testata);
        when(pianificazioneElem2.getPromozioneTestataEntity()).thenReturn(testata);

        doNothing().when(lookupUtils).initializeLookups(any(PromozioneTestataEntity.class), anyList());

    }

    @Test
    public void generateRowData_givenAdminUser_shouldViewAllAndEditAll() {
        final String rowData = columnDef.generateRowDataByPromoConfiguration("1", true, codiciGruppo,null);
        //noinspection unchecked
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(5)),
                withJsonPath("$.rowData[0].idPromoPianificazione.value", equalTo("4")),
                withJsonPath("$.rowData[0].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[0].tipoRiga.value", equalTo("ELEMENTO")),
                withJsonPath("$.rowData[0].CAMPO_BAR.editable", equalTo(true)),
                withJsonPath("$.rowData[1].idPromoPianificazione.value", equalTo("1")),
                withJsonPath("$.rowData[1].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[1].tipoRiga.value", equalTo("SET")),
                withJsonPath("$.rowData[2].idPromoPianificazione.value", equalTo("2")),
                withJsonPath("$.rowData[2].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[2].tipoRiga.value", equalTo("RAGGRUPPAMENTO")),
                withJsonPath("$.rowData[2].emptyAllEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[3].idPromoPianificazione.value", equalTo("3")),
                withJsonPath("$.rowData[3].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[3].tipoRiga.value", equalTo("ELEMENTO")),
                withJsonPath("$.rowData[3].CAMPO_FOO.editable", equalTo(true)),
                withJsonPath("$.rowData[4].idPromoPianificazione.value", equalTo("5")),
                withJsonPath("$.rowData[4].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[4].tipoRiga.value", equalTo("ELEMENTO")),
                withJsonPath("$.rowData[4].CAMPO_BAZ.editable", equalTo(true))
        )));
        verify(securityUtil, never()).isReadable(any(PromozionePianificazioneEntity.class), eq(codiciGruppo));
        verify(securityUtil, never()).isWriteable(any(PromozionePianificazioneEntity.class), eq(codiciGruppo));
        verify(securityUtil, never()).isDeletable(any(PromozionePianificazioneEntity.class), eq(codiciGruppo));
    }

    @Test
    public void generateRowData_givenReadSecurityAll_shouldViewAll() {
        when(securityUtil.isReadable(pianificazioneElem1, codiciGruppo)).thenReturn(true);
        when(securityUtil.isReadable(pianificazioneElem2, codiciGruppo)).thenReturn(true);
        when(securityUtil.isReadable(pianificazioneElem3, codiciGruppo)).thenReturn(true);
        when(securityUtil.isWriteable(pianificazioneElem1, codiciGruppo)).thenReturn(true);
        when(securityUtil.isWriteable(pianificazioneElem2, codiciGruppo)).thenReturn(true);
        when(securityUtil.isWriteable(pianificazioneElem3, codiciGruppo)).thenReturn(true);
        when(securityUtil.isDeletable(pianificazioneSet, codiciGruppo)).thenCallRealMethod();
        when(securityUtil.isDeletable(pianificazioneRaggr, codiciGruppo)).thenCallRealMethod();
        when(securityUtil.isDeletable(pianificazioneElem1, codiciGruppo)).thenCallRealMethod();
        when(securityUtil.isDeletable(pianificazioneElem2, codiciGruppo)).thenCallRealMethod();
        when(securityUtil.isDeletable(pianificazioneElem3, codiciGruppo)).thenCallRealMethod();
        final String rowData = columnDef.generateRowDataByPromoConfiguration("1", false, codiciGruppo, null);
        //noinspection unchecked
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(5)),
                withJsonPath("$.rowData[0].idPromoPianificazione.value", equalTo("4")),
                withJsonPath("$.rowData[0].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[0].tipoRiga.value", equalTo("ELEMENTO")),
                withJsonPath("$.rowData[0].CAMPO_BAR.editable", equalTo(true)),
                withJsonPath("$.rowData[1].idPromoPianificazione.value", equalTo("1")),
                withJsonPath("$.rowData[1].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[1].tipoRiga.value", equalTo("SET")),
                withJsonPath("$.rowData[2].idPromoPianificazione.value", equalTo("2")),
                withJsonPath("$.rowData[2].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[2].tipoRiga.value", equalTo("RAGGRUPPAMENTO")),
                withJsonPath("$.rowData[2].emptyAllEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[3].idPromoPianificazione.value", equalTo("3")),
                withJsonPath("$.rowData[3].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[3].tipoRiga.value", equalTo("ELEMENTO")),
                withJsonPath("$.rowData[3].CAMPO_FOO.editable", equalTo(true)),
                withJsonPath("$.rowData[4].idPromoPianificazione.value", equalTo("5")),
                withJsonPath("$.rowData[4].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[4].tipoRiga.value", equalTo("ELEMENTO")),
                withJsonPath("$.rowData[4].CAMPO_FOO.editable", equalTo(true))
        )));
        verify(securityUtil, times(1)).isReadable(pianificazioneElem1, codiciGruppo);
        verify(securityUtil, times(1)).isReadable(pianificazioneElem2, codiciGruppo);
        verify(securityUtil, times(1)).isReadable(pianificazioneElem3, codiciGruppo);
        verify(securityUtil, never()).isReadable(pianificazioneSet, codiciGruppo);
        verify(securityUtil, never()).isReadable(pianificazioneRaggr, codiciGruppo);
        verify(securityUtil, atLeastOnce()).isWriteable(pianificazioneElem1, codiciGruppo);
        verify(securityUtil, atLeastOnce()).isWriteable(pianificazioneElem2, codiciGruppo);
        verify(securityUtil, atLeastOnce()).isWriteable(pianificazioneElem3, codiciGruppo);
        verify(securityUtil, never()).isWriteable(pianificazioneSet, codiciGruppo);
        verify(securityUtil, never()).isWriteable(pianificazioneRaggr, codiciGruppo);
    }

    @Test
    public void generateRowData_givenReadSecurityNone_shouldViewOnlyRigheSetAndRaggruppamento() {
        when(securityUtil.isReadable(pianificazioneElem1, codiciGruppo)).thenReturn(false);
        when(securityUtil.isReadable(pianificazioneElem2, codiciGruppo)).thenReturn(false);
        when(securityUtil.isReadable(pianificazioneElem3, codiciGruppo)).thenReturn(false);
        when(securityUtil.isWriteable(pianificazioneElem1, codiciGruppo)).thenReturn(false);
        when(securityUtil.isWriteable(pianificazioneElem3, codiciGruppo)).thenReturn(false);
        lenient().when(securityUtil.isDeletable(pianificazioneSet, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneRaggr, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneElem1, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneElem2, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneElem3, codiciGruppo)).thenCallRealMethod();
        final String rowData = columnDef.generateRowDataByPromoConfiguration("1", false, codiciGruppo, null);
        //noinspection unchecked
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(2)),
                withJsonPath("$.rowData[0].idPromoPianificazione.value", equalTo("1")),
                withJsonPath("$.rowData[0].deleteEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[0].tipoRiga.value", equalTo("SET")),
                withJsonPath("$.rowData[1].idPromoPianificazione.value", equalTo("2")),
                withJsonPath("$.rowData[1].deleteEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[1].tipoRiga.value", equalTo("RAGGRUPPAMENTO")),
                withJsonPath("$.rowData[1].emptyAllEnabled.value", equalTo(String.valueOf(false)))
        )));
        verify(securityUtil, times(1)).isReadable(pianificazioneElem1, codiciGruppo);
        verify(securityUtil, times(1)).isReadable(pianificazioneElem2, codiciGruppo);
        verify(securityUtil, times(1)).isReadable(pianificazioneElem3, codiciGruppo);
        verify(securityUtil, never()).isReadable(pianificazioneSet, codiciGruppo);
        verify(securityUtil, never()).isReadable(pianificazioneRaggr, codiciGruppo);
        verify(securityUtil, atLeastOnce()).isWriteable(any(PromozionePianificazioneEntity.class), eq(codiciGruppo));
        verify(securityUtil, never()).isWriteable(pianificazioneSet, codiciGruppo);
        verify(securityUtil, never()).isWriteable(pianificazioneRaggr, codiciGruppo);
    }

    @Test
    public void generateRowData_givenReadSecuritySecured_shouldViewRigheElementoReadable() {
        when(securityUtil.isReadable(pianificazioneElem1, codiciGruppo)).thenReturn(false);
        when(securityUtil.isReadable(pianificazioneElem2, codiciGruppo)).thenReturn(true);
        when(securityUtil.isReadable(pianificazioneElem3, codiciGruppo)).thenReturn(true);
        when(securityUtil.isWriteable(pianificazioneElem2, codiciGruppo)).thenReturn(true);
        when(securityUtil.isWriteable(pianificazioneElem3, codiciGruppo)).thenReturn(true);
        lenient().when(securityUtil.isDeletable(pianificazioneSet, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneRaggr, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneElem1, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneElem2, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneElem3, codiciGruppo)).thenCallRealMethod();
        final String rowData = columnDef.generateRowDataByPromoConfiguration("1", false, codiciGruppo, null);
        //noinspection unchecked
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(4)),
                withJsonPath("$.rowData[0].idPromoPianificazione.value", equalTo("4")),
                withJsonPath("$.rowData[0].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[0].tipoRiga.value", equalTo("ELEMENTO")),
                withJsonPath("$.rowData[0].CAMPO_BAR.editable", equalTo(true)),
                withJsonPath("$.rowData[1].idPromoPianificazione.value", equalTo("1")),
                withJsonPath("$.rowData[1].deleteEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[1].tipoRiga.value", equalTo("SET")),
                withJsonPath("$.rowData[2].idPromoPianificazione.value", equalTo("2")),
                withJsonPath("$.rowData[2].deleteEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[2].tipoRiga.value", equalTo("RAGGRUPPAMENTO")),
                withJsonPath("$.rowData[2].emptyAllEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[3].idPromoPianificazione.value", equalTo("5")),
                withJsonPath("$.rowData[3].deleteEnabled.value", equalTo(String.valueOf(true))),
                withJsonPath("$.rowData[3].tipoRiga.value", equalTo("ELEMENTO")),
                withJsonPath("$.rowData[3].CAMPO_BAZ.editable", equalTo(true))
        )));
        verify(securityUtil, times(1)).isReadable(pianificazioneElem1, codiciGruppo);
        verify(securityUtil, times(1)).isReadable(pianificazioneElem2, codiciGruppo);
        verify(securityUtil, times(1)).isReadable(pianificazioneElem3, codiciGruppo);
        verify(securityUtil, never()).isReadable(pianificazioneSet, codiciGruppo);
        verify(securityUtil, never()).isReadable(pianificazioneRaggr, codiciGruppo);
        verify(securityUtil, atLeastOnce()).isWriteable(any(PromozionePianificazioneEntity.class), eq(codiciGruppo));
        verify(securityUtil, never()).isWriteable(pianificazioneSet, codiciGruppo);
        verify(securityUtil, never()).isWriteable(pianificazioneRaggr, codiciGruppo);
    }

    @Test
    public void generateRowData_givenPianificazioneNotWritable_shouldSetDeletableFalse() {
        when(securityUtil.isReadable(pianificazioneElem1, codiciGruppo)).thenReturn(false);
        when(securityUtil.isReadable(pianificazioneElem2, codiciGruppo)).thenReturn(true);
        when(securityUtil.isReadable(pianificazioneElem3, codiciGruppo)).thenReturn(true);
        when(securityUtil.isWriteable(pianificazioneElem1, codiciGruppo)).thenReturn(false);
        when(securityUtil.isWriteable(pianificazioneElem2, codiciGruppo)).thenReturn(false);
        when(securityUtil.isWriteable(pianificazioneElem3, codiciGruppo)).thenReturn(false);
        lenient().when(securityUtil.isDeletable(pianificazioneSet, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneRaggr, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneElem1, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneElem2, codiciGruppo)).thenCallRealMethod();
        lenient().when(securityUtil.isDeletable(pianificazioneElem3, codiciGruppo)).thenCallRealMethod();
        final String rowData = columnDef.generateRowDataByPromoConfiguration("1", false, codiciGruppo, null);
        //noinspection unchecked
        assertThat(rowData, isJson(allOf(
                withJsonPath("$.rowData", hasSize(4)),
                withJsonPath("$.rowData[1].idPromoPianificazione.value", equalTo("1")),
                withJsonPath("$.rowData[1].deleteEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[1].tipoRiga.value", equalTo("SET")),
                withJsonPath("$.rowData[2].idPromoPianificazione.value", equalTo("2")),
                withJsonPath("$.rowData[2].deleteEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[2].tipoRiga.value", equalTo("RAGGRUPPAMENTO")),
                withJsonPath("$.rowData[2].emptyAllEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[3].idPromoPianificazione.value", equalTo("5")),
                withJsonPath("$.rowData[3].deleteEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[3].tipoRiga.value", equalTo("ELEMENTO")),
                withJsonPath("$.rowData[3].CAMPO_BAZ.editable", equalTo(false)),
                withJsonPath("$.rowData[0].idPromoPianificazione.value", equalTo("4")),
                withJsonPath("$.rowData[0].deleteEnabled.value", equalTo(String.valueOf(false))),
                withJsonPath("$.rowData[0].tipoRiga.value", equalTo("ELEMENTO")),
                withJsonPath("$.rowData[0].CAMPO_BAR.editable", equalTo(false))
        )));
        verify(securityUtil, times(1)).isReadable(pianificazioneElem1, codiciGruppo);
        verify(securityUtil, times(1)).isReadable(pianificazioneElem2, codiciGruppo);
        verify(securityUtil, times(1)).isReadable(pianificazioneElem3, codiciGruppo);
        verify(securityUtil, never()).isReadable(pianificazioneSet, codiciGruppo);
        verify(securityUtil, never()).isReadable(pianificazioneRaggr, codiciGruppo);
        verify(securityUtil, atLeastOnce()).isWriteable(pianificazioneElem1, codiciGruppo);
        verify(securityUtil, atLeastOnce()).isWriteable(pianificazioneElem2, codiciGruppo);
        verify(securityUtil, atLeastOnce()).isWriteable(pianificazioneElem3, codiciGruppo);
        verify(securityUtil, never()).isWriteable(pianificazioneSet, codiciGruppo);
        verify(securityUtil, never()).isWriteable(pianificazioneRaggr, codiciGruppo);
    }

    private PromozionePianificazioneEntity mockPianificazione(long id, CfgPianificazTipoRigaEntity tipoRiga,
                                                              MeccanicheEntity meccanica) {
        final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(pianificazione.getId()).thenReturn(id);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
        return pianificazione;
    }

    private PromozionePianificazioneEntity mockPianificazione(long id, CfgPianificazTipoRigaEntity tipoRiga,
                                                              MeccanicheEntity meccanica,
                                                              String elemento) {
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(id, tipoRiga, meccanica);
        when(pianificazione.getElemento()).thenReturn(elemento);
        return pianificazione;
    }

    private CfgPianificazioneEntity mockCfgPianificazione(CfgConfHeaderEntity cfgHeader,
                                                          CfgPianificazTipoRigaEntity tipoRiga,
                                                          CfgPianificazioneCampiEntity campo) {
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        when(cfg.getMuiCfgConfHeader()).thenReturn(cfgHeader);
        when(cfg.getMuiCfgPianificazTipoRiga()).thenReturn(tipoRiga);
        when(cfg.getMuiCfgPianificazioneCampi()).thenReturn(campo);
        when(cfg.getSicurezza()).thenReturn("security");
        return cfg;
    }

    private CfgPianificazioneCampiEntity mockCfgPianificazioneCampo(String desc) {
        final CfgPianificazioneCampiEntity campo = mock(CfgPianificazioneCampiEntity.class);
        when(campo.getDescrizione()).thenReturn(desc);
        when(campo.getCampo()).thenReturn(desc);
        when(campo.getTipo()).thenReturn("string");
        return campo;
    }

    private CfgPianificazTipoRigaEntity mockTipoRiga(long id, PianificazioneRowTypeEnum rowType) {
        final CfgPianificazTipoRigaEntity riga = mock(CfgPianificazTipoRigaEntity.class);
        when(riga.getId()).thenReturn(id);
        when(riga.getDescrizione()).thenReturn(rowType.getDescription());
        when(riga.getCodiceTipo()).thenReturn(rowType.getTypeCode());
        return riga;
    }

    private CanalePromozioneEntity mockCanale() {
        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(canale.getAbilitaUpload()).thenReturn(1);
        return canale;
    }

    private MeccanicheEntity mockMeccanica(long id) {
        final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        when(meccanica.getId()).thenReturn(id);
        when(meccanica.getCodiceMeccanica()).thenReturn("MockCode"+id);
        return meccanica;
    }

    private PromozioneTestataEntity mockTestata(CfgSetPianificazioneEntity cfgSetPianificazione,
                                                CanalePromozioneEntity canale) {
        final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(testata.getCanalePromozioneEntity()).thenReturn(canale);
        when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        return testata;
    }

    private CfgSetPianificazioneEntity mockCfgSetPianificazione(Set<CfgConfHeaderEntity> headers) {
        final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(headers);
        return cfgSetPianificazione;
    }

    private CfgConfHeaderEntity mockCfgHeader(MeccanicheEntity meccanica, CfgLivelloPianificazioneEntity livello) {
        final CfgConfHeaderEntity cfgHeader = mock(CfgConfHeaderEntity.class);
        when(cfgHeader.getMeccanicaEntity()).thenReturn(meccanica);
        when(cfgHeader.getLivelloPianificazione()).thenReturn(livello);
        return cfgHeader;
    }

    private CfgLivelloPianificazioneEntity mockCfgLivelloPianificazione(String desc) {
        final CfgLivelloPianificazioneEntity livello = mock(CfgLivelloPianificazioneEntity.class);
        when(livello.getDescrizione()).thenReturn(desc);
        return livello;
    }

    private PromozioneStatoEntity mockStatoPromozione(PromoStatusEnum codiceStato) {
        final StatoPromozioneEntity stato = mock(StatoPromozioneEntity.class);
        final PromozioneStatoEntity promoStato = mock(PromozioneStatoEntity.class);
        when(stato.getCodiceStato()).thenReturn(codiceStato.getKey());
        when(promoStato.getStatoPromozioneEntity()).thenReturn(stato);
        return promoStato;
    }
}
