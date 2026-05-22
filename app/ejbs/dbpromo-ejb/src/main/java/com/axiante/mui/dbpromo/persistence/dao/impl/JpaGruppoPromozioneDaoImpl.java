package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.GruppoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import java.util.List;

@DbPromoJpaDao
public class JpaGruppoPromozioneDaoImpl extends JpaDbPromoDAO<GruppoPromozioneEntity> implements GruppoPromozioneDAO {
    private static final long serialVersionUID = 6981660614808868347L;

    @Override
    public List<GruppoPromozioneEntity> findAllByCodiciCanale(List<Long> codiciCanale) {
        if (codiciCanale != null && !codiciCanale.isEmpty()) {
            return getEm().createNamedQuery("GruppoPromozioneEntity.findAllByCodiciCanale", GruppoPromozioneEntity.class)
                    .setParameter("codiciCanale", codiciCanale)
                    .getResultList();
        } else {
            throw new IllegalArgumentException("codiciCanale cannot be null or empty");
        }
    }
}
