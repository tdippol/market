package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.SottoclasseEntity;
import lombok.NonNull;

import java.util.List;

public interface SottoclasseService extends DbPromoService<SottoclasseEntity> {
    List<SottoclasseEntity> findAll();

    SottoclasseEntity findById(@NonNull Long id);

    boolean existsByCodeOrDescription(@NonNull String code, @NonNull String description);

    boolean runFunctionPubblicaSottoclassi(@NonNull String username) throws Exception;

    Long countSottoclassiNonScaricate();
}
