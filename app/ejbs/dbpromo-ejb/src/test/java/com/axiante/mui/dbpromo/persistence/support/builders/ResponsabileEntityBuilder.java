package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;

public class ResponsabileEntityBuilder {

    private Long id;
    private String codice;
    private Long flagAttivo;

    public ResponsabileEntityBuilder() {}

    public ResponsabileEntityBuilder(Long id) {
        this.id = id;
    }

    public ResponsabileEntityBuilder withCodice(String codice) {
        this.codice = codice;
        return this;
    }

    public ResponsabileEntityBuilder withFlagAttivo(Long flagAttivo) {
        this.flagAttivo = flagAttivo;
        return this;
    }

    public ResponsabileEntity build() {
        final ResponsabileEntity entity = new ResponsabileEntity();
        entity.setId(id);
        entity.setCodiceResponsabile(codice);
        entity.setFlagAttivo(flagAttivo != null ? flagAttivo : 1L);
        return entity;
    }
}
