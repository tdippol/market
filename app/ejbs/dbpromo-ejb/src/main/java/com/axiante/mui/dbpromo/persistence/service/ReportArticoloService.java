package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiReportArticoloEntity;
import java.util.List;

public interface ReportArticoloService {
    List<MuiReportArticoloEntity> findAllInProgressFutureByIdItem(Long idItem);

    List<MuiReportArticoloEntity> findAllCompletedByIdItem(Long idItem);
}
