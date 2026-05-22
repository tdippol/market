package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.ReportBSDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@DbPromoJpaDao
public class JpaReportBSDAOImpl implements ReportBSDAO {

    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public List<MuiReportBSEntity> findAllInProgressByPrefissoBS(@NonNull String prefissoBS) {
        return getEm().createNamedQuery("MuiReportBSEntity.findAllInProgressByIdItem", MuiReportBSEntity.class)
                .setParameter("prefissoBS", prefissoBS)
                .setParameter("date", new Date())
                .getResultList();
    }

    @Override
    public List<MuiReportBSEntity> findAllFuturesByPrefissoBS(@NonNull String prefissoBS) {
        return getEm().createNamedQuery("MuiReportBSEntity.findAllFuturesByIdItem", MuiReportBSEntity.class)
                .setParameter("prefissoBS", prefissoBS)
                .setParameter("date", new Date())
                .getResultList();
    }

    @Override
    public List<MuiReportBSEntity> findAllCompletedByPrefissoBS(@NonNull String prefissoBS) {
        return getEm().createNamedQuery("MuiReportBSEntity.findAllCompletedByIdItem", MuiReportBSEntity.class)
                .setParameter("prefissoBS", prefissoBS)
                .setParameter("date", new Date())
                .getResultList();
    }

    @Override
    public List<MuiReportBSEntity> findAll() {
        return getEm().createNamedQuery("MuiReportBSEntity.findAll", MuiReportBSEntity.class)
                .getResultList();
    }

    @Override
    public MuiReportBSEntity findById(@NonNull String codicePromozione, @NonNull String prefissoBS) {
        return getEm().find(MuiReportBSEntity.class, new MuiReportBSId(codicePromozione, prefissoBS));
    }

    @Override
    public List<MuiReportBSEntity> findAllNotUsedInProgress(List<String> chiavi, Date date) {
        return getEm().createNamedQuery("MuiReportBSEntity.findAllNotUsedInProgress", MuiReportBSEntity.class)
                .setParameter("chiavi", chiavi)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<MuiReportBSEntity> findAllNotUsedInBetween(List<String> chiavi, @NonNull Date inizio, @NonNull Date fine, @NonNull Date today) {
        return getEm().createNamedQuery("MuiReportBSEntity.findNotUsedInBetween", MuiReportBSEntity.class)
                .setParameter("chiavi", chiavi)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .setParameter("today", today)
                .getResultList();
    }

    @Override
    public List<MuiReportBSEntity> findInBetween(Date inizio, Date fine, @NonNull Date today) {
        return getEm().createNamedQuery("MuiReportBSEntity.findInBetween", MuiReportBSEntity.class)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .setParameter("today", today)
                .getResultList();
    }

    @Override
    public List<MuiReportBSEntity> findInProgress(Date date) {
        return getEm().createNamedQuery("MuiReportBSEntity.findInProgress", MuiReportBSEntity.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<MuiReportBSEntity> findWithChiaveIn(List<String> chiavi) {
        if (chiavi == null || chiavi.isEmpty())
            return findAll();
        return getEm().createNamedQuery("MuiReportBSEntity.findWithChiaveIn", MuiReportBSEntity.class)
                .setParameter("chiavi", chiavi)
                .getResultList();
    }
}
