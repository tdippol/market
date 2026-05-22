package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromoFatturazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneEntity;
import lombok.NonNull;

import java.util.List;

@DbPromoJpaDao
public class JpaPromoFatturazioneDAOImpl extends JpaDbPromoDAO<PromoFatturazioneEntity>
        implements PromoFatturazioneDAO {
    private static final long serialVersionUID = 5124889604135608350L;

    @Override
    public List<PromoFatturazioneEntity> findAllByIdsCompratori(@NonNull List<Long> idsCompratori) {
        if (idsCompratori.isEmpty()) {
            throw new IllegalArgumentException("idsCompratori cannot be null or empty");
        }
        return getEm().createNamedQuery("PromoFatturazioneEntity.findAllByIdsCompratori", PromoFatturazioneEntity.class)
                .setParameter("idsCompratori", idsCompratori)
                .getResultList();
    }

    @Override
    public List<PromoFatturazioneEntity> findAllByCodiciCompratori(@NonNull List<String> codiciCompratori) {
        if (codiciCompratori.isEmpty()) {
            throw new IllegalArgumentException("codiciCompratori cannot be null or empty");
        }
        return getEm().createNamedQuery("PromoFatturazioneEntity.findAllByCodiciCompratori", PromoFatturazioneEntity.class)
                .setParameter("codiciCompratori", codiciCompratori)
                .getResultList();
    }

    @Override
    public List<PromoFatturazioneEntity> findAllByIdCompratoreAndIdPromozione(@NonNull Long idCompratore,
                                                                              @NonNull Long idPromozione) {
        return getEm().createNamedQuery("PromoFatturazioneEntity.findAllByIdCompratoreAndIdPromozione", PromoFatturazioneEntity.class)
                .setParameter("idCompratore", idCompratore)
                .setParameter("idPromozione", idPromozione)
                .getResultList();
    }
}
