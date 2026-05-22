package com.axiante.mui.dbpromo.business.enumeration;

import lombok.Getter;

public enum ModalitaMarchioPrivato {
    NO(-1),
    SI(0),
    AUTOMATICO(1),
    ESTESO(2);

    @Getter
    private final int value;


    ModalitaMarchioPrivato(int value) {
        this.value = value;
    }

    public static ModalitaMarchioPrivato fromValue(int value) {
        for (ModalitaMarchioPrivato modalitaMarchioPrivato : ModalitaMarchioPrivato.values()) {
            if (modalitaMarchioPrivato.getValue() == value) {
                return modalitaMarchioPrivato;
            }
        }
        return NO;
    }
}
