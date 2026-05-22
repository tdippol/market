package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneEntity;
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
public class PromoFatturazioneEntityHelperTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private PromoFatturazioneEntity entity;

    @InjectMocks
    private PromoFatturazioneEntityHelper helper;

    @Test
    public void invokeSetter_givenNullEntityAndJsonNode_shouldThrowException() {
        ex.expect(NullPointerException.class);
        helper.invokeSetter(null, mock(JsonNode.class));
    }

    @Test
    public void invokeSetter_givenNullJsonNode_shouldThrowException() {
        ex.expect(NullPointerException.class);
        helper.invokeSetter(entity, null);
    }

    @Test
    public void invokeSetter_givenNullFieldInJsonNode_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
        objectNode.put("field", (String) null);
        objectNode.put("value", "foo");
        helper.invokeSetter(entity, objectNode);
    }

    @Test
    public void invokeSetter_givenNullEntity_shouldThrowException() {
        ex.expect(NullPointerException.class);
        helper.invokeSetter(null, "foo", "bar");
        verifyZeroInteractions(entity);
    }

    @Test
    public void invokeSetter_givenNullFieldName_shouldThrowException() {
        ex.expect(NullPointerException.class);
        helper.invokeSetter(entity, null, "bar");
        verifyZeroInteractions(entity);
    }

    @Test
    public void invokeSetter_givenUnmanagedFieldName_shouldNotInvokeSetter() {
        helper.invokeSetter(entity, "foo", "bar");
        verifyZeroInteractions(entity);
    }

    @Test
    public void invokeSetter_givenWrongValueForFieldName_shouldNotInvokeSetter() {
        helper.invokeSetter(entity, "VARIABILEFISSO", "bar");
        verifyZeroInteractions(entity);
    }

    @Test
    public void invokeSetter_givenNullValue_shouldNullifyField() {
        helper.invokeSetter(entity, "VARIABILEFISSO", null);
        verify(entity, times(1)).setVarFiss(null);
    }
}