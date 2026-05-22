package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleSecDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleSecEntity;
import java.util.List;

@DbPromoJpaDao
public class JpaCfgCanaleSecDAOImpl extends JpaDbPromoDAO<CfgCanaleSecEntity> implements CfgCanaleSecDAO {
	private static final long serialVersionUID = -8675303890192954033L;

	@Override
	public CfgCanaleSecEntity findByCanale(CanalePromozioneEntity canale) {
		return getEm().createNamedQuery("CfgCanaleSecEntity.findByCanale", CfgCanaleSecEntity.class)
				.setParameter("canale", canale)
				.getSingleResult();
	}

	@Override
	public Long countByCanale(CanalePromozioneEntity canale) {
		return getEm().createNamedQuery("CfgCanaleSecEntity.countByCanale", Long.class)
				.setParameter("canale", canale)
				.getSingleResult();
	}

	@Override
	public List<CfgCanaleSecEntity> findAll() {
		return getEm().createNamedQuery("CfgCanaleSecEntity.findAll", CfgCanaleSecEntity.class)
				.getResultList();
	}
}
