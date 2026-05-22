package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.IniziativaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Date;

public class IniziativaStatoEntityBuilder {

    private StatoPromozioneEntity stato;
    private Date dataInizioStato;
    private Date dataFineStato;

    public IniziativaStatoEntityBuilder withStato(StatoPromozioneEntity stato) {
        this.stato = stato;
        return this;
    }

    public IniziativaStatoEntityBuilder withDataInizioStato(Date dataInizioStato) {
        this.dataInizioStato = dataInizioStato;
        return this;
    }

    public IniziativaStatoEntityBuilder withDataFineStato(Date dataFineStato) {
        this.dataFineStato = dataFineStato;
        return this;
    }

    public IniziativaStatoEntity build() {
        final IniziativaStatoEntity entity = new IniziativaStatoEntity();
        entity.setStatoPromo(stato);
        entity.setDataInizioStato(dataInizioStato);
        entity.setDataFineStato(dataFineStato);
        return entity;
    }
}
