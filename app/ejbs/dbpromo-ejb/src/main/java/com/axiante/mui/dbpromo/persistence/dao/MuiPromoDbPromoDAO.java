package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiPromoDbPromoEntity;
import java.sql.Date;
import java.util.List;

public interface MuiPromoDbPromoDAO {

	List<MuiPromoDbPromoEntity> findAll();
	List<MuiPromoDbPromoEntity> findByDataFineGreaterThan(Date dataFine);
	MuiPromoDbPromoEntity findByCodicePromozione(String codicePromozione);
}
