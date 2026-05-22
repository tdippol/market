package com.axiante.mui.dbpromo.business.enumeration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum MessaggiFontEnum {
    PICCOLO("Piccolo"),
    NORMALE("Normale"),
    DOPPIO("Doppio"),
    TRIPLO("Triplo");

    @Getter
    private final String value;

    MessaggiFontEnum(String value) {
        this.value = value;
    }

    public static MessaggiFontEnum fromValue(String value){
        for (MessaggiFontEnum messaggiFontEnum : values()) {
            if (value.equals(messaggiFontEnum.value)) return messaggiFontEnum;
        }
        log.error("Attempt to convert {} as MessaggiFontEnun", value);
        throw new IllegalArgumentException(String.format("Attempt to convert %s into MessagiFontEnum", value));
    }
}
