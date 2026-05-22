package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;

public class StatoPromozioneEntityBuilder {
    private Long id;
    private String codice;
    private String descrizione;

    public StatoPromozioneEntityBuilder() {
    }

    public StatoPromozioneEntityBuilder(Long id) {
        this.id = id;
    }

    public StatoPromozioneEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public StatoPromozioneEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public StatoPromozioneEntity build() {
        final StatoPromozioneEntity entity = new StatoPromozioneEntity();
        entity.setId(id);
        entity.setCodiceStato(codice);
        entity.setDescrizione(descrizione);
        return entity;
    }

    public static StatoPromozioneEntity buildCancellata() {
        final StatoPromozioneEntity entity = new StatoPromozioneEntity();
        entity.setCodiceStato("00");
        entity.setDescrizione("Cancellata");
        return entity;
    }

    public static StatoPromozioneEntity buildCancellata(Long id) {
        final StatoPromozioneEntity entity = buildCancellata();
        entity.setId(id);
        return entity;
    }

    public static StatoPromozioneEntity buildCreata() {
        final StatoPromozioneEntity entity = new StatoPromozioneEntity();
        entity.setCodiceStato("10");
        entity.setDescrizione("Creata");
        return entity;
    }

    public static StatoPromozioneEntity buildCreata(Long id) {
        final StatoPromozioneEntity entity = buildCreata();
        entity.setId(id);
        return entity;
    }

    public static StatoPromozioneEntity buildPubblicata() {
        final StatoPromozioneEntity entity = new StatoPromozioneEntity();
        entity.setCodiceStato("20");
        entity.setDescrizione("Pubblicata");
        return entity;
    }

    public static StatoPromozioneEntity buildPubblicata(Long id) {
        final StatoPromozioneEntity entity = buildPubblicata();
        entity.setId(id);
        return entity;
    }

    public static StatoPromozioneEntity buildDisponibile() {
        final StatoPromozioneEntity entity = new StatoPromozioneEntity();
        entity.setCodiceStato("30");
        entity.setDescrizione("Disponibile");
        return entity;
    }

    public static StatoPromozioneEntity buildDisponibile(Long id) {
        final StatoPromozioneEntity entity = buildDisponibile();
        entity.setId(id);
        return entity;
    }

    public static StatoPromozioneEntity buildFinalizzata() {
        final StatoPromozioneEntity entity = new StatoPromozioneEntity();
        entity.setCodiceStato("31");
        entity.setDescrizione("Finalizzata");
        return entity;
    }

    public static StatoPromozioneEntity buildFinalizzata(Long id) {
        final StatoPromozioneEntity entity = buildFinalizzata();
        entity.setId(id);
        return entity;
    }

    public static StatoPromozioneEntity buildInEsecuzione() {
        final StatoPromozioneEntity entity = new StatoPromozioneEntity();
        entity.setCodiceStato("400");
        entity.setDescrizione("In esecuzione");
        return entity;
    }

    public static StatoPromozioneEntity buildInEsecuzione(Long id) {
        final StatoPromozioneEntity entity = buildInEsecuzione();
        entity.setId(id);
        return entity;
    }

    public static StatoPromozioneEntity buildCompletata() {
        final StatoPromozioneEntity entity = new StatoPromozioneEntity();
        entity.setCodiceStato("500");
        entity.setDescrizione("Completata");
        return entity;
    }

    public static StatoPromozioneEntity buildCompletata(Long id) {
        final StatoPromozioneEntity entity = buildCompletata();
        entity.setId(id);
        return entity;
    }
}
