package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.AttributiPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.AttributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.service.AttributiPromoService;
import lombok.Getter;

import javax.inject.Inject;

public class AttributiPromoServiceImpl extends AbstractDbPromoService<AttributiPromoEntity> implements AttributiPromoService {
    private static final long serialVersionUID = -6676409258977365243L;

    @Inject
    @Getter
    AttributiPromoDAO dao;
}
