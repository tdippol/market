package com.axiante.mui.validator.service;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.validator.model.Promotion;
import java.util.List;
import java.util.Set;

public interface PromotionValidatorService {
    List<String> validateNewPromotion(Promotion entity,List<CanalePromozioneEntity> listaCanaliAbilitati, GruppoPromozioneEntity gruppoPromozione);

    Set<String> validateEditPromotion(Promotion promotion, PromozioneTestataEntity entity);
}
