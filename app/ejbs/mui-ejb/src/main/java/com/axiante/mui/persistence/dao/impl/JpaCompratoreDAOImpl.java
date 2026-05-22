package com.axiante.mui.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.axiante.mui.persistence.dao.CompratoreDAO;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GroupEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

@Stateless
public class JpaCompratoreDAOImpl implements CompratoreDAO {

	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public List<CompratoreEntity> findAll() {
		return getEm().createNamedQuery("CompratoreEntity.findAll", CompratoreEntity.class).getResultList();
	}

	@Override
	public List<CompratoreEntity> findAllByGroup(@NonNull GroupEntity group) {
		return getEm().createNamedQuery("CompratoreEntity.findAllByGroup", CompratoreEntity.class).setParameter("gruppo", group).getResultList();
	}

    @Override
    public CompratoreEntity findById(Integer id) {
        return getEm().find(CompratoreEntity.class, id);
    }
}
