package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.webapp.webservice.dto.RAFResponse;
import lombok.Getter;

public enum IniziativeMessageEnum {
    GEN_ERRROR(-1, "Errore imprevisto durante il salvataggio"),
    SAVE_INIZIATIVA_ERRROR(-100, "Errore in fase di creazione"),
    WS_TOTALIZZATORE_UNAVAILABLE(1, "Impossibile contattare il ws totalizzatori"),
    WS_TOTALIZZATORE_INCORRECT(2, "Risposta dal ws non valida"),
    SAVE_TOTALIZZATORE_ERROR(3,"Errore durante il salvataggio dei totalizzatori"),
    ;
    @Getter
    private int codice;
    @Getter
    private String descrizione;


    IniziativeMessageEnum(int codice, String descrizione){
        this.codice = codice;
        this.descrizione=descrizione;
    }

    public static IniziativeMessageEnum asMessageEnum(RAFResponse response){
        if ( response.getStato() == -100 ) return WS_TOTALIZZATORE_UNAVAILABLE;
        else if ( response.getStato() > 200 ) return WS_TOTALIZZATORE_INCORRECT;
        return null;
    }
}
