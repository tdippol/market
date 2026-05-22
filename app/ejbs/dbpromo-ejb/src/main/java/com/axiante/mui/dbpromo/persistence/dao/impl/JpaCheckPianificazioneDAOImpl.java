package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CheckPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CheckPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;
import lombok.NonNull;

public class JpaCheckPianificazioneDAOImpl extends JpaDbPromoDAO<CheckPianificazioneEntity>
        implements CheckPianificazioneDAO {
    private static final long serialVersionUID = 8710275433397423202L;

    @Override
    public List<CheckPianificazioneEntity> findAllByPromozioneAndCodiciCompratore(@NonNull PromozioneTestataEntity testata,
                                                                                  List<String> codiciCompratore) {
        if (codiciCompratore == null || codiciCompratore.isEmpty()) {
            throw new IllegalArgumentException("codiciCompratore cannot be null or empty");
        }
        return getEm().createNamedQuery("CheckPianificazioneEntity.findAllByPromozioneAndCodiciCompratore", CheckPianificazioneEntity.class)
                .setParameter("testata", testata)
                .setParameter("codiciCompratore", codiciCompratore)
                .getResultList();
    }
}
