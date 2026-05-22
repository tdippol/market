package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromoStatiTransizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaPromoStatiTransizioneDAOImpl extends JpaDbPromoDAO<PromoStatiTransizioneEntity>
        implements PromoStatiTransizioneDAO {
    private static final long serialVersionUID = -747584620825131827L;

    @Override
    public List<PromoStatiTransizioneEntity> findByIdAndStatus(Long promoID, Long statusID) {
        return getEm().createNamedQuery("PromoStatiTransizioneEntity.findByIdAndStatus", PromoStatiTransizioneEntity.class)
                .setParameter("promoID", promoID).setParameter("statusID", statusID).getResultList();
    }

    @Override
    public List<PromoStatiTransizioneEntity> findAllNotAutomaticByIdAndStatus(Long promoID, Long statusID) {
        return getEm().createNamedQuery("PromoStatiTransizioneEntity.findAllByIdAndStatusAndFlagAutomatico",
                        PromoStatiTransizioneEntity.class)
                .setParameter("promoID", promoID)
                .setParameter("statusID", statusID)
                .setParameter("flagAutomatico", Boolean.FALSE)
                .getResultList();
    }

    @Override
    public List<PromoStatiTransizioneEntity> findAllAutomaticByIdAndStatus(Long promoID, Long statusID) {
        return getEm().createNamedQuery("PromoStatiTransizioneEntity.findAllByIdAndStatusAndFlagAutomatico",
                        PromoStatiTransizioneEntity.class)
                .setParameter("promoID", promoID)
                .setParameter("statusID", statusID)
                .setParameter("flagAutomatico", Boolean.TRUE)
                .getResultList();
    }

    @Override
    public PromoStatiTransizioneEntity findByPromoAndStatuses(@NonNull PromozioneTestataEntity testata,
                                                              @NonNull StatoPromozioneEntity fromStatus,
                                                              @NonNull StatoPromozioneEntity toStatus) {
        final Long count = getEm().createNamedQuery("PromoStatiTransizioneEntity.countByPromoAndStatuses", Long.class)
                .setParameter("promo", testata)
                .setParameter("fromStatus", fromStatus)
                .setParameter("toStatus", toStatus)
                .getSingleResult();
        if (count != null && count == 1) {
            return getEm().createNamedQuery("PromoStatiTransizioneEntity.findByPromoAndStatuses", PromoStatiTransizioneEntity.class)
                    .setParameter("promo", testata)
                    .setParameter("fromStatus", fromStatus)
                    .setParameter("toStatus", toStatus)
                    .getSingleResult();
        }
        if (count == null || count == 0) {
            log.error(String.format("No results found for promo %d, fromStatus %s, toStatus %s",
                    testata.getId(), fromStatus.getCodiceStato(), toStatus.getCodiceStato()));
        } else if (count > 1) {
            log.error(String.format("Found %d results for promo %d, fromStatus %s, toStatus %s", count,
                    testata.getId(), fromStatus.getCodiceStato(), toStatus.getCodiceStato()));
        }
        return null;
    }

    public PromoStatiTransizioneEntity findByPromoAndStatusesAndFlagAutomatico(PromozioneTestataEntity testata,
                                                                               StatoPromozioneEntity fromStatus,
                                                                               StatoPromozioneEntity toStatus,
                                                                               Boolean flagAutomatico) {
        final Long count = getEm().createNamedQuery("PromoStatiTransizioneEntity.countByPromoAndStatusesAndFlagAutomatico", Long.class)
                .setParameter("promo", testata)
                .setParameter("fromStatus", fromStatus)
                .setParameter("toStatus", toStatus)
                .setParameter("flagAutomatico", flagAutomatico)
                .getSingleResult();
        if (count != null && count == 1) {
            return getEm().createNamedQuery("PromoStatiTransizioneEntity.findByPromoAndStatusesAndFlagAutomatico", PromoStatiTransizioneEntity.class)
                    .setParameter("promo", testata)
                    .setParameter("fromStatus", fromStatus)
                    .setParameter("toStatus", toStatus)
                    .setParameter("flagAutomatico", flagAutomatico)
                    .getSingleResult();
        }
        if (count == null || count == 0) {
            log.error(String.format("No results found for promo %d, fromStatus %s, toStatus %s",
                    testata.getId(), fromStatus.getCodiceStato(), toStatus.getCodiceStato()));
        } else if (count > 1) {
            log.error(String.format("Found %d results for promo %d, fromStatus %s, toStatus %s", count,
                    testata.getId(), fromStatus.getCodiceStato(), toStatus.getCodiceStato()));
        }
        return null;

    }

}
