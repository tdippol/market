package com.axiante.mui.webapp.webservice.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgSetPianificazioneEntityBuilder;
import com.axiante.mui.webapp.support.builders.CfgConfHeaderEntityBuilder;
import com.axiante.mui.webapp.support.builders.CfgLivelloPianificazioneEntityBuilder;
import com.axiante.mui.webapp.support.builders.CfgPianificazioneEntityBuilder;
import com.axiante.mui.webapp.support.builders.CfgTipoElementoEntityBuilder;
import com.axiante.mui.webapp.support.builders.json.GridCellNode;
import com.axiante.mui.webapp.support.builders.json.JsonBuilder;
import com.axiante.mui.webapp.webservice.util.configuration.CfgConfHeaderEntityHelper;
import com.axiante.mui.webapp.webservice.util.configuration.CfgPianificazioneEntityHelper;
import com.axiante.mui.webapp.webservice.util.configuration.CfgTipoElementoEntityHelper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationHelperTest {

    @Mock
    private CfgConfHeaderEntityHelper headerEntityHelper;

    @Mock
    private CfgTipoElementoEntityHelper tipoElementoEntityHelper;

    @Mock
    private CfgPianificazioneEntityHelper pianificazioneEntityHelper;

    @Spy
    @InjectMocks
    private ConfigurationHelper helper;

    // Useful entities
    private CfgConfHeaderEntity headerEntity;
    private ObjectNode headerJson;
    private CfgTipoElementoEntity tipoElementoEntity;
    private ObjectNode tipoElementoJson;

    @Before
    public void setUp() {
        final CfgSetPianificazioneEntity setPianificazioneEntity = new CfgSetPianificazioneEntityBuilder(1L)
                .withDescrizione("JUNIT").build();
        final CfgLivelloPianificazioneEntity livelloEntity = new CfgLivelloPianificazioneEntityBuilder(3L)
                .withCodice("ELEMENTO").withDescrizione("Elemento").build();
        tipoElementoEntity = new CfgTipoElementoEntityBuilder(1L)
                .withRaggruppamento(1).withTotale(0).withReparto(1).withGrm(1).withArticolo(0)
                .withOmogeneo(1).build();
        final CfgTipoElementoEntity tipoElementoEntity2 = new CfgTipoElementoEntityBuilder(2L)
                .withRaggruppamento(2).withTotale(0).withReparto(0).withGrm(0).withArticolo(1)
                .withOmogeneo(1).build();
        CfgPianificazioneEntity pianificazioneEntity = new CfgPianificazioneEntityBuilder(1L).build();
        final CfgPianificazioneEntity pianificazioneEntity2 = new CfgPianificazioneEntityBuilder(2L).build();
        headerEntity = new CfgConfHeaderEntityBuilder(1L).withCfgSetPianificazione(setPianificazioneEntity)
                .withTipoElemento(tipoElementoEntity).withTipoElemento(tipoElementoEntity2)
                .withPianificazione(pianificazioneEntity).withPianificazione(pianificazioneEntity2)
                .withCfgLivelloPianificazione(livelloEntity)
                .build();
        setPianificazioneEntity.setMuiCfgConfHeaders(Collections.singleton(headerEntity));
        headerJson = createHeaderJson();
        tipoElementoJson = createTipoElementoJson();
        createPianificazioneJson();
    }

    @Test(expected = NullPointerException.class)
    public void updateHeader_givenNullHeaderEntity_shouldThrowException() throws Exception {
        helper.updateHeader(null, "foo");
    }

    @Test(expected = NullPointerException.class)
    public void updateHeader_givenNullJsonEntity_shouldThrowException() throws Exception {
        helper.updateHeader(headerEntity, null);
    }

    @Test(expected = Exception.class)
    public void updateHeader_givenInvalidJsonEntity_shouldThrowException() throws Exception {
        helper.updateHeader(headerEntity, "foo");
    }

    @Test
    public void updateHeader_givenValidParams_shouldReturnTrue() throws Exception {
        when(headerEntityHelper.updateEntity(headerEntity, headerJson)).thenReturn(Collections.emptyList());
        assertTrue(helper.updateHeader(headerEntity, headerJson.toString()));
    }

    @Test
    public void updateHeader_givenInvalidParams_shouldReturnFalse() throws Exception {
        final List<String> messages = Collections.singletonList("Error");
        when(headerEntityHelper.updateEntity(headerEntity, headerJson)).thenReturn(messages);
        assertFalse(helper.updateHeader(headerEntity, headerJson.toString()));
    }

    @Test(expected = NullPointerException.class)
    public void updateTipoElemento_givenNullHeaderEntity_shouldThrowException() throws Exception {
        helper.updateTipoElemento(null, "foo");
    }

    @Test(expected = NullPointerException.class)
    public void updateTipoElemento_givenNullJsonEntity_shouldThrowException() throws Exception {
        helper.updateTipoElemento(tipoElementoEntity, null);
    }

    @Test(expected = Exception.class)
    public void updateTipoElemento_givenInvalidJsonEntity_shouldThrowException() throws Exception {
        helper.updateTipoElemento(tipoElementoEntity, "foo");
    }

    @Test
    public void updateTipoElemento_givenValidParams_shouldReturnTrue() throws Exception {
        when(tipoElementoEntityHelper.updateEntity(tipoElementoEntity, tipoElementoJson))
                .thenReturn(Collections.emptyList());
        assertTrue(helper.updateTipoElemento(tipoElementoEntity, tipoElementoJson.toString()));
    }

    @Test
    public void updateTipoElemento_givenInvalidParams_shouldReturnFalse() throws Exception {
        final List<String> messages = Collections.singletonList("Error");
        when(tipoElementoEntityHelper.updateEntity(tipoElementoEntity, tipoElementoJson)).thenReturn(messages);
        assertFalse(helper.updateTipoElemento(tipoElementoEntity, tipoElementoJson.toString()));
    }

    private ObjectNode createHeaderJson() {
        final GridCellNode nodeSetPianificazione = GridCellNode.builder()
                .name("idCfgSetPianificazione").value("1").type("string").build();
        final GridCellNode nodeMinSet = GridCellNode.builder()
                .name("min_set").value("").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeMaxSet = GridCellNode.builder()
                .name("max_set").value("").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeMinRaggr = GridCellNode.builder()
                .name("min_raggr").value("1").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeMaxRaggr = GridCellNode.builder()
                .name("max_raggr").value("").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeMeccanica = GridCellNode.builder()
                .name("meccanica").value("M001 - Foo").type("string").build();
        final GridCellNode nodeLivello = GridCellNode.builder()
                .name("livello_pianificazione").value("3").type("comboBox").editable(true).nullable(true)
                .comboBoxValue("3", "Elemento")
                .comboBoxValue("2", "Raggruppamento")
                .comboBoxValue("1", "Set")
                .build();
        final GridCellNode nodeUnicaInPromo = GridCellNode.builder()
                .name("unica_in_promo").value("false").type("checkbox").nullable(true).editable(true).build();
        return new JsonBuilder(1L).withNode(nodeMinSet).withNode(nodeMaxSet)
                .withNode(nodeMinRaggr).withNode(nodeMaxRaggr).withNode(nodeMeccanica)
                .withNode(nodeLivello).withNode(nodeSetPianificazione).withNode(nodeUnicaInPromo)
                .build();
    }

    private ObjectNode createTipoElementoJson() {
        final GridCellNode nodeRaggr = GridCellNode.builder()
                .name("raggruppamento").value("1").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeTotale = GridCellNode.builder()
                .name("totale").value("false").type("checkbox").editable(true).nullable(true).build();
        final GridCellNode nodeReparto = GridCellNode.builder()
                .name("reparto").value("true").type("checkbox").editable(true).nullable(true).build();
        final GridCellNode nodeGrm = GridCellNode.builder()
                .name("grm").value("true").type("checkbox").editable(true).nullable(true).build();
        final GridCellNode nodeArticolo = GridCellNode.builder()
                .name("articolo").value("false").type("checkbox").editable(true).nullable(true).build();
        final GridCellNode nodeOmogeneo = GridCellNode.builder()
                .name("omogeneo").value("true").type("checkbox").editable(true).nullable(true).build();
        return new JsonBuilder(1L).withNode(nodeRaggr).withNode(nodeTotale).withNode(nodeReparto)
                .withNode(nodeGrm).withNode(nodeArticolo).withNode(nodeOmogeneo).build();
    }

    private ObjectNode createPianificazioneJson() {
        final GridCellNode nodeDesc = GridCellNode.builder()
                .name("descrizione").value("FOO").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeHide = GridCellNode.builder()
                .name("hide").value("false").type("checkbox").editable(true).nullable(true).build();
        final GridCellNode nodeLength = GridCellNode.builder()
                .name("length").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeSicurezza = GridCellNode.builder()
                .name("sicurezza").value("W").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeDefValue = GridCellNode.builder()
                .name("def_value").value("DefValue").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeFlagMultiselect = GridCellNode.builder()
                .name("flag_multiselect").value("false").type("checkbox").editable(true).nullable(true).build();
        final GridCellNode nodeLista = GridCellNode.builder()
                .name("lista").value("[1..10]").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeCampo = GridCellNode.builder()
                .name("campo").value("CAMPO").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeTipoRiga = GridCellNode.builder()
                .name("tipoRiga").value("ELEMENTO").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeTipoLista = GridCellNode.builder()
                .name("tipo_lista").value("TipoLista").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeMandatory = GridCellNode.builder()
                .name("mandatory").value("true").type("checkbox").editable(true).nullable(true).build();
        final GridCellNode nodeWarn = GridCellNode.builder()
                .name("warn").value("false").type("checkbox").editable(true).nullable(true).build();
        final GridCellNode nodeOrdinamento = GridCellNode.builder()
                .name("ordinamento").value("42").type("string").editable(true).nullable(true).build();
        return new JsonBuilder(1L).withNode(nodeDesc).withNode(nodeHide).withNode(nodeLength).withNode(nodeSicurezza)
                .withNode(nodeDefValue).withNode(nodeFlagMultiselect).withNode(nodeLista).withNode(nodeCampo)
                .withNode(nodeTipoRiga).withNode(nodeTipoLista).withNode(nodeMandatory).withNode(nodeWarn)
                .withNode(nodeOrdinamento).build();
    }
}
