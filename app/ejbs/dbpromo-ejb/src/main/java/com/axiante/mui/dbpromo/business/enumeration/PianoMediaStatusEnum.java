package com.axiante.mui.dbpromo.business.enumeration;

import lombok.Getter;

public enum PianoMediaStatusEnum {
    PIANO_MEDIA_CREATO("10", "Piano Media Creato"),
    PIANO_MEDIA_PIANIFICAZIONE("30", "Piano Media In Pianificazione"),
    PIANO_MEDIA_PUBBLICATO("300", "Piano Media Pubblicato"),
    PIANO_MEDIA_APPROVATO("310", "Piano Media Approvato"),
    PIANO_MEDIA_NON_APPROVATO("320", "Piano Media Non Approvato"),
    PIANO_MEDIA_ESECUZIONE("400", "Piano Media In Esecuzione"),
    PIANO_MEDIA_SBLOCCATO("410", "Piano Media Sbloccato"),
    PIANO_MEDIA_CHIUSO("500", "Piano Media Chiuso");

    @Getter
    private String key;

    @Getter
    private String description;

    PianoMediaStatusEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }
}
