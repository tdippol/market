package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CfgSetPianificazioneEntityBuilder {
    private Long id;
    private Date dataInizio;
    private Date dataFine;
    private String descrizione;
    private Set<PromozioneTestataEntity> promozioneTestate;
    private Set<CfgConfHeaderEntity> headers;

    public CfgSetPianificazioneEntityBuilder(Long id) {
        this.id = id;
    }

    public CfgSetPianificazioneEntityBuilder withDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
        return this;
    }

    public CfgSetPianificazioneEntityBuilder withDataFine(Date dataFine) {
        this.dataFine = dataFine;
        return this;
    }

    public CfgSetPianificazioneEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public CfgSetPianificazioneEntityBuilder withTestata(PromozioneTestataEntity promozioneTestata) {
        if (promozioneTestate == null) {
            promozioneTestate = new HashSet<>();
        }
        promozioneTestate.add(promozioneTestata);
        return this;
    }

    public CfgSetPianificazioneEntityBuilder withHeader(CfgConfHeaderEntity header) {
        if (headers == null) {
            headers = new HashSet<>();
        }
        headers.add(header);
        return this;
    }

    public CfgSetPianificazioneEntity build() {
        final CfgSetPianificazioneEntity entity = new CfgSetPianificazioneEntity();
        entity.setId(id);
        entity.setDataInizio(dataInizio);
        entity.setDataFine(dataFine);
        entity.setDescrizione(descrizione);
        entity.setPromozioneTestataEntities(promozioneTestate);
        entity.setMuiCfgConfHeaders(headers);
        return entity;
    }
}
