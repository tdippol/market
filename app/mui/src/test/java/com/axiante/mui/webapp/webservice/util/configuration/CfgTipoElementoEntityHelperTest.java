package com.axiante.mui.webapp.webservice.util.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.webapp.support.builders.CfgTipoElementoEntityBuilder;
import com.axiante.mui.webapp.support.builders.json.GridCellNode;
import com.axiante.mui.webapp.support.builders.json.JsonBuilder;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgTipoElementoEntityHelperTest {

    @Spy
    private CfgTipoElementoEntityHelper helper;

    // Useful entities
    private CfgTipoElementoEntity tipoElementoEntity;
    private ObjectNode tipoElementoJson;

    @Before
    public void setUp() {
        tipoElementoJson = createTipoElementoJson();
        tipoElementoEntity = new CfgTipoElementoEntityBuilder(1L)
                .withRaggruppamento(1).withTotale(0).withReparto(1).withGrm(1).withArticolo(0)
                .withOmogeneo(1).build();
    }

    @Test(expected = NullPointerException.class)
    public void updateEntity_givenNullHeaderEntity_shouldThrowException() {
        helper.updateEntity(null, tipoElementoJson);
    }

    @Test(expected = NullPointerException.class)
    public void updateEntity_givenNullHeaderJson_shouldThrowException() {
        helper.updateEntity(tipoElementoEntity, null);
    }

    @Test
    public void updateEntity_givenValidParams_shouldReturnEmptyList_andUpdateEntity() {
        final GridCellNode node = GridCellNode.builder()
                .name("articolo").value("true").type("checkbox").editable(true).nullable(true).build();
        tipoElementoJson.set("articolo", node.asJsonNode());
        final List<String> messages = helper.updateEntity(tipoElementoEntity, tipoElementoJson);
        assertTrue(messages.isEmpty());
        assertEquals(1, (int) tipoElementoEntity.getRaggruppamento());
        assertEquals(0, (int) tipoElementoEntity.getTotale());
        assertEquals(1, (int) tipoElementoEntity.getReparto());
        assertEquals(1, (int) tipoElementoEntity.getGrm());
        assertEquals(1, (int) tipoElementoEntity.getArticolo());
        assertEquals(1, (int) tipoElementoEntity.getOmogeneo());
    }

    @Test
    public void updateEntity_givenRaggruppamentoAsString_shouldReturnListOfMessages() {
        final GridCellNode node = GridCellNode.builder()
                .name("raggruppamento").value("FOO").type("string").editable(true).nullable(true).build();
        tipoElementoJson.set("raggruppamento", node.asJsonNode());
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
    }

    @Test
    public void validate_givenNullRaggruppamentoNode_shouldReturnListOfMessages() {
        tipoElementoJson.remove("raggruppamento");
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
    }

    @Test
    public void updateEntity_givenTotaleAsOtherThanBoolean_shouldReturnListOfMessages() {
        final GridCellNode node1 = GridCellNode.builder()
                .name("totale").value("FOO").type("string").editable(true).nullable(true).build();
        tipoElementoJson.set("totale", node1.asJsonNode());
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
        final GridCellNode node2 = GridCellNode.builder()
                .name("totale").value("42").type("string").editable(true).nullable(true).build();
        tipoElementoJson.set("totale", node2.asJsonNode());
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
    }

    @Test
    public void updateEntity_givenNullTotale_shouldReturnListOfMessages() {
        tipoElementoJson.remove("totale");
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
    }

    @Test
    public void updateEntity_givenRepartoAsOtherThanBoolean_shouldReturnListOfMessages() {
        final GridCellNode node1 = GridCellNode.builder()
                .name("reparto").value("FOO").type("string").editable(true).nullable(true).build();
        tipoElementoJson.set("reparto", node1.asJsonNode());
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
        final GridCellNode node2 = GridCellNode.builder()
                .name("reparto").value("42").type("string").editable(true).nullable(true).build();
        tipoElementoJson.set("reparto", node2.asJsonNode());
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
    }

    @Test
    public void updateEntity_givenNullReparto_shouldReturnListOfMessages() {
        tipoElementoJson.remove("reparto");
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
    }

    @Test
    public void updateEntity_givenGrmAsOtherThanBoolean_shouldReturnListOfMessages() {
        final GridCellNode node1 = GridCellNode.builder()
                .name("grm").value("FOO").type("string").editable(true).nullable(true).build();
        tipoElementoJson.set("grm", node1.asJsonNode());
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
        final GridCellNode node2 = GridCellNode.builder()
                .name("grm").value("42").type("string").editable(true).nullable(true).build();
        tipoElementoJson.set("grm", node2.asJsonNode());
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
    }

    @Test
    public void updateEntity_givenNullGrm_shouldReturnListOfMessages() {
        tipoElementoJson.remove("grm");
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
    }

    @Test
    public void updateEntity_givenArticoloAsOtherThanBoolean_shouldReturnListOfMessages() {
        final GridCellNode node1 = GridCellNode.builder()
                .name("articolo").value("FOO").type("string").editable(true).nullable(true).build();
        tipoElementoJson.set("articolo", node1.asJsonNode());
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
        final GridCellNode node2 = GridCellNode.builder()
                .name("articolo").value("42").type("string").editable(true).nullable(true).build();
        tipoElementoJson.set("articolo", node2.asJsonNode());
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
    }

    @Test
    public void updateEntity_givenNullArticolo_shouldReturnListOfMessages() {
        tipoElementoJson.remove("articolo");
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
    }

    @Test
    public void updateEntity_givenOmogeneoAsOtherThanBoolean_shouldReturnListOfMessages() {
        final GridCellNode node1 = GridCellNode.builder()
                .name("omogeneo").value("FOO").type("string").editable(true).nullable(true).build();
        tipoElementoJson.set("omogeneo", node1.asJsonNode());
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
        final GridCellNode node2 = GridCellNode.builder()
                .name("omogeneo").value("42").type("string").editable(true).nullable(true).build();
        tipoElementoJson.set("omogeneo", node2.asJsonNode());
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
    }

    @Test
    public void updateEntity_givenNullOmogeneo_shouldReturnListOfMessages() {
        tipoElementoJson.remove("omogeneo");
        assertFalse(helper.updateEntity(tipoElementoEntity, tipoElementoJson).isEmpty());
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
        final GridCellNode nodeAttributo = GridCellNode.builder()
                .name("attributo").value("false").type("checkbox").editable(true).nullable(true).build();
        return new JsonBuilder(1L).withNode(nodeRaggr).withNode(nodeTotale).withNode(nodeReparto)
                .withNode(nodeGrm).withNode(nodeArticolo).withNode(nodeOmogeneo)
                .withNode(nodeAttributo).build();
    }
}
