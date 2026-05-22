package com.axiante.mui.persistence.dao;

import java.util.Collection;
import java.util.List;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.entity.GrmEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoGrmEntity;

public interface GruppoGrmDAO {
	List<GruppoGrmEntity> findAll();
	List<GruppoGrmEntity> findByGroup(GroupEntity group);
	GruppoGrmEntity findByGroupAndGrm(GroupEntity group, GrmEntity grm);
	GruppoGrmEntity save(GruppoGrmEntity gruppoGrm);
	Collection<GruppoGrmEntity> save(Collection<GruppoGrmEntity> entities);
    GruppoGrmEntity remove(GruppoGrmEntity entity);
	void removeAllByGruppo(GroupEntity group);
	Long countByGrmIdAndCodiciGruppo(Integer idGrm, List<String> codiciGruppo);
	Long countWritableByGrmIdAndCodiciGruppo(Integer idGrm, List<String> codiciGruppo);
	List<String> findAllByCodiciGruppoAndTipoAccesso(List<String> codiciGruppo, PianificazioneSecurityEnum tipoAccesso);
}
