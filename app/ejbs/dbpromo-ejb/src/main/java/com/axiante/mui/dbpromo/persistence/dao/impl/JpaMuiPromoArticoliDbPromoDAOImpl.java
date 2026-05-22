package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiPromoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoArticoliDbPromoEntity;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaMuiPromoArticoliDbPromoDAOImpl implements MuiPromoArticoliDbPromoDAO {

    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public List<MuiPromoArticoliDbPromoEntity> findAll() {
        return getEm().createNamedQuery("MuiPromoArticoliDbPromoEntity.findAll", MuiPromoArticoliDbPromoEntity.class)
                .getResultList();
    }

    @Override
    public List<MuiPromoArticoliDbPromoEntity> findAllByCodicePromozione(@NonNull String codicePromozione) {
        return getEm().createNamedQuery("MuiPromoArticoliDbPromoEntity.findAllByCodicePromozione", MuiPromoArticoliDbPromoEntity.class)
                .setParameter("codicePromozione", codicePromozione)
                .getResultList();
    }
}
