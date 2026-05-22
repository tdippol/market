package com.axiante.mui.webapp.webservice.util.pianificazione;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasNoJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PianificazioneHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
public class DepartmentColumnDefTest extends ColumnDefTest {

    @Mock
    private Instance<PianificazioneService> pianificazioneServiceInstance;

    @Mock
    private PianificazioneService pianificazioneService;

    @Mock
    private Instance<CfgConfHeaderService> confHeaderServiceInstance;

    @Mock
    private CfgConfHeaderService confHeaderService;

    @Mock
    private Instance<PianificazioneHelper> pianificazioneHelperInstance;

    @Mock
    private PianificazioneHelper pianificazioneHelper;

    @InjectMocks
    private DepartmentColumnDef columnDef = new DepartmentColumnDef();

    @Before
    public void setUp() {
        when(pianificazioneServiceInstance.get()).thenReturn(pianificazioneService);
        when(pianificazioneHelperInstance.get()).thenReturn(pianificazioneHelper);
    }

    @Test
    public void generateColumnDefByPromoConfiguration_givenPromozioneTestata_shouldReturnJsonString() throws IOException {
        // Good path
        final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
        final String hiddenCols = readJsonAsString("hiddenColsForDepartment.json");
        String json = columnDef.generateColumnDefByPromoConfiguration(entity, hiddenCols, "grid1", null);
        assertNotNull(json);
        DocumentContext cxt = JsonPath.parse(json);
        assertFalse(cxt.read("$.columnDef[0].hide", Boolean.class));
        assertTrue(cxt.read("$.columnDef[1].hide", Boolean.class));
        // Unable to apply filter
        json = columnDef.generateColumnDefByPromoConfiguration(entity, "wrong", "grid2", null);
        assertNotNull(json);
        cxt = JsonPath.parse(json);
        assertFalse(cxt.read("$.columnDef[0].hide", Boolean.class));
        assertFalse(cxt.read("$.columnDef[1].hide", Boolean.class));
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnRowData() {
        // Arrange
        final List<RepartoEntity> reparti = mockReparti();
        when(pianificazioneService.getAllReparti()).thenReturn(reparti);
        // Act
        final String json = columnDef.generateRowDataByPromoConfiguration("foo", "bar");
        // Assert
        assertNotNull(json);
        assertThat(json, isJson(withJsonPath("$.rowData", hasSize(5))));
    }

    @Test
    public void generateRowDataByPromoPianificazioneMaster_shouldReturnJsonNode() {
        // Arrange
        final MeccanicheEntity meccanica = mockMeccanica();
        final PromozioneTestataEntity testata = mockTestata(meccanica);
        final PromozionePianificazioneEntity entity = mockPianificazione(meccanica, testata);
        final List<RepartoEntity> reparti = mockReparti();
        when(pianificazioneService.getAllReparti()).thenReturn(reparti);
        // Act
        final ObjectNode node = columnDef.generateRowDataByPromoPianificazioneMaster(entity);
        // Assert
        assertNotNull(node);
        assertThat(node.toString(), isJson(withJsonPath("$.rowData", hasSize(4))));
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnNull() {
        final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
        final PlanningArticleMultiFilterParam param = mock(PlanningArticleMultiFilterParam.class);
        assertNull(columnDef.generateRowDataByPromoConfiguration(entity, 1L, param, true, null));
    }

    @Test
    public void generateRowDataFilteredByPromoConfiguration_shouldReturnNull() {
        assertNull(columnDef.generateRowDataFilteredByPromoConfiguration("foo", "bar"));
    }

    @Test
    public void generateRowDataByPromoElementMechanic_shouldDiscardDuplicateItems_whenHeaderFlagIsFalse() {
        final List<RepartoEntity> reparti = new ArrayList<>();
        final RepartoEntity reparto1 = mockReparto(1);
        final RepartoEntity reparto2 = mockReparto(2);
        reparti.add(reparto1);
        reparti.add(reparto2);
        final MeccanicheEntity meccanica = mockMeccanica();
        final PromozioneTestataEntity testata = mockTestata(meccanica);
        final CfgConfHeaderEntity confHeaderEntity = mock(CfgConfHeaderEntity.class);
        final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        final CfgLivelloPianificazioneEntity planningLevel = mock(CfgLivelloPianificazioneEntity.class);
        when(cfgSetPianificazione.getId()).thenReturn(1L);
        when(planningLevel.getCodice()).thenReturn("RAGGRUPPAMENTO");
        when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(anyLong(), anyLong())).thenReturn(confHeaderEntity);
        when(confHeaderEntity.getDuplicaReparto()).thenReturn(false);
        when(confHeaderEntity.getLivelloPianificazione()).thenReturn(planningLevel);
        when(confHeaderServiceInstance.get()).thenReturn(confHeaderService);
        when(pianificazioneService.getAllReparti()).thenReturn(reparti);
        when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        when(pianificazioneHelper.getUsedItems(testata, 1L, "RAGGRUPPAMENTO",
                PromoPianificazioneEnum.REPARTO.getTipoElemento())).thenReturn(Collections.singletonList(2L));
        final ObjectNode rowData = columnDef.generateRowDataByPromoElementMechanic(testata, 1L, "foo", "bar");
        assertThat(rowData.toString(), isJson(allOf(
                withJsonPath("$.rowData", hasSize(1)),
                withJsonPath("$.rowData[0].id.value", equalTo("1")),
                withJsonPath("$.rowData[0].reparto.value", equalTo("[R1] REPARTO1")),
                withJsonPath("$.rowData[0].elemento.value", equalTo("R1 - Reparto1"))
        )));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void generateRowDataByPromoElementMechanic_shouldAllowDuplicateItems_whenHeaderFlagIsTrue() {
        final List<RepartoEntity> reparti = new ArrayList<>();
        final RepartoEntity reparto1 = mockReparto(1);
        final RepartoEntity reparto2 = mockReparto(2);
        reparti.add(reparto1);
        reparti.add(reparto2);
        final MeccanicheEntity meccanica = mockMeccanica();
        final PromozioneTestataEntity testata = mockTestata(meccanica);
        final CfgConfHeaderEntity confHeaderEntity = mock(CfgConfHeaderEntity.class);
        final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        when(cfgSetPianificazione.getId()).thenReturn(1L);
        when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(anyLong(), anyLong())).thenReturn(confHeaderEntity);
        when(confHeaderEntity.getDuplicaReparto()).thenReturn(true);
        when(confHeaderServiceInstance.get()).thenReturn(confHeaderService);
        when(pianificazioneService.getAllReparti()).thenReturn(reparti);
        when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
        when(cfgLivello.getCodice()).thenReturn("ELEMENTO");
        when(confHeaderEntity.getLivelloPianificazione()).thenReturn(cfgLivello);
        final ObjectNode rowData = columnDef.generateRowDataByPromoElementMechanic(testata, 1L, "foo", "bar");
        assertThat(rowData.toString(), isJson(allOf(
                withJsonPath("$.rowData", hasSize(2)),
                withJsonPath("$.rowData[0].id.value", equalTo("1")),
                withJsonPath("$.rowData[0].reparto.value", equalTo("[R1] REPARTO1")),
                withJsonPath("$.rowData[0].elemento.value", equalTo("R1 - Reparto1")),
                withJsonPath("$.rowData[1].id.value", equalTo("2")),
                withJsonPath("$.rowData[1].reparto.value", equalTo("[R2] REPARTO2")),
                withJsonPath("$.rowData[1].elemento.value", equalTo("R2 - Reparto2"))
        )));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void generateRowDataByPromoElementMechanic_shouldSetBackgroundStyleYellowOnItemsAlreadyPlanned_whenDuplicaFlagSetted() {
        final List<RepartoEntity> reparti = new ArrayList<>();
        final RepartoEntity reparto1 = mockReparto(1);
        final RepartoEntity reparto2 = mockReparto(2);
        reparti.add(reparto1);
        reparti.add(reparto2);
        final MeccanicheEntity meccanica = mockMeccanica();
        final PromozioneTestataEntity testata = mockTestata(meccanica);
        final CfgConfHeaderEntity confHeaderEntity = mock(CfgConfHeaderEntity.class);
        final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        when(cfgSetPianificazione.getId()).thenReturn(1L);
        when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(anyLong(), anyLong())).thenReturn(confHeaderEntity);
        when(confHeaderEntity.getDuplicaReparto()).thenReturn(true);
        when(confHeaderServiceInstance.get()).thenReturn(confHeaderService);
        when(pianificazioneService.getAllReparti()).thenReturn(reparti);
        when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        when(pianificazioneHelper.getUsedItems(testata, 1L, "ELEMENTO", "REPARTO"))
                .thenReturn(Collections.singletonList(1L));
        final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
        when(cfgLivello.getCodice()).thenReturn("ELEMENTO");
        when(confHeaderEntity.getLivelloPianificazione()).thenReturn(cfgLivello);
        final ObjectNode rowData = columnDef.generateRowDataByPromoElementMechanic(testata, 1L, "foo", "bar");
        assertThat(rowData.toString(), isJson(allOf(
                withJsonPath("$.rowData", hasSize(2)),
                withJsonPath("$.rowData[0].id.value", equalTo("1")),
                withJsonPath("$.rowData[0].reparto.value", equalTo("[R1] REPARTO1")),
                withJsonPath("$.rowData[0].elemento.value", equalTo("R1 - Reparto1")),
                withJsonPath("$.rowData[0].reparto.warning", equalTo(true)),
                withJsonPath("$.rowData[1].id.value", equalTo("2")),
                withJsonPath("$.rowData[1].reparto.value", equalTo("[R2] REPARTO2")),
                withJsonPath("$.rowData[1].elemento.value", equalTo("R2 - Reparto2")),
                hasNoJsonPath("$.rowData[1].reparto.styleParams.backgroundColor")
        )));
    }

    private RepartoEntity mockReparto(int id) {
        final RepartoEntity e = mock(RepartoEntity.class);
        when(e.getId()).thenReturn((long) id);
        when(e.getDescrizione()).thenReturn(String.format("Reparto%d", id));
        when(e.getCodiceReparto()).thenReturn(String.format("R%d", id));
        return e;
    }

    private List<RepartoEntity> mockReparti() {
        List<RepartoEntity> entities = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final RepartoEntity entity = mock(RepartoEntity.class);
            when(entity.getId()).thenReturn((long) i);
            if (i % 2 == 0) {
                when(entity.getCodiceReparto()).thenReturn("R" + i);
                when(entity.getDescrizione()).thenReturn("Reparto " + i);
            }
            entities.add(entity);
        }
        return entities;
    }

    private PromozionePianificazioneEntity mockPianificazione(final MeccanicheEntity meccanica,
                                                              final PromozioneTestataEntity testata) {
        final PromozionePianificazioneEntity entity = mock(PromozionePianificazioneEntity.class);
        when(entity.getMeccanicaEntity()).thenReturn(meccanica);
        when(entity.getPromozioneTestataEntity()).thenReturn(testata);
        return entity;
    }

    private PromozioneTestataEntity mockTestata(final MeccanicheEntity meccanica) {
        final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(canale.getDuplicaReparto()).thenReturn(true);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        final Set<PromozionePianificazioneEntity> pianificazioni = new HashSet<>();
        for (int i = 1; i <= 5; i++) {
            final PromozionePianificazioneEntity p = mock(PromozionePianificazioneEntity.class);
            when(p.getCodiceElemento()).thenReturn(String.valueOf(i));
            when(p.getMeccanicaEntity()).thenReturn(meccanica);
            if (i == 3) {
                when(p.getTipoElemento()).thenReturn("reparto");
                when(p.getParent()).thenReturn(mock(PromozionePianificazioneEntity.class));
            }
            pianificazioni.add(p);
        }
        when(testata.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
        return testata;
    }

    private MeccanicheEntity mockMeccanica() {
        final MeccanicheEntity e = mock(MeccanicheEntity.class);
        when(e.getId()).thenReturn(1L);
        return e;
    }
}
