package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiEventoRetailEntity;

import java.util.List;

public interface MuiEventoRetailDAO extends DbPromoDAO<MuiEventoRetailEntity> {
    List<EventoRetailDTO> findAllEager();
    EventoRetailDTO findByIdEager(Long id);
    List<MuiEventoRetailEntity> findByIdMacrospazio(Long idMacrospazio);
    long countByIdMacrospazio(Long idMacrospazio);
    List<EventoRetailDTO> findAllEagerByIdMacrospazio(Long idMacrospazio);
}
