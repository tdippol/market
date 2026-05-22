package com.axiante.mui.dbpromo.business.enumeration;

import lombok.Getter;

@Getter
public enum OperazioneCumulabilita {
    INS_TES("INSTES", "Inserimento nuova anagrafica"),
    INS_DET("INSDET", "Inserimento nuovo dettaglio"),
    DEL_TES("DELTES", "Eliminazione anagrafica esistente"),
    DEL_DET("DELDET", "Eliminazione dettaglio esistente");

    private final String code;
    private final String description;

    OperazioneCumulabilita(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("OperazioneCumulabilita{code='%s'}", code);
    }
}
