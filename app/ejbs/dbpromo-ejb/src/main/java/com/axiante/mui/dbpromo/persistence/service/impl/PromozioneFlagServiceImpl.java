package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneFlagDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromozioneFlagEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozioneFlagService;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Default
@Transactional
public class PromozioneFlagServiceImpl extends AbstractDbPromoService<MuiPromozioneFlagEntity>
        implements PromozioneFlagService {
    private static final long serialVersionUID = 6196738631056389140L;

    @Inject
    @Getter
    PromozioneFlagDAO dao;
}
