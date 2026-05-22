package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgLivelloPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaCfgLivelloPianificazioneDAOImpl extends JpaDbPromoDAO<CfgLivelloPianificazioneEntity>
		implements CfgLivelloPianificazioneDAO {

	private static final long serialVersionUID = -322305491097383047L;

	@Override
	public CfgLivelloPianificazioneEntity findByDescription(@NonNull final String description) {
		List<CfgLivelloPianificazioneEntity> list = getEm()
				.createNamedQuery("CfgLivelloPianificazioneEntity.findByDescription", CfgLivelloPianificazioneEntity.class)
				.setParameter("descrizione", description).getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
