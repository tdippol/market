package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;

import java.util.Date;
import java.util.List;

public interface ReportBSDAO {
    List<MuiReportBSEntity> findAllInProgressByPrefissoBS(String prefissoBS);

    List<MuiReportBSEntity> findAllFuturesByPrefissoBS(String prefissoBS);

    List<MuiReportBSEntity> findAllCompletedByPrefissoBS(String prefissoBS);

    List<MuiReportBSEntity> findAll();

    MuiReportBSEntity findById(String codicePromozione, String prefissoBS);

    // queries for cumulabilita
    List<MuiReportBSEntity> findAllNotUsedInProgress(List<String> chiavi, Date date);

    List<MuiReportBSEntity> findInProgress(Date date);

    List<MuiReportBSEntity> findWithChiaveIn(List<String> chiavi);

    List<MuiReportBSEntity> findAllNotUsedInBetween(List<String> chiavi, Date inizio, Date fine, Date today);

    List<MuiReportBSEntity> findInBetween(Date inizio, Date fine, Date today);
}
