package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiEventoRetailDAO;
import com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiEventoRetailEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiEventoRetailService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
@Transactional
public class MuiEventoRetailServiceImpl extends AbstractDbPromoService<MuiEventoRetailEntity>
        implements MuiEventoRetailService {
    private static final long serialVersionUID = 7205359859945794500L;
    @Inject
    @Getter
    MuiEventoRetailDAO dao;

    @Override
    public List<EventoRetailDTO> getAll() {
        return dao.findAllEager();
    }

    @Override
    public List<EventoRetailDTO> findAllByIdMacrospazio(Long idMacrospazio) {
        return dao.findAllEagerByIdMacrospazio(idMacrospazio);
    }

    @Override
    public Long countByIdMacrospazio(Long idMacrospazio) {
        return getDao().countByIdMacrospazio(idMacrospazio);
    }

    @Override
    public EventoRetailDTO findEagerById(Long id) {
        return dao.findByIdEager(id);
    }

}
