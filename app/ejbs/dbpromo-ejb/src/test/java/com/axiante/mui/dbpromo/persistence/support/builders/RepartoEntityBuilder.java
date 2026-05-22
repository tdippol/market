package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;

public class RepartoEntityBuilder {

    private Long id;
    private String codice;

    public RepartoEntityBuilder() {}

    public RepartoEntityBuilder(Long id) {
        this.id = id;
    }

    public RepartoEntityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RepartoEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public RepartoEntity build() {
        final RepartoEntity entity = new RepartoEntity();
        entity.setId(id);
        entity.setCodiceReparto(codice);
        return entity;
    }
}
