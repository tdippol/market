package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;

public interface CheckCompratoriDAO extends DbPromoDAO<CheckCompratoriEntity>{

	List<CheckCompratoriEntity> findByPromozione(PromozioneTestataEntity testata);
	List<CheckCompratoriEntity> findByCompratore(CompratoreEntity compratore);
	CheckCompratoriEntity findByPromozioneAndCompratore(PromozioneTestataEntity testata, CompratoreEntity compratore);
}


