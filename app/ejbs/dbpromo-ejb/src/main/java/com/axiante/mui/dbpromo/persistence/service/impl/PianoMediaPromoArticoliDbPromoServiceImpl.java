package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoArticoliDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoArticoliDbPromoService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class PianoMediaPromoArticoliDbPromoServiceImpl extends AbstractDbPromoService<PianoMediaPromoArticoliDbPromoEntity>
        implements PianoMediaPromoArticoliDbPromoService {
    private static final long serialVersionUID = 2646759513223576991L;

    @Inject
    @Getter
    PianoMediaPromoArticoliDbPromoDAO dao;

    @Override
    public List<PianoMediaPromoArticoliDbPromoEntity> findByCodicePromo(String codicePromo) {
        return getDao().findByCodicePromo(codicePromo);
    }

    @Override
    public PianoMediaPromoArticoliDbPromoEntity findByCodiceItemAndCodicePromoAndTipoItem(String codiceItem,
                                                                                          String codicePromo,
                                                                                          String tipoItem) {
        return getDao().findByCodiceItemAndCodicePromoAndTipoItem(codiceItem, codicePromo, tipoItem);
    }
}
