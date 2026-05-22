package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.MuiPlanoDbPromoEntity;
import java.util.List;

public interface MuiPlanoDbPromoDAO {

	List<MuiPlanoDbPromoEntity> findAll();
	MuiPlanoDbPromoEntity findByIdPlano(String idPlano);
}
