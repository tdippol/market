package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaStatiTransizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaStatiTransizioneService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Dependent
@Transactional
public class PianoMediaStatiTransizioneServiceImpl extends AbstractDbPromoService<PianoMediaStatiTransizioneEntity>
        implements PianoMediaStatiTransizioneService {
    private static final long serialVersionUID = -1091458125006131580L;

    @Inject
    @Getter
    private PianoMediaStatiTransizioneDAO dao;

    @Override
    public List<PianoMediaStatiTransizioneEntity> findByIdAndStatus(Long promoID, Long statusID) {
        return dao.findByIdAndStatus(promoID, statusID);
    }

    @Override
    public List<PianoMediaStatiTransizioneEntity> findAllNotAutomaticByIdAndStatus(Long promoID, Long statusID) {
        return dao.findAllNotAutomaticByIdAndStatus(promoID, statusID);
    }

    @Override
    public List<PianoMediaStatiTransizioneEntity> findAllAutomaticByIdAndStatus(Long promoID, Long statusID) {
        return dao.findAllAutomaticByIdAndStatus(promoID, statusID);
    }

    @Override
    public List<PianoMediaStatiTransizioneEntity> findByPianoMedia(@NonNull PianoMediaEntity pianoMedia) {
        List<PianoMediaStatiTransizioneEntity> result = null;
        PianoMediaStatoEntity lastStatus = pianoMedia.getPianoMediaStati().stream()
                .filter(st -> st.getDataFineStato() == null).findFirst().orElse(null);
        if (lastStatus != null) {
            result = findByIdAndStatus(pianoMedia.getId(), lastStatus.getStato().getId());
        }
        if (result == null) {
            return new ArrayList<>();
        }
        return result;
    }

    public PianoMediaStatiTransizioneEntity findByPianoAndStatuses(@NonNull PianoMediaEntity pianoMedia,
                                                                   @NonNull StatoPromozioneEntity fromStatus,
                                                                   @NonNull StatoPromozioneEntity toStatus) {
        return dao.findByPianoAndStatuses(pianoMedia, fromStatus, toStatus);
    }

    public PianoMediaStatiTransizioneEntity findManualiByPianoAndStatuses(PianoMediaEntity pianoMedia,
                                                                          StatoPromozioneEntity fromStatus,
                                                                          StatoPromozioneEntity toStatus,
                                                                          Boolean flagAutomatico) {
        return dao.findByPianoAndStatusesAndFlagAutomatico(pianoMedia, fromStatus, toStatus, flagAutomatico);
    }
}
