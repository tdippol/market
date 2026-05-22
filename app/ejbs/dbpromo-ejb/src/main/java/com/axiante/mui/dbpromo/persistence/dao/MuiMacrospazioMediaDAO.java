package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.dto.MacrospazioWithEventsDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;

import java.util.List;

public interface MuiMacrospazioMediaDAO extends DbPromoDAO<MuiMacrospazioMediaEntity> {
    Long countByCodiceMacrospazio(String codiceMacrospazio);

    Long countByCodiceMacrospazio(String codiceMacrospazio, List<Long> excludedIds);

    Long countByDescrizioneMacrospazio(String descrizioneMacrospazio);

    Long countByDescrizioneMacrospazio(String descrizioneMacrospazio, List<Long> excludedIds);

    List<MacrospazioWithEventsDTO> findAllWithHasEvents();

    void confermaMacrospazi(String codiceUtente);
}
