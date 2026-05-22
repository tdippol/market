package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.ReportSovrapposizioniNegoziDAO;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniNegoziEntity;
import java.util.List;
import lombok.NonNull;

public class JpaReportSovrapposizioniNegoziDAOImpl extends JpaDbPromoDAO<ReportSovrapposizioniNegoziEntity>
		implements ReportSovrapposizioniNegoziDAO {
	private static final long serialVersionUID = 1L;

	public List<ReportSovrapposizioniNegoziEntity> findByIdSovrapposizione(@NonNull Long idReport){
		return getEm().createNamedQuery("ReportSovrapposizioniNegoziEntity.findByIdSovrapposizione", ReportSovrapposizioniNegoziEntity.class)
				.setParameter("id", idReport)
				.getResultList();
	}
}
