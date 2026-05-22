package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;

public class GruppoPromozioneEntityBuilder {

    private Long id;
    private String codice;
    private String descrizione;

    public GruppoPromozioneEntityBuilder() {
    }

    public GruppoPromozioneEntityBuilder(Long id) {
        this.id = id;
    }

    public GruppoPromozioneEntityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public GruppoPromozioneEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public GruppoPromozioneEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public GruppoPromozioneEntity build() {
        final GruppoPromozioneEntity entity = new GruppoPromozioneEntity();
        entity.setId(id);
        entity.setCodiceGruppo(codice);
        entity.setDescrizione(descrizione);
        return entity;
    }
}
