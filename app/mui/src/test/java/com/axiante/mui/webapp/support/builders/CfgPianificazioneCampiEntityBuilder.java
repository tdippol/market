package com.axiante.mui.webapp.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;

public class CfgPianificazioneCampiEntityBuilder {
    private Long id;
    private String codiceCampo;
    private String campo;
    private String descrizione;
    private String descrizioneEstesa;
    private String raggruppamento;
    private String tipo;
    private String entityLookup;
    private String entityAttribute;
    private Integer columnWith;

    public CfgPianificazioneCampiEntityBuilder(Long id) {
        this.id = id;
    }

    public CfgPianificazioneCampiEntityBuilder withCodiceCampo(String codiceCampo) {
        this.codiceCampo = codiceCampo;
        return this;
    }

    public CfgPianificazioneCampiEntityBuilder withCampo(String campo) {
        this.campo = campo;
        return this;
    }

    public CfgPianificazioneCampiEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public CfgPianificazioneCampiEntityBuilder withDescrizioneEstesa(String descrizioneEstesa) {
        this.descrizioneEstesa = descrizioneEstesa;
        return this;
    }

    public CfgPianificazioneCampiEntityBuilder withRaggruppamento(String raggruppamento) {
        this.raggruppamento = raggruppamento;
        return this;
    }

    public CfgPianificazioneCampiEntityBuilder withTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public CfgPianificazioneCampiEntityBuilder withEntityLookup(String entityLookup) {
        this.entityLookup = entityLookup;
        return this;
    }

    public CfgPianificazioneCampiEntityBuilder withEntityAttribute(String entityAttribute) {
        this.entityAttribute = entityAttribute;
        return this;
    }

    public CfgPianificazioneCampiEntityBuilder withColumnWidth(Integer columnWidth) {
        this.columnWith = columnWidth;
        return this;
    }

    public CfgPianificazioneCampiEntity build() {
        final CfgPianificazioneCampiEntity entity = new CfgPianificazioneCampiEntity();
        entity.setId(id);
        entity.setCodiceCampo(codiceCampo);
        entity.setCampo(campo);
        entity.setDescrizione(descrizione);
        entity.setDescrizioneEstesa(descrizioneEstesa);
        entity.setRaggruppamento(raggruppamento);
        entity.setTipo(tipo);
        entity.setEntityLookup(entityLookup);
        entity.setEntityAttribute(entityAttribute);
        entity.setColumnWidth(columnWith);
        return entity;
    }
}
