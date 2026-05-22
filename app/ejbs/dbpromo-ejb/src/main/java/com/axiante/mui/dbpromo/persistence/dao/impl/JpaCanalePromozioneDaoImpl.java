package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CanalePromozioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DbPromoJpaDao
public class JpaCanalePromozioneDaoImpl extends JpaDbPromoDAO<CanalePromozioneEntity> implements CanalePromozioneDAO {
	private static final long serialVersionUID = -4823431521981043427L;

	@SuppressWarnings("unchecked")
	@Override
	public List<CanalePromozioneEntity> findAllByGroup(GruppoPromozioneEntity group) {
		List<CanalePromozioneEntity> channelEntities = new ArrayList<>();
		if (group != null) {
			getEm().createNamedQuery("MuiCanalePromozione.findAllByGroup").setParameter("gruppoPromozioneEntity", group)
					.getResultList().forEach(entity -> channelEntities.add((CanalePromozioneEntity) entity));
		}
		return channelEntities;
	}

	@Override
	public List<CanalePromozioneEntity> findByGroupId(@NonNull final Long id) {
		final TypedQuery<CanalePromozioneEntity> query = getEm().createQuery(
				"Select c from CanalePromozioneEntity c where c.gruppoPromozioneEntity.id = :id",
				CanalePromozioneEntity.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public CanalePromozioneEntity findByCodiceCanale(Long codiceCanale) {
		CanalePromozioneEntity canalePromozioneEntity = null;
		try {
			canalePromozioneEntity = (CanalePromozioneEntity) getEm()
					.createNamedQuery("MuiCanalePromozione.findByCodiceCanale")
					.setParameter("codiceCanale", codiceCanale).getSingleResult();
		} catch (NoResultException ex) {
			log.info("No CanalePromozioneEntity with codiceCanale " + codiceCanale, ex);
		}
		return canalePromozioneEntity;
	}

	@Override
	public List<CanalePromozioneEntity> findByDescription(String description) {
		return getEm().createNamedQuery("MuiCanalePromozione.findByDescription", CanalePromozioneEntity.class)
				.setParameter("descrizione", description).getResultList();
	}

	@Override
	public List<CanalePromozioneEntity> findByCodiciCanale(@NonNull final List<Long> codiciCanale) {
		if (codiciCanale != null && !codiciCanale.isEmpty()) {
			return getEm().createNamedQuery("MuiCanalePromozione.findByCodiciCanale", CanalePromozioneEntity.class)
					.setParameter("codiciCanale", codiciCanale)
					.getResultList();
		} else {
			throw new IllegalArgumentException("codiciCanale cannot be null or empty");
		}
	}

	@Override
	public Long countByIdWithTipologiaInitialLoad(Long id) {
		return getEm().createNamedQuery("MuiCanalePromozione.countByIdWithTipologiaInitialLoad", Long.class)
				.setParameter("id", id).getSingleResult();
	}

}
