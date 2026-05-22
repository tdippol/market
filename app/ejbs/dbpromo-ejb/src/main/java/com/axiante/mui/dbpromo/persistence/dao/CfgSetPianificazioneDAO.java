package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import java.util.Date;
import java.util.List;

public interface CfgSetPianificazioneDAO extends DbPromoDAO<CfgSetPianificazioneEntity> {
	CfgSetPianificazioneEntity findByLockedAndDataInizio(Date dataInizio) throws Exception;
	CfgSetPianificazioneEntity findOpenSet() throws RuntimeException;
	List<CfgSetPianificazioneEntity> findAllEnabled();
}
