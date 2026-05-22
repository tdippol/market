package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;

import java.util.Date;
import java.util.List;

public interface IniziativaDAO extends DbPromoDAO<IniziativaEntity> {
    List<IniziativaEntity> findAllNotCancelled();

    List<IniziativaEntity> findAllPublishedAndInProgressAndValidDates(Date dataInizio, Date dataFine);

    List<StatoPromozioneEntity> findStatiTransizioneConsentiti();
}
