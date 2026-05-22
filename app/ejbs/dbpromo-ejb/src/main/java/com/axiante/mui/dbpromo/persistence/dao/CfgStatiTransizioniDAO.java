package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiTransizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;

public interface CfgStatiTransizioniDAO extends DbPromoDAO<CfgStatiTransizioniEntity> {

    List<CfgStatiTransizioniEntity> findAllPromoTransitionByChannel(CanalePromozioneEntity canalePromozioneEntity);

    List<CfgStatiTransizioniEntity> findAllByCanaleAndStatusFromAndStatusTo(CanalePromozioneEntity channel,
                                                                            StatoPromozioneEntity fromStatus,
                                                                            StatoPromozioneEntity toStatus);

    boolean existAutomaticByCanaleAndFromStatus(CanalePromozioneEntity channel, StatoPromozioneEntity fromStatus);
}
