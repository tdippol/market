package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiPromoDbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoDbPromoEntity;
import java.sql.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaMuiPromoDbPromoDAOImpl implements MuiPromoDbPromoDAO {
	@Inject
	@DbPromo
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public List<MuiPromoDbPromoEntity> findAll() {
		return getEm().createNamedQuery("MuiPromoDbPromoEntity.findAll", MuiPromoDbPromoEntity.class)
				.getResultList();
	}

	@Override
	public List<MuiPromoDbPromoEntity> findByDataFineGreaterThan(Date dataFine) {
		return getEm().createNamedQuery("MuiPromoDbPromoEntity.findByGiorniSelezione", MuiPromoDbPromoEntity.class)
				.setParameter("data", dataFine).getResultList();
	}

	@Override
	public MuiPromoDbPromoEntity findByCodicePromozione(@NonNull String codicePromozione) {
		final Long count = getEm().createNamedQuery("MuiPromoDbPromoEntity.countByCodicePromozione", Long.class)
				.setParameter("codicePromozione", codicePromozione)
				.getSingleResult();
		if (count != null && count == 1) {
			return getEm().createNamedQuery("MuiPromoDbPromoEntity.findByCodicePromozione", MuiPromoDbPromoEntity.class)
					.setParameter("codicePromozione", codicePromozione)
					.getSingleResult();
		}
		if (count == null || count == 0) {
			log.warn(String.format("No result found for 'codicePromozione' %s", codicePromozione));
		} else if (count > 1) {
			log.error(String.format("Found %d results for 'codicePromozione' %s", count, codicePromozione));
		}
		return null;
	}
}
