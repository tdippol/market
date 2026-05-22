package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Date;

public class PromozioneStatoEntityBuilder {
    private Long id;
    private StatoPromozioneEntity statoPromozione;
    private Date dataFine;

    public PromozioneStatoEntityBuilder(Long id) {
        this.id = id;
    }

    public PromozioneStatoEntityBuilder withStatoPromozione(StatoPromozioneEntity statoPromozione) {
        this.statoPromozione = statoPromozione;
        return this;
    }

    public PromozioneStatoEntityBuilder withDataFineStato(Date dataFineStato) {
        this.dataFine = dataFineStato;
        return this;
    }

    public PromozioneStatoEntity build() {
        final PromozioneStatoEntity entity = new PromozioneStatoEntity();
        entity.setId(id);
        entity.setStatoPromozioneEntity(statoPromozione);
        entity.setDataFineStato(dataFine);
        return entity;
    }
}
