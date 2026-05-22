package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiTransizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;

public class CfgStatiTransizioniEntityBuilder {
    private Long id;
    private CanalePromozioneEntity canale;
    private StatoPromozioneEntity statoPromozione;
    private StatoPromozioneEntity statoTransizione;
    private StatoPromozioneEntity statoErrore;
    private Boolean flagAutomatico;

    public CfgStatiTransizioniEntityBuilder(Long id) {
        this.id = id;
    }

    public CfgStatiTransizioniEntityBuilder withCanale(CanalePromozioneEntity canale) {
        this.canale = canale;
        return this;
    }

    public CfgStatiTransizioniEntityBuilder withStatoPromozione(StatoPromozioneEntity stato) {
        this.statoPromozione = stato;
        return this;
    }

    public CfgStatiTransizioniEntityBuilder withStatoTransizione(StatoPromozioneEntity stato) {
        this.statoTransizione = stato;
        return this;
    }

    public CfgStatiTransizioniEntityBuilder withStatoErrore(StatoPromozioneEntity stato) {
        this.statoErrore = stato;
        return this;
    }

    public CfgStatiTransizioniEntityBuilder withFlagAutomatico(Boolean flagAutomatico) {
        this.flagAutomatico = flagAutomatico;
        return this;
    }

    public CfgStatiTransizioniEntity build() {
        CfgStatiTransizioniEntity entity = new CfgStatiTransizioniEntity();
        entity.setId(id);
        entity.setMuiCanalePromozione(canale);
        entity.setStatoPromozione(statoPromozione);
        entity.setStatoTransizione(statoTransizione);
        entity.setStatoErrore(statoErrore);
        entity.setFlagAutomatico(flagAutomatico);
        return entity;
    }
}
