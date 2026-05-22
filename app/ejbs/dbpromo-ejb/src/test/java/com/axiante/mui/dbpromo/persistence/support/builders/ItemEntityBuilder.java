package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.SubGrmEntity;

public class ItemEntityBuilder {

    private Long id;
    private String codice;
    private CompratoreEntity compratore;
    private SubGrmEntity subGrm;
    private String codiceMarchioPrivato;

    public ItemEntityBuilder() {}

    public ItemEntityBuilder(Long id) {
        this.id = id;
    }

    public ItemEntityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ItemEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public ItemEntityBuilder withCompratore(CompratoreEntity compratore) {
        this.compratore = compratore;
        return this;
    }


    public ItemEntityBuilder withSubGrm(SubGrmEntity subGrm) {
        this.subGrm = subGrm;
        return this;
    }

    public ItemEntityBuilder withCodiceMarchioPrivato(String codiceMarchioPrivato) {
        this.codiceMarchioPrivato = codiceMarchioPrivato;
        return this;
    }

    public ItemEntity build() {
        final ItemEntity entity = new ItemEntity();
        entity.setId(id);
        entity.setCodiceItem(codice);
        entity.setCompratoreEntity(compratore);
        entity.setSubGrmEntity(subGrm);
        entity.setCodiceMarchioPrivato(codiceMarchioPrivato);
        return entity;
    }
}
