package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromoStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;

public interface PromoStatiTransizioneDAO extends DbPromoDAO<PromoStatiTransizioneEntity> {

    List<PromoStatiTransizioneEntity> findByIdAndStatus(Long promoID, Long statusID);

    List<PromoStatiTransizioneEntity> findAllNotAutomaticByIdAndStatus(Long promoID, Long statusID);

    List<PromoStatiTransizioneEntity> findAllAutomaticByIdAndStatus(Long promoID, Long statusID);

    PromoStatiTransizioneEntity findByPromoAndStatuses(PromozioneTestataEntity testata,
                                                       StatoPromozioneEntity fromStatus,
                                                       StatoPromozioneEntity toStatus);

    PromoStatiTransizioneEntity findByPromoAndStatusesAndFlagAutomatico(PromozioneTestataEntity testata,
                                                                        StatoPromozioneEntity fromStatus,
                                                                        StatoPromozioneEntity toStatus,
                                                                        Boolean flagAutomatico);
}
