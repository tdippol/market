package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CanaleLastProgEntityDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import java.util.List;
import javax.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaCanaleLastProgEntityDAOImpl extends JpaDbPromoDAO<CanaleLastProgEntity>
		implements CanaleLastProgEntityDAO {

	private static final long serialVersionUID = -3778254409928700079L;

	@Override
	public CanaleLastProgEntity getByYearAndChannel(String anno, CanalePromozioneEntity canalePromozioneEntity) {
		if (anno == null) {
			return null;
		}
		try {
			return getEm().createNamedQuery("MuiCanaleLastProg.findByAnnoAndCanale", CanaleLastProgEntity.class)
					.setParameter("anno", Long.parseLong(anno))
					.setParameter("muiCanalePromozione", canalePromozioneEntity).getSingleResult();
		} catch (NoResultException e) {
			log.info("No CanaleLastProgEntity found", e);
			return null;
		}
	}

	@Override
	public List<CanaleLastProgEntity> findByChannel(CanalePromozioneEntity canalePromozioneEntity) {
		return getEm().createNamedQuery("MuiCanaleLastProg.findByCanale", CanaleLastProgEntity.class)
				.setParameter("muiCanalePromozione", canalePromozioneEntity)
				.getResultList();
	}
}
