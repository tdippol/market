package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleFlagEntity;
import java.util.List;

public interface CfgCanaleFlagService extends DbPromoService<CfgCanaleFlagEntity> {
    List<CfgCanaleFlagEntity> findActiveByChannel(Long canale);
}
