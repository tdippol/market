package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.ReportSovrapposizioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniEntity;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DbPromoJpaDao
public class JpaReportSovrapposizioniDAOImpl extends JpaDbPromoDAO<ReportSovrapposizioniEntity>
        implements ReportSovrapposizioniDAO {
	private static final long serialVersionUID = 1L;

    @Override
    public List<ReportSovrapposizioniEntity> findAllByPromozioneAndCodiciCompratore(@NonNull PromozioneTestataEntity testata,
                                                                                    List<String> codiciCompratore) {
        if (codiciCompratore == null || codiciCompratore.isEmpty()) {
            throw new IllegalArgumentException("codiciCompratore cannot be null or empty");
        }
        return getEm().createNamedQuery("ReportSovrapposizioniEntity.findAllByPromozioneAndCodiciCompratore", ReportSovrapposizioniEntity.class)
                .setParameter("testata", testata)
                .setParameter("codiciCompratore", codiciCompratore)
                .getResultList();
    }
}
