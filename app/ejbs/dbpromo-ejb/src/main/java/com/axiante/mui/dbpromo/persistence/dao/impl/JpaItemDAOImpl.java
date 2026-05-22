package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.ItemDAO;
import com.axiante.mui.dbpromo.persistence.entities.AssortimentoFornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@DbPromoJpaDao
public class JpaItemDAOImpl extends JpaDbPromoDAO<ItemEntity> implements ItemDAO {
	private static final long serialVersionUID = -8428074397327896606L;

	@Override
	public ItemEntity findByCode(String codiceItem) {
		try {
			return getEm().createNamedQuery("ItemEntity.findByCode", ItemEntity.class)
					.setParameter("codiceItem", codiceItem).getSingleResult();
		} catch (NoResultException ex) {
			log.info("Item code [" + codiceItem + "] not found", ex);
			return null;
		}
	}

	@Override
	public ItemEntity findActiveByCode(String code) {
		try {
			return getEm().createNamedQuery("ItemEntity.findActiveByCode", ItemEntity.class)
					.setParameter("codiceItem", code).getSingleResult();
		} catch (NoResultException ex) {
			log.info("Item code [" + code + "] not found", ex);
			return null;
		}
	}

	@Override
	public List<ItemEntity> findAllByCompratore(CompratoreEntity compratoreEntity) {
		return getEm().createNamedQuery("ItemEntity.findAllByCompratore", ItemEntity.class)
				.setParameter("compratoreEntity", compratoreEntity).getResultList();
	}

	@Override
	public List<ItemEntity> findAllByFilter(Long idCompratore, Long idFornitore, Long idReparto, Long idCategoria,
											Long idGrm, String codiceMarchioPrivato) {
		final CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<ItemEntity> q = cb.createQuery(ItemEntity.class);
		final Root<ItemEntity> it = q.from(ItemEntity.class);
		q = q.select(it).distinct(true);
		final Join<ItemEntity, AssortimentoFornitoreEntity> af = it.join("muiAssortimentoFornitores", JoinType.INNER);
		final List<Predicate> predicates = new ArrayList<>();
		if (idCompratore != null) {
			predicates.add(cb.equal(it.get("compratoreEntity").get("id"), idCompratore));
		}
		if (idFornitore != null) {
			predicates.add(cb.equal(af.get("fornitoreEntity").get("id"), idFornitore));
		}
		if (idReparto != null) {
			predicates.add(cb.equal(it.get("subGrmEntity").get("grmEntity").get("muiCategoria").get("repartoEntity").get("id"), idReparto));
		}
		if (idCategoria != null) {
			predicates.add(cb.equal(it.get("subGrmEntity").get("grmEntity").get("muiCategoria").get("id"), idCategoria));
		}
		if (idGrm != null) {
			predicates.add(cb.equal(it.get("subGrmEntity").get("grmEntity").get("id"), idGrm));
		}
		if (codiceMarchioPrivato != null) {
			predicates.add(cb.equal(it.get("codiceMarchioPrivato"), codiceMarchioPrivato));
		}
		if (!predicates.isEmpty()) {
			q = q.where(predicates.toArray(new Predicate[0]));
		}
		q.orderBy(
				cb.asc(it.get("subGrmEntity").get("grmEntity").get("muiCategoria").get("repartoEntity").get("codiceReparto")),
				cb.asc(it.get("subGrmEntity").get("grmEntity").get("muiCategoria").get("codiceCategoria")),
				cb.asc(it.get("subGrmEntity").get("grmEntity").get("codiceGrm")),
				cb.asc(it.get("subGrmEntity").get("codiceSubgrm")),
				cb.asc(it.get("codiceItem"))
		);
		return getEm().createQuery(q).getResultList();
	}

    @Override
    public String findCodiceById(@NonNull Long id) {
        return getEm().createNamedQuery("ItemEntity.findCodiceById", String.class)
				.setParameter("id", id)
				.getSingleResult();
    }
    
    @Override
    public List<ItemEntity> findByIds(@NonNull List<Long> ids){
    	if ( ids.isEmpty())
    		return Collections.emptyList();
    	return getEm().createNamedQuery("ItemEntity.findByIds", ItemEntity.class).setParameter("ids", ids).getResultList();
    }

    @Override
    public List<ItemEntity> findByIdsAndCompratoreAndFornitore(List<Long> ids,
															   @NonNull String codiceCompratore,
															   @NonNull String codiceFornitore) {
		if (ids == null || ids.isEmpty()) {
			throw new IllegalArgumentException("ids cannot be null or empty");
		}
        return getEm().createNamedQuery("ItemEntity.findByIdsAndCompratoreAndFornitore", ItemEntity.class)
				.setParameter("ids", ids)
				.setParameter("codiceCompratore", codiceCompratore)
				.setParameter("codiceFornitore", codiceFornitore)
				.getResultList();
    }

	@Override
	public List<ItemEntity> findByIdsAndCompratoriAndFornitore(List<Long> ids, List<String> codiciCompratori,
															   @NonNull String codiceFornitore) {
		if (ids == null || ids.isEmpty()) {
			throw new IllegalArgumentException("ids cannot be null or empty");
		}
		if (codiciCompratori == null || codiciCompratori.isEmpty()) {
			throw new IllegalArgumentException("codiciCompratori cannot be null or empty");
		}
		return getEm().createNamedQuery("ItemEntity.findByIdsAndCompratoriAndFornitore", ItemEntity.class)
				.setParameter("ids", ids)
				.setParameter("codiciCompratori", codiciCompratori)
				.setParameter("codiceFornitore", codiceFornitore)
				.getResultList();
	}

	@Override
	public List<String> findCodiceMarchioPrivatoByCompratori(List<String> codiciCompratori) {
		if (codiciCompratori == null || codiciCompratori.isEmpty()) {
			throw new IllegalArgumentException("codiciCompratori cannot be null or empty");
		}
		return getEm().createNamedQuery("ItemEntity.findCodiceMarchioPrivatoByCompratori", String.class)
				.setParameter("codiciCompratori", codiciCompratori)
				.getResultList();
	}
}
