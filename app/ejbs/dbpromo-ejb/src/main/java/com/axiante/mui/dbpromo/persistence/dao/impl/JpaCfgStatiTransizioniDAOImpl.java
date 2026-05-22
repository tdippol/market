package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgStatiTransizioniDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiTransizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaCfgStatiTransizioniDAOImpl extends JpaDbPromoDAO<CfgStatiTransizioniEntity>
        implements CfgStatiTransizioniDAO {
    private static final long serialVersionUID = -3435757075146934793L;

    @Override
    public List<CfgStatiTransizioniEntity> findAllPromoTransitionByChannel(
            CanalePromozioneEntity canalePromozioneEntity) {
        if (canalePromozioneEntity != null) {
            return getEm().createNamedQuery("CfgStatiTransizioniEntity.findAllByIdCanale", CfgStatiTransizioniEntity.class)
                    .setParameter("muiCanalePromozione", canalePromozioneEntity).getResultList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<CfgStatiTransizioniEntity> findAllByCanaleAndStatusFromAndStatusTo(CanalePromozioneEntity channel,
                                                                                   StatoPromozioneEntity fromStatus,
                                                                                   StatoPromozioneEntity toStatus) {
        return getEm()
                .createNamedQuery("CfgStatiTransizioneEntity.findByCanaleAndStatusFromAndStatusTo",
                        CfgStatiTransizioniEntity.class)
                .setParameter("channel", channel)
                .setParameter("fromStatus", fromStatus)
                .setParameter("toStatus", toStatus)
                .getResultList();
    }

    @Override
    public boolean existAutomaticByCanaleAndFromStatus(@NonNull CanalePromozioneEntity channel,
                                                       @NonNull StatoPromozioneEntity fromStatus) {
        final List<CfgStatiTransizioniEntity> result = getEm()
                .createNamedQuery("CfgStatiTransizioneEntity.findByCanaleAndFromStatusAndFlagAutomatico",
                        CfgStatiTransizioniEntity.class)
                .setParameter("channel", channel)
                .setParameter("fromStatus", fromStatus)
                .setParameter("flagAutomatico", Boolean.TRUE)
                .getResultList();
        return result != null && !result.isEmpty();
    }
}
