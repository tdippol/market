package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromoStatiTransizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoStatiTransizioneService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Dependent
@Transactional
public class PromoStatiTransizioneServiceImpl extends AbstractDbPromoService<PromoStatiTransizioneEntity>
        implements PromoStatiTransizioneService {
    private static final long serialVersionUID = -1091458125006131580L;

    @Inject
    @Getter
    private PromoStatiTransizioneDAO dao;

    @Override
    public List<PromoStatiTransizioneEntity> findByIdAndStatus(Long promoID, Long statusID) {
        return dao.findByIdAndStatus(promoID, statusID);
    }

    @Override
    public List<PromoStatiTransizioneEntity> findAllNotAutomaticByIdAndStatus(Long promoID, Long statusID) {
        return dao.findAllNotAutomaticByIdAndStatus(promoID, statusID);
    }

    @Override
    public List<PromoStatiTransizioneEntity> findAllAutomaticByIdAndStatus(Long promoID, Long statusID) {
        return dao.findAllAutomaticByIdAndStatus(promoID, statusID);
    }

    @Override
    public List<PromoStatiTransizioneEntity> findByPromozione(@NonNull PromozioneTestataEntity promozione) {
        List<PromoStatiTransizioneEntity> result = null;
        PromozioneStatoEntity lastStatus = promozione.getPromozioneStatoEntities().stream()
                .filter(st -> st.getDataFineStato() == null).findFirst().orElse(null);
        if (lastStatus != null) {
            result = findByIdAndStatus(promozione.getId(), lastStatus.getStatoPromozioneEntity().getId());
        }
        if (result == null) {
            return new ArrayList<>();
        }
        return result;
    }

    public PromoStatiTransizioneEntity findByPromoAndStatuses(
            @NonNull PromozioneTestataEntity testata, @NonNull StatoPromozioneEntity fromStatus,
            @NonNull StatoPromozioneEntity toStatus) {
        return dao.findByPromoAndStatuses(testata, fromStatus, toStatus);
    }

    public PromoStatiTransizioneEntity findManualiByPromoAndStatuses(
            PromozioneTestataEntity testata, StatoPromozioneEntity fromStatus, StatoPromozioneEntity toStatus,
            Boolean flagAutomatico) {
        return dao.findByPromoAndStatusesAndFlagAutomatico(testata, fromStatus, toStatus, flagAutomatico);
    }
}
