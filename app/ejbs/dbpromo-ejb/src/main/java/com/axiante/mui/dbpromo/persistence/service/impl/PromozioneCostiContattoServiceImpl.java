package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneCostiContattoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneCostiContattoEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozioneCostiContattoService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class PromozioneCostiContattoServiceImpl extends AbstractDbPromoService<PromozioneCostiContattoEntity>
        implements PromozioneCostiContattoService {
    private static final long serialVersionUID = -2661091868841310705L;

    @Inject
    @Getter
    private PromozioneCostiContattoDAO dao;

    @Override
    public PromozioneCostiContattoEntity findByIdPromozione(Long idPromozione) {
        return dao.findByIdPromozione(idPromozione);
    }
}
