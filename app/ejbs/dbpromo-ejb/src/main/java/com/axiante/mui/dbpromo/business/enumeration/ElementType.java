package com.axiante.mui.dbpromo.business.enumeration;

import lombok.NonNull;

public enum ElementType {
    ARTICOLO("ARTICOLO"),
    GRM("GRM"),
    REPARTO("REPARTO"),
    TOTALE("TOTALE"),
    ATTRIBUTO("ATTRIBUTO");

    private final String description;

    ElementType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ElementType fromDescription(@NonNull final String description) {
        switch (description.toUpperCase()) {
            case "ARTICOLO":
                return ARTICOLO;
            case "GRM":
                return GRM;
            case "REPARTO":
                return REPARTO;
            case "TOTALE":
                return TOTALE;
            case "ATTRIBUTO":
                return ATTRIBUTO;
        }
        return null;
    }
}
