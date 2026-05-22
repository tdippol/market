package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.persistence.dao.AclDAO;
import com.axiante.mui.persistence.dao.PianoMediaSecurityDAO;
import com.axiante.mui.persistence.entity.AclEntity;
import com.axiante.mui.persistence.entity.PianoMediaSecurityEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import lombok.AccessLevel;
import lombok.Getter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class JpaPianoMediaSecurityDAO implements PianoMediaSecurityDAO {
	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public List<PianoMediaSecurityEntity> readAll() {
		return getEm().createNamedQuery("PianoMediaSecurityEntity.findAll", PianoMediaSecurityEntity.class).getResultList();
	}

	@Override
	public PianoMediaSecurityEntity persist(PianoMediaSecurityEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().flush();
		} catch (EntityExistsException e) {
			throw new Exception("Regola ACL già presente per i campi: Componente, Interfaccia, Ruolo.");
		}
		return entity;
	}

	@Override
	public void remove(PianoMediaSecurityEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().remove(entity);
		} catch (PersistenceException e) {
			throw new Exception("Impossibile rimuovere oggetto in uso.");
		}
	}

	@Override
	public PianoMediaSecurityEntity findById(Integer id) {
		return getEm().find(PianoMediaSecurityEntity.class, id);
	}

	@Override
	public List<PianoMediaSecurityEntity> findByUser(UsersEntity user) throws Exception{
		return getEm().createNamedQuery("PianoMediaSecurityEntity.findByUser", PianoMediaSecurityEntity.class).setParameter("user", user).getResultList();
	}
}
