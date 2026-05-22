package com.axiante.mui.dbpromo.persistence.dao;


import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import java.util.List;

public interface MuiFontStileDAO extends DbPromoDAO<MuiFontStileEntity>{

	List<MuiFontStileEntity> findAll();
	MuiFontStileEntity findById(String id);
}
