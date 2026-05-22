package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneCampiDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneCampiService;
import lombok.Getter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class PianificazioneCampiServiceImpl extends AbstractDbPromoService<CfgPianificazioneCampiEntity>
		implements PianificazioneCampiService {
	private static final long serialVersionUID = 5040102113129145831L;

	@Inject
	@Getter
	CfgPianificazioneCampiDAO dao;

	@Override
	public CfgPianificazioneCampiEntity saveCfgPianificazioneCampiEntity(
			@NonNull final CfgPianificazioneCampiEntity cfgPianificazioneCampiEntity,
			@NonNull final String username) {
		return (CfgPianificazioneCampiEntity) dao.persistWithAuditLog(cfgPianificazioneCampiEntity, username);
	}
}
