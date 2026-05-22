package com.axiante.mui.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.axiante.mui.persistence.dao.GrmDAO;
import com.axiante.mui.persistence.entity.GrmEntity;
import com.axiante.mui.persistence.entity.GroupEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

@Stateless
public class JpaGrmDAOImpl implements GrmDAO {

	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public List<GrmEntity> findAll() {
		return getEm().createNamedQuery("GrmEntity.findAll", GrmEntity.class).getResultList();
	}

	@Override
	public List<GrmEntity> findAllByGroup( @NonNull GroupEntity group) {
		return getEm().createNamedQuery("GrmEntity.findAllByGroup", GrmEntity.class).setParameter("gruppo", group).getResultList();
	}

    @Override
    public GrmEntity findById(Integer id) {
        return getEm().find(GrmEntity.class, id);
    }
}
