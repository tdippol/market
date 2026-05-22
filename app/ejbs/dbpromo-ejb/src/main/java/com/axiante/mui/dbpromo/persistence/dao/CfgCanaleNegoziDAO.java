package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleNegoziEntity;
import java.util.List;

public interface CfgCanaleNegoziDAO extends DbPromoDAO<CanalePromozioneEntity> {
    List<CfgCanaleNegoziEntity> findAllByIdCanale(CanalePromozioneEntity canalePromozioneEntity);
}
