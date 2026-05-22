package com.axiante.mui.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.axiante.mui.persistence.dao.ConfigurationsDAO;
import com.axiante.mui.persistence.entity.ConfigurationEntity;
import com.axiante.mui.persistence.entity.ConfigurationTypes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Slf4j
public class JpaConfigurationsDAO implements ConfigurationsDAO {
	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public List<ConfigurationEntity> readAll() {
		return getEm().createNamedQuery("ConfigurationEntity.findAllOrderedByPath", ConfigurationEntity.class).getResultList();
	}

	@Override
	public ConfigurationEntity persist(ConfigurationEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().flush();
		} catch (final EntityExistsException e) {
			throw new Exception("Configurazione già presente.");
		}
		return entity;
	}

	@Override
	public void remove(ConfigurationEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().remove(entity);
		} catch (final PersistenceException e) {
			throw new Exception("Impossibile rimuovere oggetto in uso.");
		}
	}

	@Override
	public ConfigurationEntity findById(final Integer id) {
		return getEm().find(ConfigurationEntity.class, id);
	}

	@Override
	public ConfigurationEntity findByPath(final String path, final ConfigurationTypes type) {
		final TypedQuery<ConfigurationEntity> query = getEm()
				.createNamedQuery("ConfigurationEntity.findAllByPathAndType", ConfigurationEntity.class);
		query.setParameter("path", path);
		query.setParameter("type", type);
		try {
			return query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			log.error(String.format("Error getting configuration by path [%s] and type [%s] : %s",
					path == null ? "null" : path, type == null ? "null" : type.toString(), e.getMessage()), e);

			return null;
		}
	}

	@Override
	public List<ConfigurationEntity> findByType(final ConfigurationTypes type) {
		return getEm().createNamedQuery("ConfigurationEntity.findAllByTypeOrderByPath", ConfigurationEntity.class).setParameter("type", type).getResultList();
	}

	@Override
	public List<ConfigurationEntity> findByIdMenu(@NonNull final Integer idMenu) {
		return getEm().createNamedQuery("ConfigurationEntity.findAllByIdMenu",ConfigurationEntity.class).setParameter("idMenu", idMenu).getResultList();
	}
	@Override
	public ConfigurationEntity findByIdMenuAndType(@NonNull final Integer idMenu, ConfigurationTypes type) {
		return getEm().createNamedQuery("ConfigurationEntity.findValidByTypeAndIdMenu", ConfigurationEntity.class).setParameter("idMenu", idMenu).setParameter("type", type).getSingleResult();
	}
}
