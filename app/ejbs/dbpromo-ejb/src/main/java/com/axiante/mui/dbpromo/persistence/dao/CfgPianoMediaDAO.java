package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgPianoMediaEntity;
import java.util.List;

public interface CfgPianoMediaDAO extends DbPromoDAO<CfgPianoMediaEntity>{
    List<CfgPianoMediaEntity> findAllAttivi();
}
