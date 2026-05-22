package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CanalePromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Transactional
@Slf4j
public class CanalePromozioneServiceImpl extends AbstractDbPromoService<CanalePromozioneEntity>
		implements CanalePromozioneService {
	private static final long serialVersionUID = -6890564679353488294L;

	@Inject
	@Getter
	private CanalePromozioneDAO dao;

	@Override
	public List<CanalePromozioneEntity> findAllByGroup(GruppoPromozioneEntity group) {
		return dao.findAllByGroup(group);
	}

	@Override
	public List<CanalePromozioneEntity> findByDescription(String description) {
		return dao.findByDescription(description);
	}

	@Override
	public CanalePromozioneEntity findByCodiceCanale(Long codiceCanale) {
		return dao.findByCodiceCanale(codiceCanale);
	}

	@Override
	public List<CanalePromozioneEntity> findByGroupId(Long group) {
		return dao.findByGroupId(group);
	}

	@Override
	public List<CanalePromozioneEntity> findByCodiciCanale(List<Long> codiciCanale) {
		if (codiciCanale == null || codiciCanale.isEmpty()) {
			log.warn("Trying to pass empty list to an `IN` sql statement");
			return Collections.emptyList();
		}
		return dao.findByCodiciCanale(codiciCanale);
	}

	@Override
	public Long countByIdWithTipologiaInitialLoad(Long id) {
		return dao.countByIdWithTipologiaInitialLoad(id);
	}
}
