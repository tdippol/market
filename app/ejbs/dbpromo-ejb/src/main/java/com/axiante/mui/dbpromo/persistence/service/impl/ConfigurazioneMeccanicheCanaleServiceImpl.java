package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgAbilitaMeccCanaleDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.service.ConfigurazioneMeccanicheCanaleService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Dependent
@Transactional
@Slf4j
public class ConfigurazioneMeccanicheCanaleServiceImpl extends AbstractDbPromoService<CfgAbilitaMeccCanaleEntity>
		implements ConfigurazioneMeccanicheCanaleService {
	private static final long serialVersionUID = 3382544742006679319L;

	@Inject
	@Getter
	CfgAbilitaMeccCanaleDAO dao;

	@Override
	public List<CfgAbilitaMeccCanaleEntity> findMeccanicaCanaleByPromozione(@NonNull final CanalePromozioneEntity canalePromozioneEntity) {
		try {
			return dao.findAllByIdCanale(canalePromozioneEntity);

		} catch (Exception e) {
			log.error("error getting cfg_meccaniche", e);
		}
		return new ArrayList<>();
	}

	@Override
	public CfgAbilitaMeccCanaleEntity findByMeccanicaAndCanale(Long idMeccanica, Long idCanale) {
		return dao.findByMeccanicaAndCanale(idMeccanica,idCanale);
	}
}
