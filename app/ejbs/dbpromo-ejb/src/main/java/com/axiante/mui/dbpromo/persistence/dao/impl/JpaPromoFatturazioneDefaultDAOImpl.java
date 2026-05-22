package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromoFatturazioneDefaultDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneDefaultEntity;
import lombok.NonNull;

import java.util.List;

@DbPromoJpaDao
public class JpaPromoFatturazioneDefaultDAOImpl extends JpaDbPromoDAO<PromoFatturazioneDefaultEntity>
        implements PromoFatturazioneDefaultDAO {
    private static final long serialVersionUID = -6947592829003777660L;

    @Override
    public List<PromoFatturazioneDefaultEntity> findAllByIdsCompratori(@NonNull List<Long> idsCompratori) {
        if (idsCompratori.isEmpty()) {
            throw new IllegalArgumentException("idsCompratori cannot be null or empty");
        }
        return getEm().createNamedQuery("PromoFatturazioneDefaultEntity.findAllByIdsCompratori", PromoFatturazioneDefaultEntity.class)
                .setParameter("idsCompratori", idsCompratori)
                .getResultList();
    }
}
