package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;

public class CategoriaEntityBuilder {

    private Long id;
    private String codice;
    private RepartoEntity reparto;

    public CategoriaEntityBuilder() {}

    public CategoriaEntityBuilder(Long id) {
        this.id = id;
    }

    public CategoriaEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public CategoriaEntityBuilder withReparto(RepartoEntity reparto) {
        this.reparto = reparto;
        return this;
    }

    public CategoriaEntity build() {
        final CategoriaEntity entity = new CategoriaEntity();
        entity.setId(id);
        entity.setCodiceCategoria(codice);
        entity.setRepartoEntity(reparto);
        return entity;
    }
}
