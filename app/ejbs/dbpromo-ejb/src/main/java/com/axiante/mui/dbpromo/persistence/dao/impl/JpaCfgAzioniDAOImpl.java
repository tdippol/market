package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgAzioniDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaCfgAzioniDAOImpl extends JpaDbPromoDAO<CfgAzioniEntity> implements CfgAzioniDAO {
	private static final long serialVersionUID = -2405656375328375579L;

	@Override
	public CfgAzioniEntity findByCodice(@NonNull String codice) {
		Long count = getEm().createNamedQuery("CfgAzioniEntity.countByCodice", Long.class)
				.setParameter("codice", codice)
				.getSingleResult();
		if ( (count != null) && (count == 1)) {
			return getEm().createNamedQuery("CfgAzioniEntity.findByCodice", CfgAzioniEntity.class)
					.setParameter("codice", codice)
					.getSingleResult();
		} else {
			String message = null;
			if ( count == 0 ) {
				message = String.format("Nessuna azione con codice %s trovata", codice);
				log.error(message);
				throw new NoResultException(message);
			} else {
				message = String.format("Trovate %d azioni con codice %s ", count, codice);
				throw new NonUniqueResultException(message);
			}
		}
	}
}
