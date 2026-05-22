package com.axiante.mui.webapp.webservice.util.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgLivelloPianificazioneService;
import com.axiante.mui.webapp.support.builders.CfgConfHeaderEntityBuilder;
import com.axiante.mui.webapp.support.builders.CfgLivelloPianificazioneEntityBuilder;
import com.axiante.mui.webapp.support.builders.json.GridCellNode;
import com.axiante.mui.webapp.support.builders.json.JsonBuilder;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgConfHeaderEntityHelperTest {

    @Mock
    private CfgLivelloPianificazioneService livelloPianificazioneService;

    @Spy
    @InjectMocks
    private CfgConfHeaderEntityHelper helper;

    // Useful entities
    private CfgConfHeaderEntity headerEntity;
    private ObjectNode headerJson;

    @Before
    public void setUp() {
        final CfgLivelloPianificazioneEntity livelloEntity = new CfgLivelloPianificazioneEntityBuilder(3L)
                .withCodice("ELEMENTO").withDescrizione("Elemento").build();
        headerJson = createHeaderJson();
        headerEntity = new CfgConfHeaderEntityBuilder(1L).withMinSet(2).withMaxSet(2)
                .withMinRaggruppamento(2).withMaxRaggruppamento(2).withCfgLivelloPianificazione(livelloEntity).build();
        when(livelloPianificazioneService.findById(3L)).thenReturn(livelloEntity);
        when(livelloPianificazioneService.findById(4L)).thenReturn(null);
    }

    @Test(expected = NullPointerException.class)
    public void updateEntity_givenNullHeaderEntity_shouldThrowException() {
        helper.updateEntity(null, headerJson);
    }

    @Test(expected = NullPointerException.class)
    public void updateEntity_givenNullHeaderJson_shouldThrowException() {
        helper.updateEntity(headerEntity, null);
    }

    @Test
    public void updateEntity_givenValidParams_shouldReturnEmptyList_andUpdateEntity() {
        GridCellNode node = GridCellNode.builder()
                .name("min_raggr").value("1").type("string").editable(true).nullable(true).build();
        headerJson.set("min_raggr", node.asJsonNode());
        node = GridCellNode.builder().name("logo_messaggi").value("true").type("boolean").editable(true).nullable(true).build();
        headerJson.set("logo_messaggi", node.asJsonNode());
        node = GridCellNode.builder().name("castelletti").value("true").type("boolean").editable(true).nullable(true).build();
        headerJson.set("castelletti", node.asJsonNode());

        final List<String> messages = helper.updateEntity(headerEntity, headerJson);
        assertTrue(messages.isEmpty());
        assertEquals(2, (int) headerEntity.getMinSet());
        assertEquals(2, (int) headerEntity.getMaxSet());
        assertEquals(1, (int) headerEntity.getMinRaggruppamento());
        assertEquals(2, (int) headerEntity.getMaxRaggruppamento());
        assertEquals(0, (int) headerEntity.getUnicaInPromo());
    }

    @Test
    public void updateEntity_givenMinSetAsString_shouldReturnListOfMessages() {
        final GridCellNode node = GridCellNode.builder()
                .name("min_set").value("FOO").type("string").editable(true).nullable(true).build();
        headerJson.set("min_set", node.asJsonNode());
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void validate_givenNullMinSetNode_shouldReturnListOfMessages() {
        headerJson.remove("min_set");
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenMaxSetAsString_shouldReturnListOfMessages() {
        final GridCellNode node = GridCellNode.builder()
                .name("max_set").value("FOO").type("string").editable(true).nullable(true).build();
        headerJson.set("max_set", node.asJsonNode());
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void validate_givenNullMaxSetNode_shouldReturnListOfMessages() {
        headerJson.remove("max_set");
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenMinRaggrAsString_shouldReturnListOfMessages() {
        final GridCellNode node = GridCellNode.builder()
                .name("min_raggr").value("FOO").type("string").editable(true).nullable(true).build();
        headerJson.set("min_raggr", node.asJsonNode());
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenNullMinRaggrNode_shouldReturnListOfMessages() {
        headerJson.remove("min_raggr");
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenMaxRaggrAsString_shouldReturnListOfMessages() {
        final GridCellNode node = GridCellNode.builder()
                .name("max_raggr").value("FOO").type("string").editable(true).nullable(true).build();
        headerJson.set("max_raggr", node.asJsonNode());
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenNullMaxRaggrNode_shouldReturnListOfMessages() {
        headerJson.remove("max_raggr");
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenUnicaInPromoAsOtherThanBoolean_shouldReturnListOfMessages() {
        final GridCellNode node1 = GridCellNode.builder()
                .name("unica_in_promo").value("FOO").type("string").editable(true).nullable(true).build();
        headerJson.set("unica_in_promo", node1.asJsonNode());
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
        final GridCellNode node2 = GridCellNode.builder()
                .name("unica_in_promo").value("42").type("string").editable(true).nullable(true).build();
        headerJson.set("unica_in_promo", node2.asJsonNode());
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenNullUnicaInPromo_shouldReturnListOfMessages() {
        headerJson.remove("unica_in_promo");
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenMaxSetLesserThanMin_shouldReturnListOfMessages() {
        final GridCellNode node = GridCellNode.builder()
                .name("max_set").value("1").type("string").editable(true).nullable(true).build();
        headerJson.set("max_set", node.asJsonNode());
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenMinSetGreaterThanMin_shouldReturnListOfMessages() {
        final GridCellNode node = GridCellNode.builder()
                .name("min_set").value("3").type("string").editable(true).nullable(true).build();
        headerJson.set("min_set", node.asJsonNode());
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenMaxRaggrLesserThanMin_shouldReturnListOfMessages() {
        final GridCellNode node = GridCellNode.builder()
                .name("max_raggr").value("1").type("string").editable(true).nullable(true).build();
        headerJson.set("max_raggr", node.asJsonNode());
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenMinRaggrGreaterThanMin_shouldReturnListOfMessages() {
        final GridCellNode node = GridCellNode.builder()
                .name("min_raggr").value("3").type("string").editable(true).nullable(true).build();
        headerJson.set("min_raggr", node.asJsonNode());
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenLivelloPianificazioneOutsideValidValues_shouldReturnListOfMessages() {
        final GridCellNode node = GridCellNode.builder()
                .name("livello_pianificazione").value("4").type("comboBox").editable(true).nullable(true)
                .comboBoxValue("3", "Elemento")
                .comboBoxValue("2", "Raggruppamento")
                .comboBoxValue("1", "Set")
                .build();
        headerJson.set("livello_pianificazione", node.asJsonNode());
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    @Test
    public void updateEntity_givenNullLivelloPianificazioneOutsideValidValues_shouldReturnListOfMessages() {
        headerJson.remove("livello_pianificazione");
        assertFalse(helper.updateEntity(headerEntity, headerJson).isEmpty());
    }

    private ObjectNode createHeaderJson() {
        final GridCellNode nodeSetPianificazione = GridCellNode.builder()
                .name("idCfgSetPianificazione").value("1").type("string").build();
        final GridCellNode nodeMinSet = GridCellNode.builder()
                .name("min_set").value("2").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeMaxSet = GridCellNode.builder()
                .name("max_set").value("2").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeMinRaggr = GridCellNode.builder()
                .name("min_raggr").value("2").type("string").editable(true).nullable(true).build();
        final GridCellNode nodeMaxRaggr = GridCellNode.builder()
                .name("max_raggr").value("2").type("string").editable(true).nullable(true).build();
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
        final GridCellNode nodeDuplicaArticolo = GridCellNode.builder()
                .name("duplica_articolo").value("false").type("checkbox").nullable(true).editable(true).build();
        final GridCellNode nodeDuplicaReparto = GridCellNode.builder()
                .name("duplica_reparto").value("false").type("checkbox").nullable(true).editable(true).build();
        final GridCellNode nodeDuplicaGrm = GridCellNode.builder()
                .name("duplica_grm").value("false").type("checkbox").nullable(true).editable(true).build();
        final GridCellNode nodeDuplicaTotale = GridCellNode.builder()
                .name("duplica_totale").value("false").type("checkbox").nullable(true).editable(true).build();
        return new JsonBuilder(1L).withNode(nodeMinSet).withNode(nodeMaxSet)
                .withNode(nodeMinRaggr).withNode(nodeMaxRaggr).withNode(nodeMeccanica)
                .withNode(nodeLivello).withNode(nodeSetPianificazione).withNode(nodeUnicaInPromo)
                .withNode(nodeDuplicaArticolo).withNode(nodeDuplicaReparto).withNode(nodeDuplicaGrm).withNode(nodeDuplicaTotale)
                .build();
    }
}