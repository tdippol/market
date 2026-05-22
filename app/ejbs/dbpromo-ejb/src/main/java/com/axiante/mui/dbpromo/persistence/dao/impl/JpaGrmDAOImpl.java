package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.GrmDAO;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import java.util.List;
import javax.persistence.NoResultException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DbPromoJpaDao
public class JpaGrmDAOImpl extends JpaDbPromoDAO<GrmEntity> implements GrmDAO {

	private static final long serialVersionUID = 1898230339989813661L;

	@Override
	public GrmEntity findById(Long id) {
		return getEm().find(GrmEntity.class, id);
	}

	@Override
	public GrmEntity findByCode(String codiceGrm) {
		try {
			return getEm().createNamedQuery("GrmEntity.findByCode", GrmEntity.class)
					.setParameter("codiceGrm", codiceGrm)
					.getSingleResult();
		} catch (NoResultException ex) {
			log.info("Grm code [" + codiceGrm + "] not found", ex);
			return null;
		}
	}

	@Override
	public List<GrmEntity> findAllOrderedBy() {
		return getEm().createNamedQuery("GrmEntity.findAll", GrmEntity.class).getResultList();
	}

    @Override
    public List<GrmEntity> findAllByCodiciCompratore(@NonNull List<String> codiciCompratore) {
		if (codiciCompratore.isEmpty()) {
			throw new IllegalArgumentException("codiciCompratore cannot be null or empty");
		}
        return getEm().createNamedQuery("GrmEntity.findAllByCodiciCompratore", GrmEntity.class)
				.setParameter("codiciCompratore", codiciCompratore)
				.getResultList();
    }
}
