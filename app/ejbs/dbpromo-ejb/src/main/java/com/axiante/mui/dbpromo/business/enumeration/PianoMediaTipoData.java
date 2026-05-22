 package com.axiante.mui.dbpromo.business.enumeration;

import com.axiante.mui.common.promo.grid.ComboBoxValues;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

public enum PianoMediaTipoData {
    CAMPAGNA("Con Promozione Master"),
    GIORNI_PRIMA("Giorni Prima"),
    DURATA("Durata");

    @Getter final String description;
    PianoMediaTipoData(String description) {
        this.description = description;
    }

    @Getter
    private static final List<ComboBoxValues> tipoDataInizioValues = Stream.of(
            ComboBoxValues.builder().key(CAMPAGNA.name()).label(CAMPAGNA.getDescription()).build(),
            ComboBoxValues.builder().key(GIORNI_PRIMA.name()).label(GIORNI_PRIMA.getDescription()).build()
    ).collect(Collectors.toList());

    @Getter
    private static final List<ComboBoxValues> tipoDataFineValues = Stream.of(
            ComboBoxValues.builder().key(CAMPAGNA.name()).label(CAMPAGNA.getDescription()).build(),
            ComboBoxValues.builder().key(GIORNI_PRIMA.name()).label(GIORNI_PRIMA.getDescription()).build(),
            ComboBoxValues.builder().key(DURATA.name()).label(DURATA.getDescription()).build()
    ).collect(Collectors.toList());

//    public static PianoMediaTipoData fromDescription(String description) {
//        for (PianoMediaTipoData value : PianoMediaTipoData.values()) {
//            if (value.getDescription().equals(description)) {
//                return value;
//            }
//        }
//        return null;
//    }
}
