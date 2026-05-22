package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.ValorePuntoEntity;
import java.math.BigDecimal;
import java.util.Date;

public interface ValorePuntoService extends DbPromoService<ValorePuntoEntity> {
    BigDecimal findValorePuntoWhereDate(Date date);
}
