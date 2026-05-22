package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaCheckEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import java.util.List;

public interface CfgAbilitaCheckService extends DbPromoService<CfgAbilitaCheckEntity> {

	List<CfgAbilitaCheckEntity> findByCanaleMeccanica (CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata);
}
