package com.axiante.mui.dbpromo.business.utils.pojo;

public enum PromoShopUpdateLevel {
    INFO("Info"),
    WARNING("Attenzione"),
    ERROR("Errore");

    private String value;

    PromoShopUpdateLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
