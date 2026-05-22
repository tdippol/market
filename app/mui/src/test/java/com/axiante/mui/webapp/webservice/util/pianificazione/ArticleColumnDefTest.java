package com.axiante.mui.webapp.webservice.util.pianificazione;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasNoJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.dbpromo.persistence.entities.AssortimentoFornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PianificazioneHelper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
public class ArticleColumnDefTest extends ColumnDefTest {

    @Mock
    private PianificazioneService service;

    @Mock
    private Instance<CfgConfHeaderService> confHeaderServiceInstance;

    @Mock
    private CfgConfHeaderService confHeaderService;

    @Mock
    private Instance<PianificazioneHelper> pianificazioneHelperInstance;

    @Mock
    private PianificazioneHelper pianificazioneHelper;

    @InjectMocks
    private ArticleColumnDef columnDef;

    @Before
    public void setUp() {
        when(confHeaderServiceInstance.get()).thenReturn(confHeaderService);
        when(pianificazioneHelperInstance.get()).thenReturn(pianificazioneHelper);
    }

    @Test
    public void generateColumnDefByPromoConfiguration_givenPromozioneTestata_shouldReturnJsonString() throws IOException {
        // Good path
        final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
        final String hiddenCols = readJsonAsString("hiddenColsForArticle.json");
        String json = columnDef.generateColumnDefByPromoConfiguration(entity, hiddenCols, "grid1", null);
        assertNotNull(json);
        assertThat(json, isJson(allOf(
                withJsonPath("$.columnDef[7].hide", equalTo(false)),
                withJsonPath("$.columnDef[8].hide", equalTo(true))
        )));
        // Unable to apply filter - return as is
        json = columnDef.generateColumnDefByPromoConfiguration(entity, "wrong", "grid2", null);
        assertNotNull(json);
        assertThat(json, isJson(allOf(
                withJsonPath("$.columnDef[6].hide", equalTo(false)),
                withJsonPath("$.columnDef[7].hide", equalTo(false))
        )));
    }

    @Test(expected = NullPointerException.class)
    public void generateRowDataByPromoConfiguration_givenNullBuyer_shouldThrowException() {
        columnDef.generateRowDataByPromoConfiguration(null, null);
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnRowData_whenBuyerValid() {
        // Arrange
        final CompratoreEntity compratore = mock(CompratoreEntity.class);
        final List<ItemEntity> items = mockItems();
        when(service.findCompratoreById(1L)).thenReturn(compratore);
        when(service.getAllItemsByCompratore(compratore)).thenReturn(items);
        // Act
        final String json = columnDef.generateRowDataByPromoConfiguration("1", "bar");
        // Assert
        assertNotNull(json);
        assertThat(json, isJson(
                withJsonPath("$.rowData", hasSize(10))
        ));
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnEmptyRowData_whenBuyerNotPresentOrInvalid() {
        // Arrange
        when(service.findCompratoreById(1L)).thenReturn(null);
        // Act & Assert
        String json = columnDef.generateRowDataByPromoConfiguration("1", "bar");
        assertNotNull(json);
        assertThat(json, isJson(
                withJsonPath("$.rowData", hasSize(0))
        ));
        // Act & Assert
        json = columnDef.generateRowDataByPromoConfiguration("foo", "bar");
        assertNotNull(json);
        assertThat(json, isJson(
                withJsonPath("$.rowData", hasSize(0))
        ));
    }

    @Test
    public void generateRowDataByPromoPianificazioneMaster_shouldReturnEmptyRowData() {
        final ObjectNode node = columnDef.generateRowDataByPromoPianificazioneMaster(null);
        assertNotNull(node);
        assertThat(node.toString(), isJson(
                withJsonPath("$.rowData", hasSize(0))
        ));
    }

    @Test
    public void generateRowDataByPromoConfiguration_givenFilter_shouldReturnJsonNode() {
        // Arrange
        final PromozioneTestataEntity entity = mockTestata();
        final PlanningArticleMultiFilterParam filterParam = mock(PlanningArticleMultiFilterParam.class);
        final List<ItemEntity> items = mockItems();
        final CfgConfHeaderEntity confHeader = mock(CfgConfHeaderEntity.class);
        final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
        when(cfgLivello.getCodice()).thenReturn("ELEMENTO");
        when(confHeader.getLivelloPianificazione()).thenReturn(cfgLivello);
        when(filterParam.getIdCategoriaSelected()).thenReturn(1L);
        when(filterParam.getIdCompratoreSelected()).thenReturn(null);
        when(filterParam.getIdFornitoreSelected()).thenReturn(null);
        when(filterParam.getIdGrmSelected()).thenReturn(null);
        when(filterParam.getIdRepartoSelected()).thenReturn(null);
        when(service.findAllItemsByDynamicFilters(null, null, null, 1L, null, null))
                .thenReturn(items);
        when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(anyLong(), anyLong()))
                .thenReturn(confHeader);
        // Act
        final ObjectNode node = columnDef.generateRowDataByPromoConfiguration(entity, 1L, filterParam, true, null);
        // Assert
        assertNotNull(node);
        assertThat(node.toString(), isJson(
                withJsonPath("$.rowData", hasSize(10))
        ));
    }

    @Test
    public void generateRowDataByPromoConfiguration_givenFilter_shouldReturnJsonNode_withoutItemAlreadyPresent() {
        // Arrange
        final PromozioneTestataEntity entity = mockTestata();
        final List<ItemEntity> items = mockItems();
        final PlanningArticleMultiFilterParam filterParam = mock(PlanningArticleMultiFilterParam.class);
        final CfgConfHeaderEntity confHeader = mock(CfgConfHeaderEntity.class);
        final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
        when(cfgLivello.getCodice()).thenReturn("ELEMENTO");
        when(confHeader.getLivelloPianificazione()).thenReturn(cfgLivello);
        when(filterParam.getIdCategoriaSelected()).thenReturn(1L);
        when(filterParam.getIdCompratoreSelected()).thenReturn(null);
        when(filterParam.getIdFornitoreSelected()).thenReturn(null);
        when(filterParam.getIdGrmSelected()).thenReturn(null);
        when(filterParam.getIdRepartoSelected()).thenReturn(null);
        items.get(0).setCodiceItem("DTL1");
        when(service.findAllItemsByDynamicFilters(null, null, null, 1L, null, null))
                .thenReturn(items);
        when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(anyLong(), anyLong()))
                .thenReturn(confHeader);
        // Act
        final ObjectNode node = columnDef.generateRowDataByPromoConfiguration(entity, 1L, filterParam, true, null);
        // Assert
        assertNotNull(node);
        assertThat(node.toString(), isJson(
                withJsonPath("$.rowData", hasSize(10))
        ));
    }

    @Test
    public void generateRowDataByPromoConfiguration_givenNoFilter_shouldReturnEmptyRowData() {
        // Arrange
        final PromozioneTestataEntity entity = mockTestata();
        final PlanningArticleMultiFilterParam filterParam = mock(PlanningArticleMultiFilterParam.class);
        when(filterParam.getIdCategoriaSelected()).thenReturn(null);
        when(filterParam.getIdCompratoreSelected()).thenReturn(null);
        when(filterParam.getIdFornitoreSelected()).thenReturn(null);
        when(filterParam.getIdGrmSelected()).thenReturn(null);
        when(filterParam.getIdRepartoSelected()).thenReturn(null);
        // Act
        final ObjectNode node = columnDef.generateRowDataByPromoConfiguration(entity, 1L, filterParam, true, null);
        // Assert
        assertNotNull(node);
        assertThat(node.toString(), isJson(
                withJsonPath("$.rowData", hasSize(0))
        ));
    }

    @Test(expected = NullPointerException.class)
    public void generateRowDataByPromoConfiguration_givenNullTestata_shouldThrowException() {
        columnDef.generateRowDataByPromoConfiguration(null, 1L, mock(PlanningArticleMultiFilterParam.class),
                true, null);
    }

    @Test(expected = NullPointerException.class)
    public void generateRowDataByPromoConfiguration_givenNullMeccanica_shouldThrowException() {
        columnDef.generateRowDataByPromoConfiguration(mock(PromozioneTestataEntity.class), null,
                mock(PlanningArticleMultiFilterParam.class), true, null);
    }

    @Test(expected = NullPointerException.class)
    public void generateRowDataByPromoConfiguration_givenNullFilterParam_shouldThrowException() {
        columnDef.generateRowDataByPromoConfiguration(mock(PromozioneTestataEntity.class), 1L, null, true, null);
    }

    @Test
    public void generateRowDataFilteredByPromoConfiguration_shouldReturnNull() {
        assertNull(columnDef.generateRowDataFilteredByPromoConfiguration(null, null));
    }

    @Test(expected = NullPointerException.class)
    public void generateRowDataByPromoElementMechanic_givenNullTestata_shouldThrowException() {
        columnDef.generateRowDataByPromoElementMechanic(null, 1L, "42", "FOO");
    }

    @Test(expected = NullPointerException.class)
    public void generateRowDataByPromoElementMechanic_givenNullMeccanica_shouldThrowException() {
        columnDef.generateRowDataByPromoElementMechanic(mock(PromozioneTestataEntity.class), null, "42", "FOO");
    }

    @Test
    public void generateRowDataByPromoElementMechanic_shouldReturnJson() {
        // Arrange
        final PromozioneTestataEntity entity = mockTestata();
        entity.getPromozionePianificazioneEntities().forEach(p -> p.setTipoElemento("articolo"));
        final List<ItemEntity> items = mockItems();
        when(service.findCompratoreById(1L)).thenReturn(mock(CompratoreEntity.class));
        when(service.getAllItemsByCompratore(any(CompratoreEntity.class))).thenReturn(items);
        // Act
        final ObjectNode node = columnDef.generateRowDataByPromoElementMechanic(entity, 1L, "1", "bar");
        // Assert
        assertNotNull(node);
        assertThat(node.toString(), isJson(
                withJsonPath("$.rowData", hasSize(10))
        ));
    }

    @Test
    public void generateRowDataByPromoElementMechanic_shouldReturnEmptyRowDate_whenBuyerNotPresentOrInvalid() {
        // Arrange
        when(service.findCompratoreById(1L)).thenReturn(null);
        // Act & Assert
        ObjectNode node = columnDef.generateRowDataByPromoElementMechanic(
                mock(PromozioneTestataEntity.class), 1L, null, null);
        assertNotNull(node);
        assertThat(node.toString(), isJson(
                withJsonPath("$.rowData", hasSize(0))
        ));
        // Act & Assert
        node = columnDef.generateRowDataByPromoElementMechanic(
                mock(PromozioneTestataEntity.class), 1L, "", null);
        assertNotNull(node);
        assertThat(node.toString(), isJson(
                withJsonPath("$.rowData", hasSize(0))
        ));
        // Act & Assert
        node = columnDef.generateRowDataByPromoElementMechanic(
                mock(PromozioneTestataEntity.class), 1L, "foo", null);
        assertNotNull(node);
        assertThat(node.toString(), isJson(
                withJsonPath("$.rowData", hasSize(0))
        ));
        // Act & Assert
        node = columnDef.generateRowDataByPromoElementMechanic(
                mock(PromozioneTestataEntity.class), 1L, "1", null);
        assertNotNull(node);
        assertThat(node.toString(), isJson(
                withJsonPath("$.rowData", hasSize(0))
        ));
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldSetBackgroundStyleYellowOnItemsAlreadyPlanned_whenDuplicaFlagSetted() {
        // Arrange
        final PromozioneTestataEntity testata = mockTestata();
        final PlanningArticleMultiFilterParam filterParam = mock(PlanningArticleMultiFilterParam.class);
        final List<ItemEntity> items = mockItems();
        final CfgConfHeaderEntity confHeader = mock(CfgConfHeaderEntity.class);
        final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
        when(cfgLivello.getCodice()).thenReturn("ELEMENTO");
        when(confHeader.getLivelloPianificazione()).thenReturn(cfgLivello);
        when(confHeader.getDuplicaArticolo()).thenReturn(true);
        when(filterParam.getIdCategoriaSelected()).thenReturn(1L);
        when(filterParam.getIdCompratoreSelected()).thenReturn(null);
        when(filterParam.getIdFornitoreSelected()).thenReturn(null);
        when(filterParam.getIdGrmSelected()).thenReturn(null);
        when(filterParam.getIdRepartoSelected()).thenReturn(null);
        when(service.findAllItemsByDynamicFilters(null, null, null, 1L, null, null))
                .thenReturn(items);
        when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(anyLong(), anyLong()))
                .thenReturn(confHeader);
        when(pianificazioneHelper.getUsedItems(testata, 1L, "ELEMENTO", "ARTICOLO"))
                .thenReturn(Collections.singletonList(1L));
        // Act
        final ObjectNode node = columnDef.generateRowDataByPromoConfiguration(testata, 1L, filterParam, true, null);
        // Assert
        assertNotNull(node);
        assertThat(node.toString(), isJson(allOf(
                withJsonPath("$.rowData", hasSize(10)),
                withJsonPath("$.rowData[0].codiceArticolo.value", equalTo("I-001")),
                withJsonPath("$.rowData[0].codiceArticolo.warning", equalTo(true)),
                withJsonPath("$.rowData[0].descrizioneArticolo.value", equalTo("ITEM 001")),
                hasNoJsonPath("$.rowData[1].codiceArticolo.styleParams.backgroundColor")
        )));
    }

    private PromozioneTestataEntity mockTestata() {
        final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(canale.getDuplicaArticolo()).thenReturn(true);
        when(cfgSetPianificazione.getId()).thenReturn(1L);
        when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        Set<PromozionePianificazioneEntity> pianificazioni = new HashSet<>();
        for (int i = 1; i <= 5; i++) {
            final PromozionePianificazioneEntity p = mock(PromozionePianificazioneEntity.class);
            final MeccanicheEntity meccanica = mockMeccanica(i);
            when(p.getMeccanicaEntity()).thenReturn(meccanica);
            when(p.getTipoElemento()).thenReturn(i == 3 ? "GRM" : "FOO");
            when(p.getParent()).thenReturn(mock(PromozionePianificazioneEntity.class));
            pianificazioni.add(p);
        }
        when(testata.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
        return testata;
    }

    private MeccanicheEntity mockMeccanica(int i) {
        final MeccanicheEntity m = mock(MeccanicheEntity.class);
        when(m.getId()).thenReturn((long) i);
        return m;
    }

    private List<ItemEntity> mockItems() {
        List<ItemEntity> entities = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            final ItemEntity entity = new ItemEntity();
            entity.setId((long) i);
            entity.setCodiceItem(String.format("I-%03d", i));
            entity.setDescrizione(String.format("ITEM %03d", i));
            entity.setCompratoreEntity(withCompratore(i));
            if (i % 3 == 0) {
                entity.setSubGrmEntity(withSubGrm(i));
            }
            entity.setMuiAssortimentoFornitores(new HashSet<>());
            entity.addMuiAssortimentoFornitore(withAssortimento(i));
            entities.add(entity);
        }
        return entities;
    }

    private CompratoreEntity withCompratore(int i) {
        final CompratoreEntity entity = new CompratoreEntity();
        entity.setId((long) i);
        if (i % 3 == 0) {
            entity.setCodiceCompratore(String.format("C-%03d", i));
            entity.setDescrizione("Compratore " + i);
        }
        return entity;
    }

    private AssortimentoFornitoreEntity withAssortimento(int i) {
        final AssortimentoFornitoreEntity entity = new AssortimentoFornitoreEntity();
        entity.setId((long) i);
        entity.setFornitoreEntity(withFornitore(i));
        return entity;
    }

    private FornitoreEntity withFornitore(int i) {
        final FornitoreEntity entity = new FornitoreEntity();
        entity.setId((long) i);
        entity.setCodiceFornitore(String.format("F-%03d", i));
        entity.setDescrizione("Fornitore " + i);
        return entity;
    }

    private SubGrmEntity withSubGrm(int i) {
        final SubGrmEntity entity = new SubGrmEntity();
        entity.setId((long) i);
        entity.setCodiceSubgrm(String.format("S-%03d", i));
        entity.setDescrizione("SubGrm " + 1);
        entity.setGrmEntity(withGrm(i));
        return entity;
    }

    private GrmEntity withGrm(int i) {
        final GrmEntity entity = new GrmEntity();
        entity.setId((long) i);
        entity.setCodiceGrm(String.format("G-%03d", i));
        entity.setDescrizione("Grm " + 1);
        entity.setMuiCategoria(withCategoria(i));
        return entity;
    }

    private CategoriaEntity withCategoria(int i) {
        final CategoriaEntity entity = new CategoriaEntity();
        entity.setId((long) i);
        entity.setCodiceCategoria(String.format("C-%03d", i));
        entity.setDescrizione("Categoria " + i);
        entity.setRepartoEntity(withReparto(i));
        return entity;
    }

    private RepartoEntity withReparto(int i) {
        final RepartoEntity entity = new RepartoEntity();
        entity.setId((long) i);
        entity.setCodiceReparto(String.format("R-%03d", i));
        entity.setDescrizione("Reparto " + i);
        return entity;
    }
}
