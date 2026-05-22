package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CheckCompratoriDAO;
import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaCheckCompratoreDAOImpl extends JpaDbPromoDAO<CheckCompratoriEntity> implements CheckCompratoriDAO{

	private static final long serialVersionUID = -2405656375328375579L;

	@Override
	public List<CheckCompratoriEntity> findByPromozione(PromozioneTestataEntity testata) {
		return getEm().createNamedQuery("CheckCompratoriEntity.findByPromozione", CheckCompratoriEntity.class)
				.setParameter("promozione", testata)
				.getResultList();
	}

	@Override
	public List<CheckCompratoriEntity> findByCompratore(CompratoreEntity compratore) {
		return getEm().createNamedQuery("CheckCompratoriEntity.findByCompratore", CheckCompratoriEntity.class)
				.setParameter("compratore", compratore)
				.getResultList();
	}

	@Override
	public CheckCompratoriEntity findByPromozioneAndCompratore(PromozioneTestataEntity testata, CompratoreEntity compratore) {
		Long count = getEm().createNamedQuery("CheckCompratoriEntity.countByPromozioneAndCompratore", Long.class)
				.setParameter("promozione", testata)
				.setParameter("compratore", compratore)
				.getSingleResult();
		if (count == 1) {
			return getEm().createNamedQuery("CheckCompratoriEntity.findByPromozioneAndCompratore", CheckCompratoriEntity.class)
					.setParameter("promozione", testata)
					.setParameter("compratore", compratore)
					.getSingleResult();
		}
		if (count == 0) {
			final String message = String.format("Nessun record trovato per promozione %s and compratore %s",
					testata.getDescrizioneEstesa(), compratore.getCodiceCompratore());
			log.error(message);
			throw new NoResultException(message);
		} else {
			final String message = String.format("Trovati %d record per promozione %s and compratore %s, aspettati 1 ",
					count, testata.getDescrizioneEstesa(), compratore.getCodiceCompratore());
			log.error(message);
			throw new NonUniqueResultException(message);
		}
	}
}
