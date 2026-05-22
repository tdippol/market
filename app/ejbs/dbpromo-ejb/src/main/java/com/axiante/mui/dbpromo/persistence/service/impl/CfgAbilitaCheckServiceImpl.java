package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgAbilitaCheckDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaCheckEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgAbilitaCheckService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class CfgAbilitaCheckServiceImpl extends AbstractDbPromoService<CfgAbilitaCheckEntity> implements CfgAbilitaCheckService{
	private static final long serialVersionUID = -5989397069029653503L;
	@Inject
	@Getter
	private CfgAbilitaCheckDAO dao;
	
	public List<CfgAbilitaCheckEntity> findByCanaleMeccanica (CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata){
			return getDao().findByCanaleMeccanica(meccanicaCanaleAbilitata);
	}
}
