package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiSottoclasseDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSottoclasseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@DbPromoJpaDao
@Slf4j
public class JpaMuiSottoclasseDAOImpl implements MuiSottoclasseDAO, Serializable {
    private static final long serialVersionUID = 6865956980536955539L;

    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public MuiSottoclasseEntity findById(@NonNull String id) {
        return em.find(MuiSottoclasseEntity.class, id);
    }

    @Override
    public MuiSottoclasseEntity findByCode(String codice) {
        return findById(codice);
    }

    @Override
    public MuiSottoclasseEntity findActiveByCode(String codice) {
        Long count = em.createNamedQuery("MuiSottoclasseEntity.countActiveByCode", Long.class)
                .setParameter("codice", codice).getSingleResult();
        if (count != null && count == 1) {
            return em.createNamedQuery("MuiSottoclasseEntity.findActiveByCode", MuiSottoclasseEntity.class)
                    .setParameter("codice", codice).getSingleResult();
        }
        if (count == null || count == 0) {
            log.warn("No result found for 'sottoclasse' {}", codice);
        } else if (count > 1) {
            log.warn("More than one result found for 'sottoclasse' {}", codice);
        }
        return null;
    }

    @Override
    public List<MuiSottoclasseEntity> findAll() {
        return em.createNamedQuery("MuiSottoclasseEntity.findAll", MuiSottoclasseEntity.class).getResultList();
    }

    @Override
    public List<MuiSottoclasseEntity> findAllAttive() {
        return em.createNamedQuery("MuiSottoclasseEntity.findAllAttive", MuiSottoclasseEntity.class).getResultList();
    }
}
