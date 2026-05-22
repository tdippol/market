package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;
import javax.validation.constraints.NotNull;

public interface StatoPromozioneDAO extends DbPromoDAO<StatoPromozioneEntity> {
    List<StatoPromozioneEntity> findAllSorted();
    StatoPromozioneEntity findByCode(@NotNull String code);
}
