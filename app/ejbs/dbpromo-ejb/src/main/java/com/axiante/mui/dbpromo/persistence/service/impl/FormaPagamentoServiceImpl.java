package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.FormaPagamentoDAO;
import com.axiante.mui.dbpromo.persistence.entities.FormaPagamentoEntity;
import com.axiante.mui.dbpromo.persistence.service.FormaPagamentoService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Dependent
@Transactional
@Slf4j
public class FormaPagamentoServiceImpl extends AbstractDbPromoService<FormaPagamentoEntity>
        implements FormaPagamentoService {
    private static final long serialVersionUID = -9148340704644864990L;

    @Inject
    private FormaPagamentoDAO dao;

    @Override
    public List<FormaPagamentoEntity> findAllActive() {
        return dao.findAllActive();
    }

    @Override
    public FormaPagamentoEntity findByCode(String codice) {
        return dao.findByCode(codice);
    }
}
