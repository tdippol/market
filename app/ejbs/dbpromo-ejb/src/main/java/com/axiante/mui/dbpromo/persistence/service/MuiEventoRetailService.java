package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiEventoRetailEntity;

import java.util.List;
import java.util.function.Function;

public interface MuiEventoRetailService extends DbPromoService<MuiEventoRetailEntity> {
    List<EventoRetailDTO> getAll();

    List<EventoRetailDTO> findAllByIdMacrospazio(Long idMacrospazio);

    Long countByIdMacrospazio(Long idMacrospazio);

    EventoRetailDTO findEagerById(Long id);

    Function<EventoRetailDTO, MuiEventoRetailEntity> dtoToEntityMapper = dto -> {
        MuiEventoRetailEntity entity = new MuiEventoRetailEntity();
        entity.setId(dto.getId());
        entity.setCodiceFornitore(dto.getCodiceFornitore());
        entity.setCodiceCausale(dto.getCodiceCausale());
        entity.setDataInizio(dto.getDataInizio());
        entity.setDataFine(dto.getDataFine());
        entity.setValoreContributo(dto.getValoreContributo());
        entity.setNote(dto.getNote());
        entity.setCausaleDescription(dto.getCausaleDescription());
        entity.setFornitoreDescription(dto.getFornitoreDescription());
        return entity;
    };
}
