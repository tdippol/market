package com.axiante.mui.webapp.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;

public class CfgTipoElementoEntityBuilder {
    private Long id;
    private Integer raggruppamento;
    private Integer totale;
    private Integer reparto;
    private Integer grm;
    private Integer articolo;
    private Integer omogeneo;

    public CfgTipoElementoEntityBuilder(Long id) {
        this.id = id;
    }

    public CfgTipoElementoEntityBuilder withRaggruppamento(int raggruppamento) {
        this.raggruppamento = raggruppamento;
        return this;
    }

    public CfgTipoElementoEntityBuilder withTotale(int totale) {
        this.totale = totale;
        return this;
    }

    public CfgTipoElementoEntityBuilder withReparto(int reparto) {
        this.reparto = reparto;
        return this;
    }

    public CfgTipoElementoEntityBuilder withGrm(int grm) {
        this.grm = grm;
        return this;
    }

    public CfgTipoElementoEntityBuilder withArticolo(int articolo) {
        this.articolo = articolo;
        return this;
    }

    public CfgTipoElementoEntityBuilder withOmogeneo(int omogeneo) {
        this.omogeneo = omogeneo;
        return this;
    }

    public CfgTipoElementoEntity build() {
        final CfgTipoElementoEntity entity = new CfgTipoElementoEntity();
        entity.setId(id);
        entity.setRaggruppamento(raggruppamento);
        entity.setTotale(totale);
        entity.setReparto(reparto);
        entity.setGrm(grm);
        entity.setArticolo(articolo);
        entity.setOmogeneo(omogeneo);
        return entity;
    }
}
