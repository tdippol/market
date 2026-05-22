package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import java.util.List;

public interface CanaleLastProgEntityDAO extends DbPromoDAO<CanaleLastProgEntity> {
	CanaleLastProgEntity getByYearAndChannel(String anno, CanalePromozioneEntity canalePromozioneEntity);

	List<CanaleLastProgEntity> findByChannel(CanalePromozioneEntity canalePromozioneEntity);

	@Override
	List<CanaleLastProgEntity> findEmptyUuid();
}
