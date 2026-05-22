package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiEventoRetailDAO;
import com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiEventoRetailEntity;
import lombok.AccessLevel;
import lombok.Getter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@DbPromoJpaDao
public class JpaMuiEventoRetailDAOImpl extends JpaDbPromoDAO<MuiEventoRetailEntity>
        implements MuiEventoRetailDAO {
    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public List<EventoRetailDTO> findAllEager() {
        return getEm()
                .createNamedQuery("MuiEventoRetailEntity.allEagerWithDescription", EventoRetailDTO.class)
                .getResultList();
    }


    @Override
    public List<MuiEventoRetailEntity> findByIdMacrospazio(Long idMacrospazio) {
        return getEm()
                .createNamedQuery("MuiEventoRetailEntity.findByIdMacrospazio", MuiEventoRetailEntity.class)
                .setParameter("idMacrospazio", idMacrospazio)
                .getResultList();
    }

    @Override
    public long countByIdMacrospazio(Long idMacrospazio) {
        return getEm()
                .createNamedQuery("MuiEventoRetailEntity.countByIdMacrospazio", Long.class)
                .setParameter("idMacrospazio", idMacrospazio)
                .getSingleResult();
    }

    @Override
    public List<EventoRetailDTO> findAllEagerByIdMacrospazio(Long idMacrospazio) {
        return getEm()
                .createNamedQuery(
                        "MuiEventoRetailEntity.findEagerByIdMacrospazio", EventoRetailDTO.class)
                .setParameter("idMacrospazio", idMacrospazio)
                .getResultList();
    }

    @Override
    public EventoRetailDTO findByIdEager(Long id) {
        return getEm()
                .createNamedQuery(
                        "MuiEventoRetailEntity.findEagerById", EventoRetailDTO.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
