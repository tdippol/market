package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;

public interface MuiPlanoDbPromoService {

	List<MuiPlanoDbPromoEntity> findAll();
	MuiPlanoDbPromoEntity findByIdPlano(String idPlano);
	List<MuiPlanoDbPromoEntity> findByPromo(PromozioneTestataEntity promo);
	List<String> findIdPlanoByPromo(PromozioneTestataEntity promo);
}
