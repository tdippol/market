package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.TotalizzatoriDAO;
import com.axiante.mui.dbpromo.persistence.entities.TotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.service.TotalizzatoriService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class TotalizzatoriServiceImpl extends AbstractDbPromoService<TotalizzatoriEntity> implements TotalizzatoriService {
    private static final long serialVersionUID = 2925239802547449850L;

    @Inject
    @Getter
    private TotalizzatoriDAO dao;

    @Override
    public List<TotalizzatoriEntity> findAllWithParentByIdIniziativa(Long idIniziativa) {
        return dao.findAllWithParentByIdIniziativa(idIniziativa);
    }
}
