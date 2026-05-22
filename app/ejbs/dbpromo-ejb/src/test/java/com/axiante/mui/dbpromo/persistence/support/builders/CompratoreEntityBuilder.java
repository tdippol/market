package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;

public class CompratoreEntityBuilder {

    private Long id;
    private String codice;
    private String descrizione;
    private Long flagAttivo;
    private ResponsabileEntity responsabile;

    public CompratoreEntityBuilder() {}

    public CompratoreEntityBuilder(Long id) {
        this.id = id;
    }

    public CompratoreEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public CompratoreEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public CompratoreEntityBuilder withFlagAttivo(Long flagAttivo) {
        this.flagAttivo = flagAttivo;
        return this;
    }

    public CompratoreEntityBuilder withResponsabile(ResponsabileEntity responsabile) {
        this.responsabile = responsabile;
        return this;
    }

    public CompratoreEntity build() {
        final CompratoreEntity entity = new CompratoreEntity();
        entity.setId(id);
        entity.setCodiceCompratore(codice);
        entity.setDescrizione(descrizione);
        entity.setFlagAttivo(flagAttivo != null ? flagAttivo : 1L);
        entity.setResponsabileEntity(responsabile);
        return entity;
    }
}
