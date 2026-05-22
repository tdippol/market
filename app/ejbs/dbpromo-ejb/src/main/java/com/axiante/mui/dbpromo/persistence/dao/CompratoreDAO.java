package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import java.util.List;

public interface CompratoreDAO extends DbPromoDAO<CompratoreEntity> {
    List<CompratoreEntity> findAllOrderedBy();
    List<CompratoreEntity> findAllByCodes(List<String> codes);
    List<CompratoreEntity> findAllByIdItems(List<Long> idItems);
}
