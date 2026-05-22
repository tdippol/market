package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgPianoMediaEntity;
import java.util.List;

public interface CfgPianoMediaService extends DbPromoService<CfgPianoMediaEntity> {
    List<CfgPianoMediaEntity> findAllAttivi();
}
