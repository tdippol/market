package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.AttributoArticoloDAO;
import com.axiante.mui.dbpromo.persistence.entities.AttributoArticoloEntity;
import com.axiante.mui.dbpromo.persistence.service.AttributoArticoloService;
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
public class AttributoArticoloServiceImpl extends AbstractDbPromoService<AttributoArticoloEntity>
        implements AttributoArticoloService {
    private static final long serialVersionUID = -7758012932353809015L;

    @Inject
    private AttributoArticoloDAO dao;

    @Override
    public List<AttributoArticoloEntity> findAllActive() {
        return dao.findAllActive();
    }
}
