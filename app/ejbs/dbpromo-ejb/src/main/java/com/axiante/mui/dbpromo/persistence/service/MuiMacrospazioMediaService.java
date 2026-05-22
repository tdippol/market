package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.dto.MacrospazioWithEventsDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;

import java.util.List;

public interface MuiMacrospazioMediaService extends DbPromoService<MuiMacrospazioMediaEntity> {
    boolean existsByCodiceMacrospazio(String codiceMacrospazio);

    boolean existsByCodiceMacrospazio(String codiceMacrospazio, List<Long> excludedIds);

    boolean existsByDescrizioneMacrospazio(String descrizioneMacrospazio);

    boolean existsByDescrizioneMacrospazio(String descrizioneMacrospazio, List<Long> excludedIds);

    List<MacrospazioWithEventsDTO> findAllWithHasEvents();

    void confermaMacrospazi(String codiceUtente);
}
