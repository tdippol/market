package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDettaglioDbPromoEntity;
import java.util.List;

public interface PianoMediaPromoArticoliDettaglioDbPromoDAO extends DbPromoDAO<PianoMediaPromoArticoliDettaglioDbPromoEntity>   {
    List<PianoMediaPromoArticoliDettaglioDbPromoEntity> findByCodicePromoAndItemAndTipoItem(String codicePromo, String codiceItem, String tipoItem);
}
