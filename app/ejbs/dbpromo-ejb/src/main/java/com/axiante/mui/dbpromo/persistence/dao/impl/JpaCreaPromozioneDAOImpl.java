package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.dao.CreaPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CreaPromozioneEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@DbPromoJpaDao
public class JpaCreaPromozioneDAOImpl extends JpaDbPromoDAO<CreaPromozioneEntity> implements CreaPromozioneDAO {

	private static final long serialVersionUID = -7126267244730167386L;

	@Override
	public List<CreaPromozioneEntity> findAllByUserId(String userId) throws Exception {
		return getEm().createNamedQuery("CreaPromozioneEntity.findAllByUserId", CreaPromozioneEntity.class)
				.setParameter("userId", userId)
				.getResultList();
	}

	@Override
	public CreaPromozioneEntity findByUserIdAndSlotId(String userId, String slotId) throws Exception {
		return getEm().createNamedQuery("CreaPromozioneEntity.findByUserIdAndSlotId", CreaPromozioneEntity.class)
				.setParameter("userId", userId)
				.setParameter("slotId", slotId)
				.getSingleResult();
	}


	@Override
	/**
	 * chiama una procedura che conta il numero delle testate per canale e reparto e ritorna una eccezione
	 * se il numero e' maggiore di un parameto (mui_cfg_canale_reparto) configurato
	 */
	public boolean runFunctionCountTestate(Long idUser, Long idCanale, Date dataInizio, Date dataFine)
			throws Exception {
		final StoredProcedureQuery query = getEm().createStoredProcedureQuery(Constants.SP_COUNT_PROMO_REP)
				.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, Date.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, Date.class, ParameterMode.IN)
				.setParameter(1, idUser)
				.setParameter(2, idCanale)
				.setParameter(3, dataInizio)
				.setParameter(4, dataFine);

		query.executeUpdate();
		return true;
	}

	@Override
	/**
	 * chiama una procedura che conta il numero delle testate per canale e ritorna una eccezione
	 * se il numero e' maggiore di un parametro in input
	 */
	public boolean runFunctionCountTestate(Long idCanale, Date dataInizio, Date dataFine, Long maxTestate)
			throws Exception {
		final StoredProcedureQuery query = getEm().createStoredProcedureQuery(Constants.SP_COUNT_PROMO_CAN)
				.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, Date.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
				.setParameter(1, idCanale)
				.setParameter(2, dataInizio)
				.setParameter(3, dataFine)
				.setParameter(4, maxTestate);

		query.executeUpdate();
		return true;
	}

}
