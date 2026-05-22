package com.axiante.mui.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.axiante.mui.persistence.dao.ConnectionSetupDAO;
import com.axiante.mui.persistence.entity.ConnectionSetupEntity;

import lombok.AccessLevel;
import lombok.Getter;

@Stateless
public class JpaConnectionSetupDAO implements ConnectionSetupDAO {
	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public List<ConnectionSetupEntity> readAll() {
		return getEm().createQuery("select t from ConnectionSetupEntity t", ConnectionSetupEntity.class)
				.getResultList();
	}

	@Override
	public ConnectionSetupEntity persist(ConnectionSetupEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().flush();
		} catch (final EntityExistsException e) {
			throw new Exception("Connection Setup già presente.");
		}
		return entity;
	}

	@Override
	public void remove(ConnectionSetupEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().remove(entity);
		} catch (final PersistenceException e) {
			throw new Exception("Impossibile rimuovere oggetto in uso.");
		}
	}
}
