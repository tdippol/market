package com.axiante.mui.persistence.dao;

import com.axiante.mui.persistence.dto.GruppoUtenteDto;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.UsersEntity;

import java.util.List;

public interface GroupDAO {
	GroupEntity persist(GroupEntity group);

	List<GroupEntity> persist(List<GroupEntity> groups) ;

	List<GroupEntity> findAll();

	GroupEntity remove(GroupEntity group);

	GroupEntity findById(Integer id);

	List<GroupEntity> findAllByUser(UsersEntity user);

    List<GruppoUtenteDto> findAllWithUsers();

	Long countAccessTotaleByCodiciGruppoAndTipoNotNull(List<String> codiciGruppo);

	Long countWriteableAccessTotaleByCodiciGruppo(List<String> codiciGruppo);

	Long countAccessAttributoByCodiciGruppoAndTipoNotNull(List<String> codiciGruppo);

	Long countWriteableAccessAttributoByCodiciGruppo(List<String> codiciGruppo);
}
