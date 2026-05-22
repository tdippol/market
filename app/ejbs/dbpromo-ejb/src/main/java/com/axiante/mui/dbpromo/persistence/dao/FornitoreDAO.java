package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import java.util.List;

public interface FornitoreDAO extends DbPromoDAO<FornitoreEntity> {
    List<FornitoreEntity> findAllByCodiciCompratore(List<String> codiciCompratore);

    List<FornitoreEntity> findAllFornitoriAttiviByCodiceCompratore(String codiceCompratore);
}
