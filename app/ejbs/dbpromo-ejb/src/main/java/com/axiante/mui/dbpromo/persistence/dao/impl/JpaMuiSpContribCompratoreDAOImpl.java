package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiSpContribCompratoreDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreEntity;

import javax.persistence.NoResultException;
import java.util.List;

@DbPromoJpaDao
public class JpaMuiSpContribCompratoreDAOImpl extends JpaDbPromoDAO<MuiSpContribCompratoreEntity> implements MuiSpContribCompratoreDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public List<MuiSpContribCompratoreEntity> findByPromozioneId(Long idPromozione) {
        return getEm()
                .createNamedQuery("MuiSpContribCompratoreEntity.findByPromozioneId", MuiSpContribCompratoreEntity.class)
                .setParameter("idPromozione", idPromozione)
                .getResultList();
    }

    @Override
    public MuiSpContribCompratoreEntity findByPromozioneIdAndCompratoreId(Long idPromozione, Long idCompratore) {
        try {
            return getEm()
                    .createNamedQuery("MuiSpContribCompratoreEntity.findByPromozioneIdAndCompratoreId", MuiSpContribCompratoreEntity.class)
                    .setParameter("idPromozione", idPromozione)
                    .setParameter("idCompratore", idCompratore)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}