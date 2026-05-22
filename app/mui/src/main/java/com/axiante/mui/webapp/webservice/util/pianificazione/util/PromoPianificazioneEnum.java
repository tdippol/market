package com.axiante.mui.webapp.webservice.util.pianificazione.util;

public enum PromoPianificazioneEnum {

    TOTALE("TOTALE", "TOTALE", "TOTALE"),
    GRM("GRM", "ELENCO", "ELENCO"),
    REPARTO("REPARTO", "ELENCO", "ELENCO"),
    ARTICOLO("ARTICOLO", "ELENCO", "ELENCO"),
    ATTRIBUTO("ATTRIBUTO", "ELENCO", "ELENCO");

    private String tipoElemento;
    private String codiceElementoMaster;
    private String elementoMaster;

    PromoPianificazioneEnum(String tipoElemento, String codiceElementoMaster, String elementoMaster) {
        this.tipoElemento = tipoElemento;
        this.codiceElementoMaster = codiceElementoMaster;
        this.elementoMaster = elementoMaster;
    }

    public String getTipoElemento() {
        return tipoElemento;
    }

    public String getCodiceElementoMaster() {
        return codiceElementoMaster;
    }

    public String getElementoMaster() {
        return elementoMaster;
    }
}
