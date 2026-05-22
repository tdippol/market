package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import lombok.NonNull;

public interface PromozioneNegozioService extends DbPromoService<PromozioneNegozioEntity> {
	boolean importaDaPromo(@NonNull Long idPromozioneCorrente, String codicePromozioneSorgente,
			String codiceCategoriaPlano,
			String tipologiaPlano,
			String dimensionePlano,
			@NonNull String username);
	boolean eliminaNegozi(@NonNull Long idPromozione, String codiceCategoriaPlano, String tipologiaPlano, String dimensionePlano);
}
