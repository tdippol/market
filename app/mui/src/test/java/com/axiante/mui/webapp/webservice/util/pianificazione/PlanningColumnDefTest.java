package com.axiante.mui.webapp.webservice.util.pianificazione;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
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
import com.axiante.mui.dbpromo.persistence.support.builders.CfgSetPianificazioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozionePianificazioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozioneTestataEntityBuilder;
import com.axiante.mui.webapp.support.builders.CfgConfHeaderEntityBuilder;
import com.axiante.mui.webapp.support.builders.CfgLivelloPianificazioneEntityBuilder;
import com.axiante.mui.webapp.support.builders.MeccanicaEntityBuilder;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromoConfigurationHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PlanningCommons;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlanningColumnDefTest extends ColumnDefTest {

    @Mock
    private PromoService promoService;

    @Mock
    private PianificazioneService pianificazioneService;

    @Mock
    private UploadFidatyService uploadFidaty;

    @Mock
    private PlanningCommons planningCommons;

    @Mock
    private PromoConfigurationHelper promoConfigurationHelper;

    @Mock
    PianificazionePromoUtil pianificazionePromoUtil;

    @Mock
    PromozioneTestataService promozioneTestataService;

    @Mock
    LookupUtils lookupUtils;

    @InjectMocks
    private PlanningColumnDef columnDef;

    // Useful stuff
    private CfgConfHeaderEntity cfgHeaderEntity;
    private PromozioneTestataEntity testataEntity;
    private MeccanicheEntity meccanicaEntity;
    private List<CfgPianificazioneEntity> cfgPianificazioni;

    @Before
    public void setUp() {
        final CfgSetPianificazioneEntity setPianificazioneEntity = new CfgSetPianificazioneEntityBuilder(1L)
                .withDataInizio(new GregorianCalendar(1, Calendar.APRIL, 1).getTime())
                .withDataFine(new GregorianCalendar(1, Calendar.APRIL, 30).getTime())
                .withDescrizione("JUNIT TEST")
                .build();
        testataEntity = createTestataEntity(1L, setPianificazioneEntity);
        cfgHeaderEntity = createCfgHeader(1L, setPianificazioneEntity);
        setPianificazioneEntity.setMuiCfgConfHeaders(Collections.singleton(cfgHeaderEntity));
        meccanicaEntity = new MeccanicaEntityBuilder(1L)
                .withCodice("MC1")
                .withDescrizione("MECCANICA CON SET")
                .withHeader(cfgHeaderEntity)
                .build();
        cfgHeaderEntity.setMeccanicaEntity(meccanicaEntity);
        final PromozionePianificazioneEntity promoPianificazioneEntity1 = createPromoPianificazioneEntity(1L,
                testataEntity, meccanicaEntity, PianificazioneRowTypeEnum.SET);
        final PromozionePianificazioneEntity promoPianificazioneEntity2 = createPromoPianificazioneEntity(2L,
                testataEntity, meccanicaEntity, PianificazioneRowTypeEnum.RAGGRUPPAMENTO);
        promoPianificazioneEntity1.setChildren(Collections.singleton(promoPianificazioneEntity2));
        final List<PromozionePianificazioneEntity> promoPianificazioni = Arrays.asList(promoPianificazioneEntity1,
                promoPianificazioneEntity2);
        final CfgPianificazTipoRigaEntity tipoRigaSet = createTipoRigaEntity(1L, PianificazioneRowTypeEnum.SET);
        final CfgPianificazTipoRigaEntity tipoRigaRaggr = createTipoRigaEntity(2L, PianificazioneRowTypeEnum.RAGGRUPPAMENTO);
        final CfgPianificazioneEntity cfgPianificazione1 = createCfgPianificazione(1L, cfgHeaderEntity, tipoRigaSet);
        final CfgPianificazioneEntity cfgPianificazione2 = createCfgPianificazione(2L, cfgHeaderEntity, tipoRigaRaggr);
        cfgPianificazioni = Arrays.asList(cfgPianificazione1, cfgPianificazione2);
        when(promoService.findById(1L)).thenReturn(testataEntity);
        when(promozioneTestataService.findByIdFullEagerFetch(1L)).thenReturn(testataEntity);
        when(planningCommons.isPlanningEditableCellAndRow(testataEntity)).thenReturn(true);
        when(pianificazioneService.findAllParentPromozionePianificazioneEntityByPromozioneTestata(testataEntity))
                .thenReturn(promoPianificazioni);
        when(promoConfigurationHelper.getConfigurazioniSorted(testataEntity)).thenReturn(cfgPianificazioni);
        when(uploadFidaty.findByPianificazione(anyLong())).thenReturn(null);

    // MF: now the Testata Entity is an eager fetch
    testataEntity.setPromozionePianificazioneEntities(new HashSet<>(promoPianificazioni));
    promoPianificazioni.forEach(p->p.setPromozioneTestataEntity(testataEntity));
    }

    @Test
    public void generateColumnDefByPromoConfiguration_givenPromozioneTestata_shouldReturnJsonString() throws IOException {
        // Arrange
        final String hiddenCols = readJsonAsString("hiddenColsForPlanning.json");
        when(promoConfigurationHelper.getConfigurazioniFiltrate(testataEntity)).thenReturn(cfgPianificazioni);
        // Act
        final String json = columnDef.generateColumnDefByPromoConfiguration(testataEntity, hiddenCols, "grid1", null);
        // Assert
        assertNotNull(json);
        final DocumentContext cxt = JsonPath.parse(json);
        assertFalse(cxt.read("$.columnDef[0].hide", Boolean.class));
        assertEquals("selected", cxt.read("$.columnDef[0].field", String.class));
        assertFalse(cxt.read("$.columnDef[1].hide", Boolean.class));
        assertEquals("CAMPO_1", cxt.read("$.columnDef[1].field", String.class));
        assertTrue(cxt.read("$.columnDef[2].hide", Boolean.class));
        assertEquals("CAMPO_2", cxt.read("$.columnDef[2].field", String.class));
    }

    @Test(expected = NullPointerException.class)
    public void generateRowDataByPromoConfiguration_givenNullPromo_shouldThrowException() {
        columnDef.generateRowDataByPromoConfiguration(null, null);
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnEmptyRowData_whenTestataIsNull() {
        when(promozioneTestataService.findByIdFullEagerFetch(1L)).thenReturn(null);
        final String json = columnDef.generateRowDataByPromoConfiguration("1", null);
        assertNotNull(json);
        assertEquals(0, (int) JsonPath.read(json, "$.rowData.length()"));
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnEmptyRowData_whenException() {
        when(promozioneTestataService.findByIdFullEagerFetch(1L)).thenThrow(RuntimeException.class);
        final String json = columnDef.generateRowDataByPromoConfiguration("1", null);
        assertNotNull(json);
        assertEquals(0, (int) JsonPath.read(json, "$.rowData.length()"));
    }

    @Test
    public void generateRowDataByPromoConfiguration_givenMeccanicaAtSetLevel_shouldDeleteEnabledBeTrue() {
        // Arrange
        cfgHeaderEntity.setMinSet(1);
        when(promoConfigurationHelper.getHeaderFromTestataAndMeccanica(testataEntity, meccanicaEntity))
                .thenReturn(cfgHeaderEntity);
        when(pianificazionePromoUtil.isPianificazioneLockedOnDataFine(any(PromozionePianificazioneEntity.class))).thenReturn(false);
        when(pianificazionePromoUtil.isPianificazioneLockedOnDataInizio(any(PromozionePianificazioneEntity.class))).thenReturn(false);
        // Act
        final String rowData = columnDef.generateRowDataByPromoConfiguration("1", null);
        // Assert
        assertNotNull(rowData);
        assertEquals("true", JsonPath.read(rowData, "$.rowData[0].deleteEnabled.value"));
    }

    @Test
    public void generateRowDataByPromoPianificazioneMaster_shouldReturnNull() {
        assertNull(columnDef.generateRowDataByPromoPianificazioneMaster(null));
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnNull() {
        assertNull(columnDef.generateRowDataByPromoConfiguration(null, null, null, true, null));
    }

    @Test
    public void generateRowDataByPromoElementMechanic_shouldReturnNull() {
        assertNull(columnDef.generateRowDataByPromoElementMechanic(null, null, null, null));
    }

    @Test
    public void generateRowDataFilteredByPromoConfiguration_shouldReturnJsonString() {
        // Assert
        when(promoService.findById(1L)).thenReturn(testataEntity);
        when(planningCommons.isPlanningEditableCellAndRow(testataEntity)).thenReturn(false);
        when(promoConfigurationHelper.getHeaderFromTestataAndMeccanica(testataEntity, meccanicaEntity))
                .thenReturn(cfgHeaderEntity);

        doNothing().when(lookupUtils).initializeLookups(any(PromozioneTestataEntity.class), anyList());
        // Act
        final String json = columnDef.generateRowDataFilteredByPromoConfiguration("1", null);
        // Assert
        assertNotNull(json);
        assertTrue((int) JsonPath.read(json, "$.rowData.length()") > 0);
    }

    @Test
    public void generateRowDataFilteredByPromoConfiguration_shouldReturnEmptyJsonData_whenUnableToFindPromo() {
        // Arrange
        when(promoService.findById(1L)).thenThrow(RuntimeException.class);
        // Act
        final String json = columnDef.generateRowDataFilteredByPromoConfiguration("1", null);
        // Assert
        assertNotNull(json);
        assertEquals(0, (int) JsonPath.read(json, "$.rowData.length()"));
    }

    private PromozioneTestataEntity createTestataEntity(Long id, CfgSetPianificazioneEntity cfgSetPianificazioneEntity) {
        final StatoPromozioneEntity statoPromozioneEntity = new StatoPromozioneEntity();
        statoPromozioneEntity.setId(1L);
        statoPromozioneEntity.setCodiceStato("FOO");
        final PromozioneStatoEntity promoStatoEntity = new PromozioneStatoEntity();
        promoStatoEntity.setId(1L);
        promoStatoEntity.setStatoPromozioneEntity(statoPromozioneEntity);
        final CanalePromozioneEntity canalePromozioneEntity = new CanalePromozioneEntity();
        canalePromozioneEntity.setId(1L);
        canalePromozioneEntity.setAbilitaUpload(1);
        final PromozioneTestataEntity testataEntity = new PromozioneTestataEntityBuilder(id)
                .withCfgSetPianificazione(cfgSetPianificazioneEntity)
                .build();
        testataEntity.setPromozioneStatoEntities(Collections.singleton(promoStatoEntity));
        testataEntity.setMuiCanalePromozione(canalePromozioneEntity);
        return testataEntity;
    }

    private CfgConfHeaderEntity createCfgHeader(Long id, CfgSetPianificazioneEntity cfgSetPianificazioneEntity) {
        final CfgLivelloPianificazioneEntity cfgLevelEntity = new CfgLivelloPianificazioneEntityBuilder(id)
                .withCodice(PlanningLevelEnum.SET.getCode())
                .withDescrizione(PlanningLevelEnum.SET.getDescription())
                .build();
        return new CfgConfHeaderEntityBuilder(id)
                .withCfgLivelloPianificazione(cfgLevelEntity)
                .withCfgSetPianificazione(cfgSetPianificazioneEntity)
                .build();
    }

    private PromozionePianificazioneEntity createPromoPianificazioneEntity(Long id,
                                                                           PromozioneTestataEntity testataEntity,
                                                                           MeccanicheEntity meccanicaEntity,
                                                                           PianificazioneRowTypeEnum tipoRiga) {
        return new PromozionePianificazioneEntityBuilder(id)
                .withPromozioneTestata(testataEntity)
                .withMeccanica(meccanicaEntity)
                .withTipoRiga(tipoRiga)
                .build();
    }

    private CfgPianificazioneEntity createCfgPianificazione(Long id,
                                                            CfgConfHeaderEntity confHeaderEntity,
                                                            CfgPianificazTipoRigaEntity tipoRigaEntity) {
        final CfgPianificazioneCampiEntity campoEntity = new CfgPianificazioneCampiEntity();
        campoEntity.setId(id);
        campoEntity.setDescrizione("CAMPO " + id);
        campoEntity.setCampo("CAMPO_" + id);
        campoEntity.setTipo("Integer");
        final CfgPianificazioneEntity entity = new CfgPianificazioneEntity();
        entity.setId(id);
        entity.setMuiCfgConfHeader(confHeaderEntity);
        entity.setMuiCfgPianificazioneCampi(campoEntity);
        entity.setMuiCfgPianificazTipoRiga(tipoRigaEntity);
        return entity;
    }

    private CfgPianificazTipoRigaEntity createTipoRigaEntity(Long id, PianificazioneRowTypeEnum rowType) {
        final CfgPianificazTipoRigaEntity entity = new CfgPianificazTipoRigaEntity();
        entity.setId(id);
        entity.setCodiceTipo(rowType.getTypeCode());
        entity.setDescrizione(rowType.getDescription());
        return entity;
    }
}
