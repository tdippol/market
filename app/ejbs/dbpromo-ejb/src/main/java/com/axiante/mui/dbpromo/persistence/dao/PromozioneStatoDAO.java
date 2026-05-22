package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import java.util.List;

public interface PromozioneStatoDAO extends DbPromoDAO<PromozioneStatoEntity> {

    List<PromozioneStatoEntity> findByPromozioneID(Long promoId);

}
