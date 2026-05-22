package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.SottoscrizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.SottoscrizioneEntity;
import com.axiante.mui.dbpromo.persistence.service.SottoscrizioneService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
@Transactional
public class SottoscrizioneServiceImpl extends AbstractDbPromoService<SottoscrizioneEntity>
        implements SottoscrizioneService {
    private static final long serialVersionUID = 7708964784235063582L;

    @Inject
    @Getter
    private SottoscrizioneDAO dao;

    @Override
    public List<SottoscrizioneEntity> findAll() {
        return getDao().findAll();
    }
}
