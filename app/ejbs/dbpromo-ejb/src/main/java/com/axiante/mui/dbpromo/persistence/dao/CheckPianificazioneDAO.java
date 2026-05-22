package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CheckPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;

public interface CheckPianificazioneDAO extends DbPromoDAO<CheckPianificazioneEntity> {
    List<CheckPianificazioneEntity> findAllByPromozioneAndCodiciCompratore(PromozioneTestataEntity testata,
                                                                           List<String> codiciCompratore);
}
