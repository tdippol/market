package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiSpStatiTransizioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSpStatiTransizioneService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
@Transactional
public class MuiSpStatiTransizioneServiceImpl
        extends AbstractDbPromoService<MuiSpStatiTransizioneEntity>
        implements MuiSpStatiTransizioneService {

    private static final long serialVersionUID = 1L;

    @Inject
    @Getter
    private MuiSpStatiTransizioneDAO dao;

    @Override
    public List<MuiSpStatiTransizioneEntity> findByPromozioneAndFromStato(Long idPromozione, Long idStato) {
        return getDao().findByPromozioneAndFromStato(idPromozione, idStato);
    }
}