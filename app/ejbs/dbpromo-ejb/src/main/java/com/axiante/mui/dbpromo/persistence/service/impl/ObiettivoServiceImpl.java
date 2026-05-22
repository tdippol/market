package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ObiettivoDAO;
import com.axiante.mui.dbpromo.persistence.entities.ObiettivoEntity;
import com.axiante.mui.dbpromo.persistence.service.ObiettivoService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
@Transactional
public class ObiettivoServiceImpl extends AbstractDbPromoService<ObiettivoEntity> implements ObiettivoService {
    private static final long serialVersionUID = 5137607404134801999L;
    
    @Inject
    @Getter
    private ObiettivoDAO dao;

    @Override
    public List<ObiettivoEntity> findAllByPromozione(Long idPromozione) {
        return getDao().findAllByPromozione(idPromozione);
    }
}
