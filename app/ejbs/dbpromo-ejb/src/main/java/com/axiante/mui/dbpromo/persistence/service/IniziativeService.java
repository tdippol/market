package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;

import java.util.Date;
import java.util.List;

public interface IniziativeService extends DbPromoService<IniziativaEntity> {
    List<IniziativaEntity> findAllNotCancelled();

    List<IniziativaEntity> findAllPublishedAndInProgressAndValidDates(Date dataInizio, Date dataFine);

    List<StatoPromozioneEntity> findStatiTransizioneConsentiti();
}
