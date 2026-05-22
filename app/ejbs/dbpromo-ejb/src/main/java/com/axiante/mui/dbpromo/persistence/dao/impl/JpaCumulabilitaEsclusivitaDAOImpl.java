package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.CumulabilitaType;
import com.axiante.mui.dbpromo.business.enumeration.OperazioneCumulabilita;
import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.dao.CumulabilitaEsclusivitaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.List;

public class JpaCumulabilitaEsclusivitaDAOImpl extends JpaDbPromoDAO<CumulabilitaEsclusivitaEntity>
		implements CumulabilitaEsclusivitaDAO {
	private static final long serialVersionUID = -8981688494238624427L;

	@Override
	public List<CumulabilitaEsclusivitaEntity> findByType(CumulabilitaType type) {
		return getEm().createNamedQuery("CumulabilitaEsclusivitaEntity.findByType", CumulabilitaEsclusivitaEntity.class)
				.setParameter("type", type)
				.getResultList();
	}
	@Override
	public List<CumulabilitaEsclusivitaEntity> findOpenByType(CumulabilitaType type) {
		return getEm().createNamedQuery("CumulabilitaEsclusivitaEntity.findOpenByType", CumulabilitaEsclusivitaEntity.class)
				.setParameter("type", type)
				.setParameter("dataFine", new Date(System.currentTimeMillis()))
				.getResultList();
	}

	@Override
	public List<CumulabilitaEsclusivitaEntity> findOverlapping(CumulabilitaEsclusivitaEntity entity){
		return getEm().createNamedQuery("CumulabilitsEsclusivitaEntity.findOverlapping", CumulabilitaEsclusivitaEntity.class)
				.setParameter("dataFine", entity.getDataFine())
				.setParameter("dataInizio", entity.getDataInizio())
				.setParameter("id", entity.getId())
				.getResultList();
	}

	@Override
	public boolean exportCumulabilita(Long idCumulabilita, String codiceUtente){
		final StoredProcedureQuery query = getEm().createStoredProcedureQuery(Constants.P_MUI_EXPORT_CUMUL_ESCLUS)
				.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
				.setParameter(1, idCumulabilita)
				.setParameter(2, codiceUtente);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean updateCumulabilita(Long idCumulabilita, OperazioneCumulabilita operazione, String codiceUtente) {
		final StoredProcedureQuery query = getEm().createStoredProcedureQuery(Constants.P_AGGIORNA_COMULABILITA)
				.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
				.setParameter(1, idCumulabilita)
				.setParameter(2, operazione.getCode())
				.setParameter(3, codiceUtente);
		query.executeUpdate();
		return true;
	}
}
