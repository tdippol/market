package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MeccanicheDAO;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.service.MeccanicaService;
import lombok.Getter;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Default
@Transactional
public class MeccanicaServiceImpl extends AbstractDbPromoService<MeccanicheEntity>
        implements MeccanicaService {
    private static final long serialVersionUID = 4725473102676383928L;

    @Inject
    @Getter
    MeccanicheDAO dao;

    @Override
    public MeccanicheEntity findByCodice(String codice) {
        return dao.findByCodice(codice);
    }
}
