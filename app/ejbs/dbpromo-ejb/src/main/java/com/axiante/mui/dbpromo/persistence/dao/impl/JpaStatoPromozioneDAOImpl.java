package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.StatoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;
import javax.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DbPromoJpaDao
public class JpaStatoPromozioneDAOImpl extends JpaDbPromoDAO<StatoPromozioneEntity> implements StatoPromozioneDAO {

    private static final long serialVersionUID = 5209692956161776568L;

    @Override
    public List<StatoPromozioneEntity> findAllSorted() {
        return getEm().createNamedQuery("StatoPromozioneEntity.findAllSorted", StatoPromozioneEntity.class)
                .getResultList();
    }

    @Override
    public StatoPromozioneEntity findByCode(String code) {
        try {
            return getEm().createNamedQuery("StatoPromozioneEntity.findByCode", StatoPromozioneEntity.class)
                .setParameter("code", code).getSingleResult();
        } catch (NoResultException ex) {
            log.info("StatoPromozione code [" + code + "] not found", ex);
            return null;
        }
    }
}
