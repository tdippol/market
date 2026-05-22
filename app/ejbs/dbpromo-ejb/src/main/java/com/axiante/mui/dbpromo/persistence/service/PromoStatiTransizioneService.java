package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromoStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.io.Serializable;
import java.util.List;

public interface PromoStatiTransizioneService extends DbPromoService<PromoStatiTransizioneEntity>, Serializable {

    List<PromoStatiTransizioneEntity> findByIdAndStatus(Long promoID, Long statusID);

    List<PromoStatiTransizioneEntity> findAllNotAutomaticByIdAndStatus(Long promoID, Long statusID);

    List<PromoStatiTransizioneEntity> findAllAutomaticByIdAndStatus(Long promoID, Long statusID);

    List<PromoStatiTransizioneEntity> findByPromozione(PromozioneTestataEntity promozione);

    PromoStatiTransizioneEntity findByPromoAndStatuses(PromozioneTestataEntity testata,
                                                       StatoPromozioneEntity fromStatus,
                                                       StatoPromozioneEntity toStatus);

    PromoStatiTransizioneEntity findManualiByPromoAndStatuses(PromozioneTestataEntity testata,
                                                              StatoPromozioneEntity fromStatus,
                                                              StatoPromozioneEntity toStatus,
                                                              Boolean flagAutomatico);
}
