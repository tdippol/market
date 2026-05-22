package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.FormaPagamentoEntity;
import java.util.List;

public interface FormaPagamentoService extends DbPromoService<FormaPagamentoEntity> {
    List<FormaPagamentoEntity> findAllActive();

    FormaPagamentoEntity findByCode(String codice);
}
