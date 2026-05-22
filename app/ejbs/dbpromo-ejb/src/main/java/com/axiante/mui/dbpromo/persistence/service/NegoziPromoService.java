package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import java.util.List;

public interface NegoziPromoService extends DbPromoService<PromozioneNegozioEntity> {
	PromozioneNegozioEntity savePromozioneNegozioEntity(PromozioneNegozioEntity promozioneNegozioEntity);

	List<PromozioneNegozioEntity> findById(List<Long> ids);
}
