package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgSovrapposizioneMeccanicheDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSovrapposizioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgSovrapposizioneMeccanicheService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class CfgSovrapposizioneMeccanicheServiceImpl extends AbstractDbPromoService<CfgSovrapposizioneMeccanicheEntity>
        implements CfgSovrapposizioneMeccanicheService {

	private static final long serialVersionUID = -2119187017370025800L;

	@Inject
	@Getter
	private CfgSovrapposizioneMeccanicheDAO dao;

    @Override
    public List<CfgSovrapposizioneMeccanicheEntity> findByCanaleMeccanica(CfgAbilitaMeccCanaleEntity meccanicaCanale) {
        return dao.findByCanaleMeccanica(meccanicaCanale);
    }
}
