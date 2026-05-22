package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.TotalizzatoriDAO;
import com.axiante.mui.dbpromo.persistence.entities.TotalizzatoriEntity;
import java.util.List;

@DbPromoJpaDao
public class TotalizzatoriDAOImpl extends JpaDbPromoDAO<TotalizzatoriEntity> implements TotalizzatoriDAO {
    private static final long serialVersionUID = -930755854260226044L;

    @Override
    public List<TotalizzatoriEntity> findAllWithParentByIdIniziativa(Long idIniziativa) {
        return getEm().createNamedQuery("TotalizzatoriEntity.findAllWithParentByIdIniziativa", TotalizzatoriEntity.class)
                .setParameter("idIniziativa", idIniziativa)
                .getResultList();
    }
}
