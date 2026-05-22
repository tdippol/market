package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;

import java.util.List;

public interface CanalePromozioneService extends DbPromoService<CanalePromozioneEntity> {

	List<CanalePromozioneEntity> findAllByGroup(GruppoPromozioneEntity group);

	List<CanalePromozioneEntity> findByGroupId(Long group);

	List<CanalePromozioneEntity> findByDescription(String description);

	CanalePromozioneEntity findByCodiceCanale(Long codiceCanale);

	List<CanalePromozioneEntity> findByCodiciCanale(List<Long> codiciCanale);

	List<CanalePromozioneEntity> findByFlagRateSingolaAttivita();

	Long countByIdWithTipologiaInitialLoad(Long id);
}
