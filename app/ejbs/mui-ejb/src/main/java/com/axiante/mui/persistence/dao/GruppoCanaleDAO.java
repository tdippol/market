package com.axiante.mui.persistence.dao;

import java.util.Collection;
import java.util.List;

import com.axiante.mui.persistence.entity.CanaleEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoCanaleEntity;
import lombok.NonNull;

public interface GruppoCanaleDAO {
	GruppoCanaleEntity findByCodiceGruppoAndCodiceCanale(String gruppo,Long canale) ;
	GruppoCanaleEntity findByGruppoAndCanale(GroupEntity gruppo, CanaleEntity canale) ;
	List<GruppoCanaleEntity> findAllByGruppo(GroupEntity gruppo);
	List<CanaleEntity> findCreatableCanaliByGruppo(List<GroupEntity> gruppo);
	List<CanaleEntity> findOwnedCanaliByGruppi(List<GroupEntity> gruppi);
	List<Long> findOwnershipCodiciCanaleByGruppi(Collection<GroupEntity> gruppi);
	List<Long> findOwnershipCodiciCanaleByGruppiAndCompratori(Collection<String> gruppi, Collection<String> compratori);
	GruppoCanaleEntity save(GruppoCanaleEntity entity);
	boolean toggleFlagCreate(GruppoCanaleEntity entity);
	List<GruppoCanaleEntity> findAll();
}

