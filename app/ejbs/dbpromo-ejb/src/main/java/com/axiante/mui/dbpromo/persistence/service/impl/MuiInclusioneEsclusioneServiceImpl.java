package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.TipoInclusioneEsclusione;
import com.axiante.mui.dbpromo.persistence.dao.MuiInclusioneEsclusioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiInclusioneEsclusioneEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiInclusioneEsclusioneService;
import lombok.Getter;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public class MuiInclusioneEsclusioneServiceImpl extends AbstractDbPromoService<MuiInclusioneEsclusioneEntity>
        implements MuiInclusioneEsclusioneService, Serializable {
    private static final long serialVersionUID = -137753605736631840L;

    @Inject
    @Getter
    private MuiInclusioneEsclusioneDAO dao;

    @Override
    public List<MuiInclusioneEsclusioneEntity> findByPromozione(Long idPromozione) {
        return getDao().findByPromozione(idPromozione);
    }

    @Override
    public List<MuiInclusioneEsclusioneEntity> findByPromozioneAndTipo(Long idPromozione, TipoInclusioneEsclusione tipo) {
        return getDao().findByPromozioneAndTipo(idPromozione, tipo);
    }

    @Override
    public Long countByPromozioneAndMeccanicaAndTipo(Long idPromozione, Long idMeccanica, TipoInclusioneEsclusione tipo) {
        return getDao().countByPromozioneAndMeccanicaAndTipo(idPromozione, idMeccanica, tipo);
    }
    @Override
    public Long countByPromozioneAndMeccanicaAndTipoAndTipoElemento(Long idPromozione, Long idMeccanica, TipoInclusioneEsclusione tipo, ElementType tipoElemento){
        return getDao().countByPromozioneAndMeccanicaAndTipoAndTipoElemento(idPromozione, idMeccanica, tipo, tipoElemento);
    }

    @Override
    public boolean runFunctionExportInclusioniEsclusioni(Long idPromozione, String username) throws Exception {
        return getDao().runFunctionExportInclusioniEsclusioni(idPromozione, username);
    }
}
