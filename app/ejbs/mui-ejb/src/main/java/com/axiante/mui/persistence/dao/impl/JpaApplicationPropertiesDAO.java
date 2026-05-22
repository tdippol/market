package com.axiante.mui.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import com.axiante.mui.persistence.dao.ApplicationPropertiesDAO;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Slf4j
public class JpaApplicationPropertiesDAO implements ApplicationPropertiesDAO {
	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public ApplicationPropertiesEntity findByCode(@NonNull String code) throws IllegalStateException{
		ApplicationPropertiesEntity e = null;
		try {
			long count = getEm().createNamedQuery("ApplicationProperties.countByCode", Long.class).setParameter("key", code).getSingleResult();
			if (count == 1) {
				e = getEm().createNamedQuery("ApplicationProperties.findByCode", ApplicationPropertiesEntity.class)
						.setParameter("key", code).getSingleResult();
			} else {
				if (count == 0) {
					log.error("no property found with code " + code);
				} else {
					throw new IllegalStateException(String.format("Too many entries for property %s. Expected 1 found %d", code, count));
				}
			}
		} catch (final Exception ex) {
			log.error(String.format("Error getting entity by code %s", code), ex);
		}
		return e;
	}

	@Override
	public List<ApplicationPropertiesEntity> findAnyByCode(String code) throws Exception {
		return getEm().createNamedQuery("ApplicationProperties.findByCode", ApplicationPropertiesEntity.class)
				.setParameter("key", code).getResultList();
	}

	@Override
	public ApplicationPropertiesEntity persist(ApplicationPropertiesEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().flush();
		} catch (final EntityExistsException e) {
			throw new Exception("Application Property già presente.");
		}
		return entity;
	}

	@Override
	public List<ApplicationPropertiesEntity> readAll() {
		return getEm().createQuery("select t from ApplicationPropertiesEntity t", ApplicationPropertiesEntity.class)
				.getResultList();
	}

	@Override
	public boolean delete(String key) throws  Exception{
		ApplicationPropertiesEntity e = findByCode(key);
		if ( e != null ){
			getEm().remove(e);
			getEm().flush();
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void refreshProperties() {
		getEm().clear();
	}
}
