package com.axiante.mui.persistence.dao;

import java.util.Collection;
import java.util.List;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoRepartoEntity;
import com.axiante.mui.persistence.entity.RepartoEntity;

public interface GruppoRepartoDAO {
	GruppoRepartoEntity findByGruppoAndReparto(GroupEntity gruppo, RepartoEntity reparto);
	GruppoRepartoEntity save(GruppoRepartoEntity entity);
	Collection<GruppoRepartoEntity> save(Collection<GruppoRepartoEntity> entities);
	List<GruppoRepartoEntity> findAllByGruppo(GroupEntity gruppo);
	GruppoRepartoEntity remove(GruppoRepartoEntity entity);
    void removeAllByGruppo(GroupEntity group);
	Long countByRepartoIdAndCodiciGruppo(Integer idReparto, List<String> codiciGruppo);
	Long countWriteableByRepartoIdAndCodiciGruppo(Integer idReparto, List<String> codiciGruppo);
    List<String> findAllByCodiciGruppoAndTipoAccesso(List<String> codiciGruppo, PianificazioneSecurityEnum tipoAccesso);
}
