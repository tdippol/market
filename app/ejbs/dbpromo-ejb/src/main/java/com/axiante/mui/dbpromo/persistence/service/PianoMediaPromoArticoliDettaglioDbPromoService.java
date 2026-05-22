package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDettaglioDbPromoEntity;
import java.util.List;

public interface PianoMediaPromoArticoliDettaglioDbPromoService extends DbPromoService<PianoMediaPromoArticoliDettaglioDbPromoEntity>{
    List<PianoMediaPromoArticoliDettaglioDbPromoEntity> findByCodicePromoAndItemAndTipoItem(String codicePromo, String codiceItem, String tipoItem);
}
