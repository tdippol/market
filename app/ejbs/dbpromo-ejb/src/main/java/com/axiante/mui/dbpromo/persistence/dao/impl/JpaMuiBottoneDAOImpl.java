package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiBottoneDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@DbPromoJpaDao
@Slf4j
public class JpaMuiBottoneDAOImpl extends JpaDbPromoDAO<MuiBottoneEntity> implements MuiBottoneDAO {
    private static final long serialVersionUID = 8756834605576225988L;

    @Override
    public List<MuiBottoneEntity> findAll() {
        return getEm().createNamedQuery("MuiBottoneEntity.findAll", MuiBottoneEntity.class).getResultList();
    }

    @Override
    public MuiBottoneEntity findById(Long id) {
        Long count = getEm().createNamedQuery("MuiBottoneEntity.countById", Long.class)
                .setParameter("id", id).getSingleResult();
        if (count != null && count == 1) {
            return getEm().createNamedQuery("MuiBottoneEntity.findById", MuiBottoneEntity.class)
                    .setParameter("id", id).getSingleResult();
        }
        if (count == null || count == 0) {
            log.warn("No result found for 'MuiBottone' with id: {}", id);
        } else {
            log.error("Found {} result for 'MuiBottone' with id: {}", count, id);
        }
        return null;
    }
}
