package com.axiante.mui.webapp.business.supportimedia;

import java.util.Arrays;
import lombok.Getter;

public enum SupportoMediaCheckEnum {

    DATA_INIZIO_COINCIDE_DATA_INIZIO_CAMPAGNA("CHK1", "Data inizio coincidente con data inizio campagna"),
    DURATA_MINORE_3GG("CHK2", "Durata minore di 3 giorni"),
    DURATA_MAGGIORE_5GG("CHK3", "Durata maggiore di 5 giorni"),
    CHIUSURA_1GG_PRIMA_DATA_FINE("CHK4", "Chiusura uguale a 1 giorno prima della data fine "),
    DURATA_1GG("CHK5", "Durata 1 giorno"),
    DURATA_4GG("CHK6", "Durata 4 giorni"),
    DURATA_5GG("CHK7", "Durata 5 giorni"),
    DATA_INIZIO_3_GIORNI_PRIMA_DATA_INIZIO_CAMPAGNA("CHK8", "Data inizio 3 giorni prima di data inizio campagna"),
    DATA_FINE_COINCIDE_CON_DATA_FINE_CAMPAGNA("CHK9", "Data fine coincidente con data fine campagna"),
    ;

    @Getter
    private String codice;

    @Getter
    private String descrizione;

    SupportoMediaCheckEnum(String codice, String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public static SupportoMediaCheckEnum fromCodice(String codice) {
        return Arrays.stream(SupportoMediaCheckEnum.values()).filter(supportoMediaCheckEnum -> supportoMediaCheckEnum.getCodice().equals(codice)).findFirst().orElse(null);
    }

}
