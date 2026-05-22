package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiReportArticoloEntity;
import java.util.List;

public interface ReportArticoloDAO {
    List<MuiReportArticoloEntity> findAllInProgressFuturesByIdItem(Long idItem);

    List<MuiReportArticoloEntity> findAllCompletedByIdItem(Long idItem);
}
