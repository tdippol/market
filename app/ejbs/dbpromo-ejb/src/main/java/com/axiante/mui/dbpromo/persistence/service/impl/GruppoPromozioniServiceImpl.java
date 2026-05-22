package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.GruppoPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.GruppoPromozioniService;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
@Transactional
public class GruppoPromozioniServiceImpl extends AbstractDbPromoService<GruppoPromozioneEntity>
		implements GruppoPromozioniService {
	private static final long serialVersionUID = -1253983066450368928L;

	@Inject
	@Getter
	GruppoPromozioneDAO dao;

	@Override
	public List<GruppoPromozioneEntity> findAllByCodiciCanale(List<Long> codiciCanale) {
		if (codiciCanale == null || codiciCanale.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return dao.findAllByCodiciCanale(codiciCanale);
	}
}
