package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import lombok.NonNull;

import java.util.List;

public interface ItemService extends DbPromoService<ItemEntity> {
	ItemEntity findByCode(String code);

	ItemEntity findActiveByCode(String code);

	String findCodiceById(Long id);

	List<ItemEntity> findByIds(@NonNull List<Long> ids);

	List<ItemEntity> findByIdsAndCompratoreAndFornitore(List<Long> ids, String codiceCompratore, String codiceFornitore);

	List<ItemEntity> findByIdsAndCompratoriAndFornitore(List<Long> ids, List<String> codiciCompratori, String codiceFornitore);

	List<String> findCodiceMarchioPrivatoByCompratori(List<String> codiciCompratori);
}
