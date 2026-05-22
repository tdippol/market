package com.axiante.mui.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.axiante.mui.persistence.dao.UsersDAO;
import com.axiante.mui.persistence.entity.UsersEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Slf4j
public class JpaUsersDAO implements UsersDAO {
	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public List<UsersEntity> readAll() {
		return getEm().createNamedQuery("UsersEntity.findAll", UsersEntity.class).getResultList();
	}

	@Override
	public UsersEntity persist(UsersEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().flush();
		} catch (final EntityExistsException e) {
			throw new Exception("Utente già presente.");
		}
		return entity;
	}

	@Override
	public void remove(UsersEntity entity) throws Exception {
		try {
			entity = getEm().merge(entity);
			getEm().remove(entity);
			getEm().flush();
		} catch (final PersistenceException e) {
			throw new Exception("Impossibile rimuovere oggetto in uso.");
		}
	}

	@Override
	public UsersEntity findById(final Integer id) {
		return getEm().find(UsersEntity.class, id);
	}

	@Override
	public UsersEntity findByUsername(final String username) {
		try {
			Long founds = getEm().createNamedQuery("UsersEntity.countByUsername", Long.class).setParameter("username", username).getSingleResult();
			if ( founds == 1) {
				return getEm().createNamedQuery("UsersEntity.findByUsername", UsersEntity.class).setParameter("username", username).getSingleResult();
			} else {
				if ( founds == 0) {
					log.error(String.format("Lo username %s non e' presente nel database MUI", username));
				} else {
					log.error(String.format("Lo username %s comprare %n volte nel database MUI", username, founds));
				}
			}
		} catch (final Exception e) {
			log.error(String.format("Errore durante il recupero dell' utente %s", username),e);
		}
		return null;
	}
}
