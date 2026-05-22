package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;

public class GrmEntityBuilder {

    private Long id;
    private String codice;
    private CategoriaEntity categoria;

    public GrmEntityBuilder() {}

    public GrmEntityBuilder(Long id) {
        this.id = id;
    }

    public GrmEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public GrmEntityBuilder withCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
        return this;
    }

    public GrmEntity build() {
        final GrmEntity entity = new GrmEntity();
        entity.setId(id);
        entity.setCodiceGrm(codice);
        entity.setMuiCategoria(categoria);
        return entity;
    }
}
