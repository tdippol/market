package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.ObiettivoDAO;
import com.axiante.mui.dbpromo.persistence.entities.ObiettivoEntity;

import java.util.List;

@DbPromoJpaDao
public class JpaObiettivoDAOImpl extends JpaDbPromoDAO<ObiettivoEntity> implements ObiettivoDAO {
    private static final long serialVersionUID = -187789086447613735L;

    @Override
    public List<ObiettivoEntity> findAllByPromozione(Long idPromozione) {
        return getEm().createNamedQuery("ObiettivoEntity.findAllByPromozione", ObiettivoEntity.class)
                .setParameter("idPromozione", idPromozione)
                .getResultList();
    }
}
