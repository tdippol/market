package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.persistence.dao.RolesDAO;
import com.axiante.mui.persistence.dao.SlowRolesDAO;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.SlowRolesEntity;
import lombok.AccessLevel;
import lombok.Getter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless
public class JpaSlowRolesDAO implements SlowRolesDAO {
	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;


	@Override
	public SlowRolesEntity persist(SlowRolesEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().flush();
		} catch (final EntityExistsException e) {
			throw new Exception("Ruolo già presente.");
		} catch (final Exception e) {
			if (e.getMessage().contains("Unique index or primary key violation")) {
				//        		let's remap
				throw new Exception("Ruolo già presente.");
			} else {
				throw e;
			}
		}
		return entity;
	}

	@Override
	public void remove(SlowRolesEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().remove(entity);
			getEm().flush();
		} catch (final PersistenceException e) {
			throw new Exception("Impossibile rimuovere oggetto in uso.");
		}
	}

	@Override
	public SlowRolesEntity findById(final Integer id) {
		return getEm().find(SlowRolesEntity.class, id);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void resetAllExceptions() {
		final String sql = "UPDATE RolesEntity SET overrideAdmin=false WHERE overrideAdmin=true";
		getEm().createQuery(sql).executeUpdate();
	}

}
