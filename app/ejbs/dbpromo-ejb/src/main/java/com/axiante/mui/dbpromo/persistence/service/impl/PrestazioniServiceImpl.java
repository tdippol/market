package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PrestazioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.PrestazioniEntity;
import com.axiante.mui.dbpromo.persistence.service.PrestazioniService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class PrestazioniServiceImpl extends AbstractDbPromoService<PrestazioniEntity> implements PrestazioniService {
    private static final long serialVersionUID = -6029302880507716481L;

    @Inject
    @Getter
    PrestazioniDAO dao;

    @Override
    public String findDescrizioneByCodice(@NonNull String codice) {
        return dao.findDescrizioneByCodice(codice);
    }

    @Override
    public List<PrestazioniEntity> findByCodiceGruppo(String codiceGruppo){
        return dao.findByCodiceGruppo(codiceGruppo);
    }

}
