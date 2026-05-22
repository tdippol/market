package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ValorePuntoDAO;
import com.axiante.mui.dbpromo.persistence.entities.ValorePuntoEntity;
import com.axiante.mui.dbpromo.persistence.service.ValorePuntoService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Transactional
@Slf4j
public class ValorePuntoServiceImpl extends AbstractDbPromoService<ValorePuntoEntity>
        implements ValorePuntoService {
    private static final long serialVersionUID = 6173492662314027231L;

    @Inject
    @Getter
    private ValorePuntoDAO dao;

    @Override
    public BigDecimal findValorePuntoWhereDate(@NonNull Date date) {
        final List<ValorePuntoEntity> entities = dao.findWhereDate(date);
        if (entities.isEmpty()) {
            log.warn("Non sono stati trovate entities che comprendono la data indicata " + date);
            return null;
        }
        if (entities.size() > 1) {
            log.warn(String.format("Sono stati trovate %d entities che comprendono la data indicata %s",
                    entities.size(), date));
            return null;
        }
        return entities.get(0).getValorePunto();
    }
}
