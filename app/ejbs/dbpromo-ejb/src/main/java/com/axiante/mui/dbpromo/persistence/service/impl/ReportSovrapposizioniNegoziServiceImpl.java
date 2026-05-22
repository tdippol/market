package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.ReportSovrapposizioniNegoziDAO;
import com.axiante.mui.dbpromo.persistence.entities.ReportSovrapposizioniNegoziEntity;
import com.axiante.mui.dbpromo.persistence.service.ReportSovrapposizioniNegoziService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class ReportSovrapposizioniNegoziServiceImpl 
	extends AbstractDbPromoService<ReportSovrapposizioniNegoziEntity> implements ReportSovrapposizioniNegoziService {

	private static final long serialVersionUID = -7353158738031616319L;
	@Inject
	@Getter
	private ReportSovrapposizioniNegoziDAO dao;

	@Override
	public List<ReportSovrapposizioniNegoziEntity> findByIdSovrapposizione(@NonNull Long idReport) {
		return getDao().findByIdSovrapposizione(idReport);
	}

}
