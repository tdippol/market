package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.TipoPromoRifatturazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.TipoPromoRifatturazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.TipoPromoRifatturazioneService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Dependent
@Transactional
public class TipoPromoRifatturazioneServiceImpl implements TipoPromoRifatturazioneService, Serializable {
    private static final long serialVersionUID = 4550308321686019149L;

    @Inject
    @Getter
    private TipoPromoRifatturazioneDAO dao;

    @Override
    public List<TipoPromoRifatturazioneEntity> findAll() {
        return dao.findAll();
    }
}
