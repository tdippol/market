package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;

import java.util.HashSet;

public class FornitoreEntityBuilder {

    private Long id;
    private String codice;
    private String descrizione;

    public FornitoreEntityBuilder() {}

    public FornitoreEntityBuilder(Long id) {
        this.id = id;
    }

    public FornitoreEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public FornitoreEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public FornitoreEntity build() {
        final FornitoreEntity entity = new FornitoreEntity();
        entity.setId(id);
        entity.setCodiceFornitore(codice);
        entity.setDescrizione(descrizione != null ? descrizione : String.format("Fornitore %s", codice));
        entity.setMuiAssortimentoFornitores(new HashSet<>());
        return entity;
    }
}
