package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import java.util.Date;
import java.util.List;

public interface ReportBSService {
    List<MuiReportBSEntity> findAllInProgressByPrefissoBS(String prefissoBS);

    List<MuiReportBSEntity> findAllFuturesByPrefissoBS(String prefissoBS);

    List<MuiReportBSEntity> findAllCompletedByPrefissoBS(String prefissoBS);

    List<MuiReportBSEntity> findAll();
    MuiReportBSEntity findById(String codicePromozione, String prefissoBS);

    List<MuiReportBSEntity> findAllNotUsedInProgress(List<String> chiavi, Date date);
    List<MuiReportBSEntity> findInProgress(Date date);
    List<MuiReportBSEntity> findWithChiaveIn(List<String> chiavi);
    List<MuiReportBSEntity> findAllNotUsedInBetween(List<String> chiavi,Date inizio,Date fine);
}
