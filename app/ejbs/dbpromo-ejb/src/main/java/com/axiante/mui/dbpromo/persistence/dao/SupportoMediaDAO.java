package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import java.util.List;

public interface SupportoMediaDAO extends DbPromoDAO<SupportoMediaEntity> {
    SupportoMediaEntity findByCode(String codice);
    List<SupportoMediaEntity> findAllAttivi();
}
