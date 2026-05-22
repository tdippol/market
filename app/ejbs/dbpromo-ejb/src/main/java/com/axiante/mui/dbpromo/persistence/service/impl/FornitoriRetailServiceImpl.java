package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.FornitoriRetailDAO;
import com.axiante.mui.dbpromo.persistence.entities.FornitoriRetailEntity;
import com.axiante.mui.dbpromo.persistence.service.FornitoriRetailService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Dependent
@Transactional
public class FornitoriRetailServiceImpl implements FornitoriRetailService {
    @Inject
    FornitoriRetailDAO dao;

    @Override
    public FornitoriRetailEntity getFornitoriRetail(String codice) {
        return dao.getFornitoriRetailById(codice);
    }

    @Override
    public List<FornitoriRetailEntity> getAllFornitoriRetail() {
        return dao.getAllFornitoriRetail();
    }

    @Override
    public List<FornitoriRetailEntity> getAllFornitoriRetailNotDeleted(Date date) {
        return dao.getAllFornitoriRetailNotDeleted(date);
    }

    @Override
    public FornitoriRetailEntity getFornitoriRetailByDescription(String description) {
        return dao.getFornitoriRetailByDescrizione(description);
    }

    @Override
    public List<FornitoriRetailEntity> getFornitoriRetailByCodeLike(String code) {
        return dao.getAllFornitoriRetailByCodiceLike(code);
    }

    @Override
    public List<FornitoriRetailEntity> getFornitoriRetailByDescriptionLike(String description) {
        return dao.getFornitoriRetailByDescrizioneLike(description);
    }
}
