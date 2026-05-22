package com.axiante.mui.dbpromo.business.enumeration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum MessaggiAllineamentoEnum {
    SINISTRA("Sinistra"),
    CENTRO("Centro"),
    DESTRA("Destra");

    @Getter
    private final String value;

    MessaggiAllineamentoEnum(String value) {
        this.value = value;
    }

    public static MessaggiAllineamentoEnum fromValue(String value) {
        for (MessaggiAllineamentoEnum messagiFontEnum : values()) {
            if (value.equals(messagiFontEnum.value)) return messagiFontEnum;
        }
        log.error("Attempt to convert {} as MessaggiAllineamentoEnum", value);
        throw new IllegalArgumentException(String.format("Attempt to convert %s into MessaggiAllineamentoEnum", value));
    }
}
