package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CfgAzioniEntity;

public interface CfgAzioniDAO extends DbPromoDAO<CfgAzioniEntity>{

	CfgAzioniEntity findByCodice(String codice);
}
