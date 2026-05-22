package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiPromoDbPromoEntity;
import java.util.List;

public interface MuiPromoDbPromoService {

	List<MuiPromoDbPromoEntity> findAll();
	List<MuiPromoDbPromoEntity> findByGiorniSelezione(Integer giorniSelezione);
	MuiPromoDbPromoEntity findByCodicePromozione(String codicePromozione);
}
