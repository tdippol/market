package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneMarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;
import lombok.NonNull;

public interface PromozioneMarchioPrivatoService extends DbPromoService<PromozioneMarchioPrivatoEntity>{
    List<PromozioneMarchioPrivatoEntity> findByIdPromozione(Long idPromozione);
    default List<PromozioneMarchioPrivatoEntity> findByIdPromozione(@NonNull  PromozioneTestataEntity promozioneTestataEntity){
        return findByIdPromozione(promozioneTestataEntity.getId());
    }
}
