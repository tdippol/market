package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDbPromoEntity;
import java.util.List;

public interface PianoMediaPromoArticoliDbPromoService extends DbPromoService<PianoMediaPromoArticoliDbPromoEntity> {
    List<PianoMediaPromoArticoliDbPromoEntity> findByCodicePromo(String codicePromo);

    PianoMediaPromoArticoliDbPromoEntity findByCodiceItemAndCodicePromoAndTipoItem(String codiceItem,
                                                                                   String codicePromo,
                                                                                   String tipoItem);
}
