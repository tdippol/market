package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgCanaliAttributiEntity;

import java.util.List;

public interface CfgCanaliAttributiDAO extends DbPromoDAO<CfgCanaliAttributiEntity>{
    String getListaByCanaleAndAttributo(Long idCanale, Long idAttributo);

    List<CfgCanaliAttributiEntity> getAllByCanale(Long idCanale);
}
