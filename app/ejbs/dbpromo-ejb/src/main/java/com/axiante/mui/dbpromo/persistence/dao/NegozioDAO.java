package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.dto.ZonaDto;
import com.axiante.mui.dbpromo.persistence.entities.NegozioEntity;
import java.util.List;

public interface NegozioDAO extends DbPromoDAO<NegozioEntity> {
	List<String> findAllTipoConsegna();
	List<ZonaDto> findAllDistinctZone();
	List<String> findAllDistinctCedi();
}
