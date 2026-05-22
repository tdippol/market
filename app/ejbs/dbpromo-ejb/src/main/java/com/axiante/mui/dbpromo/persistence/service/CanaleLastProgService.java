package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import java.util.List;

public interface CanaleLastProgService extends DbPromoService<CanaleLastProgEntity> {
	List<CanaleLastProgEntity> findByCanale(CanalePromozioneEntity canale);

}
