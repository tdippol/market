package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.ContributiPromoDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;

@DbPromoJpaDao
public class JpaContributiPromoDAOImpl extends JpaDbPromoDAO<ContributiPromoEntity> implements ContributiPromoDAO {
    private static final long serialVersionUID = 7762770482192587336L;
    private static final int MAX_IN_CLAUSE_SIZE = 800;


    @Override
    public List<ContributiPromoEntity> findAllByPromozioni(List<PromozioneTestataEntity> promozioni) {
        if (promozioni == null || promozioni.isEmpty()) {
            throw new IllegalArgumentException("Promozioni cannot be null or empty");
        }

        final List<PromozioneTestataEntity> uniquePromozioni = promozioni.stream()
                .distinct()
                .collect(Collectors.toList());

        final TypedQuery<ContributiPromoEntity> query = getEm().createNamedQuery(
                "ContributiPromoEntity.findAllByPromozioni",
                ContributiPromoEntity.class
        );

        if (uniquePromozioni.size() <= MAX_IN_CLAUSE_SIZE) {
            return query.setParameter("promozioni", uniquePromozioni).getResultList();
        }

        final List<ContributiPromoEntity> result = new ArrayList<>();
        for (int start = 0; start < uniquePromozioni.size(); start += MAX_IN_CLAUSE_SIZE) {
            final int end = Math.min(start + MAX_IN_CLAUSE_SIZE, uniquePromozioni.size());
            result.addAll(
                    query.setParameter("promozioni", uniquePromozioni.subList(start, end))
                            .getResultList()
            );
        }
        return result;
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
