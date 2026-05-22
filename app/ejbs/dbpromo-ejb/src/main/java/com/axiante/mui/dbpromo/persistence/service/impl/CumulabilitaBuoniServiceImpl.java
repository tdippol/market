package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CumulabilitaBuoniDAO;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaBuoniEntity;
import com.axiante.mui.dbpromo.persistence.service.CumulabilitaBuoniService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;

@Dependent
@Transactional
public class CumulabilitaBuoniServiceImpl implements CumulabilitaBuoniService, Serializable {
    private static final long serialVersionUID = -4072725028804152414L;

    @Inject
    @Getter
    CumulabilitaBuoniDAO dao;

    public CumulabilitaBuoniEntity findByCodicePromozioneAndCodiceCanaleAndPrefissoBs(String codicePromozione,
                                                                                      String codiceCanale,
                                                                                      String prefissoBS) {
        return getDao().findByCodicePromozioneAndCodiceCanaleAndPrefissoBs(codicePromozione, codiceCanale, prefissoBS);
    }
}
