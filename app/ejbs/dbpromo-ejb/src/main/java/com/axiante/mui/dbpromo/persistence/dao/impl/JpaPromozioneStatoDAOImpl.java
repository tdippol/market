package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneStatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import java.util.List;

@DbPromoJpaDao
public class JpaPromozioneStatoDAOImpl extends JpaDbPromoDAO<PromozioneStatoEntity> implements PromozioneStatoDAO {

    private static final long serialVersionUID = -6048642185597731921L;

    @Override
    public List<PromozioneStatoEntity> findByPromozioneID(Long promoId) {
        return getEm().createNamedQuery("PromozioneStatoEntity.findByPromozioneID", PromozioneStatoEntity.class)
                .setParameter("promoId", promoId).getResultList();
    }
}
