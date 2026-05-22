package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleFlagDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleFlagEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleFlagService;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Default
@Transactional
public class CfgCanaleFlagServiceImpl extends AbstractDbPromoService<CfgCanaleFlagEntity> implements CfgCanaleFlagService {
    private static final long serialVersionUID = 7002287951637387251L;

    @Inject
    @Getter
    private CfgCanaleFlagDAO dao;

    @Override
    public List<CfgCanaleFlagEntity> findActiveByChannel(@NonNull Long canale) {
        return getDao().findActiveByChannel(canale);
    }
}
