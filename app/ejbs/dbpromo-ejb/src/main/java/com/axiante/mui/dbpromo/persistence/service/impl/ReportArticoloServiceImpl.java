package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ReportArticoloDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportArticoloEntity;
import com.axiante.mui.dbpromo.persistence.service.ReportArticoloService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class ReportArticoloServiceImpl implements ReportArticoloService, Serializable {
    private static final long serialVersionUID = 5834536430325285592L;

    @Inject
    @Getter
    private ReportArticoloDAO dao;

    @Override
    public List<MuiReportArticoloEntity> findAllInProgressFutureByIdItem(@NonNull Long idItem) {
        return dao.findAllInProgressFuturesByIdItem(idItem);
    }

    @Override
    public List<MuiReportArticoloEntity> findAllCompletedByIdItem(@NonNull Long idItem) {
        return dao.findAllCompletedByIdItem(idItem);
    }
}
