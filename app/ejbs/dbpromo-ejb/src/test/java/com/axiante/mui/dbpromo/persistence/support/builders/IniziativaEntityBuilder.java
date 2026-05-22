package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaStatoEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class IniziativaEntityBuilder {

    private String descrizione;
    private Date dataInizio;
    private Date dataFine;
    private Set<IniziativaStatoEntity> stati;

    public IniziativaEntityBuilder withDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public IniziativaEntityBuilder withDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
        return this;
    }

    public IniziativaEntityBuilder withDataFine(Date dataFine) {
        this.dataFine = dataFine;
        return this;
    }

    public IniziativaEntityBuilder withStato(IniziativaStatoEntity stato) {
        if (stati == null) {
            stati = new HashSet<>();
        }
        stati.add(stato);
        return this;
    }

    public IniziativaEntity build() {
        final IniziativaEntity entity = new IniziativaEntity();
        entity.setDescrizione(descrizione);
        entity.setDataInizio(dataInizio);
        entity.setDataFine(dataFine);
        entity.setStati(stati);
        return entity;
    }
}
