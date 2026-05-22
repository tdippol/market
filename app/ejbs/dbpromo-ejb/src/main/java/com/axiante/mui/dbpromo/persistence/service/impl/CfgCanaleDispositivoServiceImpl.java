package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleDispositivoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleDispositivoService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class CfgCanaleDispositivoServiceImpl extends AbstractDbPromoService<CfgCanaleDispositivoEntity>
        implements CfgCanaleDispositivoService {
    private static final long serialVersionUID = 5442762601271394073L;

    @Inject
    @Getter
    private CfgCanaleDispositivoDAO dao;

    @Override
    public CfgCanaleDispositivoEntity findByCode(@NonNull String code) {
        return getDao().findByCodice(code);
    }
}
