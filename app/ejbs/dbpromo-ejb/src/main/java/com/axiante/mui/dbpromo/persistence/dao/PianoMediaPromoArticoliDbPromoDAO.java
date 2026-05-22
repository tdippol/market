package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDbPromoEntity;
import java.util.List;

public interface PianoMediaPromoArticoliDbPromoDAO extends DbPromoDAO<PianoMediaPromoArticoliDbPromoEntity> {

    List<PianoMediaPromoArticoliDbPromoEntity> findByCodicePromo(String codicePromo);

    PianoMediaPromoArticoliDbPromoEntity findByCodiceItemAndCodicePromoAndTipoItem(String codiceItem,
                                                                                   String codicePromo,
                                                                                   String tipoItem);
}
