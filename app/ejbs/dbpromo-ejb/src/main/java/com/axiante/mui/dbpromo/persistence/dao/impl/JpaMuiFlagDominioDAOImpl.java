package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiFlagDominioDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFlagDominioEntity;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaMuiFlagDominioDAOImpl  extends JpaDbPromoDAO<MuiFlagDominioEntity> implements MuiFlagDominioDAO {
    private static final long serialVersionUID = 6569784922396318866L;

    @Override
    public List<MuiFlagDominioEntity> findAllAttiviByFlag(@NonNull Long idFlag) {
        return getEm().createNamedQuery("MuiFlagDominioEntity.findAllAttiviByFlag", MuiFlagDominioEntity.class)
                .setParameter("flagId", idFlag)
                .getResultList();
    }

    @Override
    public List<MuiFlagDominioEntity> findAllAttiviAndDefaultByFlag(@NonNull Long idFlag) {
        return getEm().createNamedQuery("MuiFlagDominioEntity.findAllAttiviAndDefaultByFlag", MuiFlagDominioEntity.class)
                .setParameter("flagId", idFlag)
                .getResultList();
    }

}
