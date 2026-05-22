package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;

public class CanalePromozioneEntityBuilder {

    private Long id;
    private Long codice;
    private String descrizione;
    private GruppoPromozioneEntity gruppo;
    private Long codeRangeMin;
    private Long codeRangeMax;

    public CanalePromozioneEntityBuilder() {
    }

    public CanalePromozioneEntityBuilder(Long id) {
        this.id = id;
    }

    public CanalePromozioneEntityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CanalePromozioneEntityBuilder withCodice(Long codice) {
        this.codice = codice;
        return this;
    }

    public CanalePromozioneEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public CanalePromozioneEntityBuilder withGruppo(GruppoPromozioneEntity gruppo) {
        this.gruppo = gruppo;
        return this;
    }

    public CanalePromozioneEntityBuilder withCodeRangeMin(Long codeRangeMin) {
        this.codeRangeMin = codeRangeMin;
        return this;
    }

    public CanalePromozioneEntityBuilder withCodeRangeMax(Long codeRangeMax) {
        this.codeRangeMax = codeRangeMax;
        return this;
    }

    public CanalePromozioneEntity build() {
        final CanalePromozioneEntity entity = new CanalePromozioneEntity();
        entity.setId(id);
        entity.setCodiceCanale(codice);
        entity.setDescrizione(descrizione);
        entity.setGruppoPromozioneEntity(gruppo);
        entity.setCodeRangeMin(codeRangeMin);
        entity.setCodeRangeMax(codeRangeMax);
        return entity;
    }
}
