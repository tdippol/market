package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgStatiTransizioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiTransizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgStatiTransizioniService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class CfgStatiTransizioniServiceImpl extends AbstractDbPromoService<CfgStatiTransizioniEntity>
        implements CfgStatiTransizioniService {
    private static final long serialVersionUID = -6087824233630839223L;

    @Inject
    @Getter
    private CfgStatiTransizioniDAO dao;

    @Override
    public List<CfgStatiTransizioniEntity> findAllByCanaleAndStatusFromAndStatusTo(CanalePromozioneEntity channel,
                                                                                   StatoPromozioneEntity fromStatus,
                                                                                   StatoPromozioneEntity toStatus) {
        return dao.findAllByCanaleAndStatusFromAndStatusTo(channel, fromStatus, toStatus);
    }

    @Override
    public boolean existAutomaticByCanaleAndFromStatus(@NonNull CanalePromozioneEntity channel,
                                                       @NonNull StatoPromozioneEntity fromStatus) {
        return dao.existAutomaticByCanaleAndFromStatus(channel, fromStatus);
    }
}
