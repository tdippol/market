package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.SupportoMediaCfgCheckDAO;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaCfgCheckEntity;
import com.axiante.mui.dbpromo.persistence.service.SupportoMediaCfgCheckService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class SupportoMediaCfgCheckServiceImpl extends AbstractDbPromoService<SupportoMediaCfgCheckEntity>
        implements SupportoMediaCfgCheckService {
    private static final long serialVersionUID = 5321035512161500719L;

    @Inject
    @Getter
    private SupportoMediaCfgCheckDAO dao;
}
