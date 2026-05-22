package com.axiante.mui.validator.model;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromoUpdate implements Promotion {

    private String anno;

    private Date dataFine;

    private Date newDataFine;

    private Date dataInizio;

    private Date newDataInizio;

    private String descrizione;

    private String newDescrizione;

    private String descrizioneEstesa;

    private String noteMarketing;

    private String newNoteMarketing;

    private Date oraInizio;

    private Date oraFine;

    private Date newOraInizio;

    private Date newOraFine;

    @Override
    public CanalePromozioneEntity getCanalePromozioneEntity() {
        return null;
    }

    @Override
    public void setCanalePromozioneEntity(CanalePromozioneEntity canale) {
        // noop
    }
}
