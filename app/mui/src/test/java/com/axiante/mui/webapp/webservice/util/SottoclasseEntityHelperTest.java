package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.SottoclasseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class SottoclasseEntityHelperTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private SottoclasseEntity entity;

    @InjectMocks
    private SottoclasseEntityHelper helper;

    @Test
    public void invokeSetterEntity_givenNullEntity_shouldThrowException() {
        ex.expect(NullPointerException.class);
        helper.invokeSetterEntity(null, mock(JsonNode.class));
    }

    @Test
    public void invokeSetterEntity_givenNullField_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        JsonNode node = prepare(null, "value");
        helper.invokeSetterEntity(entity, node);
        verifyZeroInteractions(entity);
    }

    @Test
    public void invokeSetterEntity_givenInvalidField_shouldNotInteractWithEntity() {
        JsonNode node = prepare("invalidField", "value");
        helper.invokeSetterEntity(entity, node);
        verifyZeroInteractions(entity);
    }

    @Test
    public void invokeSetterEntity_givenNullValueDelay_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        JsonNode node = prepare("delay", null);
        helper.invokeSetterEntity(entity, node);
        verifyZeroInteractions(entity);
    }

    @Test
    public void invokeSetterEntity_givenNullValuePriorita_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        JsonNode node = prepare("priorita", null);
        helper.invokeSetterEntity(entity, node);
        verifyZeroInteractions(entity);
    }

    @Test
    public void invokeSetterEntity_givenNullValueAttiva() {
        JsonNode node = prepare("attiva", null);
        helper.invokeSetterEntity(entity, node);
        verify(entity, times(1)).setAttiva(false);
    }

    @Test
    public void invokeSetterEntity_givenBooleanValue() {
        JsonNode node = prepare("attiva", "true");
        helper.invokeSetterEntity(entity, node);
        verify(entity, times(1)).setAttiva(true);
    }

    @Test
    public void invokeSetterEntity() {
        JsonNode node = prepare("priorita", "42");
        helper.invokeSetterEntity(entity, node);
        verify(entity, times(1)).setPriorita(42);
    }

    private JsonNode prepare(String field, String value) {
        final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
        objectNode.put("field", field);
        objectNode.put("value", value);
        return objectNode;
    }
}