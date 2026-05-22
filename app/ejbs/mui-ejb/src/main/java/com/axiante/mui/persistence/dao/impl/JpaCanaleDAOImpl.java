package com.axiante.mui.persistence.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.axiante.mui.persistence.dao.CanaleDAO;
import com.axiante.mui.persistence.entity.CanaleEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Slf4j
public class JpaCanaleDAOImpl implements CanaleDAO {
	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public CanaleEntity persist(CanaleEntity canale) {
		try {
			canale = getEm().merge(canale);
			getEm().flush();
		} catch (Exception e) {
			log.error("Error saving canale " + canale.getCodiceCanale(), e);
		}
		return canale;
	}

	@Override
	public List<CanaleEntity> findAll() {
		return getEm().createNamedQuery("CanaleEntity.findAll", CanaleEntity.class).getResultList();
	}

	@Override
	public CanaleEntity remove(CanaleEntity canale) {
		try {
			canale = getEm().merge(canale);
			getEm().remove(canale);
		} catch (Exception e) {
			log.error("Error removing canale " + canale.getCodiceCanale(), e);
			throw (e);
		}
		return canale;
	}

	@Override
	@Transactional
	public Collection<CanaleEntity> persist(@NonNull Collection<CanaleEntity> canali) {
		canali.stream().filter(Objects::nonNull).forEach(c -> getEm().merge(c));
		getEm().flush();
		return canali;
	}
}
