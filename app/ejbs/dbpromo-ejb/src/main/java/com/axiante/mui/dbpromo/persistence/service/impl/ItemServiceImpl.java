package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ItemDAO;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Dependent
@Transactional
@Slf4j
public class ItemServiceImpl extends AbstractDbPromoService<ItemEntity> implements ItemService {
	private static final long serialVersionUID = -3559489572417613871L;

	@Inject
	@Getter
	private ItemDAO dao;

	@Override
	public ItemEntity findByCode(String code) {
		return getDao().findByCode(code);
	}

    @Override
    public ItemEntity findActiveByCode(String code) {
        return getDao().findActiveByCode(code);
    }

    @Override
    public String findCodiceById(Long id) {
        return getDao().findCodiceById(id);
    }

    @Override
    public List<ItemEntity> findByIds(@NonNull List<Long> ids){
    	return getDao().findByIds(ids);
    }

    @Override
    public List<ItemEntity> findByIdsAndCompratoreAndFornitore(List<Long> ids,
                                                               @NonNull String codiceCompratore,
                                                               @NonNull String codiceFornitore) {
	    if (ids == null || ids.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return getDao().findByIdsAndCompratoreAndFornitore(ids, codiceCompratore, codiceFornitore);
    }

    @Override
    public List<ItemEntity> findByIdsAndCompratoriAndFornitore(List<Long> ids, List<String> codiciCompratori,
                                                               @NonNull String codiceFornitore) {
        if (ids == null || ids.isEmpty() || codiciCompratori == null || codiciCompratori.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return getDao().findByIdsAndCompratoriAndFornitore(ids, codiciCompratori, codiceFornitore);
    }

    @Override
    public List<String> findCodiceMarchioPrivatoByCompratori(List<String> codiciCompratori) {
        if (codiciCompratori == null || codiciCompratori.isEmpty()) {
            log.warn("Trying to pass empty list to an `IN` sql statement");
            return Collections.emptyList();
        }
        return getDao().findCodiceMarchioPrivatoByCompratori(codiciCompratori);
    }
}
