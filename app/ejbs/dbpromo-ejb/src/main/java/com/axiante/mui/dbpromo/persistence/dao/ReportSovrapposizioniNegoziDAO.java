package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniNegoziEntity;
import java.util.List;

public interface ReportSovrapposizioniNegoziDAO extends DbPromoDAO<ReportSovrapposizioniNegoziEntity>{
	public List<ReportSovrapposizioniNegoziEntity> findByIdSovrapposizione(Long idReport);
}
