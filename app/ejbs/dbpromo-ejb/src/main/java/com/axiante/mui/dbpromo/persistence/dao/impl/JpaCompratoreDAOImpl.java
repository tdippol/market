package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CompratoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaCompratoreDAOImpl extends JpaDbPromoDAO<CompratoreEntity> implements CompratoreDAO {
	private static final long serialVersionUID = 8380222835965744186L;

	@Override
	public List<CompratoreEntity> findAllOrderedBy() {
		return getEm().createNamedQuery("CompratoreEntity.findAll", CompratoreEntity.class).getResultList();
	}

	@Override
	public List<CompratoreEntity> findAllByCodes(@NonNull List<String> codes) {
		if (!codes.isEmpty()) {
			return getEm().createNamedQuery("CompratoreEntity.findAllByCodes", CompratoreEntity.class)
					.setParameter("codes", codes).getResultList();
		} else {
			throw new IllegalArgumentException("codes cannot be null or empty");
		}
	}

	@Override
	public List<CompratoreEntity> findAllByIdItems(List<Long> idItems) {
		if (idItems == null || idItems.isEmpty()) {
			throw new IllegalArgumentException("idItems cannot be null or empty");
		}
		return getEm().createNamedQuery("CompratoreEntity.findAllByIdItems", CompratoreEntity.class)
				.setParameter("idItems", idItems)
				.getResultList();
	}
}
