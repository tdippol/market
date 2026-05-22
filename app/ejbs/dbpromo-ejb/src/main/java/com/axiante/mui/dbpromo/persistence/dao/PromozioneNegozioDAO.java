package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import java.util.List;

public interface PromozioneNegozioDAO extends DbPromoDAO<PromozioneNegozioEntity> {
	List<PromozioneNegozioEntity> findById(List<Long> ids);
	boolean impostaNegozi(Long idPromozioneCorrente, String codicePromozioneSorgente,
			String codiceCategoriaPlano,
			String tipologiaPlano,
			String dimensionePlano,
			String username) throws Exception;
	boolean eliminaNegozi(Long idPromozione, String codiceCategoriaPlano, String tipologiaPlano,String dimensionePlano)
			throws Exception;
}
