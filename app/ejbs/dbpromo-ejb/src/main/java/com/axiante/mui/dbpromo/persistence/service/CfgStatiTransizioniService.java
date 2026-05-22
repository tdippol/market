package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiTransizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;

public interface CfgStatiTransizioniService extends DbPromoService<CfgStatiTransizioniEntity> {
    List<CfgStatiTransizioniEntity> findAllByCanaleAndStatusFromAndStatusTo(CanalePromozioneEntity channel,
                                                                            StatoPromozioneEntity fromStatus,
                                                                            StatoPromozioneEntity toStatus);
    boolean existAutomaticByCanaleAndFromStatus(CanalePromozioneEntity channel, StatoPromozioneEntity fromStatus);
}
