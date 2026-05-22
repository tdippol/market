package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.StatoContributiDAO;
import com.axiante.mui.dbpromo.persistence.entities.ContributiStatoAnagraficaEntity;
import com.axiante.mui.dbpromo.persistence.service.StatoContributiService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class StatoContributiServiceImpl extends AbstractDbPromoService<ContributiStatoAnagraficaEntity>
        implements StatoContributiService {
    private static final long serialVersionUID = 1983535347301744143L;

    @Inject
    @Getter
    StatoContributiDAO dao;

    @Override
    public String findDescrizioneByCodice(String codice) {
        return dao.findDescrizioneByCodice(codice);
    }
}
