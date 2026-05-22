package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.service.MarchioPrivatoService;
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
public class MarchioPrivatoServiceImpl extends AbstractDbPromoService<MarchioPrivatoEntity> implements MarchioPrivatoService {
    private static final long serialVersionUID = -8629458042106715450L;

    @Inject
    @Getter
    private MarchioPrivatoDAO dao;

    public List<MarchioPrivatoEntity> findAll() {
        return getDao().findAll();
    }

    public MarchioPrivatoEntity findByCodice(String codice) {
        return getDao().findByCodice(codice);
    }

    @Override
    public List<MarchioPrivatoEntity> findByCodici(List<String> codici) {
        if (codici == null || codici.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return getDao().findByCodici(codici);
    }
}
