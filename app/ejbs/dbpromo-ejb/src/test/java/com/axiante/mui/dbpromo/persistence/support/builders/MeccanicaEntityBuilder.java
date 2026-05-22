package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;

public class MeccanicaEntityBuilder {

    private Long id;
    private String codice;
    private String descrizione;

    public MeccanicaEntityBuilder() {
    }

    public MeccanicaEntityBuilder(Long id) {
        this.id = id;
    }

    public MeccanicaEntityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public MeccanicaEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public MeccanicaEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public MeccanicheEntity build() {
        final MeccanicheEntity entity = new MeccanicheEntity();
        entity.setId(id);
        entity.setCodiceMeccanica(codice);
        entity.setDescrizione(descrizione);
        return entity;
    }
}
