package com.axiante.mui.dbpromo.business.enumeration;

import lombok.Getter;

@Getter
public enum CumulabilitaType {
    ESCUSIVA("E"),
    CUMULABILE("C");

    private final String value;

    CumulabilitaType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("CumulabilitaType{value='%s'}", value);
    }

    public static CumulabilitaType fromValue(String value) {
        for (CumulabilitaType cumulabilitaType : CumulabilitaType.values()) {
            if (cumulabilitaType.value.equals(value)) {
                return cumulabilitaType;
            }
        }
        return null;
    }

    public String getTitle() {
        switch (this){
            case ESCUSIVA:
                return "Esclusività";
            case CUMULABILE:
                return "Cumulabilità";
            default:
                return "";
        }
    }
}
