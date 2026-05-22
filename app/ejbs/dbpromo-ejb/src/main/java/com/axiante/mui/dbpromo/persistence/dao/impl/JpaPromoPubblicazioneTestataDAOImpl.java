package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromoPubblicazioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import java.util.List;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaPromoPubblicazioneTestataDAOImpl extends JpaDbPromoDAO<PromoPubblicazioneTestataEntity> implements PromoPubblicazioneTestataDAO {

	private static final long serialVersionUID = -2175701503020603462L;

	@Override
	public List<PromoPubblicazioneTestataEntity> findByPromozioneID(Long promoID) {
		return getEm().createNamedQuery("PromoPubblicazioneTestataEntity.findByPromoID", PromoPubblicazioneTestataEntity.class)
				.setParameter("promoID", promoID).getResultList();
	}

	@Override
	public boolean runUpdateEsitoPubblicazioni() {
		log.debug("launching process P_MUI_UPDATE_ESITO_PUBBLICAZ");
		StoredProcedureQuery query = getEm().createNamedStoredProcedureQuery(Constants.SP_UPDATE_ESITO_PUBBLICAZIONE);
		try {
			query.executeUpdate();
			log.debug("process did not return errors");
			return true;
		} catch ( Exception e) {
			log.error("Error running stored procedure " + Constants.SP_UPDATE_ESITO_PUBBLICAZIONE, e);
			return false;
		}
	}
}
