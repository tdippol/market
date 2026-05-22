package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleNegoziDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleNegoziEntity;
import java.util.ArrayList;
import java.util.List;

@DbPromoJpaDao
public class JpaCfgCanaleNegoziDAOImpl extends JpaDbPromoDAO<CanalePromozioneEntity> implements CfgCanaleNegoziDAO {

	private static final long serialVersionUID = 6031163798428884610L;

	@Override
	public List<CfgCanaleNegoziEntity> findAllByIdCanale(CanalePromozioneEntity canalePromozioneEntity) {
		if (canalePromozioneEntity != null) {
			return getEm().createNamedQuery("MuiCfgCanaleNegozi.findAllByIdCanale", CfgCanaleNegoziEntity.class)
					.setParameter("muiCanalePromozione", canalePromozioneEntity).getResultList();
		}
		return new ArrayList<>();
	}
}
