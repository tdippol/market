package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;

public interface CheckCompratoriService extends DbPromoService<CheckCompratoriEntity>{

	List<CheckCompratoriEntity> findByPromozione(PromozioneTestataEntity promozione);
	List<CheckCompratoriEntity> findByCompratore(CompratoreEntity compratore);
	CheckCompratoriEntity findByPromozioneAndCompratore(PromozioneTestataEntity promozione, CompratoreEntity compratore);
}
