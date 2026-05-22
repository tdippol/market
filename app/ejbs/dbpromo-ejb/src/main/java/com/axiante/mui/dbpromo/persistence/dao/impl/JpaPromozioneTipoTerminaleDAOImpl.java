package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneTipoTerminaleDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTipoTerminaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoTerminaleCassaEntity;

public class JpaPromozioneTipoTerminaleDAOImpl extends JpaDbPromoDAO<PromozioneTipoTerminaleEntity> implements PromozioneTipoTerminaleDAO{

	private static final long serialVersionUID = 4009660193509804419L;

	@Override
	public PromozioneTipoTerminaleEntity findByTestataAndCassa(PromozioneTestataEntity testata, TipoTerminaleCassaEntity cassa) {
		return getEm().createNamedQuery("PromozioneTipoTerminaleEntity.findByIdTestataIdTerminale", PromozioneTipoTerminaleEntity.class)
				.setParameter("testata", testata)
				.setParameter("cassa", cassa)
				.getSingleResult();
	}
}
