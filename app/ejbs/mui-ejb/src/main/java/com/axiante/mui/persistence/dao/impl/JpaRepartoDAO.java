package com.axiante.mui.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.axiante.mui.persistence.dao.RepartoDAO;
import com.axiante.mui.persistence.entity.RepartoEntity;

import lombok.AccessLevel;
import lombok.Getter;

@Stateless
public class JpaRepartoDAO implements RepartoDAO {

	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public List<RepartoEntity> findAll() {
		return getEm().createNamedQuery("RepartoEntity.findAll", RepartoEntity.class).getResultList();
	}

    @Override
    public RepartoEntity findById(Integer id) {
        return getEm().find(RepartoEntity.class, id);
    }
}
