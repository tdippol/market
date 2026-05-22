package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiSpRepartoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpRepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSpRepartoService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
@Transactional
public class MuiSpRepartoServiceImpl extends AbstractDbPromoService<MuiSpRepartoEntity> implements MuiSpRepartoService {

    private static final long serialVersionUID = 1L;

    @Inject
    @Getter
    private MuiSpRepartoDAO dao;

    @Override
    public List<MuiSpRepartoEntity> findByPromozioneId(Long idPromozione) {
        return getDao().findByPromozioneId(idPromozione);
    }

    @Override
    public MuiSpRepartoEntity findByPromozioneIdAndRepartoId(Long idPromozione, Long idReparto) {
        return getDao().findByPromozioneIdAndRepartoId(idPromozione, idReparto);
    }

    @Override
    public MuiSpRepartoEntity update(MuiSpRepartoEntity entity) {
        return getDao().update(entity);
    }
}