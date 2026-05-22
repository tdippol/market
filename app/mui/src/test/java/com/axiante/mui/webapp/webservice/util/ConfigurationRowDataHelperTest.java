package com.axiante.mui.webapp.webservice.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.promo.grid.ComboBoxValues;
import com.axiante.mui.dbpromo.business.utils.ComboBoxFactory;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.CfgLivelloPianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneCampiService;
import com.axiante.mui.webapp.webservice.util.configuration.CfgPianificazioneEntityHelper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import net.minidev.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationRowDataHelperTest {

    @Mock
    private CfgLivelloPianificazioneService service;

    @Mock
    private PianificazioneCampiService campiService;

    @Mock
    private CfgConfHeaderService cfgConfHeaderService;

    @Mock
    private ComboBoxFactory comboBoxFactory;

    @Mock
    private CfgPianificazioneEntityHelper pianificazioneEntityHelper;

    @Mock
    private CfgSetPianificazioneEntity cfgSetPianificazione;

    @Mock
    private CfgConfHeaderEntity cfgHeader1;

    @Mock
    private CfgConfHeaderEntity cfgHeader2;

    @Spy
    @InjectMocks
    private ConfigurationRowDataHelper helper;

    @Before
    public void setUp() {
        final CfgLivelloPianificazioneEntity lvlSet = mockCfgLivello(1L, "SET", "1", "Set");
        final CfgLivelloPianificazioneEntity lvlRaggr = mockCfgLivello(2L, "RAGGRUPPAMENTO", "2", "Raggruppamento");
        final CfgLivelloPianificazioneEntity lvlElem = mockCfgLivello(3L, "ELEMENTO", "3", "Elemento");
        final List<CfgLivelloPianificazioneEntity> levels = Arrays.asList(lvlSet, lvlRaggr, lvlElem);
        final MeccanicheEntity meccanica1 = mockMeccanica("FOO", "Meccanica Foo");
        final MeccanicheEntity meccanica2 = mockMeccanica("BAR", "Meccanica Bar");
        final CfgTipoElementoEntity tipoElemento1 = mockCfgTipoElemento(1L, 1, 1, 1, 0);
        final CfgTipoElementoEntity tipoElemento2 = mockCfgTipoElemento(2L, 2, 0, 0, 1);
        final CfgPianificazioneEntity cfgPianificazione1 = mockCfgPianificazione(1L);
        final CfgPianificazioneEntity cfgPianificazione2 = mockCfgPianificazione(2L);
        final CfgConfHeaderEntity header1 = mockCfgHeader(1L, meccanica1, lvlSet);
        final CfgConfHeaderEntity header2 = mockCfgHeader(2L, meccanica2, lvlElem);
        when(header1.getMinSet()).thenReturn(1);
        when(header1.getMinRaggruppamento()).thenReturn(2);
        when(header1.getMaxRaggruppamento()).thenReturn(2);
        when(header1.getUnicaInPromo()).thenReturn(1);
        when(header1.getDuplicaArticolo()).thenReturn(false);
        when(header1.getDuplicaReparto()).thenReturn(true);
        when(header1.getDuplicaGrm()).thenReturn(true);
        when(header2.getDuplicaArticolo()).thenReturn(true);
        final List<ComboBoxValues> values = levels.stream()
                .map(l -> new ComboBoxValues(l.getKey(), l.getLabel()))
                .sorted(Comparator.comparing(ComboBoxValues::getLabel))
                .collect(Collectors.toList());
        when(service.findAll()).thenReturn(levels);
        when(pianificazioneEntityHelper.isEntityLookup(any(CfgPianificazioneEntity.class))).thenReturn(false);
        when(comboBoxFactory.from(levels, false)).thenReturn(values);
        when(cfgSetPianificazione.getId()).thenReturn(1L);
        when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(new HashSet<>(Arrays.asList(header1, header2)));
        when(cfgHeader1.getId()).thenReturn(11L);
        when(cfgHeader1.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        when(cfgHeader1.getTipiElemento()).thenReturn(new HashSet<>(Arrays.asList(tipoElemento1, tipoElemento2)));
        when(cfgHeader1.getMuiCfgPianificaziones())
                .thenReturn(new HashSet<>(Arrays.asList(cfgPianificazione1, cfgPianificazione2)));
        when(cfgHeader2.getLivelloPianificazione()).thenReturn(lvlSet);
    }

    @Test(expected = NullPointerException.class)
    public void createRowDataPlanningHeader_givenNullEntity_shuldThrowException() {
        helper.createRowDataPlanningHeader(null);
    }

    @Test
    public void createRowDataPlanningHeader_givenValidEntity_shuldReturnJsonRowData() {
        when(cfgConfHeaderService.isFieldMeccanicaFullyConfigured(1L)).thenReturn(true);
        when(cfgConfHeaderService.isFieldMeccanicaFullyConfigured(2L)).thenReturn(false);
        final String rowData = helper.createRowDataPlanningHeader(cfgSetPianificazione);
        assertNotNull(rowData);
        assertFalse(rowData.isEmpty());
        final DocumentContext cxt = JsonPath.parse(rowData);
        assertEquals(2, (int) cxt.read("$.rowData.length()"));
        // Assertion on first node (header2)
        assertEquals("true", cxt.read("$.rowData[0].deleteEnabled.value"));
        assertEquals("2", cxt.read("$.rowData[0].id.value"));
        assertFalse(cxt.read("$.rowData[0].id.editable", Boolean.class));
        assertEquals("1", cxt.read("$.rowData[0].idCfgSetPianificazione.value"));
        assertFalse(cxt.read("$.rowData[0].idCfgSetPianificazione.editable", Boolean.class));
        assertEquals("BAR - Meccanica Bar", cxt.read("$.rowData[0].meccanica.value"));
        assertFalse(cxt.read("$.rowData[0].meccanica.editable", Boolean.class));
        assertEquals("3", cxt.read("$.rowData[0].livello_pianificazione.value"));
        assertEquals("comboBox", cxt.read("$.rowData[0].livello_pianificazione.type"));
        assertEquals(3, ((JSONArray) cxt.read("$.rowData[0].livello_pianificazione.comboBoxValues.*.label")).size());
        assertEquals("Elemento", cxt.read("$.rowData[0].livello_pianificazione.comboBoxValues[0].label"));
        assertEquals("Raggruppamento", cxt.read("$.rowData[0].livello_pianificazione.comboBoxValues[1].label"));
        assertEquals("Set", cxt.read("$.rowData[0].livello_pianificazione.comboBoxValues[2].label"));
        assertTrue(cxt.read("$.rowData[0].min_set.nullable", Boolean.class));
        assertTrue(cxt.read("$.rowData[0].max_set.nullable", Boolean.class));
        assertTrue(cxt.read("$.rowData[0].min_raggr.nullable", Boolean.class));
        assertTrue(cxt.read("$.rowData[0].max_raggr.nullable", Boolean.class));
        assertEquals("false", cxt.read("$.rowData[0].unica_in_promo.value"));
        assertEquals("checkbox", cxt.read("$.rowData[0].unica_in_promo.type"));
        assertTrue(cxt.read("$.rowData[0].id.mandatory"));
        assertTrue(cxt.read("$.rowData[0].idCfgSetPianificazione.mandatory"));
        assertTrue(cxt.read("$.rowData[0].meccanica.mandatory"));
        assertTrue(cxt.read("$.rowData[0].livello_pianificazione.mandatory"));
        assertTrue(cxt.read("$.rowData[0].min_set.mandatory"));
        assertTrue(cxt.read("$.rowData[0].max_set.mandatory"));
        assertTrue(cxt.read("$.rowData[0].min_raggr.mandatory"));
        assertTrue(cxt.read("$.rowData[0].max_raggr.mandatory"));
        assertTrue(cxt.read("$.rowData[0].unica_in_promo.mandatory"));
        assertEquals("true", cxt.read("$.rowData[0].duplica_articolo.value"));
        assertEquals("checkbox", cxt.read("$.rowData[0].duplica_articolo.type"));
        assertEquals("false", cxt.read("$.rowData[0].duplica_reparto.value"));
        assertEquals("checkbox", cxt.read("$.rowData[0].duplica_reparto.type"));
        assertEquals("false", cxt.read("$.rowData[0].duplica_grm.value"));
        assertEquals("checkbox", cxt.read("$.rowData[0].duplica_grm.type"));
        // Assertion on second node (header1)
        assertEquals("true", cxt.read("$.rowData[0].deleteEnabled.value"));
        assertEquals("1", cxt.read("$.rowData[1].id.value"));
        assertFalse(cxt.read("$.rowData[1].id.editable", Boolean.class));
        assertEquals("1", cxt.read("$.rowData[1].idCfgSetPianificazione.value"));
        assertFalse(cxt.read("$.rowData[1].idCfgSetPianificazione.editable", Boolean.class));
        assertEquals("FOO - Meccanica Foo", cxt.read("$.rowData[1].meccanica.value"));
        assertFalse(cxt.read("$.rowData[1].meccanica.editable", Boolean.class));
        assertEquals("1", cxt.read("$.rowData[1].livello_pianificazione.value"));
        assertEquals("comboBox", cxt.read("$.rowData[1].livello_pianificazione.type"));
        assertEquals(3, ((JSONArray) cxt.read("$.rowData[1].livello_pianificazione.comboBoxValues.*.label")).size());
        assertEquals("Elemento", cxt.read("$.rowData[1].livello_pianificazione.comboBoxValues[0].label"));
        assertEquals("Raggruppamento", cxt.read("$.rowData[1].livello_pianificazione.comboBoxValues[1].label"));
        assertEquals("Set", cxt.read("$.rowData[1].livello_pianificazione.comboBoxValues[2].label"));
        assertEquals("1", cxt.read("$.rowData[1].min_set.value"));
        assertTrue(cxt.read("$.rowData[1].min_set.nullable", Boolean.class));
        assertTrue(cxt.read("$.rowData[1].max_set.nullable", Boolean.class));
        assertEquals("2", cxt.read("$.rowData[1].min_raggr.value"));
        assertTrue(cxt.read("$.rowData[1].min_raggr.nullable", Boolean.class));
        assertEquals("2", cxt.read("$.rowData[1].max_raggr.value"));
        assertTrue(cxt.read("$.rowData[1].max_raggr.nullable", Boolean.class));
        assertEquals("true", cxt.read("$.rowData[1].unica_in_promo.value"));
        assertEquals("checkbox", cxt.read("$.rowData[1].unica_in_promo.type"));
        assertEquals("false", cxt.read("$.rowData[1].duplica_articolo.value"));
        assertEquals("checkbox", cxt.read("$.rowData[1].duplica_articolo.type"));
        assertEquals("true", cxt.read("$.rowData[1].duplica_reparto.value"));
        assertEquals("checkbox", cxt.read("$.rowData[1].duplica_reparto.type"));
        assertEquals("true", cxt.read("$.rowData[1].duplica_grm.value"));
        assertEquals("checkbox", cxt.read("$.rowData[1].duplica_grm.type"));
    }

    @Test(expected = NullPointerException.class)
    public void createRowDataPlanningTipoElemento_givenNullEntity_shuldThrowException() {
        helper.createRowDataPlanningTipoElemento(null);
    }

    @Test
    public void createRowDataPlanningTipoElemento_givenValidEntity_shuldReturnJsonRowData() {
        final String rowData = helper.createRowDataPlanningTipoElemento(cfgHeader1);
        assertNotNull(rowData);
        assertFalse(rowData.isEmpty());
        final DocumentContext cxt = JsonPath.parse(rowData);
        assertEquals(2, (int) cxt.read("$.rowData.length()"));
        // Assertion on first node (tipoElemento1)
        assertEquals("true", cxt.read("$.rowData[0].deleteEnabled.value"));
        assertEquals("1", cxt.read("$.rowData[0].id.value"));
        assertFalse(cxt.read("$.rowData[0].id.editable", Boolean.class));
        assertEquals("11", cxt.read("$.rowData[0].idCfgConfHeader.value"));
        assertFalse(cxt.read("$.rowData[0].idCfgConfHeader.editable", Boolean.class));
        assertEquals("1", cxt.read("$.rowData[0].raggruppamento.value"));
        assertTrue(cxt.read("$.rowData[0].raggruppamento.editable", Boolean.class));
        assertFalse(cxt.read("$.rowData[0].raggruppamento.nullable", Boolean.class));
        assertEquals("false", cxt.read("$.rowData[0].totale.value"));
        assertEquals("checkbox", cxt.read("$.rowData[0].totale.type"));
        assertEquals("true", cxt.read("$.rowData[0].reparto.value"));
        assertEquals("checkbox", cxt.read("$.rowData[0].reparto.type"));
        assertEquals("true", cxt.read("$.rowData[0].grm.value"));
        assertEquals("checkbox", cxt.read("$.rowData[0].grm.type"));
        assertEquals("false", cxt.read("$.rowData[0].articolo.value"));
        assertEquals("checkbox", cxt.read("$.rowData[0].articolo.type"));
        assertEquals("true", cxt.read("$.rowData[0].omogeneo.value"));
        assertEquals("checkbox", cxt.read("$.rowData[0].omogeneo.type"));
        // Assertion on second node (tipoElemento1)
        assertEquals("true", cxt.read("$.rowData[1].deleteEnabled.value"));
        assertEquals("2", cxt.read("$.rowData[1].id.value"));
        assertFalse(cxt.read("$.rowData[1].id.editable", Boolean.class));
        assertEquals("11", cxt.read("$.rowData[1].idCfgConfHeader.value"));
        assertFalse(cxt.read("$.rowData[1].idCfgConfHeader.editable", Boolean.class));
        assertEquals("2", cxt.read("$.rowData[1].raggruppamento.value"));
        assertTrue(cxt.read("$.rowData[1].raggruppamento.editable", Boolean.class));
        assertFalse(cxt.read("$.rowData[1].raggruppamento.nullable", Boolean.class));
        assertEquals("false", cxt.read("$.rowData[1].totale.value"));
        assertEquals("checkbox", cxt.read("$.rowData[1].totale.type"));
        assertEquals("false", cxt.read("$.rowData[1].reparto.value"));
        assertEquals("checkbox", cxt.read("$.rowData[1].reparto.type"));
        assertEquals("false", cxt.read("$.rowData[1].grm.value"));
        assertEquals("checkbox", cxt.read("$.rowData[1].grm.type"));
        assertEquals("true", cxt.read("$.rowData[1].articolo.value"));
        assertEquals("checkbox", cxt.read("$.rowData[1].articolo.type"));
        assertEquals("true", cxt.read("$.rowData[1].omogeneo.value"));
        assertEquals("checkbox", cxt.read("$.rowData[1].omogeneo.type"));
    }

    @Test(expected = NullPointerException.class)
    public void createRowDataPlanningPianificazione_givenNullEntity_shuldThrowException() {
        helper.createRowDataPlanningPianificazione(null);
    }

    @Test
    public void createRowDataPlanningPianificazione_givenValidEntity_shuldReturnJsonRowData() {
        final String rowData = helper.createRowDataPlanningPianificazione(cfgHeader1);
        assertNotNull(rowData);
        assertFalse(rowData.isEmpty());
        JsonPath.parse(rowData);
    }

    @Test
    public void createRowDataPlanningCampi_shouldReturnJsonRowData() {
        final CfgPianificazioneCampiEntity cfgCampo1 = mockCfgPianificazioneCampo(1L, "CodiceElemento",
                "CODICE_ELEMENTO", "String");
        final CfgPianificazioneCampiEntity cfgCampo2 = mockCfgPianificazioneCampo(2L, "PrezzoArticolo",
                "PREZZO_ARTICOLO", "Number");
        when(cfgCampo2.getEntityLookup()).thenReturn("ItemEntity");
        when(cfgCampo2.getEntityAttribute()).thenReturn("prezzoAttuale");
        final CfgPianificazioneCampiEntity cfgCampo3 = mockCfgPianificazioneCampo(3L, "TipoElemento",
                "TIPO_ELEMENTO", "String");
        when(cfgCampo3.getColumnWidth()).thenReturn(400);
        when(campiService.findAll()).thenReturn(Arrays.asList(cfgCampo1, cfgCampo2, cfgCampo3));
        final String rowData = helper.createRowDataPlanningCampi();
        assertNotNull(rowData);
        assertFalse(rowData.isEmpty());
        final DocumentContext cxt = JsonPath.parse(rowData);
        // Asserts on item-0
        assertTrue(cxt.read("$.rowData[0].descrizione.editable"));
        assertTrue(cxt.read("$.rowData[0].descrizione.nullable"));
        assertEquals("CODICE ELEMENTO", cxt.read("$.rowData[0].descrizione.value"));
        assertTrue(cxt.read("$.rowData[0].descrizioneEstesa.editable"));
        assertTrue(cxt.read("$.rowData[0].descrizioneEstesa.nullable"));
        assertEquals("CODICE ELEMENTO", cxt.read("$.rowData[0].descrizioneEstesa.value"));
        assertTrue(cxt.read("$.rowData[0].columnWidth.editable"));
        assertTrue(cxt.read("$.rowData[0].columnWidth.nullable"));
        assertEquals(0, (int) cxt.read("$.rowData[0].columnWidth.dataTypeParams.precision"));
        assertFalse(cxt.read("$.rowData[0].codiceCampo.editable"));
        assertEquals("CodiceElemento", cxt.read("$.rowData[0].codiceCampo.value"));
        assertFalse(cxt.read("$.rowData[0].raggruppamento.editable"));
        assertFalse(cxt.read("$.rowData[0].tipo.editable"));
        assertEquals("String", cxt.read("$.rowData[0].tipo.value"));
        assertFalse(cxt.read("$.rowData[0].campo.editable"));
        assertEquals("CODICE_ELEMENTO", cxt.read("$.rowData[0].campo.value"));
        assertFalse(cxt.read("$.rowData[0].entityLookup.editable"));
        assertFalse(cxt.read("$.rowData[0].entityAttribute.editable"));
        // Asserts on item-1
        assertTrue(cxt.read("$.rowData[1].descrizione.editable"));
        assertTrue(cxt.read("$.rowData[1].descrizione.nullable"));
        assertEquals("PREZZO ARTICOLO", cxt.read("$.rowData[1].descrizione.value"));
        assertTrue(cxt.read("$.rowData[1].descrizioneEstesa.editable"));
        assertTrue(cxt.read("$.rowData[1].descrizioneEstesa.nullable"));
        assertEquals("PREZZO ARTICOLO", cxt.read("$.rowData[1].descrizioneEstesa.value"));
        assertTrue(cxt.read("$.rowData[1].columnWidth.editable"));
        assertTrue(cxt.read("$.rowData[1].columnWidth.nullable"));
        assertEquals(0, (int) cxt.read("$.rowData[1].columnWidth.dataTypeParams.precision"));
        assertFalse(cxt.read("$.rowData[1].codiceCampo.editable"));
        assertEquals("PrezzoArticolo", cxt.read("$.rowData[1].codiceCampo.value"));
        assertFalse(cxt.read("$.rowData[1].raggruppamento.editable"));
        assertFalse(cxt.read("$.rowData[1].tipo.editable"));
        assertEquals("Number", cxt.read("$.rowData[1].tipo.value"));
        assertFalse(cxt.read("$.rowData[1].campo.editable"));
        assertEquals("PREZZO_ARTICOLO", cxt.read("$.rowData[1].campo.value"));
        assertFalse(cxt.read("$.rowData[1].entityLookup.editable"));
        assertEquals("ItemEntity", cxt.read("$.rowData[1].entityLookup.value"));
        assertFalse(cxt.read("$.rowData[1].entityAttribute.editable"));
        assertEquals("prezzoAttuale", cxt.read("$.rowData[1].entityAttribute.value"));
        // Asserts on item-2
        assertTrue(cxt.read("$.rowData[2].descrizione.editable"));
        assertTrue(cxt.read("$.rowData[2].descrizione.nullable"));
        assertEquals("TIPO ELEMENTO", cxt.read("$.rowData[2].descrizione.value"));
        assertTrue(cxt.read("$.rowData[2].descrizioneEstesa.editable"));
        assertTrue(cxt.read("$.rowData[2].descrizioneEstesa.nullable"));
        assertEquals("TIPO ELEMENTO", cxt.read("$.rowData[2].descrizioneEstesa.value"));
        assertTrue(cxt.read("$.rowData[2].columnWidth.editable"));
        assertTrue(cxt.read("$.rowData[2].columnWidth.nullable"));
        assertEquals("400", cxt.read("$.rowData[2].columnWidth.value"));
        assertEquals(0, (int) cxt.read("$.rowData[2].columnWidth.dataTypeParams.precision"));
        assertFalse(cxt.read("$.rowData[2].codiceCampo.editable"));
        assertEquals("TipoElemento", cxt.read("$.rowData[2].codiceCampo.value"));
        assertFalse(cxt.read("$.rowData[2].raggruppamento.editable"));
        assertFalse(cxt.read("$.rowData[2].tipo.editable"));
        assertEquals("String", cxt.read("$.rowData[2].tipo.value"));
        assertFalse(cxt.read("$.rowData[2].campo.editable"));
        assertEquals("TIPO_ELEMENTO", cxt.read("$.rowData[2].campo.value"));
        assertFalse(cxt.read("$.rowData[2].entityLookup.editable"));
        assertFalse(cxt.read("$.rowData[2].entityAttribute.editable"));
    }

    private CfgConfHeaderEntity mockCfgHeader(Long id, MeccanicheEntity meccanica, CfgLivelloPianificazioneEntity lvl) {
        final CfgConfHeaderEntity mock = mock(CfgConfHeaderEntity.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getMeccanicaEntity()).thenReturn(meccanica);
        when(mock.getLivelloPianificazione()).thenReturn(lvl);
        return mock;
    }

    private CfgLivelloPianificazioneEntity mockCfgLivello(Long id, String code, String key, String label) {
        final CfgLivelloPianificazioneEntity mock = mock(CfgLivelloPianificazioneEntity.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getCodice()).thenReturn(code);
        when(mock.getKey()).thenReturn(key);
        when(mock.getLabel()).thenReturn(label);
        return mock;
    }

    private MeccanicheEntity mockMeccanica(String code, String desc) {
        final MeccanicheEntity mock = mock(MeccanicheEntity.class);
        when(mock.getCodiceMeccanica()).thenReturn(code);
        when(mock.getDescrizione()).thenReturn(desc);
        return mock;
    }

    private CfgTipoElementoEntity mockCfgTipoElemento(Long id, Integer raggr, Integer reparto, Integer grm, Integer articolo) {
        final CfgTipoElementoEntity mock = mock(CfgTipoElementoEntity.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getRaggruppamento()).thenReturn(raggr);
        when(mock.getTotale()).thenReturn(0);
        when(mock.getReparto()).thenReturn(reparto);
        when(mock.getGrm()).thenReturn(grm);
        when(mock.getArticolo()).thenReturn(articolo);
        when(mock.getOmogeneo()).thenReturn(1);
        return mock;
    }

    private CfgPianificazioneEntity mockCfgPianificazione(Long id) {
        final CfgPianificazioneEntity mock = mock(CfgPianificazioneEntity.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getMuiCfgConfHeader()).thenReturn(cfgHeader2);
        return mock;
    }

    private CfgPianificazioneCampiEntity mockCfgPianificazioneCampo(Long id, String code, String field, String type) {
        final CfgPianificazioneCampiEntity mock = mock(CfgPianificazioneCampiEntity.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getCodiceCampo()).thenReturn(code);
        when(mock.getCampo()).thenReturn(field);
        when(mock.getDescrizione()).thenReturn(field.replace("_", " "));
        when(mock.getDescrizioneEstesa()).thenReturn(field.replace("_", " "));
        when(mock.getTipo()).thenReturn(type);
        return mock;
    }
}
