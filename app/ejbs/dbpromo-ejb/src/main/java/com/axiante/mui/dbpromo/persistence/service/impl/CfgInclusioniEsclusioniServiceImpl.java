package com.axiante.mui.dbpromo.persistence.service.impl;


import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.persistence.dao.CfgInclusioniEsclusioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgInclusioniEsclusioniEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgInclusioniEsclusioniService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Dependent
@Transactional
@Slf4j
public class CfgInclusioniEsclusioniServiceImpl extends AbstractDbPromoService<CfgInclusioniEsclusioniEntity>
        implements CfgInclusioniEsclusioniService {
    private static final long serialVersionUID = -4458580766498234961L;

    @Inject
    @Getter
    CfgInclusioniEsclusioniDAO dao;

    @Override
    public List<Long> findAllIdMeccanica() {
        return getDao().findAllIdMeccanica();
    }

    @Override
    public CfgInclusioniEsclusioniEntity findByMeccanicaAndTipoElemento(Long idMeccanica, ElementType tipoElemento) {
        return getDao().findByMeccanicaAndTipoElemento(idMeccanica, tipoElemento);
    }

    @Override
    public List<CfgInclusioniEsclusioniEntity> findByMeccanica(Long idMeccanica) {
        List<CfgInclusioniEsclusioniEntity> result = getDao().findByMeccanica(idMeccanica);
        return result != null ? result : Collections.emptyList();
    }
}
