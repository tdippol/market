package com.axiante.mui.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.axiante.mui.persistence.dao.AclDAO;
import com.axiante.mui.persistence.entity.AclEntity;

import lombok.AccessLevel;
import lombok.Getter;

@Stateless
public class JpaAclDAO implements AclDAO {
	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public List<AclEntity> readAll() {
		return getEm().createQuery("select t from AclEntity t", AclEntity.class).getResultList();
	}

	@Override
	public AclEntity persist(AclEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().flush();
		} catch (EntityExistsException e) {
			throw new Exception("Regola ACL già presente per i campi: Componente, Interfaccia, Ruolo.");
		}
		return entity;
	}

	@Override
	public void remove(AclEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().remove(entity);
		} catch (PersistenceException e) {
			throw new Exception("Impossibile rimuovere oggetto in uso.");
		}
	}

	@Override
	public AclEntity findById(Integer id) {
		return getEm().find(AclEntity.class, id);
	}

	@Override
	public List<String> readComponentGroups() throws Exception {
		List<String> componentGroups = new ArrayList<>();
		componentGroups.add("BUTTON");
		return componentGroups;
	}
}
