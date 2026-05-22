package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import com.axiante.mui.dbpromo.business.enumeration.OperazioneCumulabilita;
import com.axiante.mui.dbpromo.persistence.dao.CumulabilitaEsclusivitaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import com.axiante.mui.dbpromo.persistence.service.CumulabilitaEsclusivitaService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Default
@Transactional
public class CumulabilitaEsclusivitaServiceImpl extends AbstractDbPromoService<CumulabilitaEsclusivitaEntity>
        implements CumulabilitaEsclusivitaService {
    private static final long serialVersionUID = -8769927392931419499L;

    @Inject
    @Getter
    private CumulabilitaEsclusivitaDAO dao;

    @Override
    public List<CumulabilitaEsclusivitaEntity> findByType(CumulabilitaType type) {
        return dao.findByType(type);
    }

    @Override
    public List<CumulabilitaEsclusivitaEntity> findOpenByType(CumulabilitaType type) {
        return dao.findOpenByType(type);
    }

    @Override
    public List<CumulabilitaEsclusivitaEntity> findOverlapping(CumulabilitaEsclusivitaEntity entity) {
        return getDao().findOverlapping(entity);
    }

    @Override
    public boolean exportCumulabilita(@NonNull Long idCumulabilita, @NonNull String codiceUtente) {
        return getDao().exportCumulabilita(idCumulabilita, codiceUtente);
    }

    @Override
    public boolean updateCumulabilita(Long idCumulabilita, OperazioneCumulabilita operazione, String codiceUtente) {
        return getDao().updateCumulabilita(idCumulabilita, operazione, codiceUtente);
    }
}
