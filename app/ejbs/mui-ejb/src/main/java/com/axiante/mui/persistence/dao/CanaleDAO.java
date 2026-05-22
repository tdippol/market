package com.axiante.mui.persistence.dao;

import java.util.Collection;
import java.util.List;

import com.axiante.mui.persistence.entity.CanaleEntity;

public interface CanaleDAO {
	CanaleEntity persist(CanaleEntity canale);

	List<CanaleEntity> findAll();

	CanaleEntity remove(CanaleEntity canale);

	Collection<CanaleEntity> persist(Collection<CanaleEntity> canali);
}
