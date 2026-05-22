package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaCheckEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import java.util.List;

public interface CfgAbilitaCheckDAO extends DbPromoDAO<CfgAbilitaCheckEntity>{
	List<CfgAbilitaCheckEntity> findByCanaleMeccanica (CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata);
}
