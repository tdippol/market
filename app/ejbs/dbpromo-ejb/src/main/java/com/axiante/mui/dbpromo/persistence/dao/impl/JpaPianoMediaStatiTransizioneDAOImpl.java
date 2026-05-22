package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaStatiTransizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaPianoMediaStatiTransizioneDAOImpl extends JpaDbPromoDAO<PianoMediaStatiTransizioneEntity>
        implements PianoMediaStatiTransizioneDAO {
    private static final long serialVersionUID = -747584620825131827L;

    @Override
    public List<PianoMediaStatiTransizioneEntity> findByIdAndStatus(Long idPiano, Long statusID) {
        return getEm().createNamedQuery("PianoMediaStatiTransizioneEntity.findByIdAndStatus", PianoMediaStatiTransizioneEntity.class)
                .setParameter("idPiano", idPiano).setParameter("statusID", statusID).getResultList();
    }

    @Override
    public List<PianoMediaStatiTransizioneEntity> findAllNotAutomaticByIdAndStatus(Long idPiano, Long statusID) {
        return getEm().createNamedQuery("PianoMediaStatiTransizioneEntity.findAllByIdAndStatusAndFlagAutomatico",
                        PianoMediaStatiTransizioneEntity.class)
                .setParameter("idPiano", idPiano)
                .setParameter("statusID", statusID)
                .setParameter("flagAutomatico", Boolean.FALSE)
                .getResultList();
    }

    @Override
    public List<PianoMediaStatiTransizioneEntity> findAllAutomaticByIdAndStatus(Long idPiano, Long statusID) {
        return getEm().createNamedQuery("PianoMediaStatiTransizioneEntity.findAllByIdAndStatusAndFlagAutomatico",
                        PianoMediaStatiTransizioneEntity.class)
                .setParameter("idPiano", idPiano)
                .setParameter("statusID", statusID)
                .setParameter("flagAutomatico", Boolean.TRUE)
                .getResultList();
    }

    @Override
    public PianoMediaStatiTransizioneEntity findByPianoAndStatuses(@NonNull PianoMediaEntity pianoMedia,
                                                                   @NonNull StatoPromozioneEntity fromStatus,
                                                                   @NonNull StatoPromozioneEntity toStatus) {
        final Long count = getEm().createNamedQuery("PianoMediaStatiTransizioneEntity.countByPianoAndStatuses", Long.class)
                .setParameter("piano", pianoMedia)
                .setParameter("fromStatus", fromStatus)
                .setParameter("toStatus", toStatus)
                .getSingleResult();
        if (count != null && count == 1) {
            return getEm().createNamedQuery("PianoMediaStatiTransizioneEntity.findByPianoAndStatuses", PianoMediaStatiTransizioneEntity.class)
                    .setParameter("piano", pianoMedia)
                    .setParameter("fromStatus", fromStatus)
                    .setParameter("toStatus", toStatus)
                    .getSingleResult();
        }
        if (count == null || count == 0) {
            log.error(String.format("No results found for piano %d, fromStatus %s, toStatus %s",
                    pianoMedia.getId(), fromStatus.getCodiceStato(), toStatus.getCodiceStato()));
        } else if (count > 1) {
            log.error(String.format("Found %d results for piano %d, fromStatus %s, toStatus %s", count,
                    pianoMedia.getId(), fromStatus.getCodiceStato(), toStatus.getCodiceStato()));
        }
        return null;
    }

    public PianoMediaStatiTransizioneEntity findByPianoAndStatusesAndFlagAutomatico(PianoMediaEntity pianoMedia,
                                                                               StatoPromozioneEntity fromStatus,
                                                                               StatoPromozioneEntity toStatus,
                                                                               Boolean flagAutomatico) {
        final Long count = getEm().createNamedQuery("PianoMediaStatiTransizioneEntity.countByPromoAndStatusesAndFlagAutomatico", Long.class)
                .setParameter("piano", pianoMedia)
                .setParameter("fromStatus", fromStatus)
                .setParameter("toStatus", toStatus)
                .setParameter("flagAutomatico", flagAutomatico)
                .getSingleResult();
        if (count != null && count == 1) {
            return getEm().createNamedQuery("PianoMediaStatiTransizioneEntity.findByPromoAndStatusesAndFlagAutomatico", PianoMediaStatiTransizioneEntity.class)
                    .setParameter("piano", pianoMedia)
                    .setParameter("fromStatus", fromStatus)
                    .setParameter("toStatus", toStatus)
                    .setParameter("flagAutomatico", flagAutomatico)
                    .getSingleResult();
        }
        if (count == null || count == 0) {
            log.error(String.format("No results found for piano %d, fromStatus %s, toStatus %s",
                    pianoMedia.getId(), fromStatus.getCodiceStato(), toStatus.getCodiceStato()));
        } else if (count > 1) {
            log.error(String.format("Found %d results for piano %d, fromStatus %s, toStatus %s", count,
                    pianoMedia.getId(), fromStatus.getCodiceStato(), toStatus.getCodiceStato()));
        }
        return null;

    }

}
