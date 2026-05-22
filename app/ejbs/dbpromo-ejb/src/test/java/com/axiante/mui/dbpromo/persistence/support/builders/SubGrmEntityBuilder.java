package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;

public class SubGrmEntityBuilder {

    private Long id;
    private String codice;
    private GrmEntity grm;

    public SubGrmEntityBuilder() {}

    public SubGrmEntityBuilder(Long id) {
        this.id = id;
    }

    public SubGrmEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public SubGrmEntityBuilder withGrm(GrmEntity grm) {
        this.grm = grm;
        return this;
    }

    public SubGrmEntity build() {
        final SubGrmEntity entity = new SubGrmEntity();
        entity.setId(id);
        entity.setCodiceSubgrm(codice);
        entity.setGrmEntity(grm);
        return entity;
    }
}
