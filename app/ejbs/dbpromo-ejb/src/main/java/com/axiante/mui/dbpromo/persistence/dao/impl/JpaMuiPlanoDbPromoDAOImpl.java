package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiPlanoDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoDbPromoEntity;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaMuiPlanoDbPromoDAOImpl implements MuiPlanoDbPromoDAO {
	@Inject
	@DbPromo
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public List<MuiPlanoDbPromoEntity> findAll() {
		return getEm().createNamedQuery("MuiPlanoDbPromoEntity.findAll", MuiPlanoDbPromoEntity.class)
				.getResultList();
	}

	@Override
	public MuiPlanoDbPromoEntity findByIdPlano(@NonNull String idPlano) {
		final Long count = getEm().createNamedQuery("MuiPlanoDbPromoEntity.countByIdPlano", Long.class)
				.setParameter("idPlano", idPlano)
				.getSingleResult();
		if (count != null && count == 1) {
			return getEm().createNamedQuery("MuiPlanoDbPromoEntity.findByIdPlano", MuiPlanoDbPromoEntity.class)
					.setParameter("idPlano", idPlano)
					.getSingleResult();
		}
		if (count == null || count == 0) {
			log.warn(String.format("No result found for 'planogram' %s", idPlano));
		} else if (count > 1) {
			log.error(String.format("Found %d results for 'planogram' %s", count, idPlano));
		}
		return null;
	}
}
