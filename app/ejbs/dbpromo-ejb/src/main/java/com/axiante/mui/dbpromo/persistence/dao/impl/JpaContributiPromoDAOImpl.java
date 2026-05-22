package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.ContributiPromoDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaContributiPromoDAOImpl extends JpaDbPromoDAO<ContributiPromoEntity> implements ContributiPromoDAO {
    private static final long serialVersionUID = 7762770482192587336L;

    @Override
    public List<ContributiPromoEntity> findAllByPromozioni(List<PromozioneTestataEntity> promozioni) {
        if (promozioni == null || promozioni.isEmpty()) {
            throw new IllegalArgumentException("Promozioni cannot be null or empty");
        }
        return getEm().createNamedQuery("ContributiPromoEntity.findAllByPromozioni", ContributiPromoEntity.class)
                .setParameter("promozioni", promozioni)
                .getResultList();
    }

    @Override
    public List<ContributiPromoEntity> findAllByPromozione(@NonNull PromozioneTestataEntity promozione) {
        return getEm().createNamedQuery("ContributiPromoEntity.findAllByPromozione", ContributiPromoEntity.class)
                .setParameter("promozione", promozione)
                .getResultList();
    }

    @Override
    public Long countByPromozioneAndCompratoreAndFornitore(@NonNull PromozioneTestataEntity promozione,
                                                           @NonNull CompratoreEntity compratore,
                                                           @NonNull FornitoreEntity fornitore) {
        return getEm().createNamedQuery("ContributiPromoEntity.countByPromozioneAndCompratoreAndFornitore", Long.class)
                .setParameter("promozione", promozione)
                .setParameter("compratore", compratore)
                .setParameter("fornitore", fornitore)
                .getSingleResult();
    }

    @Override
    public List<Long> findAllItemsIdByPromozione(@NonNull PromozioneTestataEntity promozione) {
        return getEm().createNamedQuery("ContributiPromoEntity.findAllItemsIdByPromozione", Long.class)
                .setParameter("promozione", promozione)
                .getResultList();
    }

    @Override
    public Integer nextProgressivo(@NonNull PromozioneTestataEntity promozione, @NonNull CompratoreEntity compratore,
                                   @NonNull FornitoreEntity fornitore) {
        return getEm().createNamedQuery("ContributiPromoEntity.nextProgressivo", Long.class)
                .setParameter("promozione", promozione)
                .setParameter("compratore", compratore)
                .setParameter("fornitore", fornitore)
                .getSingleResult().intValue();
    }
}
