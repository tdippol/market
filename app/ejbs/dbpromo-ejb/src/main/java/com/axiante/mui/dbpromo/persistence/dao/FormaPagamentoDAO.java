package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.FormaPagamentoEntity;
import java.util.List;

public interface FormaPagamentoDAO extends DbPromoDAO<FormaPagamentoEntity> {
    List<FormaPagamentoEntity> findAllActive();

    FormaPagamentoEntity findByCode(String codice);
}
