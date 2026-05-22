package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;

import java.util.List;

public interface ItemDAO extends DbPromoDAO<ItemEntity> {
    ItemEntity findByCode(String code);

    ItemEntity findActiveByCode(String code);

    List<ItemEntity> findAllByCompratore(CompratoreEntity compratoreEntity);

    List<ItemEntity> findAllByFilter(Long idCompratore, Long idFornitore, Long idReparto, Long idCategoria, Long idGrm,
                                     String codiceMarchioPrivato);

    String findCodiceById(Long id);

    List<ItemEntity> findByIds(List<Long> ids);

    List<ItemEntity> findByIdsAndCompratoreAndFornitore(List<Long> ids, String codiceCompratore, String codiceFornitore);

    List<ItemEntity> findByIdsAndCompratoriAndFornitore(List<Long> ids, List<String> codiciCompratori, String codiceFornitore);

    List<String> findCodiceMarchioPrivatoByCompratori(List<String> codiciCompratori);
}
