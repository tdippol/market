package com.axiante.mui.webapp.webservice.util.pianificazione.util;

import lombok.NonNull;

public enum DynamicColumnEnum {

    TOTALE("totale"), ARTICOLO("articolo"), GRM("grm"), REPARTO("reparto"), PIANIFICAZIONE("pianificazione"),
    COPIA_INCOLLA("copiaIncolla"), COMPLEMENTARI("complementari"), ATTRIBUTO("attributo");

    private String field;

    DynamicColumnEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public static DynamicColumnEnum from(@NonNull final String string) {
        synchronized (string) {
            final String test = string.toLowerCase();
            switch (test) {
                case "totale":
                    return TOTALE;
                case "articolo":
                    return ARTICOLO;
                case "grm":
                    return GRM;
                case "reparto":
                    return REPARTO;
                case "pianificazione":
                    return PIANIFICAZIONE;
                case "copiaincolla":
                    return COPIA_INCOLLA;
                case "complementari":
                    return COMPLEMENTARI;
                case "attributo":
                    return ATTRIBUTO;
                default:
                    return null;
            }
        }
    }
}
