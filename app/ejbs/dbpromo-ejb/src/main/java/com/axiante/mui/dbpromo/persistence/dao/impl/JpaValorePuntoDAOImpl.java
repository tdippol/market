package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.ValorePuntoDAO;
import com.axiante.mui.dbpromo.persistence.entities.ValorePuntoEntity;
import java.util.Date;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaValorePuntoDAOImpl extends JpaDbPromoDAO<ValorePuntoEntity> implements ValorePuntoDAO {
    private static final long serialVersionUID = 610492058945381391L;

    @Override
    public List<ValorePuntoEntity> findWhereDate(@NonNull Date date) {
        return getEm().createNamedQuery("ValorePuntoEntity.findWhereDate", ValorePuntoEntity.class)
                .setParameter("date", date)
                .getResultList();
    }
}
