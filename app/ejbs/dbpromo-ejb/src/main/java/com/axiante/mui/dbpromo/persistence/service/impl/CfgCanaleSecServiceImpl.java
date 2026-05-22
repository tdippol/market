package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleSecDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleSecEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleSecService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

@Slf4j
@Dependent
@Transactional
public class CfgCanaleSecServiceImpl extends AbstractDbPromoService<CfgCanaleSecEntity> implements CfgCanaleSecService {
    private static final long serialVersionUID = 664202414469199922L;

    @Inject
    @Getter
    private CfgCanaleSecDAO dao;

    @Override
    public CfgCanaleSecEntity findByCanale(CanalePromozioneEntity canale) {
        Long count = getDao().countByCanale(canale);
        switch (count.intValue()) {
            case 0:
                return createDefault(canale);
            case 1:
                return getDao().findByCanale(canale);
            default:
                throw new NonUniqueResultException(String.format("Sicurezza configurata %d volte per il canale %s",
                        count.intValue(), canale.getCodiceCanale()));
        }
    }

    private CfgCanaleSecEntity createDefault(CanalePromozioneEntity canale) {
        CfgCanaleSecEntity sec = new CfgCanaleSecEntity();
        sec.setCanale(canale);
        try {
            sec = dao.persist(sec);
        } catch (Exception e) {
            log.error(String.format("Errore durante la creazione della sicurezza di default per il canale %s : ",
                    canale.getCodiceCanale()), e);
            return null;
        }
        return sec;
    }
}
