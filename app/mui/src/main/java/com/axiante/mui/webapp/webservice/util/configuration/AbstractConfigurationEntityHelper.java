package com.axiante.mui.webapp.webservice.util.configuration;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class AbstractConfigurationEntityHelper {

    protected String getValueAsString(JsonNode node) {
        return node.get("value").asText();
    }

    protected Integer getValueAsBoolean(JsonNode node) {
        String value = getValueAsString(node);
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        value = value.trim().toLowerCase();
        if ("true".equals(value)) {
            return 1;
        }
        if ("false".equals(value)) {
            return 0;
        }
        return -1;
    }

    protected Integer getValueAsInteger(JsonNode node) {
        final String value = getValueAsString(node);
        if (value == null || "null".equals(value.trim()) || value.trim().isEmpty()) {
            return null;
        }
        return Integer.parseInt(value);
    }
}
