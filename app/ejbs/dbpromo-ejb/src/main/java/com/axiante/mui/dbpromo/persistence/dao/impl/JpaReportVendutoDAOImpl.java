package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.ReportVendutoDAO;
import com.axiante.mui.dbpromo.persistence.entities.ReportVendutoEntity;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaReportVendutoDAOImpl implements ReportVendutoDAO {

    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public List<ReportVendutoEntity> findAllByIdPromozione(@NonNull Long idPromozione) {
        return getEm().createNamedQuery("ReportVendutoEntity.findAllByIdPromozione", ReportVendutoEntity.class)
                .setParameter("idPromozione", idPromozione)
                .getResultList();
    }
}
