package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgAzioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgAzioniService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class CfgAzioniServiceImpl extends AbstractDbPromoService<CfgAzioniEntity> implements CfgAzioniService{

	/**
	 *
	 */
	private static final long serialVersionUID = 5442762601271394073L;

	@Inject
	@Getter
	private CfgAzioniDAO dao;

	@Override
	public CfgAzioniEntity findByCode(@NonNull String code) {
		return getDao().findByCodice(code);
	}

}
