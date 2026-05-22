package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.dbpromo.persistence.entities.SottoclasseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;

@Dependent
@Slf4j
public class SottoclasseEntityHelper {

    public static final String PRIORITA = "PRIORITA";
    public static final String DELAY = "DELAY";
    public static final String ATTIVA = "ATTIVA";

    public void invokeSetterEntity(@NonNull SottoclasseEntity entity, JsonNode jsonNode) {
        final String field = jsonNode.get("field").asText();
        if (field == null || field.trim().isEmpty() || field.trim().equalsIgnoreCase("null")) {
            throw new IllegalArgumentException("Field is null");
        }
        String value = jsonNode.get("value").asText();
        if (value != null && (value.trim().isEmpty() || value.trim().equalsIgnoreCase("null"))) {
            if (field.equalsIgnoreCase(PRIORITA) || field.equalsIgnoreCase(DELAY)) {
                throw new IllegalArgumentException(String.format("Field '%s' cannot be null", field));
            }
            value = null;
        }
        try {
            switch (field.toUpperCase()) {
                case PRIORITA:
                    entity.setPriorita(nullableInteger(value));
                    break;
                case DELAY:
                    entity.setDelay(nullableInteger(value));
                    break;
                case ATTIVA:
                    entity.setAttiva(value == null ? Boolean.FALSE : Boolean.parseBoolean(value));
                    break;
                default:
                    log.error(String.format("Field '%s' not updatable in SottoclasseEntity", field));
                    break;
            }
        } catch (Exception ex) {
            log.error(String.format("Error updating field '%s' to '%s' in SottoclasseEntity", field, value), ex);
        }
    }

    private Integer nullableInteger(Object value) {
        return value == null ? null : Integer.parseInt(value.toString());
    }
}
