package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleReparto;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import java.util.List;

public interface CfgCanaleRepartoDAO extends DbPromoDAO<CfgCanaleReparto>{

	List<CfgCanaleReparto> findByCanale(CanalePromozioneEntity canale);
	CfgCanaleReparto findByCanaleAndReparto(CanalePromozioneEntity canale, RepartoEntity reparto);
}
