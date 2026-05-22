package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import java.util.HashSet;

public class CfgAzioniEntityBuilder {
    private Long id;
    private String codice;

    public CfgAzioniEntityBuilder(Long id) {
        this.id = id;
    }

    public CfgAzioniEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public CfgAzioniEntity build() {
        CfgAzioniEntity entity = new CfgAzioniEntity();
        entity.setId(id);
        entity.setCodice(codice);
        entity.setCanali(new HashSet<>());
        return entity;
    }
}
