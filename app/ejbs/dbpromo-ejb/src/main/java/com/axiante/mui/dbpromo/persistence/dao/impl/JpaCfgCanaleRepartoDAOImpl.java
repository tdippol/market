package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleRepartoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleReparto;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import java.util.List;

public class JpaCfgCanaleRepartoDAOImpl extends JpaDbPromoDAO<CfgCanaleReparto> implements CfgCanaleRepartoDAO {
    private static final long serialVersionUID = -1704571478972818862L;

    @Override
    public List<CfgCanaleReparto> findByCanale(CanalePromozioneEntity canale) {
        return getEm().createNamedQuery("CfgCanaleReparto.findByCanale", CfgCanaleReparto.class)
                .setParameter("canale", canale)
                .getResultList();
    }

    @Override
    public CfgCanaleReparto findByCanaleAndReparto(CanalePromozioneEntity canale, RepartoEntity reparto) {
        return getEm().createNamedQuery("CfgCanaleReparto.findByCanaleAndReparto", CfgCanaleReparto.class)
                .setParameter("canale", canale)
                .setParameter("reparto", reparto)
                .getSingleResult();
    }
}
