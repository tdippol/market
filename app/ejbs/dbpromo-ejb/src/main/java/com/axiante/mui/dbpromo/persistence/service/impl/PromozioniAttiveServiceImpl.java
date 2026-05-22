package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioniAttiveDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneAttiva;
import com.axiante.mui.dbpromo.persistence.service.PromozioniAttiveService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Dependent
@Transactional
public class PromozioniAttiveServiceImpl implements Serializable, PromozioniAttiveService {
    private static final long serialVersionUID = -4229125194483224654L;

    @Inject
    @Getter
    private PromozioniAttiveDAO dao;

    @Override
    public List<PromozioneAttiva> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean runDeletePromozione(String codicePromozione, String codiceUtente) {
        return dao.runDeletePromozione(codicePromozione, codiceUtente);
    }
}
