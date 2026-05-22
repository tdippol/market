package com.axiante.mui.dbpromo.actions;

import lombok.Getter;

public enum FormEnum {

    SCHEDA_PROMO("schedaPromo"),
    MODIFICA_PROMO("modificaPromo"),
    PIANIFICAZIONE("pianificazionePromo");

    @Getter
    private String name;

    FormEnum(String name) {
        this.name = name;
    }
}
