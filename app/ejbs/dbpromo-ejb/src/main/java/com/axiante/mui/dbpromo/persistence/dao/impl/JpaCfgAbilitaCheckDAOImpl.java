package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgAbilitaCheckDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaCheckEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import java.util.List;

@DbPromoJpaDao
public class JpaCfgAbilitaCheckDAOImpl extends JpaDbPromoDAO<CfgAbilitaCheckEntity> implements CfgAbilitaCheckDAO {
	private static final long serialVersionUID = -3665394716704798742L;

	public List<CfgAbilitaCheckEntity> findByCanaleMeccanica (CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata){
		return getEm().createNamedQuery("CfgAbilitaCheckEntity.findByCanaleMeccanica", CfgAbilitaCheckEntity.class)
				.setParameter("meccanicaCanaleAbilitata", meccanicaCanaleAbilitata)
				.getResultList();
	}
}
