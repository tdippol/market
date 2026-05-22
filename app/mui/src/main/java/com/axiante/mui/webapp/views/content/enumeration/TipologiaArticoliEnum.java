package com.axiante.mui.webapp.views.content.enumeration;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum TipologiaArticoliEnum {
    OWN("Articoli esclusivamente di propria competenza", "OWN"),
    ALL("Tutti gli articoli dell'attività in oggetto", "ALL");

    private final String label;
    private final String value;

    TipologiaArticoliEnum(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public static TipologiaArticoliEnum fromValue(@NonNull String value) {
        switch (value.trim().toUpperCase()) {
            case "OWN":
                return OWN;
            case "ALL":
                return ALL;
            default:
                log.error(String.format("TipologiaArticoli '%s' is not managed", value));
                return null;
        }
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
