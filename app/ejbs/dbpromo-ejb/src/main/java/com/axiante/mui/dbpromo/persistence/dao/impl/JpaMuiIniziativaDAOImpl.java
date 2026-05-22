package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiIniziativaDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiIniziativaEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaMuiIniziativaDAOImpl implements MuiIniziativaDAO, Serializable {
    private static final long serialVersionUID = -786503122185952534L;

    @Inject
    @Getter(value = AccessLevel.PROTECTED)
    @DbPromo
    private EntityManager em;

    @Override
    public List<MuiIniziativaEntity> findAllByDataInizioAndDataFine(@NonNull Date dataInizio, @NonNull Date dataFine) {
        return em.createNamedQuery("MuiIniziativaEntity.findAllByDataInizioAndDataFine", MuiIniziativaEntity.class)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();
    }
}
