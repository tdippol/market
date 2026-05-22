package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ReportBSDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import com.axiante.mui.dbpromo.persistence.service.ReportBSService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Dependent
@Transactional
public class ReportBSServiceImpl implements ReportBSService, Serializable {
    private static final long serialVersionUID = 7418858982738422524L;

    @Inject
    @Getter
    private ReportBSDAO dao;

    @Override
    public List<MuiReportBSEntity> findAllInProgressByPrefissoBS(@NonNull String prefissoBS) {
        return dao.findAllInProgressByPrefissoBS(prefissoBS);
    }

    @Override
    public List<MuiReportBSEntity> findAllFuturesByPrefissoBS(@NonNull String prefissoBS) {
        return dao.findAllFuturesByPrefissoBS(prefissoBS);
    }

    @Override
    public List<MuiReportBSEntity> findAllCompletedByPrefissoBS(@NonNull String prefissoBS) {
        return dao.findAllCompletedByPrefissoBS(prefissoBS);
    }

    @Override
    public List<MuiReportBSEntity> findAll() {
        return dao.findAll();
    }

    @Override
    public MuiReportBSEntity findById(@NonNull String codicePromozione, @NonNull String prefissoBS) {
        return dao.findById(codicePromozione, prefissoBS);
    }

    @Override
    public List<MuiReportBSEntity> findAllNotUsedInProgress(List<String> chiavi, Date date) {
        if (chiavi == null || chiavi.isEmpty()) {
            return dao.findInProgress(date);
        }
        return dao.findAllNotUsedInProgress(chiavi, date);
    }

    @Override
    public List<MuiReportBSEntity> findAllNotUsedInBetween(List<String> chiavi, Date inizio, Date fine) {
        final Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (chiavi == null || chiavi.isEmpty()) {
            return getDao().findInBetween(inizio, fine, today);
        } else {
            return getDao().findAllNotUsedInBetween(chiavi, inizio, fine, today);
        }
    }

    @Override
    public List<MuiReportBSEntity> findInProgress(Date date) {
        return dao.findInProgress(date);
    }

    @Override
    public List<MuiReportBSEntity> findWithChiaveIn(List<String> chiavi) {
        return getDao().findWithChiaveIn(chiavi);
    }
}
