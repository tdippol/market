package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import java.util.List;

public interface PromoPubblicazioneTestataDAO extends DbPromoDAO<PromoPubblicazioneTestataEntity> {

    List<PromoPubblicazioneTestataEntity> findByPromozioneID(Long promoID);
    
    boolean runUpdateEsitoPubblicazioni();
}
