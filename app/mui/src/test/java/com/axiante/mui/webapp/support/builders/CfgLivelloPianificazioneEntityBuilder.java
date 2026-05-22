package com.axiante.mui.webapp.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import java.util.HashSet;
import java.util.Set;

public class CfgLivelloPianificazioneEntityBuilder {
    private Long id;
    private String codice;
    private String descrizione;
    private Set<CfgConfHeaderEntity> headers;

    public CfgLivelloPianificazioneEntityBuilder(Long id) {
        this.id = id;
    }

    public CfgLivelloPianificazioneEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public CfgLivelloPianificazioneEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public CfgLivelloPianificazioneEntityBuilder withHeader(CfgConfHeaderEntity header) {
        if (headers == null) {
            headers = new HashSet<>();
        }
        headers.add(header);
        return this;
    }

    public CfgLivelloPianificazioneEntity build() {
        final CfgLivelloPianificazioneEntity entity = new CfgLivelloPianificazioneEntity();
        entity.setId(id);
        entity.setCodice(codice);
        entity.setDescrizione(descrizione);
        entity.setMuiCfgConfHeaders(headers);
        return entity;
    }
}
