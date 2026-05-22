package com.axiante.mui.dbpromo.business.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PromoShopsRadioEnum {
    VISUALIZZA_TUTTO("Tutti"), VISUALIZZA_MODIFICHE("Modifiche");

    @Getter
    private final String value;
}
