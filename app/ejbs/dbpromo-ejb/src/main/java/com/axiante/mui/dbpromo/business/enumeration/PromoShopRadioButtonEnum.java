package com.axiante.mui.dbpromo.business.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PromoShopRadioButtonEnum {

    TUTTO("ALL"),
    VARIAZIONE_SU_DEFAULT("CHANGED"),
    VISUALIZZA_SI("SI"),
    VISUALIZZA_NO("NO");

    @Getter
    private String value;
}
