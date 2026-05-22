package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleFlagEntity;
import java.util.List;

public interface CfgCanaleFlagDAO extends DbPromoDAO<CfgCanaleFlagEntity> {
    List<CfgCanaleFlagEntity> findActiveByChannel(Long idCanale);

}
