package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiSpParametroDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpParametroEntity;

import javax.persistence.NoResultException;
import java.util.List;

@DbPromoJpaDao
public class JpaMuiSpParametroDAOImpl extends JpaDbPromoDAO<MuiSpParametroEntity> implements MuiSpParametroDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public List<MuiSpParametroEntity> findByPromozioneId(Long idPromozione) {
        return getEm()
                .createNamedQuery("MuiSpParametroEntity.findByPromozioneId", MuiSpParametroEntity.class)
                .setParameter("idPromozione", idPromozione)
                .getResultList();
    }

    @Override
    public MuiSpParametroEntity findByPromozioneIdAndCompratoreAndFornitore(Long idPromozione,
                                                                            Long idCompratore,
                                                                            Long idFornitore) {
        try {
            return getEm()
                    .createNamedQuery("MuiSpParametroEntity.findByPromozioneIdAndCompratoreAndFornitore", MuiSpParametroEntity.class)
                    .setParameter("idPromozione", idPromozione)
                    .setParameter("idCompratore", idCompratore)
                    .setParameter("idFornitore", idFornitore)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}