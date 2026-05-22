package com.axiante.mui.dbpromo.business.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PromoShopsFlagEnum {

    OBBLIGATORIO("1", "B", "SI"), FACOLTATIVO("0", "F", "NO");

    @Getter
    private String key;
    @Getter
    private String description;
    @Getter
    private String label;

}
