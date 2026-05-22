package com.axiante.mui.validator.model;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import java.util.Date;

public interface Promotion {

    String getDescrizione();

    String getNewDescrizione();

    Date getDataFine();

    Date getNewDataFine();

    Date getDataInizio();

    Date getNewDataInizio();

    CanalePromozioneEntity getCanalePromozioneEntity();

    void setCanalePromozioneEntity(CanalePromozioneEntity canale);

    String getAnno();

    String getNoteMarketing();

    String getNewNoteMarketing();

    Date getOraInizio();

    Date getOraFine();

    Date getNewOraInizio();

    Date getNewOraFine();
}
