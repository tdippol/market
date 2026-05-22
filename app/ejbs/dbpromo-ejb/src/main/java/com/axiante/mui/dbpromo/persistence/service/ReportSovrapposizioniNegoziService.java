package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniNegoziEntity;
import java.util.List;

public interface ReportSovrapposizioniNegoziService extends DbPromoService<ReportSovrapposizioniNegoziEntity> {
	public List<ReportSovrapposizioniNegoziEntity> findByIdSovrapposizione(Long idReport);
}
