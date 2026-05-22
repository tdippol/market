package com.axiante.mui.dbpromo.business.enumeration;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public enum PromoStatusEnum {
    CANCELLATA_00("00", "Cancellata",
            new PromoStatusFields(false, false, false, false, false)),
    TESTATA_PROMOZIONE_CREATA("10", "Testata Promozione Creata",
            new PromoStatusFields(true, true, true, true, true)),
    PIANIFICAZIONE_DISPONIBILE("30", "Pianificazione Disponibile",
            new PromoStatusFields(true, true, true, true, true)),
    PIANIFICAZIONE_FINALIZZATA("31", "Pianificazione Finalizzata",
            new PromoStatusFields(true, true, true, true, true)),
    IN_PIANIFICAZIONE_CON_ERRORI("35", "In Pianificazione Con Errori",
            new PromoStatusFields(false, false, false, false, false)),
    SBLOCCO_PIANIFICAZIONE_PER_CORREZIONI("311", "Sblocco Pianificazione per Correzioni",
            new PromoStatusFields(true, true, true, true, true)),
    PROMOZIONE_IN_ESECUZIONE("400", "Promozione in Esecuzione",
            new PromoStatusFields(false, false, true, true, false)),
    SBLOCCO_PROMO_IN_ESECUZIONE_PER_CORREZIONI("410", "Sblocco Promo in esecuzione per correzioni",
            new PromoStatusFields(false, false, true, true, false)),
    IN_ESECUZIONE_CON_ERRORI("415", "In Esecuzione Con Errori",
            new PromoStatusFields(false, false, false, false, false)),
    PROMOZIONIE_CONCLUSA("500", "Promozione Conclusa",
            new PromoStatusFields(false, false, false, false, false));

    private final String key;
    private final String description;
    private final PromoStatusFields fields;

    PromoStatusEnum(String key, String description, PromoStatusFields fields) {
        this.key = key;
        this.description = description;
        this.fields = fields;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public static PromoStatusEnum fromCode(@NonNull String code) {
        return Arrays.stream(PromoStatusEnum.values()).filter(p -> p.getKey().equals(code)).findFirst().orElse(null);
    }
}
