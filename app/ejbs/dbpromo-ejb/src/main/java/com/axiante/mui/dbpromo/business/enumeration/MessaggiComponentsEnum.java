package com.axiante.mui.dbpromo.business.enumeration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum MessaggiComponentsEnum {
            ID_MESSAGGIO("Id Messaggio"),
            TAGLIO_CARTA("Taglio carta"),
            BARCODE("Barcode"),
            FONT("Font"),
            ALLINEAMENTO("Allineamento"),
            BOLD("Bold"),
            TESTO("Testo"),
            LOGO("Logo"),
            BOTTONE("Bottone"),
            CODICE("Codice"),
            REGOLAMENTO("Regolamento"),
            SEQ_STAMPA("Seq Stampa"),
            BARRA("Barra"),
            FONT_STILE("Font Stile"),
            VARIABILE("Variabile"),
            FONT_ENTITY("Font Entity")
    ;
    @Getter
    private String value;
    private MessaggiComponentsEnum(String value){
        this.value = value;
    }

    public static MessaggiComponentsEnum fromValue(String value){
        for(MessaggiComponentsEnum m : values()){
            if ( m.getValue().equals(value)) return m;
        }
        log.error("Attempt to convert {} into MessaggiComponent", value);
        throw new IllegalArgumentException(String.format("Attempt to convert %s into MessaggiComponent", value));
    }
}
