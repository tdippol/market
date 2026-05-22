package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiSpStatiTransizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatiTransizioneEntity;

import java.util.List;

@DbPromoJpaDao
public class JpaMuiSpStatiTransizioneDAOImpl
        extends JpaDbPromoDAO<MuiSpStatiTransizioneEntity>
        implements MuiSpStatiTransizioneDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public List<MuiSpStatiTransizioneEntity> findByPromozioneAndFromStato(Long idPromozione, Long idStato) {
        return getEm().createNamedQuery(
                        "MuiSpStatiTransizioneEntity.findByPromozioneAndFromStato",
                        MuiSpStatiTransizioneEntity.class)
                .setParameter("idPromozione", idPromozione)
                .setParameter("idStato", idStato)
                .getResultList();
    }
}