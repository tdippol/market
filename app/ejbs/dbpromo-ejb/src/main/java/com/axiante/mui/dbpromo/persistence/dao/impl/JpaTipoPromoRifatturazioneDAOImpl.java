package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.TipoPromoRifatturazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.TipoPromoRifatturazioneEntity;
import lombok.AccessLevel;
import lombok.Getter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@DbPromoJpaDao
public class JpaTipoPromoRifatturazioneDAOImpl implements TipoPromoRifatturazioneDAO {
    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public List<TipoPromoRifatturazioneEntity> findAll() {
        return getEm().createNamedQuery("TipoPromoRifatturazioneEntity.findAll", TipoPromoRifatturazioneEntity.class)
                .getResultList();
    }
}
