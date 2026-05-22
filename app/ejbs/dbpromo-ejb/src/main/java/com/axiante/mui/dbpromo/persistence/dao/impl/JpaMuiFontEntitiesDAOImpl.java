package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiFontEntitiesDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.Getter;

@DbPromoJpaDao
public class JpaMuiFontEntitiesDAOImpl implements MuiFontEntitiesDAO {

    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    /**
     * @param id
     * @return
     */
    @Override
    public MuiFontEntities findById(String id) {
        return getEm().createNamedQuery("MuiFontEntities.find", MuiFontEntities.class).setParameter("id", id).getSingleResult();
    }

    /**
     * @return
     */
    @Override
    public List<MuiFontEntities> findAll() {
        return getEm().createNamedQuery("MuiFontEntities.findAll", MuiFontEntities.class).getResultList();
    }
}
