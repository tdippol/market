package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.TemplateTotalizzatoriDAO;
import com.axiante.mui.dbpromo.persistence.entities.TemplateTotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.service.TemplateTotalizzatoriService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class TemplateTotalizzatoriServiceImpl extends AbstractDbPromoService<TemplateTotalizzatoriEntity>
        implements TemplateTotalizzatoriService {
    private static final long serialVersionUID = 6371258407570338487L;

    @Inject
    @Getter
    private TemplateTotalizzatoriDAO dao;
}
