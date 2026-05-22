package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import java.util.List;
import javax.validation.constraints.NotNull;

public interface PromoStatoService extends DbPromoService<PromozioneStatoEntity> {

	List<PromozioneStatoEntity> findByPromozioneID(@NotNull Long promoId) throws Exception;
}
