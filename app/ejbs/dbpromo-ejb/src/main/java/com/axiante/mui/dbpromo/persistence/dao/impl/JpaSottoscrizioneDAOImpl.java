package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.SottoscrizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.SottoscrizioneEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@DbPromoJpaDao
public class JpaSottoscrizioneDAOImpl extends JpaDbPromoDAO<SottoscrizioneEntity> implements SottoscrizioneDAO {
    private static final long serialVersionUID = 67223060073362913L;

    @Override
    public List<SottoscrizioneEntity> findAll() {
        return getEm().createNamedQuery("SottoscrizioneEntity.findAll", SottoscrizioneEntity.class)
                .getResultList();
    }
}
