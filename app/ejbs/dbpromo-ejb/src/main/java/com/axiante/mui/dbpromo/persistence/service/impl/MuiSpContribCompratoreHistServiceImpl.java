package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiSpContribCompratoreHistDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreHistEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSpContribCompratoreHistService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
@Transactional
public class MuiSpContribCompratoreHistServiceImpl extends AbstractDbPromoService<MuiSpContribCompratoreHistEntity>
        implements MuiSpContribCompratoreHistService {

    private static final long serialVersionUID = 1L;

    @Inject
    private MuiSpContribCompratoreHistDAO dao;

    @Override
    public MuiSpContribCompratoreHistDAO getDao() {
        return dao;
    }

    @Override
    public List<MuiSpContribCompratoreHistEntity> findByPromozioneId(Long idPromozione) {
        return getDao().findByPromozioneId(idPromozione);
    }
}