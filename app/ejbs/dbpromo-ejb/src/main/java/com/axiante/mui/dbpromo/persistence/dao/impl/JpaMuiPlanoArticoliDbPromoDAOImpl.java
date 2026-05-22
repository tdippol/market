package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiPlanoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoArticoliDbPromoEntity;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaMuiPlanoArticoliDbPromoDAOImpl implements MuiPlanoArticoliDbPromoDAO {

    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public List<MuiPlanoArticoliDbPromoEntity> findAll() {
        return getEm().createNamedQuery("MuiPlanoArticoliDbPromoEntity.findAll", MuiPlanoArticoliDbPromoEntity.class)
                .getResultList();
    }

    @Override
    public List<MuiPlanoArticoliDbPromoEntity> findAllByIdPlano(@NonNull String idPlano) {
        return getEm().createNamedQuery("MuiPlanoArticoliDbPromoEntity.findAllByIdPlano", MuiPlanoArticoliDbPromoEntity.class)
                .setParameter("idPlano", idPlano)
                .getResultList();
    }
    
    @Override
    public List<MuiPlanoArticoliDbPromoEntity> findAllByIdPlano(@NonNull List<String> idPlano) {
        if (idPlano.isEmpty()) {
            return Collections.emptyList();
        }
        return getEm().createNamedQuery("MuiPlanoArticoliDbPromoEntity.findAllByIdPlanos", MuiPlanoArticoliDbPromoEntity.class)
                .setParameter("idPlanos", idPlano)
                .getResultList();
    }

}
