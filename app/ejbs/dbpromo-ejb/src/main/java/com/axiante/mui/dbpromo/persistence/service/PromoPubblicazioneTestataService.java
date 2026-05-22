package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import java.io.Serializable;
import java.util.List;
import lombok.NonNull;

public interface PromoPubblicazioneTestataService
extends DbPromoService<PromoPubblicazioneTestataEntity>, Serializable {

	List<PromoPubblicazioneTestataEntity> findByPromoID(@NonNull Long promoID) throws Exception;

	default PromoPubblicazioneTestataEntity persist(@NonNull final PromoPubblicazioneTestataEntity entity,
			String user) {
		return (PromoPubblicazioneTestataEntity) persistWithAuditLog(entity, user);
	}

}
