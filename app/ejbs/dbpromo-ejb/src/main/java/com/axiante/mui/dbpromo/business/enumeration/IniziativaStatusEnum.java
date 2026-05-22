package com.axiante.mui.dbpromo.business.enumeration;

import lombok.Getter;

/**
 * Rappresenta gli stati possibili di una iniziativa/campagna.
 * <p>
 * Ogni stato è identificato da:
 * - codice: valore tecnico utilizzato per la persistenza e i filtri applicativi
 * - descrizione: testo descrittivo “funzionale”
 * - label: etichetta corta destinata alla UI
 * <p>
 * Nota: gli stati con codice 400 (IN_ESECUZIONE) e 500 (CONCLUSA) sono gestiti da procedure schedulate a livello DB
 * e non vengono transizionati direttamente dall'applicazione.
 */
public enum IniziativaStatusEnum {
    CANCELLATA_00("00", "Cancellata", "Cancellata"),
    NON_PUBBLICATA("10", "Non pubblicata", "Non pubblicata"),
    PUBBLICATA("20", "Pubblicata", "Pubblicata"),
    SBLOCCATA("311", "Sbloccata", "Campagna sbloccata per correzioni"),
    IN_ESECUZIONE("400", "In Esecuzione", "In esecuzione"),
    CONCLUSA("500", "Conclusa", "Conclusa");

    @Getter
    private final String codice;

    @Getter
    private final String descrizione;

    @Getter
    private final String label;

    IniziativaStatusEnum(String codice, String descrizione, String label) {
        this.codice = codice;
        this.descrizione = descrizione;
        this.label = label;
    }
}
