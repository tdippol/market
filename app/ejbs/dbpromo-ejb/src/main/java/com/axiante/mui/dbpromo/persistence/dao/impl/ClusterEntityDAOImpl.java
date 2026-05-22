package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.ClusterEntityDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.ClusterClienteEntity;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

@DbPromoJpaDao
public class ClusterEntityDAOImpl implements ClusterEntityDAO {
    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    public List<ClusterClienteEntity> findAll() {
        return getEm().createNamedQuery("ClusterClienteEntity.findAll", ClusterClienteEntity.class)
                .getResultList();
    }

    public List<ClusterClienteEntity> findAllByIdCluster(String idCluster) {
        return getEm().createNamedQuery("ClusterClienteEntity.findAllByIdCluster", ClusterClienteEntity.class)
                .setParameter("idCluster", idCluster)
                .getResultList();
    }

    public List<ClusterClienteEntity> findByDataInizioAndDataFine(@NonNull Date dataInizio, Date dataFine) {
        return getEm().createNamedQuery("ClusterClienteEntity.findByDataInizioAndDataFine", ClusterClienteEntity.class)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();
    }
}
