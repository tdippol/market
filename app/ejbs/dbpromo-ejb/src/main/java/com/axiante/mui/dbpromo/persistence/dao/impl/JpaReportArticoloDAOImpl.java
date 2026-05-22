package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.ReportArticoloDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportArticoloEntity;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaReportArticoloDAOImpl implements ReportArticoloDAO {

    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public List<MuiReportArticoloEntity> findAllInProgressFuturesByIdItem(@NonNull Long idItem) {
        return getEm().createNamedQuery("MuiReportArticoloEntity.findAllInProgressFuturesByIdItem", MuiReportArticoloEntity.class)
                .setParameter("idItem", idItem)
                .setParameter("date", new Date())
                .getResultList();
    }

    @Override
    public List<MuiReportArticoloEntity> findAllCompletedByIdItem(@NonNull Long idItem) {
        return getEm().createNamedQuery("MuiReportArticoloEntity.findAllCompletedByIdItem", MuiReportArticoloEntity.class)
                .setParameter("idItem", idItem)
                .setParameter("date", new Date())
                .getResultList();
    }
}
