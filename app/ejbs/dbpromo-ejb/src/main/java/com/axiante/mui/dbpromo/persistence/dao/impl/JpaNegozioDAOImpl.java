package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.NegozioDAO;
import com.axiante.mui.dbpromo.persistence.dto.ZonaDto;
import com.axiante.mui.dbpromo.persistence.entities.NegozioEntity;
import java.util.List;

@DbPromoJpaDao
public class JpaNegozioDAOImpl extends JpaDbPromoDAO<NegozioEntity> implements NegozioDAO {
	private static final long serialVersionUID = 3645286102410205211L;

	@Override
	public List<String> findAllTipoConsegna() {
		return getEm().createNamedQuery("NegozioEntity.findAllTipoConsegna", String.class).getResultList();
	}

	@Override
	public List<ZonaDto> findAllDistinctZone() {
		return getEm().createNamedQuery("NegozioEntity.findDistinctZone", ZonaDto.class).getResultList();
	}
	@Override
	public List<String> findAllDistinctCedi() {
		return getEm().createNamedQuery("NegozioEntity.findDistinctCedi", String.class).getResultList();
	}
}
