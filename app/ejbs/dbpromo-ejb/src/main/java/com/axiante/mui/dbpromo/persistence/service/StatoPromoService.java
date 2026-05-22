package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;
import javax.validation.constraints.NotNull;

public interface StatoPromoService extends DbPromoService<StatoPromozioneEntity> {
	List<StatoPromozioneEntity> findAllSorted() throws Exception;
	StatoPromozioneEntity findByCode(@NotNull String code) throws Exception;
}
