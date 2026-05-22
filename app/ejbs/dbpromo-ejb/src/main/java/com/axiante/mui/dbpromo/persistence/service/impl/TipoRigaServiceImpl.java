package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazTipoRigaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.service.TipoRigaService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class TipoRigaServiceImpl extends AbstractDbPromoService<CfgPianificazTipoRigaEntity>
		implements TipoRigaService {
	private static final long serialVersionUID = 2058095775416444048L;

	@Inject
	@Getter
	CfgPianificazTipoRigaDAO dao;

	@Override
	public CfgPianificazTipoRigaEntity findByDescription(String string) {
		return dao.findByDescription(string);
	}

	@Override
	public CfgPianificazTipoRigaEntity findByCodiceTipo(String string) {
		return dao.findByCodiceTipo(string);
	}

}
