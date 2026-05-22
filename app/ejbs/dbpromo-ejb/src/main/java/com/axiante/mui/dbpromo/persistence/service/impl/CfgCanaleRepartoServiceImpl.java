package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleRepartoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleReparto;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleRepartoService;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import lombok.Getter;

@Default
@Transactional
public class CfgCanaleRepartoServiceImpl extends AbstractDbPromoService<CfgCanaleReparto>
        implements CfgCanaleRepartoService {
    private static final long serialVersionUID = 7510469450331385364L;

    @Inject
    @Getter
    private CfgCanaleRepartoDAO dao;
    
    @Override
    public List<CfgCanaleReparto> findByCanale(CanalePromozioneEntity canale) {
        return getDao().findByCanale(canale);
    }

    @Override
    public CfgCanaleReparto findByCanaleAndReparto(CanalePromozioneEntity canale, RepartoEntity reparto) {
        try {
            return getDao().findByCanaleAndReparto(canale, reparto);
        } catch (NoResultException e) {
            return null;
        }
    }
}
