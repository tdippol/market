package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTestataService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Dependent
@Transactional
@Slf4j
public class PromozioneTestataServiceImpl extends AbstractDbPromoService<PromozioneTestataEntity>
		implements PromozioneTestataService {
	private static final long serialVersionUID = -8666765162424945611L;

	@Inject
	@Getter
	PromozioneTestataDAO dao;

	@Override
	public List<PromozioneTestataEntity> findByCanaleMeccanicheDate(@NonNull Long idCanale,
																	@NonNull List<String> codiciMeccaniche,
																	@NonNull Date dataInizio,
																	@NonNull Date dataFine) {
		if (codiciMeccaniche.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return dao.findByCanaleMeccanicheDate(idCanale, codiciMeccaniche, dataInizio, dataFine);
	}

	@Override
	public PromozioneTestataEntity findByIdFullEagerFetch(Long id) {
		return dao.findByIdFullEagerFetch(id);
	}
}
