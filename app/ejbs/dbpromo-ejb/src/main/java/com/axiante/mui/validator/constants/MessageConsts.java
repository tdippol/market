package com.axiante.mui.validator.constants;

public class MessageConsts {
    public static final String END_DATE_BEFORE_START_DATE = "Data Fine deve essere maggiore o uguale a Data Inizio";
    public static final String START_END_DATES_YEAR_NOT_VALID = "Almeno una tra Data Inizio e Data Fine deve corrispondere all’anno selezionato";
    public static final String YEAR_NOT_CURRENT_OR_NEXT = "Anno deve essere quello corrente o il successivo";
    public static final String START_DATE_AFTER_CURRENT_DATE = "Data Inizio deve essere maggiore uguale alla data in cui viene fatto il data entry";
    public static final String DESCRIPTION_TOO_LONG = "Descrizione ha una lunghezza massima di %d caratteri";
    public static final String NOTE_MARKETING_TOO_LONG = "Note marketing ha una lunghezza massima di 100 caratteri";
    public static final String DESCRIPTION_NOT_UPPERCASE = "Descrizione deve essere composta solo da caratteri maiuscoli";
    public static final String DEFAULT_CHANNEL_NOT_SELECTED = "Canale non selezionato";
    public static final String NOT_EMPTY_CHANNEL_NO_GROUP = "Canale deve essere vuoto, nessun Gruppo selezionato";
    public static final String START_HOUR_WITHOUT_END = "Se Ora Inizio è valorizzata, anche Ora Fine lo deve essere";
    public static final String END_HOUR_WITHOUT_START = "Se Ora Fine è valorizzata, anche Ora Inizio lo deve essere";
    public static final String END_HOUR_BEFORE_START = "Ora Fine deve essere maggiore di Ora Inizio";

    public static final String END_DATE_BEFORE_TODAY = "Data fine deve essere successivo o uguale alla data corrente";
}
