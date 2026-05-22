package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPromoArticoliDettaglioDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDettaglioDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoArticoliDettaglioDbPromoService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class JpaPianoMediaPromoArticoliDettaglioDbPromoServiceImpl extends AbstractDbPromoService<PianoMediaPromoArticoliDettaglioDbPromoEntity>
        implements PianoMediaPromoArticoliDettaglioDbPromoService {
    private static final long serialVersionUID = -1587483879196239627L;

    @Inject
    @Getter
    private PianoMediaPromoArticoliDettaglioDbPromoDAO dao;

    @Override
    public List<PianoMediaPromoArticoliDettaglioDbPromoEntity> findByCodicePromoAndItemAndTipoItem(String codicePromo,
                                                                                                   String codiceItem,
                                                                                                   String tipoItem) {
        return getDao().findByCodicePromoAndItemAndTipoItem(codicePromo, codiceItem, tipoItem);
    }
}
