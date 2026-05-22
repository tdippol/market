package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.SottoclasseEntity;
import lombok.NonNull;

import java.util.List;

public interface SottoclasseDAO extends DbPromoDAO<SottoclasseEntity> {
    List<SottoclasseEntity> findAll();

    SottoclasseEntity findById(@NonNull Long id);

    boolean existsByCodeOrDescription(@NonNull String code, @NonNull String description);

    boolean runFunctionPubblicaSottoclassi(@NonNull String username) throws Exception;

    Long countSottoclassiNonScaricate();
}
