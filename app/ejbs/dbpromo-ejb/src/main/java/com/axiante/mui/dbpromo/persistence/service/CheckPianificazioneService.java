package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CheckPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;

public interface CheckPianificazioneService extends DbPromoService<CheckPianificazioneEntity> {
    List<CheckPianificazioneEntity> findAllByPromozioneAndCodiciCompratore(PromozioneTestataEntity testata,
                                                                           List<String> codiciCompratore);
}
