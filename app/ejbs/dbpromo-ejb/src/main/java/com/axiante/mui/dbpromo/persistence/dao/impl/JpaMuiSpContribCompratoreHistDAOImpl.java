package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiSpContribCompratoreHistDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreHistEntity;

import java.util.List;

@DbPromoJpaDao
public class JpaMuiSpContribCompratoreHistDAOImpl extends JpaDbPromoDAO<MuiSpContribCompratoreHistEntity>
        implements MuiSpContribCompratoreHistDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public List<MuiSpContribCompratoreHistEntity> findByPromozioneId(Long idPromozione) {
        return getEm()
                .createNamedQuery("MuiSpContribCompratoreHistEntity.findByPromozioneId", MuiSpContribCompratoreHistEntity.class)
                .setParameter("idPromozione", idPromozione)
                .getResultList();
    }
}