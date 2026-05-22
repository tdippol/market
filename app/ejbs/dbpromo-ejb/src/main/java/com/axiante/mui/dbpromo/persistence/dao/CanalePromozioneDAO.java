package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import java.util.List;

public interface CanalePromozioneDAO extends DbPromoDAO<CanalePromozioneEntity> {

	List<CanalePromozioneEntity> findAllByGroup(GruppoPromozioneEntity group);

	List<CanalePromozioneEntity> findByDescription(String description);

	List<CanalePromozioneEntity> findByGroupId(Long id);

	CanalePromozioneEntity findByCodiceCanale(Long codiceCanale);

	List<CanalePromozioneEntity> findByCodiciCanale(List<Long> codiciCanale);

	Long countByIdWithTipologiaInitialLoad(Long id);

}
