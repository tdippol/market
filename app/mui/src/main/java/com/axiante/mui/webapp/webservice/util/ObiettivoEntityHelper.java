package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.dbpromo.persistence.entities.ObiettivoEntity;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import java.math.BigDecimal;

@Dependent
@Slf4j
public class ObiettivoEntityHelper {
    public static final String TARGET = "TARGET";
    public static final String BUONO_PREMIO = "BUONOPREMIO";
    public static final String ATTIVO = "ATTIVO";

    public void invokeSetterEntity(@NonNull ObiettivoEntity entity, JsonNode jsonNode) {
        final String field = jsonNode.get("field").asText();
        if (field == null || field.trim().isEmpty() || field.trim().equalsIgnoreCase("null")) {
            throw new IllegalArgumentException("Field is null");
        }
        String value = jsonNode.get("value").asText();
        if (value != null && (value.trim().isEmpty() || value.trim().equalsIgnoreCase("null"))) {
            if (field.equalsIgnoreCase(TARGET) || field.equalsIgnoreCase(BUONO_PREMIO)) {
                throw new IllegalArgumentException(String.format("Field '%s' cannot be null", field));
            }
            value = null;
        }
        try {
            switch (field.toUpperCase()) {
                case TARGET:
                    entity.setTarget(nullableBigDecimal(value));
                    break;
                case BUONO_PREMIO:
                    entity.setBuonoPremio(nullableBigDecimal(value));
                    break;
                case ATTIVO:
                    entity.setFlagAttivo(nullableBoolean(value));
                    break;
                default:
                    log.error(String.format("Field '%s' not updatable in ObiettivoEntity", field));
                    break;
            }
        } catch (Exception ex) {
            log.error(String.format("Error updating field '%s' to '%s' in ObiettivoEntity", field, value), ex);
        }
    }

    private BigDecimal nullableBigDecimal(String value) {
        return value == null ? null : new BigDecimal(value);
    }

    private Boolean nullableBoolean(String value) {
        return value == null ? Boolean.FALSE : Boolean.valueOf(value);
    }
}
