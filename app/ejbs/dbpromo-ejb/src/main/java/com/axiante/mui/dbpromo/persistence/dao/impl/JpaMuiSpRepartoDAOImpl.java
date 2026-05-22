package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiSpRepartoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpRepartoEntity;

import java.util.List;

@DbPromoJpaDao
public class JpaMuiSpRepartoDAOImpl extends JpaDbPromoDAO<MuiSpRepartoEntity> implements MuiSpRepartoDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public List<MuiSpRepartoEntity> findByPromozioneId(Long idPromozione) {
        return getEm()
                .createNamedQuery("MuiSpRepartoEntity.findByPromozioneId", MuiSpRepartoEntity.class)
                .setParameter("idPromozione", idPromozione)
                .getResultList();
    }

    @Override
    public MuiSpRepartoEntity findByPromozioneIdAndRepartoId(Long idPromozione, Long idReparto) {
        List<MuiSpRepartoEntity> result = getEm()
                .createNamedQuery("MuiSpRepartoEntity.findByPromozioneIdAndRepartoId", MuiSpRepartoEntity.class)
                .setParameter("idPromozione", idPromozione)
                .setParameter("idReparto", idReparto)
                .getResultList();

        return result != null && !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public MuiSpRepartoEntity update(MuiSpRepartoEntity entity) {
        return getEm().merge(entity);
    }
}