package com.axiante.mui.persistence.dao;

import java.util.Collection;
import java.util.List;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoCompratoreEntity;

public interface GruppoCompratoreDAO {

	List<GruppoCompratoreEntity> findAll();
	List<GruppoCompratoreEntity> findAllByGroup(GroupEntity group);
	GruppoCompratoreEntity findByGroupAndCompratore(GroupEntity group, CompratoreEntity compratore);
	GruppoCompratoreEntity save(GruppoCompratoreEntity gruppoCompratore);
	Collection<GruppoCompratoreEntity> save(Collection<GruppoCompratoreEntity> entities);
	GruppoCompratoreEntity remove(GruppoCompratoreEntity entity);
	void removeAllByGruppo(GroupEntity group);
	List<String> findAllCodiciCompratoreByGroupAndTipoAccesso(List<GroupEntity> groups, PianificazioneSecurityEnum security);
	List<String> findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(List<String> groups, PianificazioneSecurityEnum security);
    List<String> findAllCodiciCompratoreByCodiciGruppo(List<String> codiciGruppo);
}
