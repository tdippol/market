package com.axiante.mui.webapp.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import java.util.HashSet;
import java.util.Set;

public class MeccanicaEntityBuilder {
    private Long id;
    private String codice;
    private String descrizione;
    private Set<CfgConfHeaderEntity> headers;

    public MeccanicaEntityBuilder(Long id) {
        this.id = id;
    }

    public MeccanicaEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public MeccanicaEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public MeccanicaEntityBuilder withHeader(CfgConfHeaderEntity header) {
        if (headers == null) {
            headers = new HashSet<>();
        }
        headers.add(header);
        return this;
    }

    public MeccanicheEntity build() {
        final MeccanicheEntity entity = new MeccanicheEntity();
        entity.setId(id);
        entity.setCodiceMeccanica(codice);
        entity.setDescrizione(descrizione);
        entity.setMuiCfgConfHeaders(headers);
        return entity;
    }
}
