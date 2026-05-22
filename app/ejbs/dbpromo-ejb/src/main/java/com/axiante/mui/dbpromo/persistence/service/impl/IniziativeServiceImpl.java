package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.IniziativaDAO;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.IniziativeService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Dependent
@Transactional
@Slf4j
public class IniziativeServiceImpl extends AbstractDbPromoService<IniziativaEntity> implements IniziativeService {
    private static final long serialVersionUID = 7527608956854492301L;

    @Inject
    @Getter
    private IniziativaDAO dao;

    @Override
    public List<IniziativaEntity> findAllNotCancelled() {
        return dao.findAllNotCancelled();
    }

    @Override
    public List<IniziativaEntity> findAllPublishedAndInProgressAndValidDates(Date dataInizio, Date dataFine) {
        return dao.findAllPublishedAndInProgressAndValidDates(dataInizio, dataFine);
    }

    @Override
    public List<StatoPromozioneEntity> findStatiTransizioneConsentiti() {
        return dao.findStatiTransizioneConsentiti();
    }
}
